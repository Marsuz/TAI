package wrappers;

import model.Ingredient;

import java.util.Map;

public class IngredientsWrapper {

    private Map<Ingredient, Integer> ingredientsWithQuantity;

    public Map<Ingredient, Integer> getIngredientsWithQuantity() {
        return ingredientsWithQuantity;
    }

    public void setIngredientsWithQuantity(Map<Ingredient, Integer> ingredientsWithQuantity) {
        this.ingredientsWithQuantity = ingredientsWithQuantity;
    }
}
