package com.beautysalon.BeautySalon.Repositories;

import com.beautysalon.BeautySalon.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findById (long id);

}


