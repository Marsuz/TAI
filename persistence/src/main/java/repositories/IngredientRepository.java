package repositories;

import model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Marcin on 2016-06-29.
 */
public interface IngredientRepository extends JpaRepository<Ingredient, String>{
}
