package com.mayuktha.gateway.usersmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuktha.gateway.usersmgmt.entity.iUser;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<iUser, Integer>{
	
	Optional<iUser> findByEmail(String email);

}
