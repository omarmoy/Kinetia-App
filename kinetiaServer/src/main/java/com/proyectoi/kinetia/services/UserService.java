package com.proyectoi.kinetia.services;

import java.util.ArrayList;
import java.util.Optional;

import com.proyectoi.kinetia.repositories.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	IUserRepository userRepository;

	@Autowired
	IRolRepository rolRepository;
	
	public ArrayList<UserModel> getUsers(){
		return (ArrayList<UserModel>) userRepository.findAll();
	}

	public UserModel saveUser(UserModel user) {

		user.setRol(rolRepository.findByRolType(user.getRol().getRolType()));

		return userRepository.save(user);
	}
	
	public Optional<UserModel> getById(Long id) {
		return userRepository.findById(id);
	}
	
	public UserModel updateById(UserModel request, Long id ){
		// TODO: no se contempla que entre un id que no exista en la bd
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
