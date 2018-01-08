package com.beautysalon.BeautySalon.Repositories;

import com.beautysalon.BeautySalon.Entities.BeautyProcedure;
import com.beautysalon.BeautySalon.Entities.Employee;
import com.beautysalon.BeautySalon.Entities.Subcategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeautyProcedureRepository extends CrudRepository<BeautyProcedure, Long> {
    BeautyProcedure findById(long id);
//    double findBeautyProcedureByPrice (double price);
    List<BeautyProcedure> findAllByEmployeeId(long id);

    List<BeautyProcedure>findAllBySubcategory(Subcategory subcategory);
}
