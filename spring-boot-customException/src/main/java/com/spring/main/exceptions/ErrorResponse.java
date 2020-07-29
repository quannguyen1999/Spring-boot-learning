package com.spring.main.exceptions;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

//bắt buộc phải khai báo @XmlRootElement này để tránh in các class lỗi
@XmlRootElement(name = "error")
public class ErrorResponse {
	//tên lỗi
    private String message;
 
    //lỗi cụ thể (như id)
    private List<String> details;
    
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
 
    //Getter and setters
    
    
}
