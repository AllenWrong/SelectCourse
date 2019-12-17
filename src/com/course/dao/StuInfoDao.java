package com.course.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.course.bean.StuGrade;
import com.course.bean.StuInfo;

public class StuInfoDao implements TableDao{
	private Connection conn;
	private PreparedStatement prpst;
	
	public StuInfoDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof StuInfo) {
			// 向下转型
			StuInfo stuInfo = (StuInfo)object;
			String sql = "insert into studentinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, stuInfo.getStuID());
			prpst.setString(2, stuInfo.getStuNum());
			prpst.setString(3, stuInfo.getStuName());
			prpst.setString(4, stuInfo.getStuPwd());
			prpst.setString(5, stuInfo.getStuSex());
			prpst.setString(6, stuInfo.getStuPhone());
			prpst.setDate(7, (Date) stuInfo.getStuBirthday());
			prpst.setString(8, stuInfo.getStuHome());
			prpst.setString(9, stuInfo.getStuMajor());
			prpst.setString(10, stuInfo.getStuNation());
			prpst.setString(11, stuInfo.getStuPolicy());
			prpst.setDate(12, (Date) stuInfo.getStuInschoolDate());
			prpst.setString(13, stuInfo.isStuState());
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
		if(object instanceof StuGrade) {
			// 向下转型
			StuInfo stuInfo = (StuInfo)object;
			String sql = "update studentinfo set stu_name=?,stu_phone=?,stu_home=?,stu_major=?stu_policy=?,stu_state=?";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, stuInfo.getStuName());
			prpst.setString(2, stuInfo.getStuPhone());
			prpst.setString(3, stuInfo.getStuHome());
			prpst.setString(4, stuInfo.getStuMajor());
			prpst.setString(5, stuInfo.getStuPolicy());
			prpst.setString(6, stuInfo.isStuState());
			if(prpst.executeUpdate() != 1) {
				System.out.println("stuInfo更新执行错误");
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
		if(object instanceof StuGrade) {
			// 向下转型
			StuInfo stuInfo = (StuInfo)object;
			String sql = "delete from studentinfo where stu_num=?";			
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, stuInfo.getStuNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("stuInfo更新执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是stuInfo的父类");
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<StuInfo> findAll() throws Exception {
		String sql = "select * from studentinfo";
		prpst = conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的学生信息错误！");
			return null;
		}
		ArrayList<StuInfo> list = new ArrayList<>();
		while(rSet.next()) {
			StuInfo stuInfo = new StuInfo();
			stuInfo.setStuID(rSet.getInt("stu_id"));
			stuInfo.setStuNum(rSet.getString("stu_num"));
			stuInfo.setStuName(rSet.getString("stu_name"));
			stuInfo.setStuPwd(rSet.getString("pwd"));
			stuInfo.setStuSex(rSet.getString("stu_sex"));
			stuInfo.setStuPhone(rSet.getString("stu_phone"));
			stuInfo.setStuBirthday(rSet.getDate("stu_birthday"));
			stuInfo.setStuHome(rSet.getString("stu_home"));
			stuInfo.setStuMajor(rSet.getString("stu_major"));
			stuInfo.setStuNation(rSet.getString("stu_nation"));
			stuInfo.setStuPolicy(rSet.getString("stu_policy"));
			stuInfo.setStuInschoolDate(rSet.getDate("stu_inschooldata"));
			stuInfo.setStuState(rSet.getString("stu_state"));
			list.add(stuInfo);
		}
		return list;
	}

	@Override
	public Object findByMainKey(String mainKey) throws Exception {
		String sql = "select * from studentinfo where stu_num=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, mainKey);
		ResultSet rSet = prpst.executeQuery();
		StuInfo stuInfo = new StuInfo();
		if(rSet == null) {
			System.out.println("查询指定学生错误");
			return null;
		}else {
			while(rSet.next()) {
				stuInfo.setStuID(rSet.getInt("stu_id"));
				stuInfo.setStuNum(rSet.getString("stu_num"));
				stuInfo.setStuName(rSet.getString("stu_name"));
				stuInfo.setStuPwd(rSet.getString("stu_pwd"));
				stuInfo.setStuSex(rSet.getString("stu_sex"));
				stuInfo.setStuPhone(rSet.getString("stu_phone"));
				stuInfo.setStuBirthday(rSet.getDate("stu_birthday"));
				stuInfo.setStuHome(rSet.getString("stu_home"));
				stuInfo.setStuMajor(rSet.getString("stu_major"));
				stuInfo.setStuNation(rSet.getString("stu_nation"));
				stuInfo.setStuPolicy(rSet.getString("stu_policy"));
				stuInfo.setStuInschoolDate(rSet.getDate("stu_inschooldata"));
				stuInfo.setStuState(rSet.getString("stu_state"));
			}
		}
		return stuInfo;
	}
	
	/**
	 * 多条件查询
	 * @param grade
	 * @param major
	 * @param stuNum
	 * @return
	 * @throws Exception
	 */
	public ArrayList<StuInfo> findByMultiKey(String grade,String major,String stuNum) throws Exception{
		String sql = "select * from studentinfo where";
		if(grade != null) {
			sql += " stu_grade="+grade;
		}
		if(major != null) {
			sql += " stu_major="+major;
		}
		if(stuNum != "") {
			sql += " stu_num="+stuNum;
		}
		Statement statement = conn.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		ArrayList<StuInfo> list = new ArrayList<>();
		if(rSet == null) {
			System.out.println("查询指定学生错误");
			return null;
		}else {
			while(rSet.next()) {
				StuInfo stuInfo = new StuInfo();
				stuInfo.setStuID(rSet.getInt("stu_id"));
				stuInfo.setStuNum(rSet.getString("stu_num"));
				stuInfo.setStuName(rSet.getString("stu_name"));
				stuInfo.setStuPwd(rSet.getString("stu_pwd"));
				stuInfo.setStuSex(rSet.getString("stu_sex"));
				stuInfo.setStuPhone(rSet.getString("stu_phone"));
				stuInfo.setStuBirthday(rSet.getDate("stu_birthday"));
				stuInfo.setStuHome(rSet.getString("stu_home"));
				stuInfo.setStuMajor(rSet.getString("stu_major"));
				stuInfo.setStuNation(rSet.getString("stu_nation"));
				stuInfo.setStuPolicy(rSet.getString("stu_policy"));
				stuInfo.setStuInschoolDate(rSet.getDate("stu_inschooldata"));
				stuInfo.setStuState(rSet.getString("stu_state"));
				list.add(stuInfo);
			}
		}
		return list;
	}
}
