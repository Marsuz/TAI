package controllers;

import model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.RecipeService;
import wrappers.IngredientsWrapper;

import java.util.List;

/**
 * Created by Marcin on 2016-06-28.
 */

@RestController
@RequestMapping(value="/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @RequestMapping(value="/create", method =RequestMethod.POST)
    public ResponseEntity<String> createRecipe(@RequestParam("name") String name, @RequestParam("descripiton") String description, @ModelAttribute("ingredients") IngredientsWrapper ingredients) {

        recipeService.createRecipe(name, description, ingredients.getIngredientsWithQuantity());
        return new ResponseEntity<>("Recipe successfully created", HttpStatus.OK);

    }

}
