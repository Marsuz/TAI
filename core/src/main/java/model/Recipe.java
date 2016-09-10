package model;

import wrappers.IngredientQuantityWrapper;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe extends ObjectWithId{

    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="recipe_id")
    private List<IngredientQuantityWrapper> ingredientsWithQuantity;

    public Recipe() {
    }

    public Recipe(String name, String description, List<IngredientQuantityWrapper> ingredientsWithQuantity) {
        this.name = name;
        this.description = description;
        this.ingredientsWithQuantity = ingredientsWithQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IngredientQuantityWrapper> getIngredientsWithQuantity() {
        return ingredientsWithQuantity;
    }

    public void setIngredientsWithQuantity(List<IngredientQuantityWrapper> ingredientsWithQuantity) {
        this.ingredientsWithQuantity = ingredientsWithQuantity;
    }

    public void addIngredient(IngredientQuantityWrapper ingredientWithQuantity) {
        ingredientsWithQuantity.add(ingredientWithQuantity);
    }
}
