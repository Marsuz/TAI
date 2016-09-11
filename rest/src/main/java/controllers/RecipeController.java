package controllers;

import model.Recipe;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void createBlankRecipe(@RequestParam("name") String name, @RequestParam("desc") String desc) {
        recipeService.createNewRecipe(name, desc);
    }

    @RequestMapping(value = "/addWhole", method = RequestMethod.POST)
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.saveRecipe(recipe);
    }

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
