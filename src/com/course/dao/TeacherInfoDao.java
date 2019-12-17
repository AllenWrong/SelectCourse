package com.course.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.course.bean.TeacherInfor;

public class TeacherInfoDao implements TableDao{
	private Connection conn;
	private PreparedStatement prpst;
	
	public TeacherInfoDao() {
	}

	public TeacherInfoDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof TeacherInfor) {
			// 向下转型
			TeacherInfor teacherInfor = (TeacherInfor)object;
			String sql = "insert into teacherinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1,teacherInfor.getTeaID());
			prpst.setString(2, teacherInfor.getTeaNum());
			prpst.setString(3, teacherInfor.getTeaName());
			prpst.setString(4, teacherInfor.getTeaPwd());
			prpst.setString(5, teacherInfor.getTeaSex());
			prpst.setString(6, teacherInfor.getTeaPhone());
			prpst.setDate(7, (Date) teacherInfor.getTeaBirthday());
			prpst.setString(8, teacherInfor.getTeaHome());
			prpst.setString(9, teacherInfor.getTeaMajor());
			prpst.setString(10, teacherInfor.getTeaNation());
			prpst.setString(11, teacherInfor.getTeaPolicy());
			prpst.setDate(12, (Date) teacherInfor.getTeaInschoolTime());
			prpst.setString(13, teacherInfor.getTeaXl());
			prpst.setString(14, teacherInfor.getTeaTitle());
			if(prpst.executeUpdate() != 1) {
				System.out.println("stuInfo添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是stuInfo的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof TeacherInfor) {
			// 向下转型
			TeacherInfor teacherInfor = (TeacherInfor)object;
			String sql = "udate teacherinfo values set tea_id=?,tea_num=?,tea_name=?,tea_pwd=?,tea_sex=?,tea_phone=?"
					+ ",tea_birthday=?,tea_home=?,tea_major=?,tea_nation=?,tea_policy=?,tea_inschooltime=?,tea_xl=?,tea_title=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1,teacherInfor.getTeaID());
			prpst.setString(2, teacherInfor.getTeaNum());
			prpst.setString(3, teacherInfor.getTeaName());
			prpst.setString(4, teacherInfor.getTeaPwd());
			prpst.setString(5, teacherInfor.getTeaSex());
			prpst.setString(6, teacherInfor.getTeaPhone());
			prpst.setDate(7, (Date) teacherInfor.getTeaBirthday());
			prpst.setString(8, teacherInfor.getTeaHome());
			prpst.setString(9, teacherInfor.getTeaMajor());
			prpst.setString(10, teacherInfor.getTeaNation());
			prpst.setString(11, teacherInfor.getTeaPolicy());
			prpst.setDate(12, (Date) teacherInfor.getTeaInschoolTime());
			prpst.setString(13, teacherInfor.getTeaXl());
			prpst.setString(14, teacherInfor.getTeaTitle());
			if(prpst.executeUpdate() != 1) {
				System.out.println("stuInfo添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是stuInfo的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof TeacherInfor) {
			TeacherInfor teacherInfor = (TeacherInfor)object;
			String sql = "delete from teacherinfo where tea_num=?";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1,teacherInfor.getTeaNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("stuInfo添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是stuInfo的父类");
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<TeacherInfor> findAll() throws Exception {
		String sql = "select * from teacherinfo";
		prpst = conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的teacherinfo错误");
			return null;
		}
		ArrayList<TeacherInfor> list = new ArrayList<>();
		while(rSet.next()) {
			TeacherInfor teacherInfor = new TeacherInfor();
			teacherInfor.setTeaID(rSet.getInt("tea_id"));
			teacherInfor.setTeaNum(rSet.getString("tea_num"));
			teacherInfor.setTeaName(rSet.getString("tea_name"));
			teacherInfor.setTeaPwd(rSet.getString("tea_pwd"));
			teacherInfor.setTeaSex(rSet.getString("tea_sex"));
			teacherInfor.setTeaPhone(rSet.getString("tea_phone"));
			teacherInfor.setTeaBirthday(rSet.getDate("tea_birthday"));
			teacherInfor.setTeaHome(rSet.getString("tea_home"));
			teacherInfor.setTeaMajor(rSet.getString("tea_major"));
			teacherInfor.setTeaNation(rSet.getString("tea_nation"));
			teacherInfor.setTeaPolicy(rSet.getString("tea_policy"));
			teacherInfor.setTeaInschoolTime(rSet.getDate("tea_inschooltime"));
			teacherInfor.setTeaXl(rSet.getString("tea_xl"));
			teacherInfor.setTeaTitle(rSet.getString("tea_title"));
			list.add(teacherInfor);
		}
		return list;
	}

	/** 根据教师号查询教师信息*/
	@Override
	public Object findByMainKey(String teaNum) throws Exception {
		String sql = "select * from teacherinfo where tea_num=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, teaNum);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询指定教师信息失败");
			return false;
		}
		TeacherInfor teacherInfor = new TeacherInfor();
		while(rSet.next()) {
			teacherInfor.setTeaID(rSet.getInt("tea_id"));
			teacherInfor.setTeaNum(rSet.getString("tea_num"));
			teacherInfor.setTeaName(rSet.getString("tea_name"));
			teacherInfor.setTeaPwd(rSet.getString("tea_pwd"));
			teacherInfor.setTeaSex(rSet.getString("tea_sex"));
			teacherInfor.setTeaPhone(rSet.getString("tea_phone"));
			teacherInfor.setTeaBirthday(rSet.getDate("tea_birthday"));
			teacherInfor.setTeaHome(rSet.getString("tea_home"));
			teacherInfor.setTeaMajor(rSet.getString("tea_major"));
			teacherInfor.setTeaNation(rSet.getString("tea_nation"));
			teacherInfor.setTeaPolicy(rSet.getString("tea_policy"));
			teacherInfor.setTeaInschoolTime(rSet.getDate("tea_inschooltime"));
			teacherInfor.setTeaXl(rSet.getString("tea_xl"));
			teacherInfor.setTeaTitle(rSet.getString("tea_title"));
		}
		return teacherInfor;
	}

	public ArrayList<TeacherInfor> findByMajor(String majorName) throws SQLException{
		String sql = "select * from teacherinfo where tea_major=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, majorName);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的teacherinfo错误");
			return null;
		}
		ArrayList<TeacherInfor> list = new ArrayList<>();
		while(rSet.next()) {
			TeacherInfor teacherInfor = new TeacherInfor();
			teacherInfor.setTeaID(rSet.getInt("tea_id"));
			teacherInfor.setTeaNum(rSet.getString("tea_num"));
			teacherInfor.setTeaName(rSet.getString("tea_name"));
			teacherInfor.setTeaPwd(rSet.getString("tea_pwd"));
			teacherInfor.setTeaSex(rSet.getString("tea_sex"));
			teacherInfor.setTeaPhone(rSet.getString("tea_phone"));
			teacherInfor.setTeaBirthday(rSet.getDate("tea_birthday"));
			teacherInfor.setTeaHome(rSet.getString("tea_home"));
			teacherInfor.setTeaMajor(rSet.getString("tea_major"));
			teacherInfor.setTeaNation(rSet.getString("tea_nation"));
			teacherInfor.setTeaPolicy(rSet.getString("tea_policy"));
			teacherInfor.setTeaInschoolTime(rSet.getDate("tea_inschooltime"));
			teacherInfor.setTeaXl(rSet.getString("tea_xl"));
			teacherInfor.setTeaTitle(rSet.getString("tea_title"));
			list.add(teacherInfor);
		}
		return list;
	}
}
