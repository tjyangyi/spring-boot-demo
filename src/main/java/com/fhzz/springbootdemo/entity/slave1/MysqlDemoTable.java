package com.fhzz.springbootdemo.entity.slave1;

// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MysqlDemoTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mysql_demo_table", catalog = "medb")
public class MysqlDemoTable implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1891882130924663781L;
	private String id;
	private String content;
	private Integer num;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public MysqlDemoTable() {
	}

	/** minimal constructor */
	public MysqlDemoTable(String id, String content) {
		this.id = id;
		this.content = content;
	}

	/** full constructor */
	public MysqlDemoTable(String id, String content, Integer num, Timestamp createTime) {
		this.id = id;
		this.content = content;
		this.num = num;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "create_time", length = 26)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}