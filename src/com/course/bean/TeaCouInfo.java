package com.course.bean;

/**
 * (教师-课程)表
 * @author Thingcor
 *
 */
public class TeaCouInfo extends TableHead{
	/** ID*/
	private int teaCouID;
	/** 教师工号*/
	private String teaCouTeaNum;
	/** 课程号*/
	private String teaCourCourNum;
	
	/** 教师名<br>扩展*/
	private String teaName;
	/** 课程名<br>扩展*/
	private String couName;
	public TeaCouInfo() {
	}

	public TeaCouInfo(int teaCouID, String teaCouTeaNum, String teaCourCourNum) {
		this.teaCouID = teaCouID;
		this.teaCouTeaNum = teaCouTeaNum;
		this.teaCourCourNum = teaCourCourNum;
		this.setTableName("TeaCouInfo");
		this.setCount(3);
	}

	public int getTeaCouID() {
		return teaCouID;
	}

	public void setTeaCouID(int teaCouID) {
		this.teaCouID = teaCouID;
	}

	public String getTeaCouTeaNum() {
		return teaCouTeaNum;
	}

	public void setTeaCouTeaNum(String teaCouTeaNum) {
		this.teaCouTeaNum = teaCouTeaNum;
	}

	public String getTeaCourCourNum() {
		return teaCourCourNum;
	}

	public void setTeaCourCourNum(String teaCourCourNum) {
		this.teaCourCourNum = teaCourCourNum;
	}
	
	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	@Override
	public String toString() {
		return "TeaCouInfo [teaCouID=" + teaCouID + ", teaCouTeaNum=" + teaCouTeaNum + ", teaCourCourNum="
				+ teaCourCourNum + "]";
	}
	
}
