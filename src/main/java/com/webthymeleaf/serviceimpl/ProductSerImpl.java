package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.dto.ProductsDao;
import com.webthymeleaf.entity.Products;
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

    @Override
    public Page<Products> findAllProducts(int page) {
        Pageable pageable = PageRequest.of(page -1, 5);
        return productsRepo.findAll(pageable);
    }


    @Override
    public Page<ProductsDao> GetDataProduct(int pageable) {
        //  List<ProductsDao> list = productsRepo.GetDataProduct();
        Pageable pageable1 = PageRequest.of(pageable - 1, 12);
        Page<ProductsDao> list = productsRepo.GetDataProduct(pageable1);
        return list;
    }

    @Override
    public Page<ProductsDao> GetDataProductNew(int page) {
        Pageable pageable1 = PageRequest.of(page - 1, 9);
        Page<ProductsDao> list = productsRepo.GetDataProductNew(pageable1);

        return list;
    }

    @Override
    public List<ProductsDao> getAllProductsById(int id) {
        return productsRepo.getAllProductsById(id);
    }

    @Override
    public Page<ProductsDao> getAllProductsByIdFindPage(int id, int pageable) {
        Pageable pageable1 = PageRequest.of(pageable -1 , 12);
        Page<ProductsDao> list =  productsRepo.getAllProductsByIdFindPage(id,pageable1);
        return list;
    }
}
