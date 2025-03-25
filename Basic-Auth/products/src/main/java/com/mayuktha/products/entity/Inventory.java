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

@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryID ;
	
	/*
	@Column(name = "productID")
	private int productID;
	*/
	
	/*
	@Column(name = "locationID")
	private int locationID;
	*/
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "minimumStockLevel")
	private int minimumStockLevel;
	@Column(name = "maximumStockLevel")
	private int maximumStockLevel;
	@Column(name = "lastStockUpdate")
	private LocalDateTime lastStockUpdate;
   
	
	/*
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;
	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
*/
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productID",referencedColumnName = "productID")
	private Products products;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locationID",referencedColumnName = "locationID")
	private Location location;
	
}
