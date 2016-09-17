package controllers;

import model.Category;
import model.Ingredient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.IngredientService;

import java.util.List;

/**
 * Created by mzajda on 10/09/2016.
 */

@RestController
@RequestMapping(value = "/ing")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public void addIngredient(@RequestBody String jsonBody) {
        JSONObject jsonObject = new JSONObject(jsonBody);
        ingredientService.createNewIngredient(jsonObject.getString("name"), jsonObject.getString("unit"), jsonObject.getLong("catId"));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @RequestMapping(value ="/cat/{catId}", method = RequestMethod.GET)
    public List<Ingredient> getAllIngredientsWithGivenCategory(@PathVariable Long catId) {
        return ingredientService.getAllIngredientsByCategory(catId);
    }

}
