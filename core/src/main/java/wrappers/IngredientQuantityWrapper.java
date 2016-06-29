package wrappers;

import model.Ingredient;
import model.Recipe;

import javax.persistence.*;

@Entity
public class IngredientQuantityWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    private int quantity;

    public IngredientQuantityWrapper() {
    }

    public IngredientQuantityWrapper(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
