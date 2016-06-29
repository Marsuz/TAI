package services;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CategoryRepository;

import java.util.List;

/**
 * Created by Marcin on 2016-06-29.
 */

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public void addCategory(Category category) {
        repository.save(category);
    }

}
