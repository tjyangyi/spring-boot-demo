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
 * QrtzSimpleTriggers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QRTZ_SIMPLE_TRIGGERS")

public class QrtzSimpleTriggers implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -6364064854990762969L;
	private QrtzSimpleTriggersId id;
	private QrtzTriggers qrtzTriggers;
	private Integer repeatCount;
	private Long repeatInterval;
	private Long timesTriggered;

	// Constructors

	/** default constructor */
	public QrtzSimpleTriggers() {
	}

	/** full constructor */
	public QrtzSimpleTriggers(QrtzSimpleTriggersId id, QrtzTriggers qrtzTriggers, Integer repeatCount,
			Long repeatInterval, Long timesTriggered) {
		this.id = id;
		this.qrtzTriggers = qrtzTriggers;
		this.repeatCount = repeatCount;
		this.repeatInterval = repeatInterval;
		this.timesTriggered = timesTriggered;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "schedName", column = @Column(name = "SCHED_NAME", nullable = false, length = 120)),
			@AttributeOverride(name = "triggerName", column = @Column(name = "TRIGGER_NAME", nullable = false, length = 200)),
			@AttributeOverride(name = "triggerGroup", column = @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)) })

	public QrtzSimpleTriggersId getId() {
		return this.id;
	}

	public void setId(QrtzSimpleTriggersId id) {
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

	@Column(name = "REPEAT_COUNT", nullable = false, precision = 7, scale = 0)

	public Integer getRepeatCount() {
		return this.repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	@Column(name = "REPEAT_INTERVAL", nullable = false, precision = 12, scale = 0)

	public Long getRepeatInterval() {
		return this.repeatInterval;
	}

	public void setRepeatInterval(Long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	@Column(name = "TIMES_TRIGGERED", nullable = false, precision = 10, scale = 0)

	public Long getTimesTriggered() {
		return this.timesTriggered;
	}

	public void setTimesTriggered(Long timesTriggered) {
		this.timesTriggered = timesTriggered;
	}

}