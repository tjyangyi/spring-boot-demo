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
 * QrtzSimpropTriggers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QRTZ_SIMPROP_TRIGGERS")

public class QrtzSimpropTriggers implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 5106319312134615844L;
	private QrtzSimpropTriggersId id;
	private QrtzTriggers qrtzTriggers;
	private String strProp1;
	private String strProp2;
	private String strProp3;
	private Long intProp1;
	private Long intProp2;
	private Long longProp1;
	private Long longProp2;
	private Double decProp1;
	private Double decProp2;
	private String boolProp1;
	private String boolProp2;

	// Constructors

	/** default constructor */
	public QrtzSimpropTriggers() {
	}

	/** minimal constructor */
	public QrtzSimpropTriggers(QrtzSimpropTriggersId id, QrtzTriggers qrtzTriggers) {
		this.id = id;
		this.qrtzTriggers = qrtzTriggers;
	}

	/** full constructor */
	public QrtzSimpropTriggers(QrtzSimpropTriggersId id, QrtzTriggers qrtzTriggers, String strProp1, String strProp2,
			String strProp3, Long intProp1, Long intProp2, Long longProp1, Long longProp2, Double decProp1,
			Double decProp2, String boolProp1, String boolProp2) {
		this.id = id;
		this.qrtzTriggers = qrtzTriggers;
		this.strProp1 = strProp1;
		this.strProp2 = strProp2;
		this.strProp3 = strProp3;
		this.intProp1 = intProp1;
		this.intProp2 = intProp2;
		this.longProp1 = longProp1;
		this.longProp2 = longProp2;
		this.decProp1 = decProp1;
		this.decProp2 = decProp2;
		this.boolProp1 = boolProp1;
		this.boolProp2 = boolProp2;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "schedName", column = @Column(name = "SCHED_NAME", nullable = false, length = 120)),
			@AttributeOverride(name = "triggerName", column = @Column(name = "TRIGGER_NAME", nullable = false, length = 200)),
			@AttributeOverride(name = "triggerGroup", column = @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)) })

	public QrtzSimpropTriggersId getId() {
		return this.id;
	}

	public void setId(QrtzSimpropTriggersId id) {
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

	@Column(name = "STR_PROP_1", length = 512)

	public String getStrProp1() {
		return this.strProp1;
	}

	public void setStrProp1(String strProp1) {
		this.strProp1 = strProp1;
	}

	@Column(name = "STR_PROP_2", length = 512)

	public String getStrProp2() {
		return this.strProp2;
	}

	public void setStrProp2(String strProp2) {
		this.strProp2 = strProp2;
	}

	@Column(name = "STR_PROP_3", length = 512)

	public String getStrProp3() {
		return this.strProp3;
	}

	public void setStrProp3(String strProp3) {
		this.strProp3 = strProp3;
	}

	@Column(name = "INT_PROP_1", precision = 10, scale = 0)

	public Long getIntProp1() {
		return this.intProp1;
	}

	public void setIntProp1(Long intProp1) {
		this.intProp1 = intProp1;
	}

	@Column(name = "INT_PROP_2", precision = 10, scale = 0)

	public Long getIntProp2() {
		return this.intProp2;
	}

	public void setIntProp2(Long intProp2) {
		this.intProp2 = intProp2;
	}

	@Column(name = "LONG_PROP_1", precision = 13, scale = 0)

	public Long getLongProp1() {
		return this.longProp1;
	}

	public void setLongProp1(Long longProp1) {
		this.longProp1 = longProp1;
	}

	@Column(name = "LONG_PROP_2", precision = 13, scale = 0)

	public Long getLongProp2() {
		return this.longProp2;
	}

	public void setLongProp2(Long longProp2) {
		this.longProp2 = longProp2;
	}

	@Column(name = "DEC_PROP_1", precision = 13, scale = 4)

	public Double getDecProp1() {
		return this.decProp1;
	}

	public void setDecProp1(Double decProp1) {
		this.decProp1 = decProp1;
	}

	@Column(name = "DEC_PROP_2", precision = 13, scale = 4)

	public Double getDecProp2() {
		return this.decProp2;
	}

	public void setDecProp2(Double decProp2) {
		this.decProp2 = decProp2;
	}

	@Column(name = "BOOL_PROP_1", length = 1)

	public String getBoolProp1() {
		return this.boolProp1;
	}

	public void setBoolProp1(String boolProp1) {
		this.boolProp1 = boolProp1;
	}

	@Column(name = "BOOL_PROP_2", length = 1)

	public String getBoolProp2() {
		return this.boolProp2;
	}

	public void setBoolProp2(String boolProp2) {
		this.boolProp2 = boolProp2;
	}

}