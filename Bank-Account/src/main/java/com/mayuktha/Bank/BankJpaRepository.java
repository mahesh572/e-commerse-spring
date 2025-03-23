package com.mayuktha.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankJpaRepository extends JpaRepository<Bank, Integer>{

}
