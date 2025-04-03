package com.mayuktha.usersmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuktha.usersmgmt.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	List<Address> findByMobile(String mobile);
	
}