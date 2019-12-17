package com.course.bean;

/**
 * 课程信息表<br>
 * 对应数据库中的Course_Info表
 * @author Thingcor
 *
 */
public class CourseInfo extends TableHead{
	/** ID*/
	private int courID;
	/** 课程号*/
	private String courNum;
	/** 课程名*/
	private String courName;
	/** 课程学时*/
	private int courXs;
	/** 学分*/
	private int courRedit;
	/** 类型*/
	private String courType;
	/** 学期*/
	private String courTerm;
	/** 先行课程号*/
	private String courXxkNum;
	
	private String tpMajorNum;
	private String tpYear;
	private String tpGrade;
	public CourseInfo() {
		
	}

	public CourseInfo(int courID, String courNum, String courName, int courXs, int courRedit, String courType,
			String courTerm, String courXxkNum) {
		this.courID = courID;
		this.courNum = courNum;
		this.courName = courName;
		this.courXs = courXs;
		this.courRedit = courRedit;
		this.courType = courType;
		this.courTerm = courTerm;
		this.courXxkNum = courXxkNum;
		this.setCount(8);
		this.setTableName("CourseInfo");
	}

	public int getCourID() {
		return courID;
	}

	public void setCourID(int courID) {
		this.courID = courID;
	}

	public String getCourNum() {
		return courNum;
	}

	public void setCourNum(String courNum) {
		this.courNum = courNum;
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

	public String getCourTerm() {
		return courTerm;
	}

	public void setCourTerm(String courTerm) {
		this.courTerm = courTerm;
	}

	public String getCourXxkNum() {
		return courXxkNum;
	}

	public void setCourXxkNum(String courXxkNum) {
		this.courXxkNum = courXxkNum;
	}

	public String getTpMajorNum() {
		return tpMajorNum;
	}

	public void setTpMajorNum(String tpMajorNum) {
		this.tpMajorNum = tpMajorNum;
	}

	public String getTpYear() {
		return tpYear;
	}

	public void setTpYear(String tpYear) {
		this.tpYear = tpYear;
	}

	public String getTpGrade() {
		return tpGrade;
	}

	public void setTpGrade(String tpGrade) {
		this.tpGrade = tpGrade;
	}
	
}
