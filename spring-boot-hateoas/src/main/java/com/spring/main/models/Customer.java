package com.spring.main.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;
//extends representationModel để add các link vào 
@Document
public class Customer extends RepresentationModel<Customer>{
	
	@Id
	private ObjectId customerId;
	
    private String customerName;
    
    private String companyName;

	public Customer(ObjectId customerId, String customerName, String companyName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.companyName = companyName;
	}

	public Customer() {
		super();
	}

	public ObjectId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(ObjectId customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

    
    
}
