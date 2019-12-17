package com.course.test;

import java.sql.Connection;
import java.util.ArrayList;

import com.course.bean.OrderCourseInfo;
import com.course.dao.OrderCourseInfoDao;
import com.course.jdbc.DatabaseConnection;

public class Test {
	public static void main(String[] args) {
		Connection connection = DatabaseConnection.getConnection("teacher", "12345", "course_manager");
		OrderCourseInfoDao orderCourseInfoDao = new OrderCourseInfoDao(connection);
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = orderCourseInfoDao.findByTeacher("16011001");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
}
