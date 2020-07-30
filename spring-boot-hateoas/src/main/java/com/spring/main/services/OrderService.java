package com.spring.main.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.spring.main.models.Order;

public interface OrderService {
	public boolean themOrder(Order order);
	
	public List<Order> danhSachOrderByCustomerId(ObjectId id);
}
