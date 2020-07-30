package com.spring.main.repositorys;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.main.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId>{

	List<Order> findOrderByCustomerCustomerId(ObjectId id);
	
}
