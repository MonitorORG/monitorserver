package com.luckyryan.sample.dao.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserCommand {
	@Id
    @GeneratedValue
    private Long id;
	
	/**@OneToOne(cascade=CascadeType.ALL)
	* @JoinTable( name="关联表名",
	* joinColumns = @JoinColumn(name="主表外键"),
	* inverseJoinColumns = @JoinColumns(name="从表外键")
	*/
	
//	private User user;
//	private Host host;
	
	private String hostMacAddress;
	
	private String commandStr;
	private Date creationDate;
	private Date endDate;
	private String status;
	
	@Column(name="resultStr",length=24000) 
	private String resultStr;
	
	private String endCommandClass;
	private String endCommandMethod;
	private String endCommandParam;
	
	 // optional=true：可选，表示此对象可以没有，可以为null；false表示必须存在
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userId")
	private UserEntity user;
    
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "hostId")
    private HostStatic host;
	
    public HostStatic getHost() {
		return host;
	}
	public void setHost(HostStatic host) {
		this.host = host;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCommandStr() {
		return commandStr;
	}
	public void setCommandStr(String commandStr) {
		this.commandStr = commandStr;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResultStr() {
		return resultStr;
	}
	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}
	public String getEndCommandClass() {
		return endCommandClass;
	}
	public void setEndCommandClass(String endCommandClass) {
		this.endCommandClass = endCommandClass;
	}
	public String getEndCommandMethod() {
		return endCommandMethod;
	}
	public void setEndCommandMethod(String endCommandMethod) {
		this.endCommandMethod = endCommandMethod;
	}
	public String getEndCommandParam() {
		return endCommandParam;
	}
	public void setEndCommandParam(String endCommandParam) {
		this.endCommandParam = endCommandParam;
	}
	public String getHostMacAddress() {
		return hostMacAddress;
	}
	public void setHostMacAddress(String hostMacAddress) {
		this.hostMacAddress = hostMacAddress;
	}
}
