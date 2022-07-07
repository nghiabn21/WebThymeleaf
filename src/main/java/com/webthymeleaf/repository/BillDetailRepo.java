package com.webthymeleaf.repository;

import com.webthymeleaf.entity.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepo extends JpaRepository<BillDetails, Integer> {
}
