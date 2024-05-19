package com.emexo.aop.main;

import com.emexo.aop.dao.PassengerDAO;
import com.emexo.aop.model.Passenger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.emexo.aop");

        PassengerDAO passengerDAO = context.getBean("passengerDAOImpl", PassengerDAO.class);

        Passenger passenger =  passengerDAO.getPassenger(1);

        System.out.println(passenger);
    }
}
