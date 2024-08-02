package com.bankingmanagementwithmvc.controller;

import com.bankingmanagementwithmvc.model.BankTO;
import com.bankingmanagementwithmvc.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/banks")
public class BankController {
    @Autowired
    public BankService bankService;

    @GetMapping("/list")
    public String findAll(Model model) throws Exception {
        log.info("Inside the BankControllerV2.findAll");
        List<BankTO> bankTOS = bankService.findAll();
        model.addAttribute("banks", bankTOS);
        log.info("End of BankControllerV2.findAll");
        return "banks";
    }
}
