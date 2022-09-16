package com.webthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_roles")
public class UserAndRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;


}
