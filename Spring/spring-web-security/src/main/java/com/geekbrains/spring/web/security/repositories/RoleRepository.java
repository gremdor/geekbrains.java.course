package com.geekbrains.spring.web.security.repositories;

import com.geekbrains.spring.web.security.entities.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
