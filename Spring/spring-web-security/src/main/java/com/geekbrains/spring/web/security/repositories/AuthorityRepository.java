package com.geekbrains.spring.web.security.repositories;

import com.geekbrains.spring.web.security.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Role, Long> {
}
