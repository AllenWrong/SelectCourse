package com.course.bean;

/**
 * 学生成绩表
 * @author Thingcor
 *
 */
public class StuGrade extends TableHead{
	/** ID*/
	private int gradeID;
	/** 课程号*/
	private String gradeCouNum;
	/** 学号*/
	private String gradeStuNum;
	/** 成绩*/
	private int grade;
	/** 学分*/
	private int gradeCredit;
	
	/** 课程号<br>扩展*/
	private String couNum;
	/** 课程名<br>扩展*/
	private String couName;
	/** 课程类型<br>扩展*/
	private String couType;
	/** 学生名<br>扩展*/
	private String stuName;
	
	public StuGrade() {
		
	}

	public StuGrade(int gradeID, String gradeCouNum, String gradeStuNum, int grade, int gradeCredit) {
		this.gradeID = gradeID;
		this.gradeCouNum = gradeCouNum;
		this.gradeStuNum = gradeStuNum;
		this.grade = grade;
		this.gradeCredit = gradeCredit;
		this.setTableName("StuGrade");
		this.setCount(5);
	}

	public int getGradeID() {
		return gradeID;
	}

	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}

	public String getGradeCouNum() {
		return gradeCouNum;
	}

	public void setGradeCouNum(String gradeCouNum) {
		this.gradeCouNum = gradeCouNum;
	}

	public String getGradeStuNum() {
		return gradeStuNum;
	}

	public void setGradeStuNum(String gradeStuNum) {
		this.gradeStuNum = gradeStuNum;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGradeCredit() {
		return gradeCredit;
	}

	public void setGradeCredit(int gradeCredit) {
		this.gradeCredit = gradeCredit;
	}

	public String getCouNum() {
		return couNum;
	}

	public void setCouNum(String couNum) {
		this.couNum = couNum;
	}

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	public String getCouType() {
		return couType;
	}

	public void setCouType(String couType) {
		this.couType = couType;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
}
