package com.spring.main.models;

import javax.validation.constraints.NotEmpty;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.annotations.NotThreadSafe;

import lombok.Data;

//@Data
//+tạo getter, setter, constructor tự động
//@Document
//+khai báo 1 collection mới vào mongodb
@Data
@Document
public class DienVien {
	@Id
	private ObjectId idDV;
	
	@NotEmpty(message = "tenDV không được rỗng")
	private String tenDV;

	@NotEmpty(message = "nickName không được rỗng")
	private String nickName;
}
