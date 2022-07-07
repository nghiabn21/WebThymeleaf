package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.entity.User;
import com.webthymeleaf.repository.UserRepo;
import com.webthymeleaf.service.IUser;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;

@Service
public class UserSerImpl implements IUser {
    @Autowired
    UserRepo userRepo ;
    @Override
    public User AddAcount(User user) {
     user.setPassword(BCrypt.hashpw( user.getPassword(), BCrypt.gensalt(12))); // lấy mặt khẩu

        return userRepo.save(user);
    }

    @Override
    public User CheckAcount(User user) {
       String pass = user.getPassword();
       user =userRepo.checkUser(user.getEmail());
       if(user != null) {
           if(BCrypt.checkpw(pass, user.getPassword())) {
           return user ;
           } else {
           return null ;
           }
       }
        return null;
    }
}
