package com.webthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "billdetail")
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product_detail")
    private Products idProduct ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bills")
    private Bills bills;


    @Column(name = "quanty")
    private int quanty;

    @Column(name = "total")
    private double total;


}
