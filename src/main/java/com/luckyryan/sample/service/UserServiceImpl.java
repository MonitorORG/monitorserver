package com.luckyryan.sample.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyryan.sample.dao.UserDao;
import com.luckyryan.sample.dao.model.HostStatusInfo;
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
	
	public UserEntity findUserByUsername(String username) throws InvalidDataException {
		
		return userDao.findByUsername(username);
	}
	
	public List<UserEntity> findAll() throws InvalidDataException {
		
		return (List<UserEntity>)userDao.findAll();
	}

	@Override
	public String deleteUser(Long userId) throws InvalidDataException {
		try {
			userDao.delete(userId);
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
		return "success";
	}
	
	@Override
	public String enableUser(Long userId, Boolean isEnable) throws InvalidDataException {
		String result = "success";
		try {
			UserEntity user = userDao.findOne(userId);
			
			if (user != null) {
				user.setEnable(isEnable);
				userDao.save(user);
			} else {
				result = "Failed";
			}			
			
		} catch (Exception e) {
			result = "Error: " + e.getMessage();
		}
		
		System.out.println("SERVICE: result: " + result);
		
		return result;
	}
	
	@Override
	public String changeUserPwd(Long userId, String newPwd) throws InvalidDataException {
		String result = "success";
		try {
			UserEntity user = userDao.findOne(userId);
			
			if (user != null) {
				user.setPassword(newPwd);
				user.setConfirmPassword(newPwd);
				userDao.save(user);
			} else {
				result = "Failed";
			}			
			
		} catch (Exception e) {
			result = "Error: " + e.getMessage();
		}
		
		System.out.println("SERVICE: result: " + result);
		
		return result;
	}

}
