package com.mayuktha.Bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/bank")
public class BankController {
	
	@Autowired
	BankAccountService bankAccountService;
	
	@GetMapping
	public String getinfo() {
		System.out.println("Hi Bank........."+Thread.currentThread().getName());
		
		
		return "*****************";
	}
	@GetMapping("/deposit/{amt}/{id}")
	public String deposit(@PathVariable int amt,@PathVariable int id) throws InterruptedException {
		bankAccountService.deposit(amt,id);
		return new String("deposited");
	}
	
	@GetMapping("/withdraw/{amt}/{id}")
	public String withdraw(@PathVariable int amt,@PathVariable int id) throws InterruptedException {
		
		bankAccountService.withdraw(amt,id);
		return "withdrawn amount....";
	}
	
	@GetMapping("/create")
	public void create() {
		bankAccountService.createAccount();
	}
}
