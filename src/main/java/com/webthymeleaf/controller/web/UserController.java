package com.webthymeleaf.controller.web;

import com.webthymeleaf.entity.Role;
import com.webthymeleaf.entity.User;
import com.webthymeleaf.serviceimpl.UserSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController extends MenuController {
    @Autowired
    UserSerImpl userSer ;

    @GetMapping("/login")
    public String login(){
        return "Login";
    }

    @GetMapping(value = "/dang-ky")
    public ModelAndView getLogin(){

        List<Role> roles = userSer.getListRole();
        _mvShare.addObject("roles", roles);
        _mvShare.addObject("user", new User());
        _mvShare.setViewName("registration");
        return _mvShare ;
    }

    @PostMapping(value = "/dang-ky")
    public String CreateAccount(@ModelAttribute("user") User user){
      userSer.saveUserWithRole(user);
      return "redirect:/dang-ky?success";
    }

    @GetMapping
    public String show(){
        return "registration";
    }

    @PostMapping(value = "/dang-nhap")
    public ModelAndView Login(HttpSession session, @ModelAttribute("user") User user){
         user =  userSer.CheckAcount(user);
        if(user != null) {
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

    @ModelAttribute("user")
    public User userRegistration(){
        return new User();
    }
}
