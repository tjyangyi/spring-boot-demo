package com.fhzz.springbootdemo.entity.master.attendance;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TAttendance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ATTENDANCE")
public class TAttendance implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8244247361790793610L;
	private String id;
	private String year;
	private String month;
	private String fileName;
	private String title;

	// Constructors

	/** default constructor */
	public TAttendance() {
	}

	/** minimal constructor */
	public TAttendance(String id) {
		this.id = id;
	}

	/** full constructor */
	public TAttendance(String id, String year, String month, String fileName, String title) {
		this.id = id;
		this.year = year;
		this.month = month;
		this.fileName = fileName;
		this.title = title;
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

	@Column(name = "YEAR", length = 8)

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "MONTH", length = 8)

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "FILE_NAME")

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "TITLE")

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}