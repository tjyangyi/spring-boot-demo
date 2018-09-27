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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * QrtzTriggers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QRTZ_TRIGGERS")

public class QrtzTriggers implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 8633671044749622473L;
	private QrtzTriggersId id;
	private QrtzJobDetails qrtzJobDetails;
	private String description;
	private Long nextFireTime;
	private Long prevFireTime;
	private Long priority;
	private String triggerState;
	private String triggerType;
	private Long startTime;
	private Long endTime;
	private String calendarName;
	private Byte misfireInstr;
	private byte[] jobData;
	private Set<QrtzCronTriggers> qrtzCronTriggerses = new HashSet<QrtzCronTriggers>(0);
	private Set<QrtzBlobTriggers> qrtzBlobTriggerses = new HashSet<QrtzBlobTriggers>(0);
	private Set<QrtzSimpropTriggers> qrtzSimpropTriggerses = new HashSet<QrtzSimpropTriggers>(0);
	private Set<QrtzSimpleTriggers> qrtzSimpleTriggerses = new HashSet<QrtzSimpleTriggers>(0);

	// Constructors

	/** default constructor */
	public QrtzTriggers() {
	}

	/** minimal constructor */
	public QrtzTriggers(QrtzTriggersId id, QrtzJobDetails qrtzJobDetails, String triggerState, String triggerType,
			Long startTime) {
		this.id = id;
		this.qrtzJobDetails = qrtzJobDetails;
		this.triggerState = triggerState;
		this.triggerType = triggerType;
		this.startTime = startTime;
	}

	/** full constructor */
	public QrtzTriggers(QrtzTriggersId id, QrtzJobDetails qrtzJobDetails, String description, Long nextFireTime,
			Long prevFireTime, Long priority, String triggerState, String triggerType, Long startTime, Long endTime,
			String calendarName, Byte misfireInstr, byte[] jobData, Set<QrtzCronTriggers> qrtzCronTriggerses,
			Set<QrtzBlobTriggers> qrtzBlobTriggerses, Set<QrtzSimpropTriggers> qrtzSimpropTriggerses,
			Set<QrtzSimpleTriggers> qrtzSimpleTriggerses) {
		this.id = id;
		this.qrtzJobDetails = qrtzJobDetails;
		this.description = description;
		this.nextFireTime = nextFireTime;
		this.prevFireTime = prevFireTime;
		this.priority = priority;
		this.triggerState = triggerState;
		this.triggerType = triggerType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.calendarName = calendarName;
		this.misfireInstr = misfireInstr;
		this.jobData = jobData;
		this.qrtzCronTriggerses = qrtzCronTriggerses;
		this.qrtzBlobTriggerses = qrtzBlobTriggerses;
		this.qrtzSimpropTriggerses = qrtzSimpropTriggerses;
		this.qrtzSimpleTriggerses = qrtzSimpleTriggerses;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "schedName", column = @Column(name = "SCHED_NAME", nullable = false, length = 120)),
			@AttributeOverride(name = "triggerName", column = @Column(name = "TRIGGER_NAME", nullable = false, length = 200)),
			@AttributeOverride(name = "triggerGroup", column = @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)) })

	public QrtzTriggersId getId() {
		return this.id;
	}

	public void setId(QrtzTriggersId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "SCHED_NAME", referencedColumnName = "SCHED_NAME", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "JOB_NAME", referencedColumnName = "JOB_NAME", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "JOB_GROUP", referencedColumnName = "JOB_GROUP", nullable = false, insertable = false, updatable = false) })

	public QrtzJobDetails getQrtzJobDetails() {
		return this.qrtzJobDetails;
	}

	public void setQrtzJobDetails(QrtzJobDetails qrtzJobDetails) {
		this.qrtzJobDetails = qrtzJobDetails;
	}

	@Column(name = "DESCRIPTION", length = 250)

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "NEXT_FIRE_TIME", precision = 13, scale = 0)

	public Long getNextFireTime() {
		return this.nextFireTime;
	}

	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	@Column(name = "PREV_FIRE_TIME", precision = 13, scale = 0)

	public Long getPrevFireTime() {
		return this.prevFireTime;
	}

	public void setPrevFireTime(Long prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	@Column(name = "PRIORITY", precision = 13, scale = 0)

	public Long getPriority() {
		return this.priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	@Column(name = "TRIGGER_STATE", nullable = false, length = 16)

	public String getTriggerState() {
		return this.triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	@Column(name = "TRIGGER_TYPE", nullable = false, length = 8)

	public String getTriggerType() {
		return this.triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	@Column(name = "START_TIME", nullable = false, precision = 13, scale = 0)

	public Long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", precision = 13, scale = 0)

	public Long getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Column(name = "CALENDAR_NAME", length = 200)

	public String getCalendarName() {
		return this.calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	@Column(name = "MISFIRE_INSTR", precision = 2, scale = 0)

	public Byte getMisfireInstr() {
		return this.misfireInstr;
	}

	public void setMisfireInstr(Byte misfireInstr) {
		this.misfireInstr = misfireInstr;
	}

	@Lob
	@Column(name = "JOB_DATA")

	public byte[] getJobData() {
		return this.jobData;
	}

	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qrtzTriggers")

	public Set<QrtzCronTriggers> getQrtzCronTriggerses() {
		return this.qrtzCronTriggerses;
	}

	public void setQrtzCronTriggerses(Set<QrtzCronTriggers> qrtzCronTriggerses) {
		this.qrtzCronTriggerses = qrtzCronTriggerses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qrtzTriggers")

	public Set<QrtzBlobTriggers> getQrtzBlobTriggerses() {
		return this.qrtzBlobTriggerses;
	}

	public void setQrtzBlobTriggerses(Set<QrtzBlobTriggers> qrtzBlobTriggerses) {
		this.qrtzBlobTriggerses = qrtzBlobTriggerses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qrtzTriggers")

	public Set<QrtzSimpropTriggers> getQrtzSimpropTriggerses() {
		return this.qrtzSimpropTriggerses;
	}

	public void setQrtzSimpropTriggerses(Set<QrtzSimpropTriggers> qrtzSimpropTriggerses) {
		this.qrtzSimpropTriggerses = qrtzSimpropTriggerses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qrtzTriggers")

	public Set<QrtzSimpleTriggers> getQrtzSimpleTriggerses() {
		return this.qrtzSimpleTriggerses;
	}

	public void setQrtzSimpleTriggerses(Set<QrtzSimpleTriggers> qrtzSimpleTriggerses) {
		this.qrtzSimpleTriggerses = qrtzSimpleTriggerses;
	}

}