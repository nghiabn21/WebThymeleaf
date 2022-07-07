package com.webthymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDao {
	@Id
	private int id_product;
	private int id_category;
	private String size;
	private String name;
	private double price;
	private int sale;
	private String title;
	private boolean highlight;
	private boolean new_product;
	private String detail;
	private int id_color;
	private String name_color;
	private String code_color;
	private String img;
	private Date created_at;
	private Date updated_at;


}
