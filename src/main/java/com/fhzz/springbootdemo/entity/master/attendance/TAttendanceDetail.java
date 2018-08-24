package com.fhzz.springbootdemo.entity.master.attendance;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TAttendanceDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ATTENDANCE_DETAIL")
public class TAttendanceDetail implements java.io.Serializable {
	private static final long serialVersionUID = 6781159313794951955L;

	private String id;
	private String attendaceId;
	private String username;
	private String realname;
	private String cardNumber;
	private Integer a;
	private Integer b;
	private Integer c;
	private Integer d;
	private Integer e;
	private Integer f;
	private Integer g;
	private Integer indexNumber;

	// Constructors

	/** default constructor */
	public TAttendanceDetail() {
	}

	/** minimal constructor */
	public TAttendanceDetail(String id) {
		this.id = id;
	}

	/** full constructor */
	public TAttendanceDetail(String id, String attendaceId, String username, String realname, String cardNumber,
			Integer a, Integer b, Integer c, Integer d, Integer e) {
		this.id = id;
		this.attendaceId = attendaceId;
		this.username = username;
		this.realname = realname;
		this.cardNumber = cardNumber;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
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

	@Column(name = "ATTENDACE_ID")

	public String getAttendaceId() {
		return this.attendaceId;
	}

	public void setAttendaceId(String attendaceId) {
		this.attendaceId = attendaceId;
	}

	@Column(name = "USERNAME", length = 16)

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "REALNAME", length = 32)

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "CARD_NUMBER", length = 32)

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name = "A", precision = 0)

	public Integer getA() {
		return this.a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	@Column(name = "B", precision = 0)

	public Integer getB() {
		return this.b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	@Column(name = "C", precision = 0)

	public Integer getC() {
		return this.c;
	}

	public void setC(Integer c) {
		this.c = c;
	}

	@Column(name = "D", precision = 0)

	public Integer getD() {
		return this.d;
	}

	public void setD(Integer d) {
		this.d = d;
	}

	@Column(name = "E", precision = 0)

	public Integer getE() {
		return this.e;
	}

	public void setE(Integer e) {
		this.e = e;
	}

	@Column(name = "F", precision = 0)

	public Integer getF() {
		return this.f;
	}

	public void setF(Integer f) {
		this.f = f;
	}

	@Column(name = "G", precision = 0)

	public Integer getG() {
		return this.g;
	}

	public void setG(Integer g) {
		this.g = g;
	}

	@Column(name = "INDEX_NUMBER", precision = 0)
	public Integer getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(Integer indexNumber) {
		this.indexNumber = indexNumber;
	}

}