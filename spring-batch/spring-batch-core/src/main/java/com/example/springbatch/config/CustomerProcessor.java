package com.example.springbatch.config;

import com.example.springbatch.model.Customers;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customers, Customers> {
    @Override
    public Customers process(Customers customers) throws Exception {
//        //handler customer
//        if(customers.getCountry().equals("United States")){
            return customers;
//        }else{
//            return null;
//        }
    }
}
