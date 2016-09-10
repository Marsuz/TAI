package controllers;

import model.Category;
import model.Ingredient;
import model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CategoryService;
import services.IngredientService;
import services.RecipeService;
import wrappers.IngredientsWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
