package com.spring.main.controllers;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.models.Customer;
import com.spring.main.models.Order;
import com.spring.main.services.CustomerService;
import com.spring.main.services.OrderService;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {
	//các service CRUD
	@Autowired
	public CustomerService customerService;

	@Autowired
	public OrderService orderService;

	//thêm customer
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> themCustomer(@Valid @RequestBody Customer customer){

		customerService.themCustomer(customer);

		return new ResponseEntity<String>(HttpStatus.OK);

	}

	//chi tiết khách hàng
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Customer> chiTietKhachHang(@PathVariable("id") String id){
		
		return new ResponseEntity<Customer>(customerService.findCustomerById(id),HttpStatus.OK);

	}
	
	
	//danh sách order qua id khách hàng
	@GetMapping(value = "/orders/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Order>> danhSachOrderQuaId(@PathVariable("id") String id){
		
		return new ResponseEntity<List<Order>>(orderService.danhSachOrderByCustomerId(new ObjectId(id)),HttpStatus.OK);

	}

	
	//danh sách khách hàng
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> danhSachCustomer(HttpServletRequest request){

		List<Customer> listCustomerFromDB=customerService.danhSachCustomer();
		
		for(int i=0;i<listCustomerFromDB.size();i++) {
			
			String idCUstomer=listCustomerFromDB.get(i).getCustomerId().toString();
			
			//linkTo: kiểm tra class controller và lấy đường dẫn
			//slash: gắn id vào sau linkTo
			//methodOn:fake phương thức trong class này
			listCustomerFromDB.get(i).
				add(
					linkTo(methodOn(this.getClass()).chiTietKhachHang(idCUstomer)).withRel("chi tiết khách hàng")
				);
			
			List<Order> listOrder=orderService.danhSachOrderByCustomerId(listCustomerFromDB.get(i).getCustomerId());
			
			if(listOrder.size()>0) {

				//linkTo: kiểm tra class controlelr và lấy đường dẫn
				//slash: gắn id vào sau linkTo
				listCustomerFromDB.get(i).add(linkTo(methodOn(CustomerController.class).danhSachOrderQuaId(idCUstomer)).withRel("danh sách order"));

			}

		}

		return listCustomerFromDB;

	}

}
