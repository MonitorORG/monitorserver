package com.luckyryan.sample.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyryan.sample.dao.UserDao;
import com.luckyryan.sample.dao.model.UserEntity;
import com.luckyryan.sample.exception.InvalidDataException;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public String userLogin(UserEntity user) {
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		
		if (currentUser != null && currentUser.getPassword().equals(user.getPassword())) {
            return "success";  
        } else {  
            return "false";  
        }  
	}
	
	public UserEntity saveUser(UserEntity user)
			throws InvalidDataException {
		
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		return userDao.save(user);
	}

	
}
