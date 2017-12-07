package com.beautysalon.BeautySalon.Repositories;

import com.beautysalon.BeautySalon.Entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);

}
