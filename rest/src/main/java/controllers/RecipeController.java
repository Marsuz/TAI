package controllers;

import model.Recipe;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.RecipeService;

import java.util.List;

/**
 * Created by Marcin on 2016-06-28.
 */

@RestController
@RequestMapping(value="/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public void createBlankRecipe(@RequestBody String jsonBody) throws Exception{
        JSONObject jsonObject = new JSONObject(jsonBody);
        recipeService.createNewRecipe(jsonObject.getString("name"), jsonObject.getString("desc"));
    }

    @RequestMapping(value = "/addWhole", method = RequestMethod.POST)
    public void addRecipe(@RequestBody Recipe recipe) throws Exception {
        recipeService.createRecipe(recipe);
    }

    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public List<Recipe> getTopRecipes() { return recipeService.getTop20Recipes(); }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @RequestMapping(value = "/{recId}", method = RequestMethod.GET)
    public Recipe getRecipeById(@PathVariable Long recId) {
        return recipeService.getRecipeById(recId);
    }

    @RequestMapping(value = "/add/{recId}/{ingId}", method = RequestMethod.PUT)
    public void addIngredientToRecipe(@PathVariable("recId") Long recId, @PathVariable("ingId") Long ingId, @RequestParam("quantity") Long quantity) {
        recipeService.addIngredientToRecipe(recId, ingId, quantity);
    }

    @RequestMapping(value = "/like/{recId}", method = RequestMethod.PUT)
    public Long likeRecipe(@PathVariable Long recId) {
        return recipeService.likeRecipe(recId);
    }

    @RequestMapping(value = "/dislike/{recId}", method = RequestMethod.PUT)
    public Long dislikeRecipe(@PathVariable Long recId) {
        return recipeService.dislikeRecipe(recId);
    }

}
