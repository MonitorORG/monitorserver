package com.luckyryan.sample.dao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class HostStatusInfo {
	
	@Id
    @GeneratedValue
	private Long id;
	private int cpuCount;
	private double cpuTotalUsed;
	private double totalMem;
	private double freeMem;
	private String status;
	
	private String hostname;
	private String macAddress;
	
	private Date createDate;
	private Date updateDate;
	
	private String processList;
	private String processStatusResults;
	
	@Transient
	private Boolean isAgentCommited;	// null is default
	
	private boolean enable;
	private Long userId;
	private String userFullname;
	
	public String getUserFullname() {
		return userFullname;
	}
	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}
	@OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="hostId")
    private HostStatic host;
	
	
	public HostStatic getHost() {
		return host;
	}
	public void setHost(HostStatic host) {
		this.host = host;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
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
	public Boolean getIsAgentCommited() {
		return isAgentCommited;
	}
	public void setIsAgentCommited(Boolean isAgentCommited) {
		this.isAgentCommited = isAgentCommited;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
