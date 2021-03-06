package com.luckyryan.sample.transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luckyryan.sample.dao.model.HostStatusInfo;
import com.luckyryan.sample.dao.model.UserCommand;
import com.luckyryan.sample.dto.HostStatusInfoDTO;
import com.luckyryan.sample.dto.UserCommandDTO;


@Component
public class Transformer {

//    private DozerBeanMapper mapper = new DozerBeanMapper();
//	UserCommandDTO dto = mapper.map(info, UserCommandDTO.class);

    public HostStatusInfo dtoToHostInfo(HostStatusInfoDTO dto) {
    	HostStatusInfo info = new HostStatusInfo();
    	info.setId(dto.getId());
    	info.setHostname(dto.getHostname());
    	info.setTotalMem(dto.getTotalMem());
    	info.setFreeMem(dto.getFreeMem());
    	info.setCpuTotalUsed(dto.getCpuTotalUsed());
    	info.setCpuCount(dto.getCpuCount());
    	info.setMacAddress(dto.getMacAddress());
    	info.setProcessList(dto.getProcessList());
    	info.setProcessStatusResults(dto.getProcessStatusResults());  
    	info.setIsAgentCommited(dto.getIsAgentCommited());
    	
        return info;
    }

    public HostStatusInfoDTO hostInfoToDto(HostStatusInfo info) {
    	
    	HostStatusInfoDTO dto = new HostStatusInfoDTO();
    	dto.setId(info.getId());
    	dto.setHostname(info.getHostname());
    	dto.setTotalMem(info.getTotalMem());
    	dto.setFreeMem(info.getFreeMem());
    	dto.setCpuTotalUsed(info.getCpuTotalUsed());
    	dto.setCpuCount(info.getCpuCount());
    	dto.setMacAddress(info.getMacAddress());
    	dto.setProcessList(info.getProcessList());
    	dto.setProcessStatusResults(info.getProcessStatusResults());
    	dto.setStatus(info.getStatus());
    	
    	java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddhhmmss");  
    	if (info.getCreateDate() != null) {
    		try {
    			dto.setCreateDate(format.format(info.getCreateDate()));
    		} catch (Exception e) {
    			dto.setCreateDate("");
    		}
    	}
    	
    	if (info.getUpdateDate() != null) {
    		try {
    			dto.setUpdateDate(format.format(info.getUpdateDate()));
    		} catch (Exception e) {
    			dto.setUpdateDate("");
    		}
    	}
    	
        return dto;
    }
    
    public List<HostStatusInfoDTO> hostInfoListToDtoList(List<HostStatusInfo> infoList) {
    	List<HostStatusInfoDTO> dtoList = new ArrayList<HostStatusInfoDTO>();
    	for (HostStatusInfo info : infoList) {
    		
    		HostStatusInfoDTO dto = new HostStatusInfoDTO();
    		dto.setId(info.getId());
        	dto.setHostname(info.getHostname());
        	dto.setTotalMem(info.getTotalMem());
        	dto.setFreeMem(info.getFreeMem());
        	dto.setCpuTotalUsed(info.getCpuTotalUsed());
        	dto.setCpuCount(info.getCpuCount());
        	dto.setMacAddress(info.getMacAddress());
        	dto.setStatus(info.getStatus());
        	
        	dto.setProcessList(info.getProcessList());
        	dto.setProcessStatusResults(info.getProcessStatusResults());
        	
        	java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddhhmmss");  
        	if (info.getCreateDate() != null) {
        		try {
        			dto.setCreateDate(format.format(info.getCreateDate()));
        		} catch (Exception e) {
        			dto.setCreateDate("");
        		}
        	}
        	
        	if (info.getUpdateDate() != null) {
        		try {
        			dto.setUpdateDate(format.format(info.getUpdateDate()));
        		} catch (Exception e) {
        			dto.setUpdateDate("");
        		}
        	}
        	
        	dtoList.add(dto);
    	}
        return dtoList;
    }
    
    
    
    public Long stringToLong(String id) {
    	return Long.valueOf(id);
    }
    
    public String stringToStr(String str) {
    	return str;
    }
    
    public UserCommandDTO userCommandToDto(UserCommand cmd) {
    	
    	UserCommandDTO dto = new UserCommandDTO();
    	dto.setId(cmd.getId());
    	dto.setCommandStr(cmd.getCommandStr());
    	dto.setSecureCommandStr(cmd.getSecureCommandStr());
    	dto.setCreationDate(cmd.getCreationDate());
    	dto.setEndDate(cmd.getEndDate());
    	dto.setHostMacAddress(cmd.getHostMacAddress());
    	dto.setResultStr(cmd.getResultStr());
    	dto.setStatus(cmd.getStatus());    	
    	
        return dto;
    }
    
    public List<UserCommandDTO> userCmdListToDtoList(List<UserCommand> cmdList) {
    	List<UserCommandDTO> dtoList = new ArrayList<UserCommandDTO>();
    	for (UserCommand cmd : cmdList) {
    		
    		UserCommandDTO dto = new UserCommandDTO();
        	dto.setId(cmd.getId());
        	dto.setCommandStr(cmd.getCommandStr());
        	dto.setSecureCommandStr(cmd.getSecureCommandStr());
        	dto.setCreationDate(cmd.getCreationDate());
        	dto.setEndDate(cmd.getEndDate());
        	dto.setHostMacAddress(cmd.getHostMacAddress());
        	dto.setResultStr(cmd.getResultStr());
        	dto.setStatus(cmd.getStatus());    	
        	dtoList.add(dto);
    	}
        return dtoList;
    }
    
    public UserCommand dtoToUserCommand(UserCommandDTO dto) {
    	
    	UserCommand cmd = new UserCommand();
    	cmd.setId(dto.getId());
    	cmd.setCommandStr(dto.getCommandStr());
    	cmd.setCreationDate(dto.getCreationDate());
    	cmd.setEndDate(dto.getEndDate());
    	cmd.setHostMacAddress(dto.getHostMacAddress());
    	cmd.setResultStr(dto.getResultStr());
    	cmd.setStatus(dto.getStatus());    	
    	
        return cmd;
    }

}
