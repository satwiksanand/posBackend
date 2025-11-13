package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.models.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    Customer getCustomerById(Long id) throws Exception;
    List<Customer> getAllCustomer();
    List<Customer> searchCustomer(String keyword);
}
