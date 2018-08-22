package com.fhzz.springbootdemo.entity.master.security;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_USER_ROLE")

public class TUserRole implements java.io.Serializable {
	private static final long serialVersionUID = -8939469863604431338L;
	// Fields

	private String id;
	private String userId;
	private String roleId;

	// Constructors

	/** default constructor */
	public TUserRole() {
	}

	/** minimal constructor */
	public TUserRole(String id) {
		this.id = id;
	}

	/** full constructor */
	public TUserRole(String id, String userId, String roleId) {
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors
	@Id

	@Column(name = "ID", unique = true, nullable = false)

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USER_ID")

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ROLE_ID")

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}