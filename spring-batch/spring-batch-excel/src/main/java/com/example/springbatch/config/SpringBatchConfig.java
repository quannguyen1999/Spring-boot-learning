package com.example.springbatch.config;

import com.example.springbatch.model.Customers;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
   @Bean
   ItemStreamReader<Customers> reader() {
        PoiItemReader reader = new PoiItemReader();
        reader.setResource(new ClassPathResource("input.xlsx"));
        reader.setRowMapper(new RowMapperImpl());
        reader.setLinesToSkip(1);
        return reader;
    }

}
