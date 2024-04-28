package com.proyectoi.kinetia.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	IUserRepository userRepository;
	
	public ArrayList<UserModel> getUsers(){
		return (ArrayList<UserModel>) userRepository.findAll();
	}
	
	public UserModel saveUser(UserModel user) {
		return userRepository.save(user);
	}
	
	public Optional<UserModel> getById(Long id) {
		return userRepository.findById(id);
	}
	
	public UserModel updateById(UserModel request, Long id ){
		UserModel user = userRepository.findById(id).get();
		user.setName(request.getName());
		userRepository.save(user);
		return user;		
	}
	
	public Boolean deleteUser(Long id) {
		try {
			this.userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
