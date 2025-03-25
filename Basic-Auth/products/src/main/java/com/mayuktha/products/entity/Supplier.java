package com.mayuktha.products.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier")
public class Supplier {

	@Id
	@Column(name = "supplierID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierID;
	@Column(name = "name")
	private String name;
	@Column(name = "contactEmail")
	private String contactEmail;
	@Column(name = "phoneNumber")
	private String phoneNumber;
	@Column(name = "address")
	private String address;
	
	
}
