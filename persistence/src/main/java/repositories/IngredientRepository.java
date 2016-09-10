package repositories;

import model.Category;
import model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Marcin on 2016-06-29.
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("Select ing from Ingredient ing where ing.category.id = :catid")
    List<Ingredient> findByCategoryId(@Param("catid")long categoryId);
}
