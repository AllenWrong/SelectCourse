package com.course.bean;

public class ArrgCourView {
	/** 学期号 [1-2]*/
	private int tpTerm;
	/** 学年*/
	private String tpYear;
	/** 专业号*/
	private String tpMajorNum;
	/** 年级*/
	private String tpGrade;
	/** 课程号*/
	private String tpCourseNum;
	/** 课程名*/
	private String courName;
	/** 课程学时*/
	private int courXs;
	/** 学分*/
	private int courRedit;
	/** 类型*/
	private String courType;
	
	public ArrgCourView() {
	}

	public int getTpTerm() {
		return tpTerm;
	}

	public void setTpTerm(int tpTerm) {
		this.tpTerm = tpTerm;
	}

	public String getTpYear() {
		return tpYear;
	}

	public void setTpYear(String tpYear) {
		this.tpYear = tpYear;
	}

	public String getTpMajorNum() {
		return tpMajorNum;
	}

	public void setTpMajorNum(String tpMajorNum) {
		this.tpMajorNum = tpMajorNum;
	}

	public String getTpGrade() {
		return tpGrade;
	}

	public void setTpGrade(String tpGrade) {
		this.tpGrade = tpGrade;
	}

	public String getTpCourseNum() {
		return tpCourseNum;
	}

	public void setTpCourseNum(String tpCourseNum) {
		this.tpCourseNum = tpCourseNum;
	}

	public String getCourName() {
		return courName;
	}

	public void setCourName(String courName) {
		this.courName = courName;
	}

	public int getCourXs() {
		return courXs;
	}

	public void setCourXs(int courXs) {
		this.courXs = courXs;
	}

	public int getCourRedit() {
		return courRedit;
	}

	public void setCourRedit(int courRedit) {
		this.courRedit = courRedit;
	}

	public String getCourType() {
		return courType;
	}

	public void setCourType(String courType) {
		this.courType = courType;
	}
	
}
