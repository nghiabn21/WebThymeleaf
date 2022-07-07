package com.webthymeleaf.repository;

import com.webthymeleaf.entity.Menus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<Menus, Integer> {
}
