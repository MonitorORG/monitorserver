package com.luckyryan.sample.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.luckyryan.sample.service.HostStatusInfoServiceImpl;
import com.socket.server.util.HostStatus;

public class AgentStatusJob {
	
	@Autowired
	private HostStatusInfoServiceImpl hostService;
	
	public void work() {
        System.out.println("Starting job");
        
        hostService.updateDisconnectedHostStatus(HostStatus.UNCONNECTED);
    }  
	
}
