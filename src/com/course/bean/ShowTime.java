package com.course.bean;

/**
 * 上课时间
 * @author Thingcor
 *
 */
public class ShowTime  extends TableHead{
	private String classTime;
	
	public ShowTime() {
	}

	public ShowTime(String classTime) {
		this.classTime = classTime;
		this.setTableName("ShowTime");
		this.setCount(1);
	}

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}

	@Override
	public String toString() {
		return "ShowTime [classTime=" + classTime + "]";
	}
	
	
}
