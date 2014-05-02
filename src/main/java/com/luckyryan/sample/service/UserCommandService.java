package com.luckyryan.sample.service;

import java.util.List;

import com.luckyryan.sample.dao.model.UserCommand;
import com.luckyryan.sample.exception.InvalidUserException;

public interface UserCommandService {
	public UserCommand saveCommand(UserCommand command) throws InvalidUserException;
	public UserCommand getCommand(Long id) throws InvalidUserException;
	
	public UserCommand getLastUnProcessCommand(String macAddress) throws InvalidUserException;
	
	public List<UserCommand> getAll(String macAddress) throws InvalidUserException;
	public List<UserCommand> findByIds(String ids) throws InvalidUserException;
}
