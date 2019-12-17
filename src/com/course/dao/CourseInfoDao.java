package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.course.bean.CourseInfo;

	public class CourseInfoDao implements TableDao{
		private Connection conn;
		private PreparedStatement pst;
		private ResultSet rst ;
		public CourseInfoDao(Connection conn) {
			this.conn = conn;
		}
		
		
//	//添加
//		public boolean doInsert(CourseInfo info) throws Exception{	
//			String sql = "insert into courseinfo values(?,?,?,?,?,?,?,?,?)";
//			pst = conn.prepareStatement(sql);
//			pst.setInt(1, info.getCourID());
//			pst.setString(2, info.getCourNum());
//			pst.setString(3, info.getCourName());
//			pst.setInt(4, info.getCourXs());
//			pst.setInt(5, info.getCourRedit());
//			pst.setString(6, info.getCourType());
//			pst.setString(7, info.getCourTerm());
//			pst.setString(8, info.getCourXxkNum());
//			int row = pst.executeUpdate();
//			if(row!=0) {
//				return true;
//			}else {
//			    return false;
//			}
//		}
//	//修改表数据
//		public boolean doUpdate(CourseInfo info) throws SQLException {
//			String sql = "updata courseinfo set cou_id =?,cou_num= ?,cou_name = ?,cou_xs = ?,cou_redit=?,cou_type = ?,cou_term = ?,cou_xxknum=?";
//			pst = conn.prepareStatement(sql);
//			pst.setInt(1, info.getCourID());
//			pst.setString(2, info.getCourNum());
//			pst.setString(3, info.getCourName());
//			pst.setInt(4, info.getCourXs());
//			pst.setInt(5, info.getCourRedit());
//			pst.setString(6, info.getCourType());
//			pst.setString(7, info.getCourTerm());
//			pst.setString(8, info.getCourXxkNum());
//			int row = pst.executeUpdate();
//			if(row!=0) {
//				return true;
//			}else {
//			    return false;
//			}
//		}
//	//删除
//		public boolean doDelete(CourseInfo info) throws SQLException {
//			String sql = "delete from courseinfo where cou_num = ?";
//			pst = conn.prepareStatement(sql);
//			pst.setInt(1, info.getCourID());
//			int row = pst.executeUpdate();
//			if(row!=0) {
//				return true;
//			}else {
//			    return false;
//			}
//		}
	//查找表的全部数据
		public List<CourseInfo> findAll(CourseInfo info) throws SQLException {
			List<CourseInfo> list = new ArrayList<>();
			String sql = "select * from courseinfo";
			pst = conn.prepareStatement(sql);
			rst = pst.executeQuery();
			while(rst.next()) {
				CourseInfo course = new CourseInfo();
				
				course.setCourID(rst.getInt("cou_id"));
				course.setCourNum(rst.getString("cou_num"));
				course.setCourName(rst.getString("cou_name"));
				course.setCourXs(rst.getInt("cou_xs"));
				course.setCourRedit(rst.getInt("cou_redit"));
				course.setCourType(rst.getString("cou_type"));
				course.setCourTerm(rst.getString("cou_term"));
				course.setCourXxkNum(rst.getString("cou_xxknum"));
				list.add(course);
			}
				return list;
		}
	//通过主键查找
		public ArrayList<CourseInfo> findByMainKey(String mainKey) throws SQLException {
			String sql = "select * from courseinfo where cou_num = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, mainKey);
			rst = pst.executeQuery();
			ArrayList<CourseInfo> list = new ArrayList<>();
			while(rst.next()) {
				CourseInfo course = new CourseInfo();
				course.setCourID(rst.getInt("cou_id"));
				course.setCourNum(rst.getString("cou_num"));
				course.setCourName(rst.getString("cou_name"));
				course.setCourXs(rst.getInt("cou_xs"));
				course.setCourRedit(rst.getInt("cou_redit"));
				course.setCourType(rst.getString("cou_type"));
				course.setCourTerm(rst.getString("cou_term"));
				course.setCourXxkNum(rst.getString("cou_xxknum"));
				list.add(course);
			}
			return list;
		}


	@Override
	public boolean doCreate(Object object) throws Exception {		
		if(object instanceof CourseInfo) {
			// 向下转型
			CourseInfo info = (CourseInfo)object;
			String sql = "insert into courseinfo(cou_num,cou_name,cou_xs,cou_redit,cou_type,cou_term,cou_xxknum) values(?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getCourNum());
			pst.setString(2, info.getCourName());
			pst.setInt(3, info.getCourXs());
			pst.setInt(4, info.getCourRedit());
			pst.setString(5, info.getCourType());
			pst.setString(6, info.getCourTerm());
			pst.setString(7, info.getCourXxkNum());
			int row = pst.executeUpdate();
			if(row != 1) {
				System.out.println("CourseInfo添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是CourseInfo的父类");
			return false;
		}
		return true;
	}


	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof CourseInfo) {
			// 向下转型
			CourseInfo info = (CourseInfo)object;
			String sql = "update courseinfo set cou_num= ?,cou_name = ?,cou_xs = ?,cou_redit=?,cou_type = ?,cou_term = ?,cou_xxknum=? where cou_num=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getCourNum());
			pst.setString(2, info.getCourName());
			pst.setInt(3, info.getCourXs());
			pst.setInt(4, info.getCourRedit());
			pst.setString(5, info.getCourType());
			pst.setString(6, info.getCourTerm());
			pst.setString(7, info.getCourXxkNum());
			pst.setString(8, info.getCourNum());
			int row = pst.executeUpdate();
			if(row != 1) {
				System.out.println("CourseInfo修改执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是CourseInfo的父类");
			return false;
		}
		return true;
	}


	@Deprecated
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof CourseInfo) {
			// 向下转型
			CourseInfo info = (CourseInfo)object;
			String sql = "delete from courseinfo where cou_num = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, info.getCourID());
			int row = pst.executeUpdate();
			if(row != 1) {
				System.out.println("CourseInfo删除执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是CourseInfo的父类");
			return false;
		}
		return true;
	}
	
	public boolean doDelete(String couNum)throws Exception{
		// 向下转型
		String sql = "delete from courseinfo where cou_num = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, couNum);
		int row = pst.executeUpdate();
		if(row != 1) {
			System.out.println("CourseInfo删除执行错误");
			return false;
		}
		return true;
	}


	@Override
	public ArrayList<CourseInfo> findAll() throws Exception {
		ArrayList<CourseInfo> list = new ArrayList<>();
		String sql = "select * from courseinfo";
		pst = conn.prepareStatement(sql);
		rst = pst.executeQuery();
		while(rst.next()) {
			CourseInfo course = new CourseInfo();
			
			course.setCourID(rst.getInt("cou_id"));
			course.setCourNum(rst.getString("cou_num"));
			course.setCourName(rst.getString("cou_name"));
			course.setCourXs(rst.getInt("cou_xs"));
			course.setCourRedit(rst.getInt("cou_redit"));
			course.setCourType(rst.getString("cou_type"));
			course.setCourTerm(rst.getString("cou_term"));
			course.setCourXxkNum(rst.getString("cou_xxknum"));
			list.add(course);
		}
			return list;
	}

	}



