package com.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Customer;
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
//	responseStatus(HttpStatus.OK)
	
	@PostMapping
	public Object createnewCustomer(@RequestBody Customer customer){
		return new ResponseEntity<>(customer,HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public Object findCustomer(@PathVariable("id") String id){
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public Object deleteStation(@PathVariable("id") String id){
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public Object updateStation(@PathVariable("id") String id) {
		return new ResponseEntity(HttpStatus.OK);
	}


}
