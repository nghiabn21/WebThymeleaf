package com.webthymeleaf.controller;

import com.webthymeleaf.entity.User;
import com.webthymeleaf.serviceimpl.UserSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController extends MenuController{

    @Autowired
    UserSerImpl userSer ;

    @GetMapping(value = "/dang-ky")
    public ModelAndView getLogin(){
        _mvShare.setViewName("/login");
        _mvShare.addObject("user", new User());
        return _mvShare ;
    }

    @PostMapping(value = "/dang-ky")
    public ModelAndView CreateAccount(@ModelAttribute("user") User user){
        List<User> count = new ArrayList<>();
        count.add(userSer.AddAcount(user));
        if(count.size() > 0) {
            _mvShare.addObject("status", "Đăng ký thành công");
        }else {
            _mvShare.addObject("status", "Đăng ký không thành công");
        }
//        userSer.AddAcount(user);

        _mvShare.setViewName("/login");
        return _mvShare ;
    }

    @PostMapping(value = "/dang-nhap")
    public ModelAndView Login(HttpSession session, @ModelAttribute("user") User user){
         user =  userSer.CheckAcount(user);
        if(user != null) {
        //    _mvShare.addObject("statusLogin", "Đăng nhập thành công");
            _mvShare.setViewName("redirect:trang-chu");
            session.setAttribute("LoginInfo", user);
        }else {
            _mvShare.addObject("statusLogin", "Đăng nhập không thành công");
        }


        return _mvShare ;
    }

    @RequestMapping(value = "/dang-xuat", method = RequestMethod.GET)
    public String dangXuat(HttpSession session, HttpServletRequest request) {
        session.removeAttribute("LoginInfo");

        return "redirect:" + request.getHeader("Referer");
    }
}
