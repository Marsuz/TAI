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
    public void addRecipe(@RequestParam("name") String name, @RequestParam("desc") String desc) {
        recipeService.createNewRecipe(name, desc);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @RequestMapping(value = "/add/{recId}/{ingId}", method = RequestMethod.PUT)
    public void addIngredientToRecipe(@PathVariable("recId") Long recId, @PathVariable("ingId") Long ingId, @RequestParam("quantity") Long quantity) {
        recipeService.addIngredientToRecipe(recId, ingId, quantity);
    }

}
