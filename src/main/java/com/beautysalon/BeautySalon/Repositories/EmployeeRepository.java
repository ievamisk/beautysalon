package com.beautysalon.BeautySalon.Repositories;

import com.beautysalon.BeautySalon.Entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findById (long id);
}
