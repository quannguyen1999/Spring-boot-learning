package com.example.springbatch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "customers")
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private String contactNo;

    private String country;

    private String dob;


}
