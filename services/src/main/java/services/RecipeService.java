package services;

import model.Ingredient;
import model.Recipe;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.RecipeRepository;
import repositories.UserRepository;
import wrappers.IngredientQuantityWrapper;

import java.util.ArrayList;
import java.util.List;


@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    @Qualifier("userService")
    SocialUserService userService;

    @Autowired
    UserRepository userRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> getTop20Recipes() {
        return recipeRepository.findTopRecipes();
    }

    @Transactional
    public void createNewRecipe(String name, String desc) throws Exception {
        User currentUser = getCurrentUser();
        currentUser = userRepository.findById(currentUser.getId());
        Recipe recipe = new Recipe(name, desc, currentUser, new ArrayList<>());
        recipeRepository.save(recipe);
    }

    @Transactional
    public void createRecipe(Recipe recipe) throws Exception {
        User currentUser = getCurrentUser();
        currentUser = userRepository.findById(currentUser.getId());
        recipeRepository.save(recipe);
    }

    @Transactional
    public void updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findOne(id);
        Assert.notNull(recipe, "There is no such recipe");
        return recipe;
    }

    public void addIngredientToRecipe(Long recipeId, Long ingId, Long quantity) {
        Recipe recipe = getRecipeById(recipeId);
        Ingredient ingredient = ingredientService.getIngredientById(ingId);
        recipe.addIngredient(new IngredientQuantityWrapper(ingredient, recipe, quantity));
        recipeRepository.save(recipe);
    }

    public Long likeRecipe(Long recipeId) {
        Recipe recipe = getRecipeById(recipeId);
        Long result = recipe.likeRecipe();
        updateRecipe(recipe);
        return result;
    }

    public Long dislikeRecipe(Long recipeId) {
        Recipe recipe = getRecipeById(recipeId);
        Long result = recipe.dislikeRecipe();
        updateRecipe(recipe);
        return result;
    }

    private User getCurrentUser() throws Exception {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new Exception("You are not permitted to view this site");
        }
        return userRepository.findById(currentUser.getId());
    }
}
