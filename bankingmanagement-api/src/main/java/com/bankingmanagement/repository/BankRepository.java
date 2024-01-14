package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    Optional<Bank> findByBankName(String name);

    @Query(value = "select bank from Bank bank where bankName=:name")
    Optional<Bank> findBankByName(@Param("name") String name);
}
