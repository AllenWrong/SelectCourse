package com.course.bean;

import java.util.Date;

/**
 * 对应数据库中的teacherinfor表
 * @author Thingcor
 *
 */
public class TeacherInfor extends TableHead{
	/** ID*/
	private int teaID;
	/** 教职工号*/
	private String teaNum;
	/** 姓名*/
	private String teaName;
	/** 密码*/
	private String teaPwd;
	/** 性别*/
	private String teaSex;
	/** 电话*/
	private String teaPhone;
	/** 生日*/
	private Date teaBirthday;
	/** 家庭住址*/
	private String teaHome;
	/** 专业*/
	private String teaMajor;
	/** 民族*/
	private String teaNation;
	/** 政治面貌*/
	private String teaPolicy;
	/** 入职时间*/
	private Date teaInschoolTime;
	/** 学历*/
	private String teaXl;
	/** 职称	*/
	private String teaTitle;

	public TeacherInfor() {
	}

	public TeacherInfor(int teaID, String teaNum, String teaName, String teaPwd, String teaSex, String teaPhone, Date teaBirthday,
			String teaHome, String teaMajor, String teaNation, String teaPolicy, Date teaInschoolTime, String teaXl,
			String teaTitle) {
		super();
		this.teaID = teaID;
		this.teaNum = teaNum;
		this.teaName = teaName;
		this.teaPwd = teaPwd;
		this.teaSex = teaSex;
		this.teaPhone = teaPhone;
		this.teaBirthday = teaBirthday;
		this.teaHome = teaHome;
		this.teaMajor = teaMajor;
		this.teaNation = teaNation;
		this.teaPolicy = teaPolicy;
		this.teaInschoolTime = teaInschoolTime;
		this.teaXl = teaXl;
		this.teaTitle = teaTitle;
		this.setTableName("TeacherInfor");
		this.setCount(13);
	}

	public int getTeaID() {
		return teaID;
	}

	public void setTeaID(int teaID) {
		this.teaID = teaID;
	}

	public String getTeaNum() {
		return teaNum;
	}

	public void setTeaNum(String teaNum) {
		this.teaNum = teaNum;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getTeaSex() {
		return teaSex;
	}

	public void setTeaSex(String teaSex) {
		this.teaSex = teaSex;
	}

	public String getTeaPhone() {
		return teaPhone;
	}

	public void setTeaPhone(String teaPhone) {
		this.teaPhone = teaPhone;
	}

	public Date getTeaBirthday() {
		return teaBirthday;
	}

	public void setTeaBirthday(Date teaBirthday) {
		this.teaBirthday = teaBirthday;
	}

	public String getTeaHome() {
		return teaHome;
	}

	public void setTeaHome(String teaHome) {
		this.teaHome = teaHome;
	}

	public String getTeaMajor() {
		return teaMajor;
	}

	public void setTeaMajor(String teaMajor) {
		this.teaMajor = teaMajor;
	}

	public String getTeaNation() {
		return teaNation;
	}

	public void setTeaNation(String teaNation) {
		this.teaNation = teaNation;
	}

	public String getTeaPolicy() {
		return teaPolicy;
	}

	public void setTeaPolicy(String teaPolicy) {
		this.teaPolicy = teaPolicy;
	}

	public Date getTeaInschoolTime() {
		return teaInschoolTime;
	}

	public void setTeaInschoolTime(Date teaInschoolTime) {
		this.teaInschoolTime = teaInschoolTime;
	}

	public String getTeaXl() {
		return teaXl;
	}

	public void setTeaXl(String teaXl) {
		this.teaXl = teaXl;
	}

	public String getTeaTitle() {
		return teaTitle;
	}

	public void setTeaTitle(String teaTitle) {
		this.teaTitle = teaTitle;
	}

	public String getTeaPwd() {
		return teaPwd;
	}

	public void setTeaPwd(String teaPwd) {
		this.teaPwd = teaPwd;
	}

	@Override
	public String toString() {
		return "TeacherInfor [teaID=" + teaID + ", teaNum=" + teaNum + ", teaName=" + teaName + ", teaSex=" + teaSex
				+ ", teaPhone=" + teaPhone + ", teaBirthday=" + teaBirthday + ", teaHome=" + teaHome + ", teaMajor="
				+ teaMajor + ", teaNation=" + teaNation + ", teaPolicy=" + teaPolicy + ", teaInschoolTime="
				+ teaInschoolTime + ", teaXl=" + teaXl + ", teaTitle=" + teaTitle + "]";
	}

	
	
}
