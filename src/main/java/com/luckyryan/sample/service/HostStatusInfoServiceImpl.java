package com.luckyryan.sample.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyryan.sample.dao.HostStatusInfoDao;
import com.luckyryan.sample.dao.UserDao;
import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.dao.model.UserEntity;
import com.luckyryan.sample.exception.InvalidDataException;
import com.socket.server.util.HostStatus;
import com.socket.server.util.ProcessStatus;
import com.socket.server.util.StringUtil;

import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Propagation;

@Service("hostStatusInfoService")
public class HostStatusInfoServiceImpl implements HostStatusInfoService {

	@Autowired
	private HostStatusInfoDao dao;
	
	@Autowired
	private UserDao userDao;
	
	public HostStatusInfo saveInfo(HostStatusInfo pushedHost)
			throws InvalidDataException {
		
		if(pushedHost == null) {
            throw new InvalidDataException("Sorry Dave");
        }
		
		HostStatusInfo updateHost = dao.getHostByMacAddress(pushedHost.getMacAddress());
		
		if (updateHost != null && updateHost.getId() != null) {	
			// 1. Edit host status
			
			if (pushedHost.getIsAgentCommited() != null && pushedHost.getIsAgentCommited()) {
				// 1.1 pushed by host agent
				// mac address should not be changed
//				updateHost.setMacAddress(pushedHost.getMacAddress());
				
				updateHost.setHostname(pushedHost.getHostname());
				updateHost.setTotalMem(pushedHost.getTotalMem());
				updateHost.setFreeMem(pushedHost.getFreeMem());
				updateHost.setCpuTotalUsed(pushedHost.getCpuTotalUsed());
				updateHost.setCpuCount(pushedHost.getCpuCount());
				
				// process list should not be changed by agent
//				updateHost.setProcessList(pushedHost.getProcessList());				
				boolean isProcessListNotChanged = StringUtil.isEquals(pushedHost.getProcessList(), updateHost.getProcessList());
				if (isProcessListNotChanged) {
					// process status only changed when process list is not changed
					updateHost.setProcessStatusResults(pushedHost.getProcessStatusResults());
				}
				updateHost.setStatus(HostStatus.RUNNING);
				// set user id
				// set enable & disable
				
//				updateHost.setCreateDate(new Date());
				updateHost.setUpdateDate(new Date());
				
			} else {
				// 1.2 pushed by browser
				// mac address should not be changed
//				updateHost.setMacAddress(pushedHost.getMacAddress());
				// monitor info should not be changed by broswer
//				updateHost.setHostname(pushedHost.getHostname());
//				updateHost.setTotalMem(pushedHost.getTotalMem());
//				updateHost.setFreeMem(pushedHost.getFreeMem());
//				updateHost.setCpuTotalUsed(pushedHost.getCpuTotalUsed());
//				updateHost.setCpuCount(pushedHost.getCpuCount());
				boolean isProcessListChanged = !StringUtil.isEquals(pushedHost.getProcessList(), updateHost.getProcessList());
				updateHost.setProcessList(pushedHost.getProcessList());
				if (isProcessListChanged) {
					// all process status will be reseted when process list is changed
					StringBuffer initProcStaInfoBuf = new StringBuffer();
					for (String procName : StringUtil.getProcessArray(pushedHost.getProcessList())) {
						initProcStaInfoBuf.append(ProcessStatus.START_SYMBOL)
										  .append(ProcessStatus.WAITING)
										  .append(ProcessStatus.SEP_SYMBOL)
										  .append(procName)
										  .append(ProcessStatus.END_SYMBOL);
					}
					updateHost.setProcessStatusResults(initProcStaInfoBuf.toString());
				}
				
				updateHost.setStatus(HostStatus.UNINITIAL);
				// set user id
				// set enable & disable
				
//				updateHost.setCreateDate(new Date());
				updateHost.setUpdateDate(new Date());
			}
			
		} else {	 
			// 2. New host status
			
			updateHost = new HostStatusInfo();			
			if (pushedHost.getIsAgentCommited() != null && pushedHost.getIsAgentCommited()) {
				// 2.1 pushed by host agent				
				updateHost.setMacAddress(pushedHost.getMacAddress());
				
				updateHost.setHostname(pushedHost.getHostname());
				updateHost.setTotalMem(pushedHost.getTotalMem());
				updateHost.setFreeMem(pushedHost.getFreeMem());
				updateHost.setCpuTotalUsed(pushedHost.getCpuTotalUsed());
				updateHost.setCpuCount(pushedHost.getCpuCount());
				
				updateHost.setProcessList(StringUtil.EMPTY);
				updateHost.setProcessStatusResults(StringUtil.EMPTY);
				updateHost.setStatus(HostStatus.UNINITIAL);
				// set user id
				// set enable & disable
				
				updateHost.setCreateDate(new Date());
				updateHost.setUpdateDate(new Date());
				
			} else {
				// 2.2 pushed by browser
				// TODO This is not happened yet
				throw new InvalidDataException("Sorry Dave");
			}
		}
		return dao.save(updateHost);
		
		
//		HostStatusInfo orginalHost = dao.getHostByMacAddress(newHost.getMacAddress());
//		if (orginalHost != null && orginalHost.getId() != null) {	
//			// Edit
//			newHost.setId(orginalHost.getId());
//			newHost.setCreateDate(orginalHost.getCreateDate());
//			newHost.setUpdateDate(new Date());
//			
//			if (newHost.getIsAgentCommited() != null && newHost.getIsAgentCommited()) {
//				// don't update process list
//				boolean isProcessListChanged = !StringUtil.isEquals(newHost.getProcessList(), orginalHost.getProcessList());
//				newHost.setProcessList(orginalHost.getProcessList());
//				
//				if (isProcessListChanged) {
//					// don't update process status
//					newHost.setProcessStatusResults(orginalHost.getProcessStatusResults());
//				}
//			} else {
//				orginalHost.setProcessList(newHost.getProcessList());
//				orginalHost.setUpdateDate(new Date());
//				
//				StringBuffer initProcStaInfoBuf = new StringBuffer();
//				for (String procName : StringUtil.getProcessArray(newHost.getProcessList())) {
//					initProcStaInfoBuf.append(ProcessStatus.START_SYMBOL)
//									  .append(ProcessStatus.WAITING)
//									  .append(ProcessStatus.SEP_SYMBOL)
//									  .append(procName)
//									  .append(ProcessStatus.END_SYMBOL);
//				}
//				orginalHost.setProcessStatusResults(initProcStaInfoBuf.toString());
//				
//				newHost = orginalHost;
//			}
//			
//		} else {	
//			// Add
//			newHost.setCreateDate(new Date());
//			newHost.setUpdateDate(new Date());
//			
//			if (!StringUtil.isEmpty(newHost.getProcessList())) {
//				// TODO generate status by process list
//				// currently, the process list will always empty in create a new host info
//			}
//		}
//		
//        return dao.save(newHost);
	}

	public HostStatusInfo getInfo(Long id) throws InvalidDataException {
		if(id == null) {
            throw new InvalidDataException("Sorry Dave");
        }
		
		return dao.findOne(id);
	}

	public HostStatusInfo getHostByMacAddress(String macAddress) throws InvalidDataException {
		
		return dao.getHostByMacAddress(macAddress);
	}

	public List<HostStatusInfo> getAll(Long userId) throws InvalidDataException {
		
		return dao.getAll();
	}
	
	@Transactional//(readOnly = false, propagation = Propagation.REQUIRES_NEW)  
	public int updateDisconnectedHostStatus(String newStatus)  throws InvalidDataException {
		
		return dao.updateDisconnectedHostStatus(newStatus);
	}

	@Override
	public String deleteHostInfo(Long hostId) throws InvalidDataException {
		try {
			dao.delete(hostId);
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
		return "success";
	}
	
	public List<HostStatusInfo> getAllHostList() throws InvalidDataException {
		
		return dao.getAllHostList();
	}

	@Override
	public List<HostStatusInfo> getAssignedHostList(Long userId) throws InvalidDataException {
		
		return dao.getAssignedHostList(userId);
	}
	
	
	@Override
	public String assignUserToHost(Long userId, Long hostId) throws InvalidDataException {
		String result = "success";
		try {
			
			UserEntity user = userDao.findOne(userId);
			HostStatusInfo host = dao.findOne(hostId);
			
			if (user != null && host != null) {
				host.setUserId(userId);
				host.setUserFullname(user.getUsername());
				dao.save(host);
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
	public String enableHost(Long hostId, Boolean isEnable) throws InvalidDataException {
		String result = "success";
		try {
			HostStatusInfo host = dao.findOne(hostId);
			
			if (host != null) {
				host.setEnable(isEnable);
				dao.save(host);
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
