package com.main.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Customer {
	private UUID uuid;
	private String name;
}
