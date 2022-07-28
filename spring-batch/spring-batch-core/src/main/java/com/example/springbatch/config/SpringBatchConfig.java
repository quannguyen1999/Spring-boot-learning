package com.example.springbatch.config;

import com.example.springbatch.model.Customers;
import com.example.springbatch.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private CustomerRepository customerRepository;

    private CustomerWriter customerWriter;

    //get locate file and read item csv
    @Bean
    public FlatFileItemReader<Customers> reader(){
       FlatFileItemReader<Customers> itemReader = new FlatFileItemReader();
       itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
       itemReader.setName("csvReader");
       //avoid header
       itemReader.setLinesToSkip(1);
       //mapper
       itemReader.setLineMapper(lineMapper());
       return itemReader;
    }

    public LineMapper<Customers> lineMapper(){
        DefaultLineMapper<Customers> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","firstName","lastName","email","gender","contactNo","country","dob");

        BeanWrapperFieldSetMapper<Customers> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customers.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    //excute handler logic
    @Bean
    public CustomerProcessor processor(){
        return new CustomerProcessor();
    }

    //Writer
    //Writer to database CustomerWriter

    //step 1
    //chunk: get only 5 record then save data to CustomerWriter
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("csv-step").<Customers, Customers>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(customerWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    //main job
    @Bean
    public Job runJob(){
        return jobBuilderFactory.get("importCustomers")
                .flow(step1())
                .end().build();
    }

    //execute async, only trigger 10 thread
    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }


}
