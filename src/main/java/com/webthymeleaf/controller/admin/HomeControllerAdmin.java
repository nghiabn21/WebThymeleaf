package com.webthymeleaf.controller.admin;

import com.webthymeleaf.entity.Bills;
import com.webthymeleaf.entity.Products;
import com.webthymeleaf.entity.User;
import com.webthymeleaf.repository.BillRepo;
import com.webthymeleaf.serviceimpl.BillsSerImpl;
import com.webthymeleaf.serviceimpl.ProductSerImpl;
import com.webthymeleaf.serviceimpl.UserSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class HomeControllerAdmin {
    @Autowired
    ProductSerImpl products ;

    @Autowired
    UserSerImpl userSer ;

    @Autowired
    BillsSerImpl billsSer;

    @GetMapping
    public String home(){
        return  "admin/admin";
    }

    @GetMapping("/find-all/{pageNumber}")
    public String findAll(Model model, @PathVariable("pageNumber") int currentPage) {

        Page<Products> products1 = products.findAllProducts(currentPage);
        int totalPages = products1.getTotalPages(); //lấy tổng số page
        long totalItems = products1.getTotalElements();  // lấy tổng số phần tử
        List<Products> getAllProducts = products1.getContent();

//        model.addAttribute("id", id);
        model.addAttribute("currentPage", currentPage); // trang hiện tại
        model.addAttribute("totalPages", totalPages);  // tổng số trang
//        model.addAttribute("totalItems", totalItems); // tổng số sản phẩm
        model.addAttribute("getAllProducts", getAllProducts);   // 1 trang bn sản phẩm


        return "/admin/product";
    }

    @GetMapping("/find-all")
    public String getAllPages(Model model){
        return findAll(model, 1);
    }

    @GetMapping("/nguoi-dung")
    public String findByUserAndRole(Model model){
        Pageable pageable = PageRequest.of(0,100);
        Page<User> getAllProducts = userSer.findByUserAndRole(pageable);
        List<User> getAll = userSer.listAll();

//        model.addAttribute("id", id);
//        model.addAttribute("currentPage", currentPage); // trang hiện tại
//        model.addAttribute("totalPages", totalPages);  // tổng số trang
////        model.addAttribute("totalItems", totalItems); // tổng số sản phẩm
         model.addAttribute("All", getAll);   // 1 trang bn sản phẩm
        return "/admin/role";
    }

    @GetMapping("/customer")
    public String findByAllCustomer(Model model){
        Pageable pageable = PageRequest.of(0,100);
        Page<Bills> getAllProducts = billsSer.findAllBill(pageable);
        List<Bills> getAll = getAllProducts.getContent();

//        model.addAttribute("id", id);
//        model.addAttribute("currentPage", currentPage); // trang hiện tại
//        model.addAttribute("totalPages", totalPages);  // tổng số trang
////        model.addAttribute("totalItems", totalItems); // tổng số sản phẩm
        model.addAttribute("All", getAll);   // 1 trang bn sản phẩm
        return "/admin/customer";
    }

//    @GetMapping ("/nguoi-dung")
//    public String OneRole(Model model){
//        return findByUserAndRole(model, 1);
//    }
}
