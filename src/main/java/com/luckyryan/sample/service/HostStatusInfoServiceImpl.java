package com.luckyryan.sample.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyryan.sample.dao.HostStatusInfoDao;
import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.exception.InvalidUserException;
import com.socket.server.util.ProcessStatus;
import com.socket.server.util.StringUtil;

@Service("hostStatusInfoService")
public class HostStatusInfoServiceImpl implements HostStatusInfoService {

	@Autowired
	private HostStatusInfoDao dao;
	
	public HostStatusInfo saveInfo(HostStatusInfo info)
			throws InvalidUserException {
		
		if(info == null) {
            throw new InvalidUserException("Sorry Dave");
        }
		
		HostStatusInfo orginal = dao.getHostByMacAddress(info.getMacAddress());
		if (orginal != null && orginal.getId() != null) {	
			// Edit
			info.setId(orginal.getId());
			info.setCreateDate(orginal.getCreateDate());
			info.setUpdateDate(new Date());
			
			System.out.println("IsCommited: " + info.getIsAgentCommited());
			System.out.println("ProcessList: " + info.getProcessList());
			System.out.println("OProcessList: " + orginal.getProcessList());
			System.out.println("ProcessStatusResults: " + info.getProcessStatusResults());
			if (info.getIsAgentCommited() != null && info.getIsAgentCommited()) {
				// don't update process list
				boolean isProcessListChanged = !StringUtil.isEquals(info.getProcessList(), orginal.getProcessList());
				info.setProcessList(orginal.getProcessList());
				
				System.out.println("isProcessListChanged: " + isProcessListChanged);
				if (isProcessListChanged) {
					// don't update process status
					info.setProcessStatusResults(orginal.getProcessStatusResults());
				}
			} else {
				orginal.setProcessList(info.getProcessList());
				orginal.setUpdateDate(new Date());
				
				StringBuffer initProcStaInfoBuf = new StringBuffer();
				for (String procName : StringUtil.getProcessArray(info.getProcessList())) {
					initProcStaInfoBuf.append(ProcessStatus.START_SYMBOL)
									  .append(ProcessStatus.STOP)
									  .append(ProcessStatus.SEP_SYMBOL)
									  .append(procName)
									  .append(ProcessStatus.END_SYMBOL);
				}
				orginal.setProcessStatusResults(initProcStaInfoBuf.toString());
				
				info = orginal;
			}
			
//			if (!StringUtil.isEmpty(info.getProcessList())) {
//				if (info.getIsAgentCommited() != null && info.getIsAgentCommited()) {	
//					// send by agent, if process list matched, then update process status, else dont change the list and status					
//					if (info.getProcessList().equals(orginal.getProcessList())) {
//						// the updated process status info is send by agent
//					} else {
//						info.setProcessList(orginal.getProcessList());
//						info.setProcessStatusResults(orginal.getProcessStatusResults());
//					}
//					
//				} else {  
//					// send by broswer, only update process list, and update status list (only add or del)
//					orginal.setProcessList(info.getProcessList());
//					orginal.setUpdateDate(new Date());
//					
//					StringBuffer initProcStaInfoBuf = new StringBuffer();
//					for (String procName : StringUtil.getProcessArray(info.getProcessList())) {
//						initProcStaInfoBuf.append(ProcessStatus.START_SYMBOL)
//										  .append(ProcessStatus.STOP)
//										  .append(ProcessStatus.SEP_SYMBOL)
//										  .append(procName)
//										  .append(ProcessStatus.END_SYMBOL);
//					}
//					orginal.setProcessStatusResults(initProcStaInfoBuf.toString());
//					
//					info = orginal;
//				}
//			} else if (!StringUtil.isEmpty(orginal.getProcessList())) {
//				info.setProcessList(orginal.getProcessList());
//				info.setProcessStatusResults(orginal.getProcessStatusResults());
//			}
			
		} else {	
			// Add
			info.setCreateDate(new Date());
			info.setUpdateDate(new Date());
			
			if (!StringUtil.isEmpty(info.getProcessList())) {
				// TODO generate status by process list
				// currently, the process list will always empty in create a new host info
			}
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
