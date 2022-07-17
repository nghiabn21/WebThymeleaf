package com.webthymeleaf.service;

import com.webthymeleaf.dto.ProductsDao;
import com.webthymeleaf.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProducts {
    ProductsDao getProductById(int id );

    Page<ProductsDao> getProductByIdCategory(int id , int page);

    Page<Products> findAllProducts(int page);

    Page<ProductsDao> GetDataProduct(int page );

    Page<ProductsDao> GetDataProductNew(int page );
    List<ProductsDao> getAllProductsById(int id );

    Page<ProductsDao> getAllProductsByIdFindPage(int id, int pageable);
}
