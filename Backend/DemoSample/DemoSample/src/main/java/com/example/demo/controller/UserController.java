package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.enums.UserRole;
import com.example.demo.response.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.config.JWTTokenGeneratorImpl;
import com.example.demo.config.JWTTokenParser;
import com.example.demo.exception.UserIdAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.UserService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired
   private JWTTokenParser jwtTokenParser;

	@Autowired
	private UserService userService;
	
	

	@Autowired
	private JWTTokenGeneratorImpl jwtTokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@RequestBody User user) throws UserIdAlreadyExistsException {

		return ResponseHandler.generateResponse("User created successfully", HttpStatus.CREATED,
				userService.createUser(user));
	}

	@GetMapping("/viewAll")
	public ResponseEntity<Object> getAllUsers() {

		List<User> list = userService.getAllUsers();
		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, list);

	}

	@GetMapping("/getByUserId/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable int userId) throws UserNotFoundException {

		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
				userService.getUserByUserId(userId));

	}
	

	@GetMapping("/{emailId}")
	public ResponseEntity<Object> getUserByEmailId(@PathVariable String emailId) throws UserNotFoundException {

		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
				userService.getUserByEmail(emailId));

	}

	@PutMapping("/updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody User user) throws UserNotFoundException {

		return ResponseHandler.generateResponse("User updated successfully", HttpStatus.OK,
				userService.updateUserByEmail(user));
	}

	@DeleteMapping("/{emailId}")
	public ResponseEntity<Object> deleteUserByEmailId(@PathVariable String emailId) throws UserNotFoundException {
		return ResponseHandler.generateResponse("User deleted successfully", HttpStatus.OK,
				userService.deleteUserByEmail(emailId));
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Login login) throws UserNotFoundException {
	    // Authenticate user
	    User user = userService.loginUser(login.getEmail(), login.getPassword());

	    // Generate JWT token
	    String token = jwtTokenGenerator.generateToken(user).get("token");

	    // Retrieve user details
	    Map<String, Object> claims = jwtTokenParser.parseToken(token);
	    String email = (String) claims.get("email");
	    int userId = userService.getUserByEmail(email).getUserId();
	    List<User> userList = userService.getUserByUserId(userId);

	    // Prepare response map
	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("userList", userList);
	    responseMap.put("token", token); // Add the token to the response

	    return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> findAllBasedOnRole(@RequestParam(value = "role", required = false) UserRole role) {

		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
				userService.getAllUsersBasedOnRole(role));

	}

	@PutMapping()
	public ResponseEntity<?> updateUserById(@RequestBody User user) throws UserNotFoundException {
		return ResponseHandler.generateResponse("User updated successfully", HttpStatus.OK,
				userService.updateUserById(user));
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteUserByEmailId(@PathVariable int id) throws UserNotFoundException {
		return ResponseHandler.generateResponse("User deleted successfully", HttpStatus.OK,
				userService.deleteUserById(id));
	}
}
