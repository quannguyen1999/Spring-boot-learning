package com.spring.main.services;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.exceptions.NotFoundException;
import com.spring.main.models.Customer;
import com.spring.main.repositorys.CustomerRepository;
import com.spring.main.repositorys.OrderRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public boolean themCustomer(Customer customer) {

		customerRepository.save(customer);

		return true;
	}

	@Override
	public Customer findCustomerById(String id) {

		Customer customer=null;
		
		try {
			
			customer = customerRepository.findById(new ObjectId(id)).orElseThrow(()->new NotFoundException("không tìm thấy"));

		} catch (Exception e) {
			
			throw new NotFoundException("id không hợp lệ");
			
		}
		
		return customer;
	}

	@Override
	public List<Customer> danhSachCustomer() {
		
		return customerRepository.findAll();
	}

}
