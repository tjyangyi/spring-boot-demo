package com.fhzz.springbootdemo.entity.master.attendance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TAttendanceRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ATTENDANCE_RECORD")

public class TAttendanceRecord implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -1512307125415859730L;
	private String id;
	private String username;
	private String targetUsername;
	private String loginIp;
	private String modifyDetailId;
	private Integer preB;
	private Integer newB;
	private Integer preC;
	private Integer newC;
	private Integer preG;
	private Integer newG;
	private Date modifyTime;

	// Constructors

	/** default constructor */
	public TAttendanceRecord() {
	}

	/** minimal constructor */
	public TAttendanceRecord(String id) {
		this.id = id;
	}

	/** full constructor */
	public TAttendanceRecord(String id, String username, String loginIp, String modifyDetailId, Integer preB,
			Integer newB, Integer preC, Integer newC, Integer preG, Integer newG, Date modifyTime) {
		this.id = id;
		this.username = username;
		this.loginIp = loginIp;
		this.modifyDetailId = modifyDetailId;
		this.preB = preB;
		this.newB = newB;
		this.preC = preC;
		this.newC = newC;
		this.preG = preG;
		this.newG = newG;
		this.modifyTime = modifyTime;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "ID", unique = true, nullable = false)

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USERNAME")

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "TARGET_USERNAME")

	public String getTargetUsername() {
		return targetUsername;
	}

	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}

	@Column(name = "LOGIN_IP", length = 64)

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "MODIFY_DETAIL_ID")

	public String getModifyDetailId() {
		return this.modifyDetailId;
	}

	public void setModifyDetailId(String modifyDetailId) {
		this.modifyDetailId = modifyDetailId;
	}

	@Column(name = "PRE_B", precision = 38, scale = 0)

	public Integer getPreB() {
		return this.preB;
	}

	public void setPreB(Integer preB) {
		this.preB = preB;
	}

	@Column(name = "NEW_B", precision = 38, scale = 0)

	public Integer getNewB() {
		return this.newB;
	}

	public void setNewB(Integer newB) {
		this.newB = newB;
	}

	@Column(name = "PRE_C", precision = 38, scale = 0)

	public Integer getPreC() {
		return this.preC;
	}

	public void setPreC(Integer preC) {
		this.preC = preC;
	}

	@Column(name = "NEW_C", precision = 38, scale = 0)

	public Integer getNewC() {
		return this.newC;
	}

	public void setNewC(Integer newC) {
		this.newC = newC;
	}

	@Column(name = "PRE_G", precision = 38, scale = 0)

	public Integer getPreG() {
		return this.preG;
	}

	public void setPreG(Integer preG) {
		this.preG = preG;
	}

	@Column(name = "NEW_G", precision = 38, scale = 0)

	public Integer getNewG() {
		return this.newG;
	}

	public void setNewG(Integer newG) {
		this.newG = newG;
	}

	@Column(name = "MODIFY_TIME", length = 7)

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}