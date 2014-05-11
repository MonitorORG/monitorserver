package com.luckyryan.sample.service;

import java.util.List;

import com.luckyryan.sample.dao.model.UserCommand;
import com.luckyryan.sample.exception.InvalidDataException;

public interface UserCommandService {
	public UserCommand saveCommand(UserCommand command) throws InvalidDataException;
	public UserCommand getCommand(Long id) throws InvalidDataException;
	
	public UserCommand getLastUnProcessCommand(String macAddress) throws InvalidDataException;
	
	public List<UserCommand> getAll(String macAddress) throws InvalidDataException;
	public List<UserCommand> findByIds(String ids) throws InvalidDataException;
}
