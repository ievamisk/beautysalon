package com.beautysalon.BeautySalon.Controllers;


import com.beautysalon.BeautySalon.Entities.Category;
import com.beautysalon.BeautySalon.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    public @ResponseBody
    @RequestMapping(value="/", method = RequestMethod.POST, produces = "application/json")
    Category newCategory(@RequestParam(value = "category") String category) {
        Category newCategory = new Category(category);
        categoryRepository.save(newCategory);
        return newCategory;
    }


}
