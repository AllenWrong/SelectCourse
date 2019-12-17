package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.course.bean.CourseInfo;
import com.course.bean.MajorPlanInfo;
import com.course.bean.TrainPlan;

public class MajorPlanInfoDao implements TableDao{
		private Connection conn;
		private PreparedStatement pst;
		private ResultSet rst ;
		public MajorPlanInfoDao(Connection conn) {
			this.conn = conn;
		}
		
	//通过主键查找
		public MajorPlanInfo findByMainKey(String mainKey) throws SQLException {
			MajorPlanInfo course = new MajorPlanInfo();
			String sql = "select * from majorplaninfo where majorplan_num = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, mainKey);
			rst = pst.executeQuery();
			while(rst.next()) {
				course.setMajorPlanID(rst.getInt("majorplan_id"));
				course.setMajorPlanNum(rst.getString("majorplan_num"));
				course.setMajorPlanName(rst.getString("majorplan_name"));
				course.setMajorPlanGrade(rst.getString("majorplan_grade"));
				course.setMajorPlanComcre(rst.getInt("majorplan_comcre"));
				course.setMajorPlanElcre(rst.getInt("majorplan_elcre"));
			}
			return course;
			
		}


	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof MajorPlanInfo) {
			// 向下转型
			MajorPlanInfo info = (MajorPlanInfo)object;
			String sql = "insert into majorplaninfo values(?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, info.getMajorPlanID());
			pst.setString(2, info.getMajorPlanNum());
			pst.setString(3, info.getMajorPlanName());
			pst.setString(4, info.getMajorPlanGrade());
			pst.setInt(5, info.getMajorPlanComcre());
			pst.setInt(6, info.getMajorPlanElcre());
			int row = pst.executeUpdate();
			if(row != 1) {
				System.out.println("MajorPlanInfo添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是MajorPlanInfo的父类");
			return false;
		}
		return true;
	}


	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof MajorPlanInfo) {
			// 向下转型
			MajorPlanInfo info = (MajorPlanInfo)object;
			String sql = "updata majorplaninfo set majorplan_id =? ,majorplan_num= ?,majorplan_name = ?,majorplan_grade = ?,majorplan_comcre = ?,majorplan_elcre=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, info.getMajorPlanID());
			pst.setString(2, info.getMajorPlanNum());
			pst.setString(3, info.getMajorPlanName());
			pst.setString(4, info.getMajorPlanGrade());
			pst.setInt(5, info.getMajorPlanComcre());
			pst.setInt(6, info.getMajorPlanElcre());
			int row = pst.executeUpdate();
			if(row != 1) {
				System.out.println("MajorPlanInfo修改执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是MajorPlanInfo的父类");
			return false;
		}
		return true;
	}


	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof MajorPlanInfo) {
			// 向下转型
			MajorPlanInfo info = (MajorPlanInfo)object;
			String sql = "delete from majorplaninfo where majorplan_num =?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getMajorPlanNum());
			int row = pst.executeUpdate();
			if(row != 1) {
				System.out.println("MajorPlanInfo删除执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是MajorPlanInfo的父类");
			return false;
		}
		return true;
	}


	@Override
	public ArrayList<MajorPlanInfo> findAll() throws Exception {
		ArrayList<MajorPlanInfo> list = new ArrayList<>();
		String sql = "select * from majorplaninfo";
		pst = conn.prepareStatement(sql);
		rst = pst.executeQuery();
		while(rst.next()) {
			MajorPlanInfo course = new MajorPlanInfo();
			course.setMajorPlanID(rst.getInt("majorplan_id"));
			course.setMajorPlanNum(rst.getString("majorplan_num"));
			course.setMajorPlanName(rst.getString("majorplan_name"));
			course.setMajorPlanGrade(rst.getString("majorplan_grade"));
			course.setMajorPlanComcre(rst.getInt("majorplan_comcre"));
			course.setMajorPlanElcre(rst.getInt("majorplan_elcre"));
			list.add(course);
		}
			return list;
	}

	
	public ArrayList<CourseInfo> findAll(String stuNum,String grade) throws Exception {
		ArrayList<CourseInfo> list = new ArrayList<>();
		String sql = "select stu_major,cou_num,cou_name,cou_type,cou_redit,cou_xs,tp_term from studentinfo,termplaninfo,courseinfo,majorplaninfo where"+
				" stu_num=? and majorplan_grade=? and tp_majornum = majorplan_num and cou_num = tp_coursenum and majorplan_name = stu_major";
		pst = conn.prepareStatement(sql);
		pst.setString(1, stuNum);
		pst.setString(2,grade);
		rst = pst.executeQuery();
		while(rst.next()) {
			CourseInfo course = new CourseInfo();
			course.setCourNum(rst.getString("cou_num"));
			course.setCourName(rst.getString("cou_name"));
			course.setCourType(rst.getString("cou_type"));
			course.setCourRedit(rst.getInt("cou_redit"));
			course.setCourXs(rst.getInt("cou_xs"));
			course.setCourTerm(rst.getString("tp_term"));
			/** 这里使用此字段保存学生专业名*/
			course.setCourXxkNum(rst.getString("stu_major"));
			list.add(course);
		}
		return list;
	}
	
	/**
	 * 根据年级和专业名查询专业专业计划
	 * @param grade 年级
	 * @param majorName 专业名
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<MajorPlanInfo> findByKey(String grade,String majorName) throws SQLException{
		ArrayList<MajorPlanInfo> list = new ArrayList<>();
		String sql = "select * from majorplaninfo where majorplan_grade = ? and majorplan_name=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, grade);
		pst.setString(2,majorName);
		rst = pst.executeQuery();
		while(rst.next()) {
			MajorPlanInfo course = new MajorPlanInfo();
			course.setMajorPlanID(rst.getInt("majorplan_id"));
			course.setMajorPlanNum(rst.getString("majorplan_num"));
			course.setMajorPlanName(rst.getString("majorplan_name"));
			course.setMajorPlanGrade(rst.getString("majorplan_grade"));
			course.setMajorPlanComcre(rst.getInt("majorplan_comcre"));
			course.setMajorPlanElcre(rst.getInt("majorplan_elcre"));
			list.add(course);
		}
		return list;
	}

	/**
	 * 根据专业名和年级查询培养计划
	 * @param grade
	 * @param majorPlanName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<TrainPlan> findMajorPlanByGrade(String grade,String majorPlanName) throws Exception{
		String sql = "select majorplan_name,majorplan_grade,tp_coursenum,cou_name,cou_type,cou_redit,cou_xs,tp_term from majorplaninfo,courseinfo,termplaninfo where tp_majornum=majorplan_num and tp_coursenum=cou_num"
				+ " and majorplan_name=? and majorplan_grade=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, majorPlanName);
		pst.setString(2, grade);
		rst = pst.executeQuery();
		ArrayList<TrainPlan> list = new ArrayList<>();
		while(rst.next()) {
			TrainPlan trainPlan = new TrainPlan();
			trainPlan.setMajorPlanName(rst.getString("majorplan_name"));
			trainPlan.setMajorPlanGrade(rst.getString("majorplan_grade"));
			trainPlan.setTpCoureNum(rst.getString("tp_coursenum"));
			trainPlan.setCouName(rst.getString("cou_name"));
			trainPlan.setCouType(rst.getString("cou_type"));
			trainPlan.setCouRedit(rst.getInt("cou_redit"));
			trainPlan.setCou_xs(rst.getString("cou_xs"));
			trainPlan.setTpTerm(rst.getString("tp_term"));
			list.add(trainPlan);
		}
		return list;
	}
}



