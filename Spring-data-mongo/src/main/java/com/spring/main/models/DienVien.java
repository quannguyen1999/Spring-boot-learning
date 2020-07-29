package com.spring.main.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class DienVien {
	@Id
	private ObjectId idDV;
	
	private String tenDV;
}
