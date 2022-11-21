package com.webthymeleaf.repository;


import com.webthymeleaf.entity.Products;


import com.webthymeleaf.dto.ProductsDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {
    @Query(value = "select new ProductsDao(p.id , p.id_category, p.size , p.name , p.price , p.sale , p.title , p.highlight  " +
            ", p.new_product , p.detail , c.id  , c.name  , c.code  , c.img  " +
            ", p.created_at ,  p.updated_at) from Products p Inner join Colors c on p.id = c.products.id where  p.new_product = true group by p.id, c.products.id")
    Page<ProductsDao> GetDataProduct(Pageable pageable);

    @Query(value = "select new ProductsDao(p.id , p.id_category, p.size , p.name , p.price , p.sale , p.title , p.highlight  " +
            ", p.new_product , p.detail , c.id  , c.name  , c.code  , c.img  " +
            ", p.created_at ,  p.updated_at) from Products p Inner join Colors c on p.id = c.products.id where  p.highlight = true group by p.id, c.products.id")
    Page<ProductsDao> GetDataProductNew(Pageable pageable);

    @Query(value = "select new ProductsDao(p.id , p.id_category, p.size , p.name , p.price , p.sale , p.title , p.highlight  " +
            ", p.new_product , p.detail , c.id  , c.name  , c.code  , c.img  " +
            ", p.created_at ,  p.updated_at) from Products p Inner join Colors c on p.id = c.products.id where 1 =1 and p.id_category = ?1" )
    List<ProductsDao> getAllProductsById(int id);

    @Query(value = "select new ProductsDao(p.id , p.id_category, p.size , p.name , p.price , p.sale , p.title , p.highlight  " +
            ", p.new_product , p.detail , c.id  , c.name  , c.code  , c.img  " +
            ", p.created_at ,  p.updated_at) from Products p Inner join Colors c on p.id = c.products.id where 1 =1 and p.id_category = ?1" )
    Page<ProductsDao> getAllProductsByIdFindPage(int id, Pageable pageable);

    @Query(value = "select new ProductsDao(p.id, p.id_category, p.size , p.name , p.price , p.sale , p.title , p.highlight  " +
            ", p.new_product , p.detail , c.id  , c.name  , c.code  , c.img  " +
            ", p.created_at ,  p.updated_at) from Products p Inner join Colors c on p.id = c.products.id where 1 =1 and p.id = ?1" )
    List<ProductsDao> getProductById(int id);

    @Query(value = "from Products pr where pr.id=?1")
    Optional<Products> findById(int id);




}
