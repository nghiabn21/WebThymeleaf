package com.webthymeleaf.repository;

import com.webthymeleaf.entity.Slides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlidesRepo extends JpaRepository<Slides,Integer> {


}
