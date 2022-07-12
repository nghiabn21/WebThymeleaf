package com.webthymeleaf.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerAdmin {

    @GetMapping (value = "/admin")
    public String home(){
        return  "admin/admin";
    }
}
