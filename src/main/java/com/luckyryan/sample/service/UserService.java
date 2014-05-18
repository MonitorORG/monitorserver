package com.luckyryan.sample.service;

import com.luckyryan.sample.dao.model.UserEntity;

public interface UserService {
	
	 public String userLogin(UserEntity user);  
	 public UserEntity saveUser(UserEntity user);
	 
}
