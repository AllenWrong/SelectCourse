package com.course.bean;

/**
 * 选课表
 * @author Thingcor
 *
 */
public class SelCourse extends TableHead{
	/** ID*/
	private int selectID;
	/** 学年*/
	private String selectYear;
	/** 年级*/
	private String selectGrade;
	/** 学期*/
	private String selectTerm;
	/** 教职工号*/
	private String selectTeaNum;
	/** 学号*/
	private String selectStuNum;
	/** 课程号*/
	private String selectCourseNum;
	/** 上课时间*/
	private String selTeaTime;
	/** 上课地点*/
	private String selTeaPlace;

	/** 学生专业<br>扩展*/
	private String stuMajor;
	/** 课程名<br>扩展*/
	private String couName;
	/** 教师名<br>扩展*/
	private String teaName;
	/** 学生名<br>扩展*/
	private String stuName;
	
	public SelCourse() {
	}

	public SelCourse(int selectID, String selectYear, String selectGrade, String selectTerm, String selectTeaNum,
			String selectStuNum, String selectCourseNum, String selTeaTime, String selTeaPlace) {
		this.selectID = selectID;
		this.selectYear = selectYear;
		this.selectGrade = selectGrade;
		this.selectTerm = selectTerm;
		this.selectTeaNum = selectTeaNum;
		this.selectStuNum = selectStuNum;
		this.selectCourseNum = selectCourseNum;
		this.selTeaTime = selTeaTime;
		this.selTeaPlace = selTeaPlace;
	}

	public int getSelectID() {
		return selectID;
	}

	public void setSelectID(int selectID) {
		this.selectID = selectID;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectGrade() {
		return selectGrade;
	}

	public void setSelectGrade(String selectGrade) {
		this.selectGrade = selectGrade;
	}

	public String getSelectTerm() {
		return selectTerm;
	}

	public void setSelectTerm(String selectTerm) {
		this.selectTerm = selectTerm;
	}

	public String getSelectTeaNum() {
		return selectTeaNum;
	}

	public void setSelectTeaNum(String selectTeaNum) {
		this.selectTeaNum = selectTeaNum;
	}

	public String getSelectStuNum() {
		return selectStuNum;
	}

	public void setSelectStuNum(String selectStuNum) {
		this.selectStuNum = selectStuNum;
	}

	public String getSelectCourseNum() {
		return selectCourseNum;
	}

	public void setSelectCourseNum(String selectCourseNum) {
		this.selectCourseNum = selectCourseNum;
	}

	public String getSelTeaTime() {
		return selTeaTime;
	}

	public void setSelTeaTime(String selTeaTime) {
		this.selTeaTime = selTeaTime;
	}

	public String getSelTeaPlace() {
		return selTeaPlace;
	}

	public void setSelTeaPlace(String selTeaPlace) {
		this.selTeaPlace = selTeaPlace;
	}

	public String getStuMajor() {
		return stuMajor;
	}

	public void setStuMajor(String stuMajor) {
		this.stuMajor = stuMajor;
	}

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
}
