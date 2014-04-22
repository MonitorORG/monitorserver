package com.luckyryan.sample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.exception.InvalidUserException;

public interface HostStatusInfoDao extends CrudRepository<HostStatusInfo,Long> {
	@Query("select h from HostStatusInfo h where h.macAddress = :macAddress")  
	public HostStatusInfo getHostByMacAddress(@Param("macAddress") String macAddress) throws InvalidUserException;
	
	@Query("select h from HostStatusInfo h order by h.id desc")  
	public List<HostStatusInfo> getAll() throws InvalidUserException;
}
