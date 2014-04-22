package com.luckyryan.sample.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyryan.sample.dao.UserCommandDao;
import com.luckyryan.sample.dao.model.UserCommand;
import com.luckyryan.sample.exception.InvalidUserException;

@Service("userCommandService")
public class UserCommandServiceImpl implements UserCommandService {
	
	@Autowired
	private UserCommandDao dao;
	
	public UserCommand saveCommand(UserCommand command)
			throws InvalidUserException {
		
		command.setCreationDate(new Date());
		return dao.save(command);
	}

	public UserCommand getCommand(Long id) throws InvalidUserException {
		
		return dao.findOne(id);
	}

	public UserCommand getLastUnProcessCommand(String macAddress)
			throws InvalidUserException {
		
		UserCommand command = new UserCommand();
		List<UserCommand> commands = dao.getUnProcessCommand(macAddress); 
		if (commands.size() > 0) {
			command = commands.get(0);
		}
		return command;
	}

	public List<UserCommand> getAll(String macAddress)
			throws InvalidUserException {
		
		return dao.getAllByMacAddress(macAddress);
	}

	

}
