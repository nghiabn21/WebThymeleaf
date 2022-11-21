package com.webthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bills")
@Entity
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "user")
    private String email ;

    @Column(name = "phone")
    private String phone ;

    @Column(name = "display_name")
    private String displayName ;

    @Column(name = "address")
    private String address ;

    @Column(name = "note")
    private String note ;

    @Column(name = "total")
    private Double total ;

    @Column(name = "quanty")
    private Integer quanty;

    @OneToMany(mappedBy = "bills",cascade = CascadeType.ALL)
    private List<BillDetails> billDetails;
}
