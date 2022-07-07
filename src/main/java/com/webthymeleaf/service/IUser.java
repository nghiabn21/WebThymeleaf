package com.webthymeleaf.service;

import com.webthymeleaf.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
public interface IUser {
 User AddAcount(User user);
 User CheckAcount(User user);


}
