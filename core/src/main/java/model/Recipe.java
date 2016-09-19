package model;

import wrappers.IngredientQuantityWrapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Recipe extends ObjectWithId{

    private String name;
    private String description;
    private AtomicLong likeCounter = new AtomicLong(0);
    private AtomicLong dislikeCounter = new AtomicLong(0);

    @NotNull
    @ManyToOne
    private User owner;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="recipe_id")
    private List<IngredientQuantityWrapper> ingredientsWithQuantity;

    public Recipe() {
    }

    public Recipe(String name, String description, User owner, List<IngredientQuantityWrapper> ingredientsWithQuantity) {
        this.name = name;
        this.description = description;
        this.owner = owner;
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

    public AtomicLong getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(AtomicLong likeCounter) {
        this.likeCounter = likeCounter;
    }

    public AtomicLong getDislikeCounter() {
        return dislikeCounter;
    }

    public void setDislikeCounter(AtomicLong dislikeCounter) {
        this.dislikeCounter = dislikeCounter;
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

    public Long likeRecipe() {
        return likeCounter.incrementAndGet();
    }

    public Long dislikeRecipe() {
        return dislikeCounter.incrementAndGet();
    }
}
