package com.beautysalon.BeautySalon.Controllers;
import com.beautysalon.BeautySalon.Entities.Category;
import com.beautysalon.BeautySalon.Entities.Employee;
import com.beautysalon.BeautySalon.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployees(){
        return (List<Employee>) employeeRepository.findAll();
    }

    public @ResponseBody
    @RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
    Employee newEmployee (
            @RequestParam (value = "name") String name,
            @RequestParam (value = "last_name") String lastName,
            @RequestParam (value = "description") String description) {
        Employee newEmployee = new Employee(name,lastName,description);
        employeeRepository.save(newEmployee);
        return newEmployee;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Employee deleteEmployee(@PathVariable(value = "id") long id){
        employeeRepository.delete(id);
        return employeeRepository.findOne(id);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public @ResponseBody Employee editEmployee(
            @PathVariable(value = "id") long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "description") String description){
        Employee updateEmployee = employeeRepository.findById(id);
        updateEmployee.setName(name);
        updateEmployee.setLastName(lastName);
        updateEmployee.setDescription(description);
        employeeRepository.save(updateEmployee);
        return updateEmployee;
    }
}