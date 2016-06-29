package model;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="recipeId")
    private long id;
    private String name;
    private String description;

    @OneToMany
    private List<Ingredient> ingredients;

    private AtomicInteger likesCounter = new AtomicInteger(0);
    private AtomicInteger dislikesCounter = new AtomicInteger(0);

    public Recipe() {
    }

    public Recipe(String name, String description, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
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

        if (id != recipe.id) return false;
        if (!name.equals(recipe.name)) return false;
        if (!description.equals(recipe.description)) return false;
        return ingredients != null ? ingredients.equals(recipe.ingredients) : recipe.ingredients == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        return result;
    }
}
