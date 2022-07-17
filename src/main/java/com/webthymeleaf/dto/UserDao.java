package com.webthymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
    @Id
    private Integer id ;
    private String email;
    private String password ;
    private String displayName ;
    private String address ;
    private String name ;

    @Override
    public String toString() {
        return name ;
    }
}
