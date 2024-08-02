package com.bankingmanagementwithmvc.service;

import com.bankingmanagementwithmvc.entity.Bank;
import com.bankingmanagementwithmvc.exception.BankDetailsNotFound;
import com.bankingmanagementwithmvc.model.BankTO;
import com.bankingmanagementwithmvc.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BankServiceImpl implements BankService{
    @Autowired
    public BankRepository bankMongoRepository;

    @Override
    public List<BankTO> findAll() throws BankDetailsNotFound {
        log.info("Inside the BankServiceImplV2.findAllBank");
        List<Bank> banks = bankMongoRepository.findAll();

        log.info("List of Banks:{}", banks);
        if(CollectionUtils.isEmpty(banks)){
            log.error("Bank details not found");
            throw new BankDetailsNotFound("Bank Details not found");
        }

        List<BankTO> bankTOS = banks.stream().map(bank -> {
            BankTO bankTO = new BankTO();
            bankTO.setBankCode(bank.getBankCode());
            bankTO.setBankName(bank.getBankName());
            bankTO.setBankAddress(bank.getBankAddress());
            return bankTO;
        }).collect(Collectors.toList());

        log.info("End of BankServiceImplV2.findAllBank");
        return bankTOS;
    }
}
