package com.bankingmanagementwithmvc.controller;

import com.bankingmanagementwithmvc.exception.BankDetailsNotFound;
import com.bankingmanagementwithmvc.model.BankRequest;
import com.bankingmanagementwithmvc.model.BankTO;
import com.bankingmanagementwithmvc.model.BankUpdateRequest;
import com.bankingmanagementwithmvc.service.BankService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/banks")
public class BankController {
    @Autowired
    public BankService bankService;

    @GetMapping("/list")
    public String findAll(Model model) {
        log.info("Inside the BankControllerV2.findAll");
        List<BankTO> bankTOS = null;
        try {
            bankTOS = bankService.findAll();
        } catch (BankDetailsNotFound e) {
            model.addAttribute("error", "Bank Details not Found");
        }
        model.addAttribute("banks", bankTOS);
        log.info("End of BankControllerV2.findAll");
        return "banks";
    }

    @GetMapping("/create")
    public String create(Model model){
        log.info("Inside the BankControllerV2.create");
        BankRequest bank = new BankRequest();
        model.addAttribute("bank", bank);
        return "createbank";
    }

    @PostMapping("/save")
    public RedirectView save(@ModelAttribute("bank") BankRequest bank) throws BankDetailsNotFound {
        bankService.save(bank);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/banks/list");
        return redirectView;
    }

    @GetMapping("/delete/{bankCode}")
    public RedirectView delete(@PathVariable("bankCode") int bankCode) throws BankDetailsNotFound {
        bankService.delete(bankCode);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/banks/list");
        return redirectView;
    }

    @GetMapping("/edit/{bankCode}")
    public String update(@PathVariable("bankCode") int bankCode, Model model) throws BankDetailsNotFound {
        log.info("Inside the BankControllerV2.create");
        BankTO bankTO = bankService.findById(bankCode);
        model.addAttribute("bank", bankTO);
        return "updatebank";
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("bank") BankUpdateRequest bank) throws BankDetailsNotFound {
        bankService.update(bank);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/banks/list");
        return redirectView;
    }

}
