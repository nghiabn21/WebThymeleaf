package com.webthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "billdetail")
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "id_bills")
    private Integer idBills;
    @Column(name = "quanty")
    private int quanty;
    @Column(name = "total")
    private double total;
}
