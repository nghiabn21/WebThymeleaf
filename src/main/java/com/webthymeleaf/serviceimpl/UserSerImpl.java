package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.entity.Role;
import com.webthymeleaf.entity.User;
import com.webthymeleaf.repository.RoleRepo;
import com.webthymeleaf.repository.UserRepo;
import com.webthymeleaf.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class UserSerImpl implements IUserService {
    @Autowired
    UserRepo userRepo ;

    @Autowired
    RoleRepo roleRepo ;


  private  User user ;
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

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void saveUserWithDefaultRole(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        Collection<Role> role = new ArrayList<>();
        role.add( roleRepo.findByName("User") ) ;
        user.setRoles(role);

        userRepo.save(user);

    }



    @Override
    public Page<User> findByUserAndRole(Pageable pageable) {
        return userRepo.findByUserAndRole(pageable);
    }

    @Override
    public Optional<User> update(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return Optional.of(  userRepo.save(user));
    }

    public void saveUserWithRole(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepo.save(user);
    }

    public List<User> listAll(){
        return userRepo.findAll();
    }

    public User getUser(int id ){
        return userRepo.findById(id).get();
    }

    public List<Role> getListRole(){
        return roleRepo.findAll();
    }
                            // cấu hình security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getDisplayName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
          authorities.add(new SimpleGrantedAuthority(role.getName()));

        }
        return authorities;
    }

}
