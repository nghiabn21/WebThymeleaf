package com.webthymeleaf.service;

import com.webthymeleaf.dto.ProductsDao;
import com.webthymeleaf.entity.Categorys;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
     public List<Categorys>  GetDataCategorys();
     public List<ProductsDao> getDataProductsPaginate(int id , int start, int end);

     public List<ProductsDao> getAllProductsById(int id);
}
