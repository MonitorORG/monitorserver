package com.luckyryan.sample.service;

import java.util.List;

import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.exception.InvalidUserException;

public interface HostStatusInfoService {
	public HostStatusInfo saveInfo(HostStatusInfo user) throws InvalidUserException;
	public HostStatusInfo getInfo(Long id) throws InvalidUserException;
	public HostStatusInfo getHostByMacAddress(String macAddress) throws InvalidUserException;
	
	public List<HostStatusInfo> getAll(Long userId) throws InvalidUserException;
}
