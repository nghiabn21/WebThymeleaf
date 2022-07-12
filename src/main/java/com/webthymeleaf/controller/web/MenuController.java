package com.webthymeleaf.controller.web;

import com.webthymeleaf.serviceimpl.CategorysSerImpl;
import com.webthymeleaf.serviceimpl.MenusSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.persistence.Column;

@Controller
public class MenuController {

    @Autowired
    private MenusSerImpl menusSer;

    @Autowired
    private CategorysSerImpl categorysSer;

    public ModelAndView _mvShare = new ModelAndView();

    @PostConstruct
    public ModelAndView Init(){
        _mvShare.addObject("menus", menusSer.GetDataMenus());
        _mvShare.addObject("categorys", categorysSer.GetDataCategorys());
        return _mvShare ;
    }
}
