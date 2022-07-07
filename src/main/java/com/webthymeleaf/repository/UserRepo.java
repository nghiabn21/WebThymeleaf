package com.webthymeleaf.repository;

import com.webthymeleaf.entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = " from User u  where u.email = ?1" )
    public User checkUser(String email);
}
