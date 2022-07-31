package com.example.springbatch.service.impl.writer;

import com.example.springbatch.model.Customers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomerWriter implements ItemWriter<Customers> {
    @Override
    public void write(List<? extends Customers> list) throws Exception {
        log.info(String.valueOf(list.size()));
    }
}
