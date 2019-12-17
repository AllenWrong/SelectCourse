package com.course.bean;

import java.util.Date;

/**
 * 学生变动表
 * @author Thingcor
 *
 */
public class StudentStruction extends TableHead{
	/** ID*/
	private int actionID;
	/** 学生号*/
	private String actionStuNum;
	/** 变动日期Y-M-D*/
	private Date actionDate;
	/** 变动信息描述*/
	private String actionDes;
	
	/** 学生姓名<br>扩展*/
	private String stuName;
	
	public StudentStruction() {
		
	}

	public StudentStruction(int actionID, String actionStuNum, Date actionDate, String actionDes) {
		super();
		this.actionID = actionID;
		this.actionStuNum = actionStuNum;
		this.actionDate = actionDate;
		this.actionDes = actionDes;
		this.setTableName("StudentStruction");
		this.setCount(4);
	}

	public int getActionID() {
		return actionID;
	}

	public void setActionID(int actionID) {
		this.actionID = actionID;
	}

	public String getActionStuNum() {
		return actionStuNum;
	}

	public void setActionStuNum(String actionStuNum) {
		this.actionStuNum = actionStuNum;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionDes() {
		return actionDes;
	}

	public void setActionDes(String actionDes) {
		this.actionDes = actionDes;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
}
