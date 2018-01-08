package com.beautysalon.BeautySalon.Repositories;

import com.beautysalon.BeautySalon.Entities.Workday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkDayRepository extends CrudRepository<Workday, Long>{
    Workday findById(long id);

    List<Workday> findAllByEmployeeId(long employeeId);
}
