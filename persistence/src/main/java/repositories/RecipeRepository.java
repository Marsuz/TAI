package repositories;

import model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marcin on 2016-06-28.
 */

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
}
