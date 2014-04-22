package com.luckyryan.sample.dao.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class HostStatic {

    @Id
    @GeneratedValue
    private Long id;

    private String hostName;
    private String macAddress;
    private String ip;
    private int port;
    
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userId")
    private UserEntity user;
    
    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="hostInfoId")
    private HostStatusInfo hostInfo;

    
	public HostStatusInfo getHostInfo() {
		return hostInfo;
	}

	public void setHostInfo(HostStatusInfo hostInfo) {
		this.hostInfo = hostInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}    
}
