package com.fhzz.springbootdemo.core.quartz.entity;

// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * QrtzCronTriggers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QRTZ_CRON_TRIGGERS")

public class QrtzCronTriggers implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1493577339164328023L;
	private QrtzCronTriggersId id;
	private QrtzTriggers qrtzTriggers;
	private String cronExpression;
	private String timeZoneId;

	// Constructors

	/** default constructor */
	public QrtzCronTriggers() {
	}

	/** minimal constructor */
	public QrtzCronTriggers(QrtzCronTriggersId id, QrtzTriggers qrtzTriggers, String cronExpression) {
		this.id = id;
		this.qrtzTriggers = qrtzTriggers;
		this.cronExpression = cronExpression;
	}

	/** full constructor */
	public QrtzCronTriggers(QrtzCronTriggersId id, QrtzTriggers qrtzTriggers, String cronExpression,
			String timeZoneId) {
		this.id = id;
		this.qrtzTriggers = qrtzTriggers;
		this.cronExpression = cronExpression;
		this.timeZoneId = timeZoneId;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "schedName", column = @Column(name = "SCHED_NAME", nullable = false, length = 120)),
			@AttributeOverride(name = "triggerName", column = @Column(name = "TRIGGER_NAME", nullable = false, length = 200)),
			@AttributeOverride(name = "triggerGroup", column = @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)) })

	public QrtzCronTriggersId getId() {
		return this.id;
	}

	public void setId(QrtzCronTriggersId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "SCHED_NAME", referencedColumnName = "SCHED_NAME", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TRIGGER_NAME", referencedColumnName = "TRIGGER_NAME", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TRIGGER_GROUP", referencedColumnName = "TRIGGER_GROUP", nullable = false, insertable = false, updatable = false) })

	public QrtzTriggers getQrtzTriggers() {
		return this.qrtzTriggers;
	}

	public void setQrtzTriggers(QrtzTriggers qrtzTriggers) {
		this.qrtzTriggers = qrtzTriggers;
	}

	@Column(name = "CRON_EXPRESSION", nullable = false, length = 120)

	public String getCronExpression() {
		return this.cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	@Column(name = "TIME_ZONE_ID", length = 80)

	public String getTimeZoneId() {
		return this.timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

}