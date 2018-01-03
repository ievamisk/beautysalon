package com.beautysalon.BeautySalon.Controllers;

import com.beautysalon.BeautySalon.Entities.BeautyProcedure;
import com.beautysalon.BeautySalon.Entities.Employee;
import com.beautysalon.BeautySalon.Repositories.BeautyProcedureRepository;
import com.beautysalon.BeautySalon.Repositories.EmployeeRepository;
import com.beautysalon.BeautySalon.Repositories.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/procedures")
public class BeautyProceduresController {

    @Autowired
    BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public @ResponseBody
    @RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
    BeautyProcedure newProcedure(@RequestParam(value = "price") double price,
                                 @RequestParam(value = "duration") int duration,
                                 @RequestParam(value = "subcategory_id") long subcategoryId,
                                 @RequestParam(value = "employee_id") long employeeId) {
        BeautyProcedure newProcedure = new BeautyProcedure(price, duration, subcategoryRepository.findById(subcategoryId),employeeRepository.findById(employeeId));
        beautyProcedureRepository.save(newProcedure);
        return newProcedure;
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<BeautyProcedure> getProcedures(@PathVariable(value = "id") long id){
        return beautyProcedureRepository.findAllByEmployeeId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody BeautyProcedure deleteProcedure(@PathVariable(value = "id") long id) {
        beautyProcedureRepository.delete(id);
        return beautyProcedureRepository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public BeautyProcedure updateProcedure(@PathVariable(value = "id") long id,
                                           @RequestParam(value = "price") double price,
                                           @RequestParam(value = "duration") int duration) {
        BeautyProcedure updatedProcedure = beautyProcedureRepository.findById(id);
        updatedProcedure.setPrice(price);
        updatedProcedure.setDuration(duration);
        beautyProcedureRepository.save(updatedProcedure);
        return updatedProcedure;
    }
}
