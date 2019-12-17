package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.course.bean.TeacherPrize;

public class TeacherPrizeDao implements TableDao {
	private Connection conn;
	private PreparedStatement prpst;
	
	public TeacherPrizeDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof TeacherPrize) {
			// 向下转型
			TeacherPrize teacherPrize = (TeacherPrize) object;
			String sql = "insert into teacherprize values(?,?,?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, teacherPrize.getTeaPriID());
			prpst.setString(2, teacherPrize.getTeaPriTeaNum());
			prpst.setString(3, teacherPrize.getTeaPriNum());
			prpst.setString(4, teacherPrize.getTeaPriType());
			prpst.setString(5, teacherPrize.getTeaPriDes());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teacherPrize添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是teacherPrize的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof TeacherPrize) {
			// 向下转型
			TeacherPrize teacherPrize = (TeacherPrize) object;
			String sql = "update table teacherprize set teapri_id=?,teapri_tea_num=?,teapri_num=?,teapri_type=?,teapri_des=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, teacherPrize.getTeaPriID());
			prpst.setString(2, teacherPrize.getTeaPriTeaNum());
			prpst.setString(3, teacherPrize.getTeaPriNum());
			prpst.setString(4, teacherPrize.getTeaPriType());
			prpst.setString(5, teacherPrize.getTeaPriDes());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teacherPrize更新执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是teacherPrize的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof TeacherPrize) {
			// 向下转型
			TeacherPrize teacherPrize = (TeacherPrize) object;
			String sql = "delete from table teacherprize where teapri_num=?";			
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, teacherPrize.getTeaPriNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teacherPrize删除执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是teacherPrize的父类");
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<TeacherPrize> findAll() throws Exception {
		String sql = "select * from teacherprize";
		prpst = conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有teacherPrize信息错误");
			return null;
		}
		ArrayList<TeacherPrize> list = new ArrayList<>();
		while(rSet.next()) {
			TeacherPrize teacherPrize = new TeacherPrize(rSet.getInt("teapri_id"),rSet.getString("teapri_tea_num"),
					rSet.getString("teapri_num"),rSet.getString("teapri_type"),rSet.getString("teapri_des"));
			list.add(teacherPrize);
		}
		return list;
	}
	
	@Override
	public Object findByMainKey(String teaNum) throws Exception {
		String sql = "select teapri_tea_num,teapri_num,teapri_type,teapri_des,tea_name from teacherprize,teacherinfo where tea_num=teapri_tea_num and teapri_tea_num=?";
		prpst = conn.prepareStatement(sql);
		prpst.setInt(1, Integer.parseInt(teaNum));
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询指定奖项错误");
			return null;
		}
		ArrayList<TeacherPrize> list = new ArrayList<>();
		while(rSet.next()) {
			TeacherPrize teacherPrize = new TeacherPrize();
			teacherPrize.setTeaPriTeaNum(rSet.getString("teapri_tea_num"));
			teacherPrize.setTeaPriNum(rSet.getString("teapri_num"));
			teacherPrize.setTeaPriType(rSet.getString("teapri_type"));
			teacherPrize.setTeaPriDes(rSet.getString("teapri_des"));
			teacherPrize.setTeaName(rSet.getString("tea_name"));
			list.add(teacherPrize);
		}
		return list;
	}

}
