package com.course.bean;

import java.util.Date;

/**
 * 对应数据库中的teachprop，表示教师的著作相关信息
 * @author Thingcor
 *
 */
public class TeachProp extends TableHead{
	/** ID*/
	private int propID;
	/** 著作对应的教师工号*/
	private String propTeaNum;
	/** 著作名*/
	private String propName;
	/** 著作发表时间*/
	private Date propTime;
	/** 著作编号*/
	private String propNum;
	
	/** 教师名<br>扩展*/
	private String teaName;
		
	public TeachProp() {
	}

	public TeachProp(int propID, String propTeaNum, String propName, Date propTime, String propNum) {
		this.propID = propID;
		this.propTeaNum = propTeaNum;
		this.propName = propName;
		this.propTime = propTime;
		this.propNum = propNum;
		this.setTableName("TeachProp");
		this.setCount(5);
	}

	public int getPropID() {
		return propID;
	}

	public void setPropID(int propID) {
		this.propID = propID;
	}

	public String getPropTeaNum() {
		return propTeaNum;
	}

	public void setPropTeaNum(String propTeaNum) {
		this.propTeaNum = propTeaNum;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public Date getPropTime() {
		return propTime;
	}

	public void setPropTime(Date propTime) {
		this.propTime = propTime;
	}

	public String getPropNum() {
		return propNum;
	}

	public void setPropNum(String propNum) {
		this.propNum = propNum;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

}
