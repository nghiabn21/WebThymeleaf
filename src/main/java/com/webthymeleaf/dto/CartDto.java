package com.webthymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
	private int quanty;
	private double totalPrice; //tổng giá
	private ProductsDao product; // list danh sachs sanr pahm




}
