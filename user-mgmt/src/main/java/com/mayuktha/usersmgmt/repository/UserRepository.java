package com.mayuktha.usersmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuktha.usersmgmt.entity.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByEmail(String email);

}
