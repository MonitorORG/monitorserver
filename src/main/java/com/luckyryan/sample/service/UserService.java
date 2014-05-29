package com.luckyryan.sample.service;

import java.util.List;

import com.luckyryan.sample.dao.model.UserEntity;
import com.luckyryan.sample.exception.InvalidDataException;

public interface UserService {
	
	 public String userLogin(UserEntity user);  
	 public UserEntity saveUser(UserEntity user);
	 
	 public List<UserEntity> findAll() throws InvalidDataException;
	 public String deleteUser(Long userId) throws InvalidDataException;
	 public String enableUser(Long userId, Boolean isEnable) throws InvalidDataException;
	 public String changeUserPwd(Long userId, String newPwd) throws InvalidDataException;
	 public UserEntity findUserByUsername(String username) throws InvalidDataException;
}
