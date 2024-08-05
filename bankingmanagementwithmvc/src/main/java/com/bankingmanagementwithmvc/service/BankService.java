package com.bankingmanagementwithmvc.service;

import com.bankingmanagementwithmvc.exception.BankDetailsNotFound;
import com.bankingmanagementwithmvc.model.BankRequest;
import com.bankingmanagementwithmvc.model.BankTO;
import com.bankingmanagementwithmvc.model.BankUpdateRequest;

import java.util.List;

public interface BankService {
    List<BankTO> findAll() throws BankDetailsNotFound;
    BankTO save(BankRequest bankRequest) throws BankDetailsNotFound;
    BankTO update(BankUpdateRequest bankRequest) throws BankDetailsNotFound;
    String delete(int id) throws BankDetailsNotFound;

    BankTO findById(int id) throws BankDetailsNotFound;
}
