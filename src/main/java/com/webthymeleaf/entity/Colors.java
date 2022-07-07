package com.webthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "colors")
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code ;

    @Column(name = "img")
    private String img ;

    @ManyToOne
    @JoinColumn(name = "id_product") // thông qua khóa ngoại
    private Products products;


}
