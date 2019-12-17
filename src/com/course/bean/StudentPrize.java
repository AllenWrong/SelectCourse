package com.course.bean;

/**
 * 学生获奖信息表
 * @author Thingcor
 *
 */
public class StudentPrize extends TableHead{
	/** ID*/
	private int stuPrizeID;
	/** 该奖项对应的学生号*/
	private String stuPrizeStuNum;
	/** 奖项编号*/
	private String stuPrizeNum;
	/** 获奖类型*/
	private String stuPrizeType;
	/** 获奖描述*/
	private String stuPrizeDes;
	
	public StudentPrize() {
		
	}
	public StudentPrize(int stuPrizeID, String stuPrizeStuNum, String stuPrizeNum, String stuPrizeType,
			String stuPrizeDes) {
		super();
		this.stuPrizeID = stuPrizeID;
		this.stuPrizeStuNum = stuPrizeStuNum;
		this.stuPrizeNum = stuPrizeNum;
		this.stuPrizeType = stuPrizeType;
		this.stuPrizeDes = stuPrizeDes;
		this.setTableName("StudentPrize");
		this.setCount(5);
	}
	public int getStuPrizeID() {
		return stuPrizeID;
	}
	public void setStuPrizeID(int stuPrizeID) {
		this.stuPrizeID = stuPrizeID;
	}
	public String getStuPrizeStuNum() {
		return stuPrizeStuNum;
	}
	public void setStuPrizeStuNum(String stuPrizeStuNum) {
		this.stuPrizeStuNum = stuPrizeStuNum;
	}
	public String getStuPrizeNum() {
		return stuPrizeNum;
	}
	public void setStuPrizeNum(String stuPrizeNum) {
		this.stuPrizeNum = stuPrizeNum;
	}
	public String getStuPrizeType() {
		return stuPrizeType;
	}
	public void setStuPrizeType(String stuPrizeType) {
		this.stuPrizeType = stuPrizeType;
	}
	public String getStuPrizeDes() {
		return stuPrizeDes;
	}
	public void setStuPrizeDes(String stuPrizeDes) {
		this.stuPrizeDes = stuPrizeDes;
	}
}
