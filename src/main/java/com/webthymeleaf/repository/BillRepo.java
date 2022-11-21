package com.webthymeleaf.repository;

import com.webthymeleaf.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepo extends JpaRepository<Bills, Integer> {
    @Query(value = "select MAX(id) from Bills ")
    int GetIDLastBills() ;
}
