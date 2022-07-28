package com.example.springbatch.repository;

import com.example.springbatch.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {
}
