package com.mayuktha.usersmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mayuktha.usersmgmt.entity.iUser;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<iUser, Long>{
	
	Optional<iUser> findByEmail(String email);

}
