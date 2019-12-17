package com.course.bean;

/**
 * 专业计划表，即培养计划
 * @author Thingcor
 *
 */
public class MajorPlanInfo extends TableHead{
	/** ID*/
	private int majorPlanID;
	/** 专业计划编号*/
	private String majorPlanNum;
	/** 专业计划名*/
	private String majorPlanName;
	/** 所属年级*/
	private String majorPlanGrade;
	/** 专业必修学分*/
	private int majorPlanComcre;
	/** 专业选修学分*/
	private int majorPlanElcre;

	public MajorPlanInfo() {
	}

	public MajorPlanInfo(int majorPlanID, String majorPlanNum, String majorPlanName, String majorPlanGrade,
			int majorPlanComcre, int majorPlanElcre) {
		this.majorPlanID = majorPlanID;
		this.majorPlanNum = majorPlanNum;
		this.majorPlanName = majorPlanName;
		this.majorPlanGrade = majorPlanGrade;
		this.majorPlanComcre = majorPlanComcre;
		this.majorPlanElcre = majorPlanElcre;
		this.setCount(6);
		this.setTableName("MajorPlanInfo");
	}

	public int getMajorPlanID() {
		return majorPlanID;
	}

	public void setMajorPlanID(int majorPlanID) {
		this.majorPlanID = majorPlanID;
	}

	public String getMajorPlanNum() {
		return majorPlanNum;
	}

	public void setMajorPlanNum(String majorPlanNum) {
		this.majorPlanNum = majorPlanNum;
	}

	public String getMajorPlanName() {
		return majorPlanName;
	}

	public void setMajorPlanName(String majorPlanName) {
		this.majorPlanName = majorPlanName;
	}

	public String getMajorPlanGrade() {
		return majorPlanGrade;
	}

	public void setMajorPlanGrade(String majorPlanGrade) {
		this.majorPlanGrade = majorPlanGrade;
	}

	public int getMajorPlanComcre() {
		return majorPlanComcre;
	}

	public void setMajorPlanComcre(int majorPlanComcre) {
		this.majorPlanComcre = majorPlanComcre;
	}

	public int getMajorPlanElcre() {
		return majorPlanElcre;
	}

	public void setMajorPlanElcre(int majorPlanElcre) {
		this.majorPlanElcre = majorPlanElcre;
	}

}
