package model;

import wrappers.IngredientQuantityWrapper;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Recipe {

    @Id
    @Column(name="recipe_id")
    private String name;
    private String description;

    @OneToMany(mappedBy="recipe")
    List<IngredientQuantityWrapper> ingredientsWithQuantity;

    private AtomicInteger likesCounter = new AtomicInteger(0);
    private AtomicInteger dislikesCounter = new AtomicInteger(0);

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

    public void addIngredient(Ingredient ingredient, int quantity) {
        ingredientsWithQuantity.add(new IngredientQuantityWrapper(this, ingredient, quantity));
    }

    public int getLikesCounter() {
        return likesCounter.get();
    }

    public void setLikesCounter(int likesCounter) {
        this.likesCounter.set(likesCounter);
    }

    public int getDislikesCounter() {
        return dislikesCounter.get();
    }

    public void setDislikesCounter(int dislikesCounter) {
        this.dislikesCounter.set(dislikesCounter);
    }

    public void incLikesCounter() {
        likesCounter.incrementAndGet();
    }

    public void incDislikesCounter() {
        dislikesCounter.incrementAndGet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (!name.equals(recipe.name)) return false;
        return description.equals(recipe.description);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
