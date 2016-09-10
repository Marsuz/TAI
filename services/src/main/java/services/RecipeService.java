package services;

import model.Ingredient;
import model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.IngredientRepository;
import repositories.RecipeRepository;
import wrappers.IngredientQuantityWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2016-06-28.
 */
@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientService ingredientService;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Transactional
    public void createNewRecipe(String name, String desc) {
        Recipe recipe = new Recipe(name, desc, new ArrayList<>());
        recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findOne(id);
        Assert.notNull(recipe, "There is no such recipe");
        return recipe;
    }

    public void addIngredientToRecipe(Long recipeId, Long ingId, Long quantity) {
        Recipe recipe = getRecipeById(recipeId);
        Ingredient ingredient = ingredientService.getIngredientById(ingId);
        recipe.addIngredient(new IngredientQuantityWrapper(ingredient, recipe, quantity));
        recipeRepository.save(recipe);
    }
}
