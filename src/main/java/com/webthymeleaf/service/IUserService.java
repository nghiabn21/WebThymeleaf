package com.webthymeleaf.service;

import com.webthymeleaf.entity.User;
import com.webthymeleaf.entity.UserAndRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService extends UserDetailsService {
 User AddAcount(User user);
 User CheckAcount(User user);

 public User save(User user);

 void saveUserWithDefaultRole(User user);

 Page<User> findByUserAndRole(Pageable pageable);

 Optional<User> update(User user);
}
