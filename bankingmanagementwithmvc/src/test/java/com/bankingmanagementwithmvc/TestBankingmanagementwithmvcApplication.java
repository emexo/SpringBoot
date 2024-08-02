package com.bankingmanagementwithmvc;

import org.springframework.boot.SpringApplication;

public class TestBankingmanagementwithmvcApplication {

	public static void main(String[] args) {
		SpringApplication.from(BankingmanagementwithmvcApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
