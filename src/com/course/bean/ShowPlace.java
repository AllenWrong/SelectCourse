package com.course.bean;

/**
 * 上课地点<br>
 * 枚举类，可选的上课地点假设是固定的<br>
 * 实例还没有设计
 * @author Thingcor
 *
 */
public class ShowPlace extends TableHead{
	private String classPlace;
	private int courseSize;
	public ShowPlace() {
		
	}
	
	public ShowPlace(String classPlace, int courseSize) {
		this.classPlace = classPlace;
		this.courseSize = courseSize;
	}

	public String getClassPlace() {
		return classPlace;
	}
	public void setClassPlace(String classPlace) {
		this.classPlace = classPlace;
	}

	public int getCourseSize() {
		return courseSize;
	}

	public void setCourseSize(int courseSize) {
		this.courseSize = courseSize;
	}
	
}
