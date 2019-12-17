package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.course.bean.Notice;

public class NoticeDao implements TableDao{
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rst ;
	public NoticeDao(Connection conn) {
		this.conn = conn;
	}
	
	
	
////添加
//	public boolean doInsert(Notice info) throws Exception{	
//		String sql = "insert into notice values(?)";
//		pst = conn.prepareStatement(sql);
//		pst.setString(1, info.getNoticeContent());
//		
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////修改表数据
//	public boolean doUpdate(Notice info) throws SQLException {
//		sql = "updata notice set noticecntent= ?";
//		pst = conn.prepareStatement(sql);
//		pst.setString(1, info.getNoticeContent());
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////删除
//	public boolean doDelete(Notice info) throws SQLException {
//		sql = "delete from askcourse where noticecntent = ?";
//		pst = conn.prepareStatement(sql);
//		pst.setString(1, info.getNoticeContent());
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////查找表的全部数据
//	public List<Notice> findAll(Notice info) throws SQLException {
//		List<Notice> list = new ArrayList<>();
//		sql = "select * from notice";
//		pst = conn.prepareStatement(sql);
//		rst = pst.executeQuery();
//		while(rst.next()) {
//			Notice n = new Notice();
//			n.setNoticeContent(rst.getString("noticecntent"));
//			list.add(n);
//		}
//			return list;
//	}
//通过主键查找
	public Notice findByMainKey(String mainKey) throws SQLException {
		Notice n = new Notice();
		String sql = "select * from notice where noticecntent = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, mainKey);
		rst = pst.executeQuery();
		while(rst.next()) {
			n.setNoticeContent(rst.getString("noticecntent"));
		}
		return n;
		
	}



@Override
public boolean doCreate(Object object) throws Exception {
	if(object instanceof Notice) {
		// 向下转型
		Notice info = (Notice)object;
		String sql = "insert into notice values(?)";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getNoticeContent());
		
		int row = pst.executeUpdate();
		if(row != 1) {
			System.out.println("Notice添加执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是Notice的父类");
		return false;
	}
	return true;
}



@Override
public boolean doUpdate(Object object) throws Exception {
	if(object instanceof Notice) {
		// 向下转型
		Notice info = (Notice)object;
		String sql = "updata notice set noticecntent= ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getNoticeContent());
		int row = pst.executeUpdate();
		if(row != 1) {
			System.out.println("Notice修改执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是Notice的父类");
		return false;
	}
	return true;
}



@Override
public boolean doDelete(Object object) throws Exception {
	if(object instanceof Notice) {
		// 向下转型
		Notice info = (Notice)object;
		String sql = "delete from askcourse where noticecntent = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getNoticeContent());
		int row = pst.executeUpdate();
		if(row != 1) {
			System.out.println("Notice删除执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是Notice的父类");
		return false;
	}
	return true;
}



@Override
public ArrayList<Notice> findAll() throws Exception {
	ArrayList<Notice> list = new ArrayList<>();
	String sql = "select * from notice ORDER BY noticetime DESC";
	pst = conn.prepareStatement(sql);
	rst = pst.executeQuery();
	while(rst.next()) {
		Notice n = new Notice();
		n.setNoticeTime(rst.getDate("noticetime").toString());
		n.setNoticeContent(rst.getString("noticecntent"));
		list.add(n);
	}
		return list;
}

}

