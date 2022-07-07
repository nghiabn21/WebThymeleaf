package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.entity.Menus;
import com.webthymeleaf.repository.MenuRepo;
import com.webthymeleaf.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenusSerImpl implements IMenuService {
    @Autowired
    private MenuRepo menuRepo ;
    @Override
    public List<Menus> GetDataMenus() {
        return menuRepo.findAll();
    }
}
