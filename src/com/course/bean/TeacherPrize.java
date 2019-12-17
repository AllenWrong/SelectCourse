package com.course.bean;

/**
 * 对应数据库中的teacherprize表，表示教师获奖表
 * @author Thingcor
 *
 */
public class TeacherPrize extends TableHead{
	/** ID*/
	private int teaPriID;
	/** 教职工号*/
	private String teaPriTeaNum;
	/** 获奖编号*/
	private String teaPriNum;
	/** 获奖类型*/
	private String teaPriType;
	/** 获奖描述*/
	private String teaPriDes;

	/** 教师名<br>扩展*/
	private String teaName;
	public TeacherPrize() {
	}

	public TeacherPrize(int teaPriID, String teaPriTeaNum, String teaPriNum, String teaPriType, String teaPriDes) {
		this.teaPriID = teaPriID;
		this.teaPriTeaNum = teaPriTeaNum;
		this.teaPriNum = teaPriNum;
		this.teaPriType = teaPriType;
		this.teaPriDes = teaPriDes;
		this.setTableName("TeacherPrize");
		this.setCount(5);
	}

	public int getTeaPriID() {
		return teaPriID;
	}

	public void setTeaPriID(int teaPriID) {
		this.teaPriID = teaPriID;
	}

	public String getTeaPriTeaNum() {
		return teaPriTeaNum;
	}

	public void setTeaPriTeaNum(String teaPriTeaNum) {
		this.teaPriTeaNum = teaPriTeaNum;
	}

	public String getTeaPriNum() {
		return teaPriNum;
	}

	public void setTeaPriNum(String teaPriNum) {
		this.teaPriNum = teaPriNum;
	}

	public String getTeaPriType() {
		return teaPriType;
	}

	public void setTeaPriType(String teaPriType) {
		this.teaPriType = teaPriType;
	}

	public String getTeaPriDes() {
		return teaPriDes;
	}

	public void setTeaPriDes(String teaPriDes) {
		this.teaPriDes = teaPriDes;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	
}
