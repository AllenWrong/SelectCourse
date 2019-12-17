package com.course.bean;

import java.util.Date;

/**
 * 对应数据库中的studentinfo表
 * @author Thingcor
 *
 */
public class StuInfo extends TableHead{
	/** ID*/
	private int stuID;
	/** 学号*/
	private String stuNum;
	/** 学生姓名*/
	private String stuName;
	/** 密码*/
	private String stuPwd;
	/** 性别*/
	private String stuSex;
	/** 学生电话*/
	private String stuPhone;
	/** 生日*/
	private Date stuBirthday;
	/** 家庭地址*/
	private String stuHome;
	/** 专业*/
	private String stuMajor;
	/** 民族*/
	private String stuNation;
	/** 政治面貌*/
	private String stuPolicy;
	/** 入学时间*/
	private Date stuInschoolDate;
	/** 在籍状态*/
	private String stuState;
	/** 年级*/
	private String stuGrade;

	
	public String getStuGrade() {
		return stuGrade;
	}

	public String getStuState() {
		return stuState;
	}

	public StuInfo() {
	}

	public StuInfo(int stuID, String stuNum, String stuName, String stuPwd, String stuSex, String stuPhone,
			Date stuBirthday, String stuHome, String stuMajor, String stuNation, String stuPolicy, Date stuInschoolDate,
			String stuState) {
		this.stuID = stuID;
		this.stuNum = stuNum;
		this.stuName = stuName;
		this.stuPwd = stuPwd;
		this.stuSex = stuSex;
		this.stuPhone = stuPhone;
		this.stuBirthday = stuBirthday;
		this.stuHome = stuHome;
		this.stuMajor = stuMajor;
		this.stuNation = stuNation;
		this.stuPolicy = stuPolicy;
		this.stuInschoolDate = stuInschoolDate;
		this.stuState = stuState;
		this.setTableName("StuInfo");
		this.setCount(13);
	}

	public int getStuID() {
		return stuID;
	}

	public void setStuID(int stuID) {
		this.stuID = stuID;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public Date getStuBirthday() {
		return stuBirthday;
	}

	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	public String getStuHome() {
		return stuHome;
	}

	public void setStuHome(String stuHome) {
		this.stuHome = stuHome;
	}

	public String getStuMajor() {
		return stuMajor;
	}

	public void setStuMajor(String stuMajor) {
		this.stuMajor = stuMajor;
	}

	public String getStuNation() {
		return stuNation;
	}

	public void setStuNation(String stuNation) {
		this.stuNation = stuNation;
	}

	public String getStuPolicy() {
		return stuPolicy;
	}

	public void setStuPolicy(String stuPolicy) {
		this.stuPolicy = stuPolicy;
	}

	public Date getStuInschoolDate() {
		return stuInschoolDate;
	}

	public void setStuInschoolDate(Date stuInschoolDate) {
		this.stuInschoolDate = stuInschoolDate;
		this.stuGrade = this.stuInschoolDate.toString().split("-")[0];
	}

	public String isStuState() {
		return stuState;
	}

	public void setStuState(String stuState) {
		this.stuState = stuState;
	}
	
	public String getStuPwd() {
		return stuPwd;
	}

	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}

}
