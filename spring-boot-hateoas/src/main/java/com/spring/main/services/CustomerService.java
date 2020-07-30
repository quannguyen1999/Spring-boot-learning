package com.spring.main.services;


import java.util.List;

import com.spring.main.models.Customer;

public interface CustomerService {
	public boolean themCustomer(Customer customer);
	
	public Customer findCustomerById(String id);
	
	public List<Customer> danhSachCustomer();
}
