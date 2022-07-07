package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.dto.CartDto;
import com.webthymeleaf.entity.BillDetails;
import com.webthymeleaf.entity.Bills;
import com.webthymeleaf.repository.BillDetailRepo;
import com.webthymeleaf.repository.BillRepo;
import com.webthymeleaf.service.IBills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BillsSerImpl implements IBills {
    @Autowired
    BillRepo billsDao ;
    @Autowired
    BillDetailRepo billDetailRepo ;
    @Override
    public Bills AddBills(Bills bill) {

        return billsDao.save(bill);
    }

    @Override
    public void AddBillsDetail(HashMap<Integer, CartDto> carts) {
        int idBills = billsDao.GetIDLastBills();

        for(Map.Entry<Integer, CartDto> itemCart : carts.entrySet()) {
            BillDetails billDetail = new BillDetails();
            billDetail.setIdBills(idBills);
            billDetail.setIdProduct(itemCart.getValue().getProduct().getId_product());
            billDetail.setQuanty(itemCart.getValue().getQuanty());
            billDetail.setTotal(itemCart.getValue().getTotalPrice());

             billDetailRepo.save(billDetail) ;
        }
    }
}
