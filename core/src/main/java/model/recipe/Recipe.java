package model.recipe;

import model.Ingredient;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Recipe {

    private long id;

    private RecipeDescription description;

    @OneToMany
    private List<Ingredient> ingredients;

    private AtomicInteger likesCounter = new AtomicInteger(0);
    private AtomicInteger dislikesCounter = new AtomicInteger(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RecipeDescription getDescription() {
        return description;
    }

    public void setDescription(RecipeDescription description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
}
