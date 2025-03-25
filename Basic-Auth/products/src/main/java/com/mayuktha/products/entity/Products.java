package com.mayuktha.products.entity;

import java.time.LocalDateTime;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="product")
@Getter 
@Setter 
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productID")
	private int productID ;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "sku")
	private String sku;
	
	
	@Column(name = "categoryID")
	private int categoryID;
	
	
	@Column(name = "unitPrice")
	private Float unitPrice;
	
	@Column(name = "supplierID")
	private int supplierID ;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;
	
	/*
	@Column(name = "inventoryId")
	private int inventory_id;
	@Column(name = "price")
	private float price;
	@Column(name = "discountId")
	private int discount_id;
	@Column(name = "deletedAt")
	private LocalDateTime deleted_at;
	*/
	
	@Column(name = "image_url")
	private String imgeUrl;

	/*
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryID",referencedColumnName = "categoryID")
	private Category category;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplierID",referencedColumnName = "supplierID")
	private Supplier supplier;
*/
}
