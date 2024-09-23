package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.enums.UserRole;
import com.example.demo.response.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserIdAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) throws UserIdAlreadyExistsException {
		if (userRepository.existsById(user.getUserId())) {
			throw new UserIdAlreadyExistsException("User with ID " + user.getUserId() + " already exists.");
		}
			user.setUserRole(UserRole.CUSTOMER);


		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public List<User> getUserByUserId(int userId) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElse(null);
		List<User> list = new ArrayList<User>();
		if (user != null) {

			
			list.add(user);
			return list;
		}

		throw new UserNotFoundException("User not found");
	}

	public User getUserByEmail(String emailId) throws UserNotFoundException {
		User user = userRepository.findByEmail(emailId).orElse(null);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

	public User deleteUserByEmail(String emailId) throws UserNotFoundException {
		User user = userRepository.findByEmail(emailId).orElse(null);
		if (user != null) {
			userRepository.delete(user);
			return user;
		} else {
			throw new UserNotFoundException("User not found");
		}

	}

	public User updateUserByEmail(User updatedUser) throws UserNotFoundException {

		User user = userRepository.findByEmail(updatedUser.getEmail()).orElse(null);
		if (user != null) {
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			user.setConfirmPassword(updatedUser.getConfirmPassword());
			return userRepository.save(user);

		} else {
			throw new UserNotFoundException("User not found");
		}

	}

	public User loginUser(String email, String password) throws UserNotFoundException {

		User user = userRepository.findByEmailAndPassword(email, password).orElse(null);
		if (user != null) {
			return user;

		} else {
			throw new UserNotFoundException("User not found");
		}

	}


	public Object getAllUsersBasedOnRole(UserRole role) {

		List<User> users = userRepository.findAllByUserRole(role);
		List<UserDto> userDtos = new ArrayList<>();
		users.forEach(e -> userDtos.add(UserDto.convertDto(e)));
		return userDtos;

	}

	public Object updateUserById(User user) throws UserNotFoundException {
		Optional<User> userObj = userRepository.findByUserId(user.getUserId());
		if(userObj.isEmpty())throw new UserNotFoundException("User id "+user.getUserId());
		if(Objects.nonNull(user.getName())){
			userObj.get().setName(user.getName());
		}
		if(Objects.nonNull(user.getDob())){
			userObj.get().setDob(user.getDob());
		}
		if( Objects.nonNull(user.getEmail())){
			userObj.get().setEmail(user.getEmail());
		}
		if(Objects.nonNull(user.getPhoneNumber())){
			userObj.get().setPhoneNumber(user.getPhoneNumber());
		}
		userRepository.save(userObj.get());
		return "Updated Successfully";
	}

	public Object deleteUserById(int id) throws UserNotFoundException {
		Optional<User> userObj = userRepository.findByUserId(id);
		if(userObj.isPresent()) userRepository.delete(userObj.get());
		else throw new UserNotFoundException("User not found");
		return "deleted..";
	}
}
