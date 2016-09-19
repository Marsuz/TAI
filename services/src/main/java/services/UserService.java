package services;

import model.User;
import model.auth.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserRepository;

import java.util.List;

/**
 * Created by mzajda on 18/09/2016.
 */
@Service("userService")
@Transactional
public class UserService implements SocialUserService {

    @Autowired
    private UserRepository userRepository;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUserId(String userId) {
        final User user = userRepository.findById(Long.valueOf(userId));
        return checkUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) {
        final User user = userRepository.findByUsername(username);
        return checkUser(user);
    }

    @Override
    public void saveAndFlush(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByConnectionKey(ConnectionKey connectionKey) {
        final User user = userRepository
                .findByProviderIdAndProviderUserId(connectionKey.getProviderId(), connectionKey.getProviderUserId());
        return checkUser(user);
    }

    @Override
    public void updateUserDetails(User user) {
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            return ((UserAuthentication) authentication).getDetails();
        }
        return new User(); //anonymous user support
    }

    private User checkUser(User user) {
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        detailsChecker.check(user);
        return user;
    }
}