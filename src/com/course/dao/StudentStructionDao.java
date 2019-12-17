package com.course.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.course.bean.StudentStruction;

public class StudentStructionDao implements TableDao{
	private Connection conn;
	private PreparedStatement prpst;
	
	public StudentStructionDao(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof StudentStruction) {
			// 向下转型
			StudentStruction studentStruction = (StudentStruction)object;
			String sql = "insert into student_struction values(?,?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, studentStruction.getActionID());
			prpst.setString(2, studentStruction.getActionStuNum());
			prpst.setDate(3, (Date) studentStruction.getActionDate());
			prpst.setString(4, studentStruction.getActionDes());
			if(prpst.executeUpdate() != 1) {
				System.out.println("studentStruction添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是StudentStruction的父类");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof StudentStruction) {
			// 向下转型
			StudentStruction studentStruction = (StudentStruction)object;
			String sql = "update student_struction set action_id=?,action_stu_num=?,action_date=?,action_des=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, studentStruction.getActionID());
			prpst.setString(2, studentStruction.getActionStuNum());
			prpst.setDate(3, (Date) studentStruction.getActionDate());
			prpst.setString(4, studentStruction.getActionDes());
			if(prpst.executeUpdate() != 1) {
				System.out.println("studentStruction更新执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是StudentStruction的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof StudentStruction) {
			// 向下转型
			StudentStruction studentStruction = (StudentStruction)object;
			String sql = "delete from student_struction where action_stu_num=? and action_date=?";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1,studentStruction.getActionStuNum());
			prpst.setDate(2, (Date) studentStruction.getActionDate());
			if(prpst.executeUpdate() != 1) {
				System.out.println("studentStruction删除执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是StudentStruction的父类");
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<StudentStruction> findAll() throws Exception {
		String sql = "select action_stu_num,action_date,action_des,stu_name from student_struction,studentinfo and stu_num=action_stu_num";
		prpst = conn.prepareStatement(sql);
		ResultSet resultSet = prpst.executeQuery();
		if(resultSet == null) {
			System.out.println("studentStruction查询错误");
			return null;
		}
		ArrayList<StudentStruction> list = new ArrayList<>();
		while(resultSet.next()) {
			StudentStruction stuStruc = new StudentStruction();
			stuStruc.setStuName(resultSet.getString("stu_name"));
			stuStruc.setActionStuNum(resultSet.getString("action_stu_num"));
			stuStruc.setActionDate(resultSet.getDate("action_date"));
			stuStruc.setActionDes(resultSet.getString("action_des"));
			list.add(stuStruc);
		}
		return list;
	}

	@Override
	public ArrayList<StudentStruction> findByMainKey(String stuNum) throws Exception {
		String sql = "select action_stu_num,action_date,action_des,stu_name from student_struction,studentinfo and stu_num=action_stu_num and action_stu_num=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, stuNum);
		ResultSet resultSet = prpst.executeQuery();
		if(resultSet == null) {
			System.out.println("stuNum查询错误");
			return null;
		}
		ArrayList<StudentStruction> list = new ArrayList<>();
		while(resultSet.next()) {
			StudentStruction stuStruc = new StudentStruction();
			stuStruc.setStuName(resultSet.getString("stu_name"));
			stuStruc.setActionStuNum(resultSet.getString("action_stu_num"));
			stuStruc.setActionDate(resultSet.getDate("action_date"));
			stuStruc.setActionDes(resultSet.getString("action_des"));
			list.add(stuStruc);
		}
		return list;
	}
	
}
