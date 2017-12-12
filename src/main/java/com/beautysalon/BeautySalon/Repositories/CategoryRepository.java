package com.beautysalon.BeautySalon.Repositories;

import com.beautysalon.BeautySalon.Entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findById(long id);
}
