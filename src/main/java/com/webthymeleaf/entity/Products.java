package com.webthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_category")
    private Integer id_category;

    @Column(name = "size")
    private String size;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "sale")
    private Integer sale;
    @Column(name = "title")
    private String title;
    @Column(name = "highlight")
    private Boolean highlight;
    @Column(name = "new_product")
    private Boolean new_product;
    @Column(name = "detail")
    private String detail;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "updated_at")
    private Date updated_at;

    @OneToMany // 1 brand cho nhiều loại
    @JoinColumn(name = "id_product")
    private List<Colors> colors ;
}
