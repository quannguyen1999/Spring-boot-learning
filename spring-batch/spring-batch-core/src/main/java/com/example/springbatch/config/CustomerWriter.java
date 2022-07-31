package com.example.springbatch.config;

import com.example.springbatch.model.Customers;
import com.example.springbatch.repository.CustomerRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerWriter implements ItemWriter<Customers> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void write(List<? extends Customers> list) throws Exception {
        System.out.println(list.size());
        System.out.println("Thread Name : " + Thread.currentThread().getName());
        customerRepository.saveAll(list);
    }
}
