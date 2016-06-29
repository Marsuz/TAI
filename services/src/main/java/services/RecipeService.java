package services;

import model.Ingredient;
import model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2016-06-28.
 */
@Service
public class RecipeService {

    @Autowired
    RecipeRepository repository;

    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    public void createRecipe(String name, String desc, Map<Ingredient, Integer> ingredients) {
        Recipe recipe = new Recipe(name, desc, new ArrayList<>());
        for(Ingredient ingredient : ingredients.keySet()) {
            recipe.addIngredient(ingredient, ingredients.get(ingredient));
        }
        repository.save(recipe);
    }
}
