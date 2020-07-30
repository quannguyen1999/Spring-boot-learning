package com.spring.main.repositorys;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.main.models.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, ObjectId>{

}
