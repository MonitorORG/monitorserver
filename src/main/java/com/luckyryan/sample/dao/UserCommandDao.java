package com.luckyryan.sample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.luckyryan.sample.dao.model.UserCommand;
import com.luckyryan.sample.exception.InvalidUserException;

public interface UserCommandDao extends CrudRepository<UserCommand,Long> {
	
	@Query("select u from UserCommand u where u.status = 'Created' and u.hostMacAddress = :macAddress order by u.creationDate asc")  
	public List<UserCommand> getUnProcessCommand(@Param("macAddress") String macAddress) throws InvalidUserException;
	
	@Query("select u from UserCommand u order by u.creationDate desc")  
	public List<UserCommand> getAll() throws InvalidUserException;
	
	@Query("select u from UserCommand u where u.hostMacAddress= :macAddress order by u.creationDate desc")  
	public List<UserCommand> getAllByMacAddress(@Param("macAddress") String macAddress) throws InvalidUserException;
}
