package com.spring.main.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.exceptions.NotFoundException;
import com.spring.main.models.Order;
import com.spring.main.repositorys.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public boolean themOrder(Order order) {
		
		if(order.getCustomer()==null || order.getCustomer().getCustomerId()==null) {
			
			throw new NotFoundException("id không hợp lệ");
			
		}
		
		orderRepository.save(order);
		
		return true;
	}

	@Override
	public List<Order> danhSachOrderByCustomerId(ObjectId id) {
		
		return orderRepository.findOrderByCustomerCustomerId(id);
	}

	
	
}
