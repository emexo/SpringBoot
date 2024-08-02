package com.bankingmanagementwithmvc.repository;

import com.bankingmanagementwithmvc.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}
