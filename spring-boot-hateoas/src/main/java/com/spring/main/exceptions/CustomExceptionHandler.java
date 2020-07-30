package com.spring.main.exceptions;

import java.util.ArrayList;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.main.controllers.CustomerController;

//khai báo bean và scan các class controller
//dùng để trả về lỗi view cho người dùng
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice(basePackageClasses = {CustomerController.class})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	//nếu quăng lỗi exception thì trả về kiểu Json này
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		//khởi tạo
		List<String> details = new ArrayList<>();
		
		ex.printStackTrace();
		
		System.out.println(ex.getCause());

		System.out.println(ex.getMessage());
		
		//lưu message vào đây
		details.add(ex.getLocalizedMessage());
		
		ErrorResponse error = new ErrorResponse("Server Error", details);
		
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//nếu quăng lỗi NotFoundException thì trả về kiểu Json này
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
		//khởi tạo
		List<String> details = new ArrayList<>();
		
		//lưu message vào đây
		details.add(ex.getLocalizedMessage());
		
		ErrorResponse error = new ErrorResponse("Record Not Found", details);
		
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		
	}


	//phải khai báo @Vallidated trong các model thì mới hoạt động
	//sau đó nếu có lỗi thì sẽ báo cho client biết
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		//khởi tạo
		List<String> details = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			
			details.add(error.getDefaultMessage());
			
		}
		
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
