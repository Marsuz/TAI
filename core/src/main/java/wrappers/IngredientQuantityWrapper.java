package wrappers;

import model.Ingredient;
import model.ObjectWithId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class IngredientQuantityWrapper extends ObjectWithId{

    @ManyToOne
    private Ingredient ingredient;

    private Long quantity;

    public IngredientQuantityWrapper() {
    }

    public IngredientQuantityWrapper(Ingredient ingredient, Long quantity) {
        this.ingredient = ingredient;
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
