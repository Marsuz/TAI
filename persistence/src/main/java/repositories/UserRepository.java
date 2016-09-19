package repositories;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mzajda on 18/09/2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByUsername(String username);

    User findById(Long id);

    User findByProviderIdAndProviderUserId(String providerId, String providerUserId);
}