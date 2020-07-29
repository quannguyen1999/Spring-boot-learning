package com.spring.main.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.models.DienVien;
import com.spring.main.repositorys.DienVienRepository;

@Service
public class DienVienServiceImpl implements DienVienServices{
	@Autowired
	private DienVienRepository dienVienRepository;

	@Override
	public DienVien themDienVien(DienVien entity) {
		// TODO Auto-generated method stub
		return dienVienRepository.save(entity);
	}

	@Override
	public List<DienVien> findAll() {
		// TODO Auto-generated method stub
		return dienVienRepository.findAll();
	}

}