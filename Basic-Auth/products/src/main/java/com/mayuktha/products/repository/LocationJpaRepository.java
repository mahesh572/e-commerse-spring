package com.mayuktha.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuktha.products.entity.Location;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Integer>{

}
