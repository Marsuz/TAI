package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wrappers.IngredientQuantityWrapper;

/**
 * Created by Marcin on 2016-06-29.
 */
public interface IngredientsQuantityWrapperRepository extends JpaRepository<IngredientQuantityWrapper, Long> {
}
