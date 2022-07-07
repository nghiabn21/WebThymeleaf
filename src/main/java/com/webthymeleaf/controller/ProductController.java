package com.webthymeleaf.controller;

import com.webthymeleaf.service.IProducts;
import com.webthymeleaf.serviceimpl.ProductSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController extends MenuController{
    @Autowired
    ProductSerImpl products ;

    @RequestMapping(value = "/chi-tiet-san-pham/{id}", method = RequestMethod.GET)
    public ModelAndView chiTietSanPham(@PathVariable("id") int id) {
        _mvShare.addObject("product", products.getProductById(id));
        int idCategory = products.getProductById(id).getId_category();
        _mvShare.addObject("productByIdCategory", products.getProductByIdCategory(products.getProductById(id).getId_category(),1));

        _mvShare.setViewName("/sanpham");
        return _mvShare;
    }
}
