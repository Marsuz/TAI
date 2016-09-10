package services;

import model.Category;
import model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CategoryRepository;
import repositories.IngredientRepository;

import java.util.List;

/**
 * Created by Marcin on 2016-06-29.
 */

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    CategoryService categoryService;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Transactional
    public void createNewIngredient(String name, String unit, long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        Assert.notNull(category, "There is no such category");
        Ingredient ingredient = new Ingredient(name, unit, category);
        ingredientRepository.save(ingredient);
    }

    public Ingredient getIngredientById(Long id) {
        Ingredient ingredient = ingredientRepository.findOne(id);
        Assert.notNull(ingredient);
        return ingredient;
    }
}
