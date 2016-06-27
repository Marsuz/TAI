package model.recipe;

import model.Ingredient;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RecipeBuilder {

    private RecipeDescription description;
    private List<Ingredient> ingredients;
    private AtomicInteger likesCounter;
    private AtomicInteger dislikesCounter;

}
