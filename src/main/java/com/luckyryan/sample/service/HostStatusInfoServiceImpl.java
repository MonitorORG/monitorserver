package com.luckyryan.sample.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyryan.sample.dao.HostStatusInfoDao;
import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.exception.InvalidUserException;

@Service("hostStatusInfoService")
public class HostStatusInfoServiceImpl implements HostStatusInfoService {

	@Autowired
	private HostStatusInfoDao dao;
	
	public HostStatusInfo saveInfo(HostStatusInfo info)
			throws InvalidUserException {
		
		if(info == null) {
            throw new InvalidUserException("Sorry Dave");
        }
		
		HostStatusInfo host = dao.getHostByMacAddress(info.getMacAddress());
		if (host != null && host.getId() != null) {
			info.setId(host.getId());
			info.setCreateDate(host.getCreateDate());
			info.setUpdateDate(new Date());
			
			if (info.getProcessStatusResults() == null) {	// send by broswer
				
			}
			
			if (info.getProcessList() == null) {	// send by agent
				
			}
			
//			if (!info.getProcessList().equals(host.getProcessList())) {	// Update process list
//				
//			}
			
		} else {
			info.setCreateDate(new Date());
			info.setUpdateDate(new Date());
		}
		
        return dao.save(info);
	}

	public HostStatusInfo getInfo(Long id) throws InvalidUserException {
		if(id == null) {
            throw new InvalidUserException("Sorry Dave");
        }
		
		return dao.findOne(id);
	}

	public HostStatusInfo getHostByMacAddress(String macAddress) throws InvalidUserException {
		
		return dao.getHostByMacAddress(macAddress);
	}

	public List<HostStatusInfo> getAll(Long userId) throws InvalidUserException {
		
		return dao.getAll();
	}
}
