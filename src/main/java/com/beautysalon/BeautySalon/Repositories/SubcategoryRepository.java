package com.beautysalon.BeautySalon.Repositories;

import com.beautysalon.BeautySalon.Entities.Subcategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {
    Subcategory findById(long id);
}
