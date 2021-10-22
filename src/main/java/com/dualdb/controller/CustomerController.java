package com.dualdb.controller;

import com.dualdb.customer.CustomerRepository;
import com.dualdb.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {


    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/customer")
    @Transactional("customerTransactionManager")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
