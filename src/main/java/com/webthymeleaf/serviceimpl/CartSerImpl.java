package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.dto.CartDto;
import com.webthymeleaf.dto.ProductsDao;
import com.webthymeleaf.service.ICartSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartSerImpl implements ICartSer {

    @Autowired
    ProductSerImpl productSer ;



    @Override
    public HashMap<Integer, CartDto> AddCart(int id, HashMap<Integer, CartDto> cart) {
        CartDto item = new CartDto() ;
        ProductsDao productsDao = productSer.getProductById(id);
        if(productsDao != null && cart.containsKey(id)) {
            item = cart.get(id); // lấy sản phẩm muốn thêm
            item.setQuanty(item.getQuanty() + 1);
            item.setTotalPrice(item.getQuanty() * item.getProduct().getPrice());
        }else {
            item.setProduct( productsDao);
            item.setQuanty(1);
            item.setTotalPrice(productsDao.getPrice());
        }
        cart.put(id, item); // lấy hàng cũ set vào hàng mới
        return cart;
    }

    @Override
    public HashMap<Integer, CartDto> EditCart(int id, int quanty , HashMap<Integer, CartDto> cart) {

        CartDto item = new CartDto() ;
        if( cart.containsKey(id)) {
            item = cart.get(id);  // lấy sản phẩm theo id
            item.setQuanty(quanty);
            item.setTotalPrice(quanty * item.getProduct().getPrice());
        }
        cart.put(id, item); // lấy hàng cũ set vào hàng mới , nếu có cái cũ nó set luôn thành cái mới
        return cart;
    }

    @Override
    public HashMap<Integer, CartDto> Delete(int id, HashMap<Integer, CartDto> cart){
        if(cart == null) {
        return cart ;
        }
        if(cart.containsKey(id)) {
          cart.remove(id) ;
        }
        return cart;
    }

    @Override
    public int TotalQuanty(HashMap<Integer, CartDto> cart) {
        int totalQuanty = 0 ;
        for (Map.Entry<Integer, CartDto> item : cart.entrySet() ) {
         totalQuanty += item.getValue().getQuanty(); // lấy từng giá trị quanty số lượng
        }
        return totalQuanty;
    }

    @Override
    public double TotalPrice(HashMap<Integer, CartDto> cart) {
        double totalQuanty = 0 ;
        for (Map.Entry<Integer, CartDto> item : cart.entrySet() ) {
            totalQuanty += item.getValue().getTotalPrice(); // lấy từng giá trị price
        }
        return totalQuanty;
    }
}
