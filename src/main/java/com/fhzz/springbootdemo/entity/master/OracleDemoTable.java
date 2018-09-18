package com.fhzz.springbootdemo.entity.master;

// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * OracleDemoTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ORACLE_DEMO_TABLE")
public class OracleDemoTable implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 2156793562276654725L;
	private String id;
	private String content;
	private Double num;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public OracleDemoTable() {
	}

	/** minimal constructor */
	public OracleDemoTable(String id) {
		this.id = id;
	}

	/** full constructor */
	public OracleDemoTable(String id, String content, Double num, Timestamp createTime) {
		this.id = id;
		this.content = content;
		this.num = num;
		this.createTime = createTime;
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

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "NUM", precision = 0)
	public Double getNum() {
		return this.num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	@Column(name = "CREATE_TIME", length = 7)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}