package com.course.bean;

/**
 * 开课申请表
 * @author Thingcor
 *
 */

public class AskCourse extends TableHead{
	/** ID*/
	private int askCouID;
	/** 申请的教师号*/
	private String askCouTeaNum;
	/** 申请的课程号*/
	private String askCouCouNum;
	
	/** 教师名<br>扩展*/
	private String teaName;
	/** 课程名<br>扩展*/
	private String couName;
	public AskCourse() {
	}

	public AskCourse(int askCouID, String askCouTeaNum, String askCouCouNum) {
		this.askCouID = askCouID;
		this.askCouTeaNum = askCouTeaNum;
		this.askCouCouNum = askCouCouNum;
		this.setTableName("AskCourse");
		this.setCount(3);
	}

	public int getAskCouID() {
		return askCouID;
	}

	public void setAskCouID(int askCouID) {
		this.askCouID = askCouID;
	}

	public String getAskCouTeaNum() {
		return askCouTeaNum;
	}

	public void setAskCouTeaNum(String askCouTeaNum) {
		this.askCouTeaNum = askCouTeaNum;
	}

	public String getAskCouCouNum() {
		return askCouCouNum;
	}

	public void setAskCouCouNum(String askCouCouNum) {
		this.askCouCouNum = askCouCouNum;
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
		return "AskCourse [askCouID=" + askCouID + ", askCouTeaNum=" + askCouTeaNum + ", askCouCouNum=" + askCouCouNum
				+ "]";
	}
}
