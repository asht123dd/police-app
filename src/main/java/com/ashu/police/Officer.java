package com.ashu.police;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Officer {
	
	@Id
	@Column
	private long officerId;
	@Column
	private String name;
	@Column
	private String location;
	@Column
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	List<AppUserRole> appUserRoles;
	public long getOfficerId() {
		return officerId;
	}
	public void setOfficerId(long officerId) {
		this.officerId = officerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AppUserRole> getAppUserRoles() {
		return appUserRoles;
	}
	public void setAppUserRoles(List<AppUserRole> appUserRoles) {
		this.appUserRoles = appUserRoles;
	}

}
