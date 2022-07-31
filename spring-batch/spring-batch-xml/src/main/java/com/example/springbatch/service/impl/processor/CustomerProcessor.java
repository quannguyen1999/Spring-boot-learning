package com.example.springbatch.service.impl.processor;

import com.example.springbatch.model.Customers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerProcessor implements ItemProcessor<Customers, Customers> {
    @Override
    public Customers process(Customers customers) throws Exception {
       log.info(customers.toString());
        return customers;
    }
}
