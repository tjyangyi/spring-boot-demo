package com.fhzz.springbootdemo.entity.master.security;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ROLE")

public class TRole implements java.io.Serializable {
	private static final long serialVersionUID = -8204496123246147819L;
	// Fields

	private String id;
	private String roleNumber;
	private String roleName;

	// Constructors

	/** default constructor */
	public TRole() {
	}

	/** minimal constructor */
	public TRole(String id) {
		this.id = id;
	}

	/** full constructor */
	public TRole(String id, String roleNumber, String roleName) {
		this.id = id;
		this.roleNumber = roleNumber;
		this.roleName = roleName;
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

	@Column(name = "ROLE_NUMBER")

	public String getRoleNumber() {
		return this.roleNumber;
	}

	public void setRoleNumber(String roleNumber) {
		this.roleNumber = roleNumber;
	}

	@Column(name = "ROLE_NAME")

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}