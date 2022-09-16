package com.webthymeleaf.repository;

import com.webthymeleaf.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Query(value = "select r from Role r where r.name =?1")
    Role findByName(String name);
}
