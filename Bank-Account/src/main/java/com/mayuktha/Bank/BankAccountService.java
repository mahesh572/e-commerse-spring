package com.mayuktha.Bank;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
	
	@Autowired
	BankJpaRepository bankJpaRepository;

	public synchronized void deposit(int depoamt,int id) throws InterruptedException {
		Optional<Bank>  bankObj = bankJpaRepository.findById(id);
		if(bankObj.isPresent()) {
			System.out.println("********...deposit......"+Thread.currentThread().getName());
			Bank bankentity = bankObj.get();
			Thread.sleep(10000);
			int bal = bankentity.getBalance() + depoamt;
			bankentity.setBalance(bal);
			bankJpaRepository.save(bankentity);
			System.out.println("Total bal after deposit:::::"+bal);
			System.out.println("OBJ deposit:::::"+bankentity);
			
			
		}
		
	}
	public synchronized void withdraw(int withamt,int id) throws InterruptedException {
		
		Optional<Bank>  bankObj =bankJpaRepository.findById(id);
		if(bankObj.isPresent()) {
			System.out.println("####...withdraw......"+Thread.currentThread().getName());
			Bank bankentity = bankObj.get();
			int bal = bankentity.getBalance() - withamt;
			bankentity.setBalance(bal);
			bankJpaRepository.save(bankentity);
			Thread.sleep(10000);
			System.out.println("Total bal after withdraw:::::"+bal);
			
			System.out.println("OBJ withdraw:::::"+bankentity);
		}
	}
	
	public void createAccount() {
		Bank bank = new Bank();
		bank.setBalance(1000);
		bankJpaRepository.save(bank);
	}
}
