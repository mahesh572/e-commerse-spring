package com.mayuktha.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuktha.products.entity.Supplier;

@Repository
public interface SupplierJpaRepository extends JpaRepository<Supplier, Integer>{

}
