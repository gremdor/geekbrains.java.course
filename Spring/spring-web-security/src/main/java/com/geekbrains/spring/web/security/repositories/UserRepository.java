package com.geekbrains.spring.web.security.repositories;

import com.geekbrains.spring.web.security.entities.Role;
import com.geekbrains.spring.web.security.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "select distinct a.name from authorities a " +
            "where id in (select ur.authority_id from users_roles ur" +
            "   where ur.user_id = :userId and ur.authority_id is not null" +
            "   union " +
            "select ra.authority_id from users_roles ur " +
            "join roles_authorities ra on ra.role_id = ur.role_id " +
            "where ur.user_id = :userId) " +
            "union all " +
            "select distinct r.name from users_roles ur " +
            "join roles r on r.id = ur.role_id " +
            "where ur.user_id = :userId", nativeQuery = true)
    List<String> findUserRolesAndAuthorities (Long userId);
}
