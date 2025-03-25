package com.mayuktha.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuktha.products.entity.Inventory;
@Repository
public interface InventoryJparepository extends JpaRepository<Inventory, Integer>{

}
