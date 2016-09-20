package com.github.ashim.web.service;

import java.util.List;

import com.github.ashim.persistence.model.Customer;

public interface CustomerService {

	public List<Customer> allCustomers();

	public Customer getCustomerDetail(final String id);

}
