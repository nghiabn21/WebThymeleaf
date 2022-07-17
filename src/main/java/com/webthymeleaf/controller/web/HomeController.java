package com.webthymeleaf.controller.web;

import com.webthymeleaf.dto.ProductsDao;
import com.webthymeleaf.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController extends MenuController {
    @Autowired
    SlidesSerImpl slidesSer;

    @Autowired
    private MenusSerImpl menusSer;

    @Autowired
    private CategorysSerImpl categorysSer;

    @Autowired
    private ProductSerImpl dto;

    @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        _mvShare.addObject("slides", slidesSer.GetDataSlide());
        _mvShare.addObject("categorys", categorysSer.GetDataCategorys());
        _mvShare.addObject("products", dto.GetDataProduct(1));
        _mvShare.addObject("productsNoiBat", dto.GetDataProductNew(1));

        _mvShare.setViewName("/web/index");

        return _mvShare;
    }

    @GetMapping("/san-pham/{id}/{pageNumber}")
    public String getOnePage(Model model,@PathVariable("id") int id , @PathVariable("pageNumber") int currentPage){
        model.addAttribute("menus", menusSer.GetDataMenus());
        Page<ProductsDao> page = dto.getAllProductsByIdFindPage(id,currentPage); // lấy id sản phẩm rồi số trang
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<ProductsDao> getAllProducts = page.getContent();

        model.addAttribute("id", id);
        model.addAttribute("currentPage", currentPage); // trang hiện tại
        model.addAttribute("totalPages", totalPages);  // tổng số trang
        model.addAttribute("totalItems", totalItems); // tổng số sản phẩm
        model.addAttribute("getAllProducts", getAllProducts);   // 1 trang bn sản phẩm

        return "/web/products";
    }

    @GetMapping("/san-pham/{id}")
    public String getAllPages(Model model,@PathVariable("id") int id){
        return getOnePage(model,id, 1);
    }

    @GetMapping(value = "/san-pham")
    public ModelAndView sanPham() {
        _mvShare.setViewName("/web/product");
        return _mvShare;
    }

    @RequestMapping(value = "/gioi-thieu", method = RequestMethod.GET)
    public ModelAndView aboutUs() {
        _mvShare.setViewName("/web/aboutUs");
        return _mvShare;
    }





}
