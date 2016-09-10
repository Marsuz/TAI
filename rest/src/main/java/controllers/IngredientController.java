package controllers;

import model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addIngredient(@RequestParam("name") String name, @RequestParam("unit") String unit, @RequestParam("catId") Long catId) {
        ingredientService.createNewIngredient(name, unit, catId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }
}
