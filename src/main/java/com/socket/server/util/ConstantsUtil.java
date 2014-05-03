package com.socket.server.util;

public class ConstantsUtil {

	// load in starting web application, and configed to spring by PropertyPlaceholderConfigurer
	private String agentDownloadPath;
	private String agentDownloadFilename;

	public String getAgentDownloadPath() {
		return agentDownloadPath;
	}

	public void setAgentDownloadPath(String agentDownloadPath) {
		this.agentDownloadPath = agentDownloadPath;
	}

	public String getAgentDownloadFilename() {
		return agentDownloadFilename;
	}

	public void setAgentDownloadFilename(String agentDownloadFilename) {
		this.agentDownloadFilename = agentDownloadFilename;
	}
	
}
