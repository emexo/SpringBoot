package com.bankingmanagement.service;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.CustomerNotFoundException;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.CustomerRequest;
import com.bankingmanagement.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    /**
     * get all customer details
     * @return
     * @throws CustomerNotFoundException
     */
    @Override
    public List<CustomerDTO> findAll() throws CustomerNotFoundException {
        log.info("Inside the CustomerServiceImpl.findAll");
        List<Customer> customerList = customerRepository.findAll();
        log.info("Customer details, customerList:{}", customerList);

        if(CollectionUtils.isEmpty(customerList)){
           log.error("Customer details not found");
           throw new CustomerNotFoundException("Customer details not found");
        }

        List<CustomerDTO> customerDTOS = customerList.stream().map(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerID(customer.getCustomerID());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerPhone(customer.getCustomerPhone());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            return customerDTO;
        }).collect(Collectors.toList());

        log.info("End of CustomerServiceImpl.findAll");
        return customerDTOS;
    }

    @Override
    public CustomerDTO findCustomerById(int customerId) throws CustomerNotFoundException {
        log.info("Input to CustomerServiceImpl.findCustomerById, customerId:{}", customerId);

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(!customerOptional.isPresent()){
            log.info("Customer details not found for customerId:{} ", customerId);
            throw new CustomerNotFoundException("Customer details not found for the given customerId");
        }

        Customer customer = customerOptional.get();
        log.info("Customer details for the customerId:{}, response:{}", customerId, customer);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customer.getCustomerID());
        customer.setCustomerName(customer.getCustomerName());
        customer.setCustomerPhone(customer.getCustomerPhone());
        customer.setCustomerAddress(customer.getCustomerAddress());

        return customerDTO;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerRequest customerRequest) throws IllegalArgumentException, CustomerNotFoundException {
        log.info("Input to CustomerServiceImpl.saveCustomer, customerRequest:{}", customerRequest);

        if(customerRequest == null){
            log.info("Invalid customer request,customerRequest:{} ", customerRequest);
            throw new IllegalArgumentException("Invalid customer request,customerRequest");
        }
        Customer customer = new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setCustomerPhone(customerRequest.getCustomerPhone());
        customer.setCustomerAddress(customerRequest.getCustomerAddress());

        Customer customer1 = customerRepository.save(customer);

        if(customer1 == null){
            log.info("Exception while saving the customer");
            throw new CustomerNotFoundException("Exception while saving the customer");
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customer1.getCustomerID());
        customer.setCustomerName(customer1.getCustomerName());
        customer.setCustomerPhone(customer1.getCustomerPhone());
        customer.setCustomerAddress(customer1.getCustomerAddress());

        return null;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerRequest customerRequest) throws IllegalArgumentException, CustomerNotFoundException {
        log.info("Input to CustomerServiceImpl.saveCustomer, customerRequest:{}", customerRequest);

        if(customerRequest == null){
            log.info("Invalid customer request,customerRequest:{} ", customerRequest);
            throw new IllegalArgumentException("Invalid customer request,customerRequest");
        }
        Customer customer = new Customer();
        customer.setCustomerID(customerRequest.getCustomerId());
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setCustomerPhone(customerRequest.getCustomerPhone());
        customer.setCustomerAddress(customerRequest.getCustomerAddress());

        Customer customer1 = customerRepository.save(customer);

        if(customer1 == null){
            log.info("Exception while saving the customer");
            throw new CustomerNotFoundException("Exception while saving the customer");
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customer1.getCustomerID());
        customer.setCustomerName(customer1.getCustomerName());
        customer.setCustomerPhone(customer1.getCustomerPhone());
        customer.setCustomerAddress(customer1.getCustomerAddress());

        return null;
    }
}
