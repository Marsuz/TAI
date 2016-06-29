package services;

import model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.IngredientRepository;

import java.util.List;

/**
 * Created by Marcin on 2016-06-29.
 */

@Service
public class IngredientService {

    @Autowired
    IngredientRepository repository;

    public List<Ingredient> getAll() {
        return repository.findAll();
    }

    public void addIngredient(Ingredient ingredient) {
        repository.save(ingredient);
    }
}
