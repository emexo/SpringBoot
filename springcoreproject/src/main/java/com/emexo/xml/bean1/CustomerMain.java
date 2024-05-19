package com.emexo.xml.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class CustomerMain {
    public static void main(String[] args) {

        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext1.xml");

        ApplicationContext context = new FileSystemXmlApplicationContext("SpringRepo/springcoreproject/src/main/resources/applicationcontext1.xml");

        Customer customer = context.getBean("customer", Customer.class);
        customer.getCustomerDetails();
    }
}
