package com.bankingmanagementwithmvc.service;

import com.bankingmanagementwithmvc.exception.BankDetailsNotFound;
import com.bankingmanagementwithmvc.model.BankTO;

import java.util.List;

public interface BankService {
    List<BankTO> findAll() throws BankDetailsNotFound;
}
