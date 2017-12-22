package com.beautysalon.BeautySalon.Controllers;

import com.beautysalon.BeautySalon.Entities.Subcategory;
import com.beautysalon.BeautySalon.Repositories.CategoryRepository;
import com.beautysalon.BeautySalon.Repositories.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subcategory")
public class SubcategoryController {

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public @ResponseBody
    @RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
    Subcategory newCategory(
            @RequestParam(value = "subcategory") String subcategory,
            @RequestParam(value = "category_id") long id) {
        Subcategory newSubcategory = new Subcategory(subcategory, categoryRepository.findById(id));
        subcategoryRepository.save(newSubcategory);
        return newSubcategory;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    List<Subcategory> getSubcategories(){
        return (List<Subcategory>) subcategoryRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Subcategory deleteSubcategory(@PathVariable(value = "id") long id) {
        subcategoryRepository.delete(id);
        return subcategoryRepository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public Subcategory updateSubcategory(@PathVariable(value = "id") long id,
                                  @RequestParam(value = "subcategory") String subcategory) {
        Subcategory updatedSubcategory = subcategoryRepository.findById(id);
        updatedSubcategory.setSubcategory(subcategory);
        subcategoryRepository.save(updatedSubcategory);

        return updatedSubcategory;
    }
}
