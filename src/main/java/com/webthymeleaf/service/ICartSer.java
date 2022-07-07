package com.webthymeleaf.service;

import com.webthymeleaf.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface ICartSer {
    HashMap<Integer, CartDto> AddCart(int id, HashMap<Integer, CartDto> cart);

    HashMap<Integer, CartDto> EditCart(int id, int quanty, HashMap<Integer, CartDto> cart);

    HashMap<Integer, CartDto> Delete(int id, HashMap<Integer, CartDto> cart);

    int TotalQuanty(HashMap<Integer, CartDto> cart);  //tổng sản phẩm

    double TotalPrice(HashMap<Integer, CartDto> cart);  //tổng giá
}


