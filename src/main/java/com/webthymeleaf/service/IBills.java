package com.webthymeleaf.service;

import com.webthymeleaf.dto.CartDto;
import com.webthymeleaf.entity.Bills;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface IBills {
    public Bills AddBills(Bills bill);

    public void AddBillsDetail(HashMap<Integer, CartDto> carts) ;
}
