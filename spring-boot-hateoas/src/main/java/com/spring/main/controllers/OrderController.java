package com.spring.main.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.models.Order;
import com.spring.main.services.OrderService;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
	@Autowired
	public OrderService orderService;
	
	//thêm order
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> themOrder(@Valid @RequestBody Order order){
		
		orderService.themOrder(order);
		
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}
	
}
