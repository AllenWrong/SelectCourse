package com.course.bean;

/**
 * 排课表
 * @author Thingcor
 *
 */
public class OrderCourseInfo extends TableHead{
	/** ID*/
	private int ordCouID;
	/** 专业号*/
	private String ordCouMajorNum;
	/** 年级*/
	private String ordCouGrade;
	/** 学年*/
	private String ordCouYear;
	/** 学期*/
	private int ordCouTerm;
	/** 课程号*/
	private String ordCouCouNum;
	/** 教师工号*/
	private String ordCouTeaNum;
	/** 课程最大容量*/
	private int ordCouMax;
	/** 起止周*/
	private String ordCouQzz;
	/** 上课时间*/
	private String ordCouTeaTime;
	/** 上课地点*/
	private String ordCouTeaPlace;
	/** 专业计划名<br>扩展*/
	private String majorPlanName;
	/** 课程名<br>扩展*/
	private String couName;
	/** 教师名<br>扩展*/
	private String teaName;
	/** 课程类型<br>扩展*/
	private String couType;
	/** 学分<br>扩展*/
	private int couRedit;
	/** 学时<br>扩展*/
	private String couXs;

	public OrderCourseInfo() {
	}
	
	public OrderCourseInfo(int ordCouID, String ordCouMajorNum, String ordCouGrade, String ordCouYear, int ordCouTerm,
			String ordCouCouNum, String ordCouTeaNum, int ordCouMax, String ordCouQzz, String ordCouTeaTime,
			String ordCouTeaPlace) {
		this.ordCouID = ordCouID;
		this.ordCouMajorNum = ordCouMajorNum;
		this.ordCouGrade = ordCouGrade;
		this.ordCouYear = ordCouYear;
		this.ordCouTerm = ordCouTerm;
		this.ordCouCouNum = ordCouCouNum;
		this.ordCouTeaNum = ordCouTeaNum;
		this.ordCouMax = ordCouMax;
		this.ordCouQzz = ordCouQzz;
		this.ordCouTeaTime = ordCouTeaTime;
		this.ordCouTeaPlace = ordCouTeaPlace;
		this.setTableName("OrderCourseInfo");
		this.setCount(11);
	}

	public int getOrdCouID() {
		return ordCouID;
	}

	public void setOrdCouID(int ordCouID) {
		this.ordCouID = ordCouID;
	}

	public String getOrdCouMajorNum() {
		return ordCouMajorNum;
	}

	public void setOrdCouMajorNum(String ordCouMajorNum) {
		this.ordCouMajorNum = ordCouMajorNum;
	}

	public String getOrdCouGrade() {
		return ordCouGrade;
	}

	public void setOrdCouGrade(String ordCouGrade) {
		this.ordCouGrade = ordCouGrade;
	}

	public String getOrdCouYear() {
		return ordCouYear;
	}

	public void setOrdCouYear(String ordCouYear) {
		this.ordCouYear = ordCouYear;
	}

	public int getOrdCouTerm() {
		return ordCouTerm;
	}

	public void setOrdCouTerm(int ordCouTerm) {
		this.ordCouTerm = ordCouTerm;
	}

	public String getOrdCouCouNum() {
		return ordCouCouNum;
	}

	public void setOrdCouCouNum(String ordCouCouNum) {
		this.ordCouCouNum = ordCouCouNum;
	}

	public String getOrdCouTeaNum() {
		return ordCouTeaNum;
	}

	public void setOrdCouTeaNum(String ordCouTeaNum) {
		this.ordCouTeaNum = ordCouTeaNum;
	}

	public int getOrdCouMax() {
		return ordCouMax;
	}

	public void setOrdCouMax(int ordCouMax) {
		this.ordCouMax = ordCouMax;
	}

	public String getOrdCouQzz() {
		return ordCouQzz;
	}

	public void setOrdCouQzz(String ordCouQzz) {
		this.ordCouQzz = ordCouQzz;
	}

	public String getOrdCouTeaTime() {
		return ordCouTeaTime;
	}

	public void setOrdCouTeaTime(String ordCouTeaTime) {
		this.ordCouTeaTime = ordCouTeaTime;
	}

	public String getOrdCouTeaPlace() {
		return ordCouTeaPlace;
	}

	public void setOrdCouTeaPlace(String ordCouTeaPlace) {
		this.ordCouTeaPlace = ordCouTeaPlace;
	}

	public String getMajorPlanName() {
		return majorPlanName;
	}

	public void setMajorPlanName(String majorPlanName) {
		this.majorPlanName = majorPlanName;
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

	public String getCouType() {
		return couType;
	}

	public void setCouType(String couType) {
		this.couType = couType;
	}

	public int getCouRedit() {
		return couRedit;
	}

	public void setCouRedit(int couRedit) {
		this.couRedit = couRedit;
	}

	public String getCouXs() {
		return couXs;
	}

	public void setCouXs(String couXs) {
		this.couXs = couXs;
	}

	@Override
	public String toString() {
		return "OrderCourseInfo [ordCouGrade=" + ordCouGrade + ", ordCouYear=" + ordCouYear + ", ordCouTerm="
				+ ordCouTerm + ", ordCouCouNum=" + ordCouCouNum + ", ordCouTeaNum=" + ordCouTeaNum + ", ordCouTeaTime="
				+ ordCouTeaTime + "]";
	}

}
