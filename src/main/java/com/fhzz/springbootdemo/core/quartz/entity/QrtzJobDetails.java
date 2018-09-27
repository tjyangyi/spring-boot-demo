package com.fhzz.springbootdemo.core.quartz.entity;

// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * QrtzJobDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QRTZ_JOB_DETAILS")

public class QrtzJobDetails implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 4117458465954382318L;
	private QrtzJobDetailsId id;
	private String description;
	private String jobClassName;
	private String isDurable;
	private String isNonconcurrent;
	private String isUpdateData;
	private String requestsRecovery;
	private byte[] jobData;
	private Set<QrtzTriggers> qrtzTriggerses = new HashSet<QrtzTriggers>(0);

	// Constructors

	/** default constructor */
	public QrtzJobDetails() {
	}

	/** minimal constructor */
	public QrtzJobDetails(QrtzJobDetailsId id, String jobClassName, String isDurable, String isNonconcurrent,
			String isUpdateData, String requestsRecovery) {
		this.id = id;
		this.jobClassName = jobClassName;
		this.isDurable = isDurable;
		this.isNonconcurrent = isNonconcurrent;
		this.isUpdateData = isUpdateData;
		this.requestsRecovery = requestsRecovery;
	}

	/** full constructor */
	public QrtzJobDetails(QrtzJobDetailsId id, String description, String jobClassName, String isDurable,
			String isNonconcurrent, String isUpdateData, String requestsRecovery, byte[] jobData,
			Set<QrtzTriggers> qrtzTriggerses) {
		this.id = id;
		this.description = description;
		this.jobClassName = jobClassName;
		this.isDurable = isDurable;
		this.isNonconcurrent = isNonconcurrent;
		this.isUpdateData = isUpdateData;
		this.requestsRecovery = requestsRecovery;
		this.jobData = jobData;
		this.qrtzTriggerses = qrtzTriggerses;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "schedName", column = @Column(name = "SCHED_NAME", nullable = false, length = 120)),
			@AttributeOverride(name = "jobName", column = @Column(name = "JOB_NAME", nullable = false, length = 200)),
			@AttributeOverride(name = "jobGroup", column = @Column(name = "JOB_GROUP", nullable = false, length = 200)) })

	public QrtzJobDetailsId getId() {
		return this.id;
	}

	public void setId(QrtzJobDetailsId id) {
		this.id = id;
	}

	@Column(name = "DESCRIPTION", length = 250)

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "JOB_CLASS_NAME", nullable = false, length = 250)

	public String getJobClassName() {
		return this.jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	@Column(name = "IS_DURABLE", nullable = false, length = 1)

	public String getIsDurable() {
		return this.isDurable;
	}

	public void setIsDurable(String isDurable) {
		this.isDurable = isDurable;
	}

	@Column(name = "IS_NONCONCURRENT", nullable = false, length = 1)

	public String getIsNonconcurrent() {
		return this.isNonconcurrent;
	}

	public void setIsNonconcurrent(String isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}

	@Column(name = "IS_UPDATE_DATA", nullable = false, length = 1)

	public String getIsUpdateData() {
		return this.isUpdateData;
	}

	public void setIsUpdateData(String isUpdateData) {
		this.isUpdateData = isUpdateData;
	}

	@Column(name = "REQUESTS_RECOVERY", nullable = false, length = 1)

	public String getRequestsRecovery() {
		return this.requestsRecovery;
	}

	public void setRequestsRecovery(String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}

	@Lob
	@Column(name = "JOB_DATA")

	public byte[] getJobData() {
		return this.jobData;
	}

	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qrtzJobDetails")

	public Set<QrtzTriggers> getQrtzTriggerses() {
		return this.qrtzTriggerses;
	}

	public void setQrtzTriggerses(Set<QrtzTriggers> qrtzTriggerses) {
		this.qrtzTriggerses = qrtzTriggerses;
	}

}