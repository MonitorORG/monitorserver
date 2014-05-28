package com.luckyryan.sample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.exception.InvalidDataException;

public interface HostStatusInfoDao extends CrudRepository<HostStatusInfo,Long> {
	@Query("select h from HostStatusInfo h where h.macAddress = :macAddress")  
	public HostStatusInfo getHostByMacAddress(@Param("macAddress") String macAddress) throws InvalidDataException;
	
	@Query("select h from HostStatusInfo h order by h.id desc")  
	public List<HostStatusInfo> getAll() throws InvalidDataException;
	
	@Modifying
	@Query("update HostStatusInfo h set h.status = :newStatus, h.processStatusResults = replace(h.processStatusResults, '*1', '*2') where (now()-h.updateDate) > '10 seconds'")
	public int updateDisconnectedHostStatus(@Param("newStatus") String newStatus);
	
	@Query("select h from HostStatusInfo h order by h.id desc")  
	public List<HostStatusInfo> getAllHostList() throws InvalidDataException;
	
	@Query("select h from HostStatusInfo h where h.userId is not null order by h.id desc")  
	public List<HostStatusInfo> getAssignedHostList() throws InvalidDataException;
	
}
