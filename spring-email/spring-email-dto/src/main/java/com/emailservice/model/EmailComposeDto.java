package com.emailservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EmailComposeDto {

  private List<String> toUser = new ArrayList<>();

  private List<String> ccUser = new ArrayList<>();

  private String subject;

  private String message;

  Map<String, Object> properties = new HashMap<>();

}
