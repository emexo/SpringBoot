package com.bankingmanagementwithmvc.service;

import com.bankingmanagementwithmvc.entity.Bank;
import com.bankingmanagementwithmvc.exception.BankDetailsNotFound;
import com.bankingmanagementwithmvc.model.BankRequest;
import com.bankingmanagementwithmvc.model.BankTO;
import com.bankingmanagementwithmvc.model.BankUpdateRequest;
import com.bankingmanagementwithmvc.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BankServiceImpl implements BankService{
    @Autowired
    public BankRepository bankRepository;

    @Override
    public List<BankTO> findAll() throws BankDetailsNotFound {
        log.info("Inside the BankServiceImplV2.findAllBank");
        List<Bank> banks = bankRepository.findAll();

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

    @Override
    public BankTO save(BankRequest bankRequest) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.save, bankRequest:{}", bankRequest);
        Bank bank = new Bank();
        bank.setBankCode(bankRequest.getBankCode());
        bank.setBankName(bankRequest.getBankName());
        bank.setBankAddress(bankRequest.getBankAddress());
        Bank response = bankRepository.save(bank);

        if(Objects.isNull(response)){
            log.error("Bank details not saved");
            throw new BankDetailsNotFound("Bank details not saved");
        }
        // apply the business logic
        BankTO bankTO = new BankTO();
        bankTO.setBankName(response.getBankName());
        bankTO.setBankAddress(response.getBankAddress());

        log.info("End of BankServiceImpl.save");
        return bankTO;
    }

    @Override
    public BankTO update(BankUpdateRequest bankRequest) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.save, bankRequest:{}", bankRequest);
        Optional<Bank> bankOptional = bankRepository.findById(bankRequest.getBankCode());

        if(bankOptional.isEmpty()){
            log.error("Bank details not found");
            throw new BankDetailsNotFound("Bank details not found");
        }
        Bank bank = bankOptional.get();
        bank.setBankCode(bankRequest.getBankCode());
        if(Objects.nonNull(bankRequest.getBankCode())) {
            bank.setBankCode(bankRequest.getBankCode());
        }

        if(Objects.nonNull(bankRequest.getBankName())) {
            bank.setBankName(bankRequest.getBankName());
        }
        if(Objects.nonNull(bankRequest.getBankAddress())) {
            bank.setBankAddress(bankRequest.getBankAddress());
        }
        Bank response = bankRepository.save(bank);

        if(Objects.isNull(response)){
            log.error("Bank details not saved");
            throw new BankDetailsNotFound("Bank details not saved");
        }
        // apply the business logic
        BankTO bankTO = new BankTO();
        bankTO.setBankName(response.getBankName());
        bankTO.setBankAddress(response.getBankAddress());

        log.info("End of BankServiceImpl.save");
        return bankTO;
    }

    @Override
    public String delete(int id) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.delete, id:{}", id);
        Optional<Bank> bankOptional = bankRepository.findById(id);

        if(bankOptional.isEmpty()){
            log.error("Bank details not found");
            throw new BankDetailsNotFound("Bank details not found");
        }
        bankRepository.deleteById(id);
        log.info("End of BankServiceImpl.delete");
        return "Bank detail has been deleted";
    }

    @Override
    public BankTO findById(int id) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.findById, id:{}", id);
        Optional<Bank> bankOptional = bankRepository.findById(id);

        if(bankOptional.isEmpty()){
            log.error("Bank details not found");
            throw new BankDetailsNotFound("Bank details not found");
        }
        // apply the business logic
        Bank bank = bankOptional.get();
        BankTO bankTO = new BankTO();
        bankTO.setBankCode(bank.getBankCode());
        bankTO.setBankName(bank.getBankName());
        bankTO.setBankAddress(bank.getBankAddress());

        log.info("End of BankServiceImpl.findAll");
        return bankTO;
    }
}
