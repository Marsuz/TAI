package controllers;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.CategoryService;

import java.util.List;

/**
 * Created by mzajda on 10/09/2016.
 */

@RestController
@RequestMapping(value = "/cat")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
