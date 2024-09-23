package com.example.demo.model;

import com.example.demo.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="Users")
@Data
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userId;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String confirmPassword;
	private UserRole userRole;
	private Long phoneNumber;
	private LocalDate dob;


}
