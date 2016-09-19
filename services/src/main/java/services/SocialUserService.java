package services;

import model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.List;

/**
 * Created by mzajda on 18/09/2016.
 */

public interface SocialUserService extends SocialUserDetailsService, UserDetailsService {
    void saveAndFlush(User user);

    List<User> findAll();
    User loadUserByConnectionKey(ConnectionKey connectionKey);
    User loadUserByUserId(String userId);
    User loadUserByUsername(String username);
    void updateUserDetails(User user);

    User getCurrentUser();
}
