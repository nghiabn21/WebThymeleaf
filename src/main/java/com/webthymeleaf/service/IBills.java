package com.webthymeleaf.service;

import com.webthymeleaf.dto.CartDto;
import com.webthymeleaf.entity.Bills;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface IBills {
    public Bills AddBills(Bills bill);

    public void AddBillsDetail(HashMap<Integer, CartDto> carts) ;

    Page<Bills> findAllBill(Pageable pageable);
}
