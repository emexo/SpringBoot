package com.emexo.xml.bean1;

import com.emexo.aop.aspect.LoggingAspect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer {

    private static final Logger log = LogManager.getLogger(Customer.class);

    private  int customerId;
    private String customerName;

    public Customer(){
        log.info("#### Default constructor####");
        this.customerName = "Regu";
        this.customerId = 1;
    }

    public void getCustomerDetails(){
        log.info("Customer details, customer id :{}, customer name :{}", customerId, customerName);
    }
}
