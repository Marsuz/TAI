package repositories;

import model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcin on 2016-06-28.
 */

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("select rec from Recipe rec order by rec.likeCounter DESC LIMIT 0,20")
    List<Recipe> findTopRecipes();
}
