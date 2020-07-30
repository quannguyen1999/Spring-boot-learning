package com.spring.main.models;

import javax.validation.constraints.NotEmpty;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class Order {
	
	@Id
	private ObjectId idOrder;
	
	@NotEmpty
	private String tenHang;
	
	@DBRef
	private Customer customer;
}
