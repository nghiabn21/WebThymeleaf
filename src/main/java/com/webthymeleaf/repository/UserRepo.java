package com.webthymeleaf.repository;

import com.webthymeleaf.entity.User;
import com.webthymeleaf.entity.UserAndRole;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = " from User u  where u.email = ?1" )
    public User checkUser(String email);

    User findByEmail(String email);

    @Query(value = "SELECT new UserDao(u.id, u.email, u.password, u.displayName ,u.address, r.name) from User u inner join UserAndRole ur on u.id = ur.userId " +
            " inner join Role r on ur.roleId = r.id ")
    Page<User> findByUserAndRole(Pageable pageable);
}
