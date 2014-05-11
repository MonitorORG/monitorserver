package com.luckyryan.sample.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyryan.sample.dao.UserCommandDao;
import com.luckyryan.sample.dao.model.UserCommand;
import com.luckyryan.sample.exception.InvalidDataException;
import com.socket.server.util.StringUtil;

@Service("userCommandService")
public class UserCommandServiceImpl implements UserCommandService {
	
	@Autowired
	private UserCommandDao dao;
	
	public UserCommand saveCommand(UserCommand command)
			throws InvalidDataException {
		
		command.setCreationDate(new Date());
		return dao.save(command);
	}

	public UserCommand getCommand(Long id) throws InvalidDataException {
		
		return dao.findOne(id);
	}

	public UserCommand getLastUnProcessCommand(String macAddress)
			throws InvalidDataException {
		
		UserCommand command = new UserCommand();
		List<UserCommand> commands = dao.getUnProcessCommand(macAddress); 
		if (commands.size() > 0) {
			command = commands.get(0);
		}
		return command;
	}

	public List<UserCommand> getAll(String macAddress)
			throws InvalidDataException {
		
		return dao.getAllByMacAddress(macAddress);
	}

	@Override
	public List<UserCommand> findByIds(String ids) throws InvalidDataException {
		
		List<Long> newIds = new ArrayList<Long>();
				
		if (StringUtil.isEmpty(ids)) {
			return new ArrayList<UserCommand>();
		}
		
		for(String id : ids.split(",")) {
			try {
				if (!StringUtil.isEmpty(id)) {
					newIds.add(Long.valueOf(id));
				}
			} catch (Exception e) {
				
			}
			
		}
		
		if (newIds.size() > 0) {
			List<UserCommand> list = dao.findByIds(newIds);
			return list;
		} else {
			return new ArrayList<UserCommand>();
		}
	}
	
}
