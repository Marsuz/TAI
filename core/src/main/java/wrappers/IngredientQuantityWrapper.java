package wrappers;

import model.Ingredient;
import model.ObjectWithId;
import model.Recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class IngredientQuantityWrapper extends ObjectWithId{

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    @JsonIgnore
    private Recipe recipe;

    private Long quantity;

    public IngredientQuantityWrapper() {
    }

    public IngredientQuantityWrapper(Ingredient ingredient, Recipe recipe, Long quantity) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


}
