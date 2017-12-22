package com.beautysalon.BeautySalon.Controllers;
import com.beautysalon.BeautySalon.Entities.Category;
import com.beautysalon.BeautySalon.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    public @ResponseBody
    @RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
    Category newCategory(@RequestParam(value = "category") String category) {
        Category newCategory = new Category(category);
        categoryRepository.save(newCategory);
        return newCategory;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Category getCategory(@PathVariable(value = "id") long id) {
        return categoryRepository.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategories(){
        return (List<Category>) categoryRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Category deleteCategory(@PathVariable(value = "id") long id) {
        categoryRepository.delete(id);
        return categoryRepository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public Category updateCategory(@PathVariable(value = "id") long id,
                            @RequestParam(value = "category") String category) {
        Category updatedCategory = categoryRepository.findById(id);
        updatedCategory.setCategory(category);
        categoryRepository.save(updatedCategory);

        return updatedCategory;
    }






}
