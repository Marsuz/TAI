package repositories;

import model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Marcin on 2016-06-29.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
