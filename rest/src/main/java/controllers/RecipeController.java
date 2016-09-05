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

    @Autowired
    CategoryService categoryService;

    @Autowired
    IngredientService ingredientService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @RequestMapping(value="/create/{name}", method =RequestMethod.POST)
    public ResponseEntity<String> createRecipe(@PathVariable("name") String name, @RequestParam("descripiton") String description, @ModelAttribute("ingredients") IngredientsWrapper ingredients) {

        recipeService.createRecipe(name, description, ingredients.getIngredientsWithQuantity());
        return new ResponseEntity<>("Recipe successfully created", HttpStatus.OK);

    }

    @RequestMapping(value="/testcreate", method=RequestMethod.GET)
    public ResponseEntity<String> createDumbData() {
        Category category = new Category("ryby");

        categoryService.addCategory(category);

        Ingredient ingredient1 = new Ingredient("losos", category, "kg");
        Ingredient ingredient2 = new Ingredient("sledz", category, "kg");

        ingredientService.addIngredient(ingredient1);
        ingredientService.addIngredient(ingredient2);


        Map<Ingredient, Integer> quantities = new HashMap<>();
        quantities.put(ingredient1, 1);
        quantities.put(ingredient2, 2);
        recipeService.createRecipe("testrybny", "testrybnydesc", quantities);
        return new ResponseEntity<>("Test rybny done", HttpStatus.OK);
    }

}
