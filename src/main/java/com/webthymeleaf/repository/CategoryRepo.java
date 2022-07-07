package com.webthymeleaf.repository;

import com.webthymeleaf.entity.Categorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Categorys, Integer> {
}
