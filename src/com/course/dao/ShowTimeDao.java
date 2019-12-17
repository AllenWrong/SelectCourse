package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.course.bean.ShowTime;

public class ShowTimeDao implements TableDao{
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rst ;
	public ShowTimeDao(Connection conn) {
		this.conn = conn;
	}
	
	
////添加
//	public boolean doInsert(ShowTime info) throws Exception{	
//		String sql = "insert into showtime values(?)";
//		pst = conn.prepareStatement(sql);
//		pst.setString(1, info.getClassTime());
//		
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////修改表数据
//	public boolean doUpdate(ShowTime info) throws SQLException {
//		sql = "updata showtime set classtime= ?";
//		pst = conn.prepareStatement(sql);
//		pst.setString(1, info.getClassTime());
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////删除
//	public boolean doDelete(ShowTime info) throws SQLException {
//		sql = "delete from showtime where classtime = ?";
//		pst = conn.prepareStatement(sql);
//		pst.setString(1, info.getClassTime());
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////查找表的全部数据
//	public List<ShowTime> findAll(ShowTime info) throws SQLException {
//		List<ShowTime> list = new ArrayList<>();
//		sql = "select * from showtime";
//		pst = conn.prepareStatement(sql);
//		rst = pst.executeQuery();
//		while(rst.next()) {
//			ShowTime n = new ShowTime();
//			n.setClassTime(rst.getString("classtime"));
//			list.add(n);
//		}
//			return list;
//	}
//通过主键查找
	public ShowTime findByMainKey(String mainKey) throws SQLException {
		ShowTime n = new ShowTime();
		String sql = "select * from showtime where classtime = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, mainKey);
		rst = pst.executeQuery();
		while(rst.next()) {
			n.setClassTime(rst.getString("classtime"));
		}
		return n;
		
	}


@Override
public boolean doCreate(Object object) throws Exception {
	if(object instanceof ShowTime) {
		// 向下转型
		ShowTime info = (ShowTime)object;
		String sql = "insert into showtime values(?)";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getClassTime());
		
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("ShowTime添加执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是ShowTime的父类");
		return false;
	}
	return true;
}


@Override
public boolean doUpdate(Object object) throws Exception {

	if(object instanceof ShowTime) {
		// 向下转型
		ShowTime info = (ShowTime)object;
		String sql = "updata showtime set classtime= ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getClassTime());
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("ShowTime修改执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是ShowTime的父类");
		return false;
	}
	return true;

}


@Override
public boolean doDelete(Object object) throws Exception {

	if(object instanceof ShowTime) {
		// 向下转型
		ShowTime info = (ShowTime)object;
		String sql = "delete from showtime where classtime = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getClassTime());
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("ShowTime删除执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是ShowTime的父类");
		return false;
	}
	return true;

}


@Override
public ArrayList<ShowTime> findAll() throws Exception {
	ArrayList<ShowTime> list = new ArrayList<>();
	String sql = "select * from showtime";
	pst = conn.prepareStatement(sql);
	rst = pst.executeQuery();
	while(rst.next()) {
		ShowTime n = new ShowTime();
		n.setClassTime(rst.getString("classtime"));
		list.add(n);
	}
		return list;
}

}
