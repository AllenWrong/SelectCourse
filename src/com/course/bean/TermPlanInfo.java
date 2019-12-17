package com.course.bean;

/**
 * 学期计划表
 * @author Thingcor
 *
 */
public class TermPlanInfo extends TableHead{
	/** ID*/
	private int termID;
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
	/** 专业名*/
	private String tpCourseName;
	/** 课程名*/
	private String tpMajorName;
	
	public TermPlanInfo() {
		
	}

	public TermPlanInfo(int termID, int tpTerm, String tpYear, String tpMajorNum, String tpGrade, String tpCourseNum) {
		this.termID = termID;
		this.tpTerm = tpTerm;
		this.tpYear = tpYear;
		this.tpMajorNum = tpMajorNum;
		this.tpGrade = tpGrade;
		this.tpCourseNum = tpCourseNum;
	}

	public int getTermID() {
		return termID;
	}

	public void setTermID(int termID) {
		this.termID = termID;
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
	
	public String getTpCourseName() {
		return tpCourseName;
	}

	public void setTpCourseName(String tpCourseName) {
		this.tpCourseName = tpCourseName;
	}

	public String getTpMajorName() {
		return tpMajorName;
	}

	public void setTpMajorName(String tpMajorName) {
		this.tpMajorName = tpMajorName;
	}
	
}
