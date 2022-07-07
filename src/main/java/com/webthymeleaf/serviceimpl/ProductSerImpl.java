package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.dto.ProductsDao;
import com.webthymeleaf.repository.ProductsRepo;
import com.webthymeleaf.service.IProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSerImpl implements IProducts {
    @Autowired
    ProductsRepo productsRepo ;

    @Override
    public ProductsDao getProductById(int id) {
        List<ProductsDao> list = productsRepo.getProductById(id);
        return list.get(0);
    }

    @Override
    public Page<ProductsDao> getProductByIdCategory(int id, int page) {
        Pageable pageable = PageRequest.of(page -1 , 5);
        Page<ProductsDao> list = productsRepo.getAllProductsByIdFindPage(id, pageable);
        return list;
    }
}
