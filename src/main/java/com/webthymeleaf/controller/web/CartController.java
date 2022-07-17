package com.webthymeleaf.controller.web;

import com.webthymeleaf.dto.CartDto;
import com.webthymeleaf.entity.Bills;
import com.webthymeleaf.entity.User;
import com.webthymeleaf.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@SessionAttributes("Cart")
public class CartController extends MenuController {
    @Autowired
    CartSerImpl cartService ;
    @Autowired
    private MenusSerImpl menusSer;

    @Autowired
    private CategorysSerImpl categorysSer;

     @Autowired
     private BillsSerImpl billsService;

    @RequestMapping(value = "/gio-hang")
    public ModelAndView Index() {
        _mvShare.addObject("menus", menusSer.GetDataMenus());
        _mvShare.addObject("categorys", categorysSer.GetDataCategorys());

        _mvShare.setViewName("/web/cart");
        return _mvShare;
    }


    @RequestMapping(value = "/addCart/{id}")
    public String AddCart(HttpServletRequest request, HttpSession session, @PathVariable int id) {

        HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("Cart");  // giả sử có sẵn hàng

        if (cart == null) {
            cart = new HashMap<Integer, CartDto>();
        }

        cart = cartService.AddCart(id, cart); // set hàng mới về hàng cũ
        session.setAttribute("Cart", cart); // set lại hàng

        session.setAttribute("TotalQuantityCart", cartService.TotalQuanty(cart));
        session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/EditCart/{id}/{quanty}")
    public String EditCart(HttpServletRequest request, HttpSession session, @PathVariable("id") int id, @PathVariable("quanty") int quanty) {
        HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("Cart"); // giả sử có sẵn hàng

        if (cart == null) {
            cart = new HashMap<Integer, CartDto>();
        }
        cart = cartService.EditCart(id, quanty, cart); // set hàng mới về hàng cũ
        session.setAttribute("Cart", cart); // set lại hàng

        session.setAttribute("TotalQuantyCart", cartService.TotalQuanty(cart));
        session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/DeleteCart/{id}")
    public String DeleteCart(HttpServletRequest request, HttpSession session, @PathVariable int id) {
        HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("Cart"); // giả sử có sẵn hàng

        if (cart == null) {
            cart = new HashMap<Integer, CartDto>();
        }
        cart = cartService.Delete(id, cart); // sau khi xóa thành công thì put lại hàng
        session.setAttribute("Cart", cart); // set lại hàng
        session.setAttribute("TotalQuantyCart", cartService.TotalQuanty(cart));
        session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkOut(HttpServletRequest request, HttpSession session) {

        Bills bills = new Bills(); //tạo đối tượng mới
        User loginInfo = (User) session.getAttribute("LoginInfo"); //lấy thông tin login
        if(loginInfo != null) {  //khác rỗng có nghĩa là ng dùng đã đăng nhập r
            bills.setAddress(loginInfo.getAddress()) ;
            bills.setDisplayName(loginInfo.getDisplayName()) ;
            bills.setEmail(loginInfo.getEmail());
        }
        _mvShare.addObject("bills", bills);
        _mvShare.setViewName("/web/thanhtoan");
        return _mvShare;
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkOutBill(HttpServletRequest request, HttpSession session, @ModelAttribute("bills") Bills bill) {
        List<Bills> bills = new ArrayList<>();
        bills.add(bill) ;
        bill.setQuanty(Integer.parseInt((String) session.getAttribute("TotalQuantyCart")));
        bill.setTotal(Double.parseDouble((String) session.getAttribute("TotalPriceCart")));
        billsService.AddBills(bill);
        if(bills.size() > 0) {

            HashMap<Integer, CartDto> carts = (HashMap<Integer, CartDto>) session.getAttribute("Cart");
            billsService.AddBillsDetail(carts);
        }

        session.removeAttribute("Cart");

        return "redirect:gio-hang";
    }
}
