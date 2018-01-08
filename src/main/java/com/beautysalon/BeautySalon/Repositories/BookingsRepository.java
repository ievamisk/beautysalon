package com.beautysalon.BeautySalon.Repositories;

import com.beautysalon.BeautySalon.Entities.BeautyProcedure;
import com.beautysalon.BeautySalon.Entities.Bookings;
import com.beautysalon.BeautySalon.Entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends CrudRepository<Bookings, Long> {
    Bookings findById(long id);
}
