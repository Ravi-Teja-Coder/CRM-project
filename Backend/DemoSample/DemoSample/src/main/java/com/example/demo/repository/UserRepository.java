package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmailAndPassword(String email,String password);
	
	Optional<User> findByEmail(String email);

	List<User> findAllByUserRole(UserRole userRole);
	
	Optional<User> findByUserId(int id);
}
