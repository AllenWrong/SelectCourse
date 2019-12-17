package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.course.bean.ShowPlace;

public class ShowPlaceDao implements TableDao{
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rst ;
	public ShowPlaceDao(Connection conn) {
		this.conn = conn;
	}
	
//通过主键查找
	public ShowPlace findByMainKey(String mainKey) throws SQLException {
		ShowPlace n = new ShowPlace();
		String sql = "select * from showplace where classplace = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, mainKey);
		rst = pst.executeQuery();
		while(rst.next()) {
			n.setClassPlace(rst.getString("classplace"));
			n.setCourseSize(rst.getInt("course_size"));
		}
		return n;
	}


@Override
public boolean doCreate(Object object) throws Exception {
	if(object instanceof ShowPlace) {
		// 向下转型
		ShowPlace info = (ShowPlace)object;
		String sql = "insert into showplace values(?,?)";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getClassPlace());
		pst.setInt(2, info.getCourseSize());
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("ShowPlace添加执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是ShowPlace的父类");
		return false;
	}
	return true;
}


@Override
public boolean doUpdate(Object object) throws Exception {

	if(object instanceof ShowPlace) {
		// 向下转型
		ShowPlace info = (ShowPlace)object;
		String sql = "updata showplace set classplace= ?,?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getClassPlace());
		pst.setInt(2, info.getCourseSize());
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("ShowPlace修改执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是ShowPlace的父类");
		return false;
	}
	return true;

}


@Override
public boolean doDelete(Object object) throws Exception {

	if(object instanceof ShowPlace) {
		// 向下转型
		ShowPlace info = (ShowPlace)object;
		String sql = "delete from showplace where classplace = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getClassPlace());
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("ShowPlace删除执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是ShowPlace的父类");
		return false;
	}
	return true;

}


@Override
public ArrayList<ShowPlace> findAll() throws Exception {
	ArrayList<ShowPlace> list = new ArrayList<>();
	String sql = "select * from showplace";
	pst = conn.prepareStatement(sql);
	rst = pst.executeQuery();
	while(rst.next()) {
		ShowPlace n = new ShowPlace(rst.getString("classplace"),rst.getInt("course_size"));
		list.add(n);
	}
		return list;
}

}
