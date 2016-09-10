package model;

import wrappers.IngredientQuantityWrapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Recipe extends ObjectWithId{

    @Column(name="name")
    private String name;

    @Column(name="desc")
    private String desc;

    @OneToMany
    private List<IngredientQuantityWrapper> ingredientsWithQuantity;

    public Recipe() {
    }

    public Recipe(String name, String desc, List<IngredientQuantityWrapper> ingredientsWithQuantity) {
        this.name = name;
        this.desc = desc;
        this.ingredientsWithQuantity = ingredientsWithQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
