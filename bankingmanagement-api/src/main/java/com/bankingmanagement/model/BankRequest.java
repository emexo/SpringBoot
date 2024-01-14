package com.bankingmanagement.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BankRequest {
    private int bankCode;
    private String bankName;
    private String bankAddress;
}
