package com.spring.main.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spring.main.exceptions.NotFoundException;
import com.spring.main.models.DienVien;
import com.spring.main.services.DienVienServices;
@Controller
@RequestMapping("/api/dienViens")
public class DienVienController {
	@Autowired
	public DienVienServices dienVienServices;
	
	//nếu rỗng thì sẽ trả về lỗi
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> themDienVien(@Valid @RequestBody DienVien dienVien){
		
		dienVienServices.themDienVien(dienVien);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<DienVien>> danhSachDienVien(HttpServletRequest request){
		
		//demo thử
		throw new NotFoundException("reaction không được null");
		
	}

	
}
