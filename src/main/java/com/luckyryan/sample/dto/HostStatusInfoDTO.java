package com.luckyryan.sample.dto;

import java.util.Date;

public class HostStatusInfoDTO {

	private Long id;
	private String hostname;
	private int cpuCount;
	private double cpuTotalUsed;
	private double totalMem;
	private double freeMem;
	
	private String macAddress;

	private String createDate;
	private String updateDate;
	
	private String processList;
	private String processStatusResults;
	
	private String commandStr;
	
	public String getCommandStr() {
		return commandStr;
	}
	public void setCommandStr(String commandStr) {
		this.commandStr = commandStr;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getCpuCount() {
		return cpuCount;
	}
	public void setCpuCount(int cpuCount) {
		this.cpuCount = cpuCount;
	}
	public double getCpuTotalUsed() {
		return cpuTotalUsed;
	}
	public void setCpuTotalUsed(double cpuTotalUsed) {
		this.cpuTotalUsed = cpuTotalUsed;
	}
	public double getTotalMem() {
		return totalMem;
	}
	public void setTotalMem(double totalMem) {
		this.totalMem = totalMem;
	}
	public double getFreeMem() {
		return freeMem;
	}
	public void setFreeMem(double freeMem) {
		this.freeMem = freeMem;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getProcessList() {
		return processList;
	}
	public void setProcessList(String processList) {
		this.processList = processList;
	}
	public String getProcessStatusResults() {
		return processStatusResults;
	}
	public void setProcessStatusResults(String processStatusResults) {
		this.processStatusResults = processStatusResults;
	}	
	
}
