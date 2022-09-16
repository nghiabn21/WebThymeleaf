package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.dto.CartDto;
import com.webthymeleaf.dto.ProductsDao;
import com.webthymeleaf.entity.BillDetails;
import com.webthymeleaf.entity.Bills;
import com.webthymeleaf.entity.Products;
import com.webthymeleaf.repository.BillDetailRepo;
import com.webthymeleaf.repository.BillRepo;
import com.webthymeleaf.repository.ProductsRepo;
import com.webthymeleaf.service.IBills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillsSerImpl implements IBills {
    @Autowired
    BillRepo billsDao ;
    @Autowired
    BillDetailRepo billDetailRepo ;

    @Autowired
    ProductsRepo productsRepo ;
    @Override
    public Bills AddBills(Bills bill) {

        return billsDao.save(bill);
    }

    @Override
    public void AddBillsDetail(HashMap<Integer, CartDto> carts) {
       int idBills = billsDao.GetIDLastBills();
       Optional<Bills> bills = billsDao.findById(idBills);
        for(Map.Entry<Integer, CartDto> itemCart : carts.entrySet()) {
            BillDetails billDetail = new BillDetails();
            billDetail.setBills(bills.get());
            billDetail.setQuanty(itemCart.getValue().getQuanty());
            billDetail.setTotal(itemCart.getValue().getTotalPrice());

            int id = itemCart.getValue().getProduct().getId_product();
            Optional<Products> pro = productsRepo.findById(id);
            billDetail.setIdProduct(pro.get());
            billDetailRepo.save(billDetail) ;
        }
    }

    @Override
    public Page<Bills> findAllBill(Pageable pageable) {
        return billsDao.findAll(pageable);
    }
}
