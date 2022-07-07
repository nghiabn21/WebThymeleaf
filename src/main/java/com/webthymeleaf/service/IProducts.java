package com.webthymeleaf.service;

import com.webthymeleaf.dto.ProductsDao;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProducts {
    ProductsDao getProductById(int id );

    Page<ProductsDao> getProductByIdCategory(int id , int page);
}
