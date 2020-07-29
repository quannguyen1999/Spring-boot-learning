package com.spring.main.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.spring.main.models.DienVien;

public interface DienVienServices{
	DienVien themDienVien(DienVien entity);      

	//liệt kê danh sách
	List<DienVien> findAll();               
}
