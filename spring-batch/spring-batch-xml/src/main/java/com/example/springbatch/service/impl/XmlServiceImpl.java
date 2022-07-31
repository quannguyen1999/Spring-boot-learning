package com.example.springbatch.service.impl;

import com.example.springbatch.config.XStreamMarshallerConfig;
import com.example.springbatch.enums.TypeXml;
import com.example.springbatch.model.Customers;
import com.example.springbatch.model.Tutorials;
import com.example.springbatch.repository.CustomerRepository;
import com.example.springbatch.service.XmlService;
import com.example.springbatch.service.impl.processor.CustomerProcessor;
import com.example.springbatch.service.impl.processor.TutorialProcessor;
import com.example.springbatch.service.impl.writer.CustomerWriter;
import com.example.springbatch.service.impl.writer.TutorialWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
@Service
public class XmlServiceImpl implements XmlService {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private CustomerRepository customerRepository;

    private TutorialWriter tutorialWriter;

    private CustomerWriter customerWriter;

    private ApplicationContext applicationContext;

    //excute handler logic
    @Bean
    public TutorialProcessor processorTutorial(){
        return new TutorialProcessor();
    }

    //excute handler logic
    @Bean
    public CustomerProcessor processorCustomer(){
        return new CustomerProcessor();
    }

    //step tutorial
    //chunk: get only 5 record then save data to CustomerWriter
    @Bean
    public Step stepTutorial(){
        return stepBuilderFactory.get("csv-step").<Tutorials, Tutorials>chunk(1)
                .reader(xmlItemReaderTutorial())
                .processor(processorTutorial())
                .writer(tutorialWriter)
                .build();
    }

    //step customer
    //chunk: get only 5 record then save data to CustomerWriter
    @Bean
    public Step stepCustomer(){
        return stepBuilderFactory.get("csv-step").<Customers, Customers>chunk(5)
                .reader(xmlItemReaderCustomer())
                .processor(processorCustomer())
                .writer(customerWriter)
                .build();
    }

    //get locate file and read item csv
    @Bean
    public StaxEventItemReader<Tutorials> xmlItemReaderTutorial(){
        StaxEventItemReader<Tutorials> staxEventItemReader = new StaxEventItemReader<>();
        staxEventItemReader.setStrict(false);
        staxEventItemReader.setFragmentRootElementName("tutorial");
        staxEventItemReader.setResource(new FileSystemResource("spring-batch-xml/src/main/resources/tutorial.xml"));
        staxEventItemReader.setUnmarshaller(customTutorialUnMarshaller());
        return staxEventItemReader;
    }

    //get locate file and read item csv
    @Bean
    public StaxEventItemReader<Customers> xmlItemReaderCustomer(){
        StaxEventItemReader<Customers> staxEventItemReader = new StaxEventItemReader<>();
        staxEventItemReader.setStrict(false);
        staxEventItemReader.setFragmentRootElementName("customer");
        staxEventItemReader.setResource(new FileSystemResource("spring-batch-xml/src/main/resources/customer.xml"));
        staxEventItemReader.setUnmarshaller(customCustomerUnMarshaller());
        return staxEventItemReader;
    }

    @Bean
    public XStreamMarshallerConfig customTutorialUnMarshaller(){
        XStreamMarshallerConfig XStreamMarshallerConfig = new XStreamMarshallerConfig();
        //scan annotation
        XStreamMarshallerConfig.setAutodetectAnnotations(true);
        Map<String, Class<Tutorials>> tutorialsMap = new HashMap<>();
        tutorialsMap.put("tutorial", Tutorials.class);
        XStreamMarshallerConfig.setAliases(tutorialsMap);
        return XStreamMarshallerConfig;
    }

    @Bean
    public XStreamMarshallerConfig customCustomerUnMarshaller(){
        XStreamMarshallerConfig XStreamMarshallerConfig = new XStreamMarshallerConfig();
        //scan annotation
        XStreamMarshallerConfig.setAutodetectAnnotations(true);
        Map<String, Class<Customers>> customersMap = new HashMap<>();
        customersMap.put("customer", Customers.class);
        XStreamMarshallerConfig.setAliases(customersMap);
        return XStreamMarshallerConfig;
    }

    @Override
    public Job runJobBaseOnType(TypeXml typeXml) {
        if(typeXml.equals(TypeXml.TYPE_TUTORIAL)){
            return (Job) applicationContext.getBean("runJobTutorial");
        }else{
            return  (Job) applicationContext.getBean("runJobCustomer");
        }
    }

    @Primary
    @Bean
    public Job runJobTutorial(){
        return jobBuilderFactory.get("runJobTutorial")
                    .flow(stepTutorial())
                    .end().build();
    }

    @Bean
    public Job runJobCustomer(){
        return jobBuilderFactory.get("runJobCustomer")
                .flow(stepCustomer())
                .end().build();
    }

    @Bean
    public TypeXml typeXml(){
        return TypeXml.TYPE_TUTORIAL;
    }

}
