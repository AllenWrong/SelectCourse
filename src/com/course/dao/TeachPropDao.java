package com.course.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.course.bean.TeachProp;

public class TeachPropDao implements TableDao{
	private Connection conn;
	private PreparedStatement prpst;
	
	public TeachPropDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof TeachProp) {
			// 向下转型
			TeachProp teachProp = (TeachProp)object;
			String sql = "insert into teachprop values(?,?,?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, teachProp.getPropID());
			prpst.setString(2, teachProp.getPropTeaNum());
			prpst.setString(3, teachProp.getPropName());
			prpst.setDate(4, (Date) teachProp.getPropTime());
			prpst.setString(5, teachProp.getPropNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teachprop添加执行失败");
				return false;
			}
		}else {
			System.out.println("不是TeachProp的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof TeachProp) {
			// 向下转型
			TeachProp teachProp = (TeachProp)object;
			String sql = "update table teachprop set prop_id=?,prop_tea_num=?,prop_name=?,prop_time=?,prop_num=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, teachProp.getPropID());
			prpst.setString(2, teachProp.getPropTeaNum());
			prpst.setString(3, teachProp.getPropName());
			prpst.setDate(4, (Date) teachProp.getPropTime());
			prpst.setString(5, teachProp.getPropNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teachprop更新执行失败");
				return false;
			}
		}else {
			System.out.println("不是TeachProp的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof TeachProp) {
			// 向下转型
			TeachProp teachProp = (TeachProp)object;
			String sql = "delete from teachprop where prop_num=?";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, teachProp.getPropNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teachprop删除执行失败");
				return false;
			}
		}else {
			System.out.println("不是TeachProp的父类");
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<TeachProp> findAll() throws Exception {
		String sql = "select prop_tea_num,prop_name,prop_time,prop_num,tea_name from teachprop,teacherinfo and tea_num=prop_tea_num";
		prpst =conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的teachprop失败");
			return null;
		}
		ArrayList<TeachProp> list = new ArrayList<>();
		while(rSet.next()) {
			TeachProp teachProp = new TeachProp();
			teachProp.setTeaName(rSet.getString("tea_name"));
			teachProp.setPropTeaNum(rSet.getString("prop_tea_num"));
			teachProp.setPropName(rSet.getString("prop_name"));
			teachProp.setPropNum(rSet.getString("prop_num"));
			teachProp.setPropTime(rSet.getDate("prop_time"));
			list.add(teachProp);
		}
		return list;
	}

	/**
	 * 根据教师号查询奖项，而不是根据id,返回一个list
	 */
	@Override
	public Object findByMainKey(String teaNum) throws Exception {
		String sql = "select prop_tea_num,prop_name,prop_time,prop_num,tea_name from teachprop,teacherinfo and tea_num=prop_tea_num where prop_tea_num=?";
		prpst = conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询指定teachprop失败");
			return null;
		}
		
		ArrayList<TeachProp> list = new ArrayList<>();
		while(rSet.next()) {
			TeachProp teachProp = new TeachProp();
			teachProp.setPropName(rSet.getString("tea_name"));
			teachProp.setPropTeaNum(rSet.getString("prop_tea_num"));
			teachProp.setPropName(rSet.getString("prop_name"));
			teachProp.setPropNum(rSet.getString("prop_num"));
			teachProp.setPropTime(rSet.getDate("prop_time"));
			list.add(teachProp);
		}
		return list;
	}

}
