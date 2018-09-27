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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * QrtzBlobTriggers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QRTZ_BLOB_TRIGGERS")

public class QrtzBlobTriggers implements java.io.Serializable {

	// Fields

	private QrtzBlobTriggersId id;
	private QrtzTriggers qrtzTriggers;
	private byte[] blobData;

	// Constructors

	/** default constructor */
	public QrtzBlobTriggers() {
	}

	/** minimal constructor */
	public QrtzBlobTriggers(QrtzBlobTriggersId id, QrtzTriggers qrtzTriggers) {
		this.id = id;
		this.qrtzTriggers = qrtzTriggers;
	}

	/** full constructor */
	public QrtzBlobTriggers(QrtzBlobTriggersId id, QrtzTriggers qrtzTriggers, byte[] blobData) {
		this.id = id;
		this.qrtzTriggers = qrtzTriggers;
		this.blobData = blobData;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "schedName", column = @Column(name = "SCHED_NAME", nullable = false, length = 120)),
			@AttributeOverride(name = "triggerName", column = @Column(name = "TRIGGER_NAME", nullable = false, length = 200)),
			@AttributeOverride(name = "triggerGroup", column = @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)) })

	public QrtzBlobTriggersId getId() {
		return this.id;
	}

	public void setId(QrtzBlobTriggersId id) {
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

	@Lob
	@Column(name = "BLOB_DATA")

	public byte[] getBlobData() {
		return this.blobData;
	}

	public void setBlobData(byte[] blobData) {
		this.blobData = blobData;
	}

}