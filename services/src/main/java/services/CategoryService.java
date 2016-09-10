package services;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CategoryRepository;

import java.util.List;

/**
 * Created by Marcin on 2016-06-29.
 */

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        Category category = categoryRepository.findOne(id);
        Assert.notNull(category, "There is no such category");
        return category;
    }

}
