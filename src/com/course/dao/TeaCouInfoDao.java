package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.course.bean.OrderCourseInfo;
import com.course.bean.TeaCouInfo;
import com.course.bean.TeacherInfor;

public class TeaCouInfoDao implements TableDao{
	private Connection conn;
	private PreparedStatement prpst;
	
	public TeaCouInfoDao(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof TeaCouInfo) {
			// 向下转型
			TeaCouInfo teaCouInfo = (TeaCouInfo)object;
			String sql = "insert into teacouinfo values(1,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, teaCouInfo.getTeaCouTeaNum());
			prpst.setString(2, teaCouInfo.getTeaCourCourNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teaCouInfo添加执行失败");
				return false;
			}
		}else {
			System.out.println("不是teaCouInfo的父类");
			return false;
		}
		return true;
	}
	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof TeaCouInfo) {
			// 向下转型
			TeaCouInfo teaCouInfo = (TeaCouInfo)object;
			String sql = "update teacouinfo set teacou_teanum=?,teacou_conum=?";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, teaCouInfo.getTeaCouTeaNum());
			prpst.setString(2, teaCouInfo.getTeaCourCourNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teaCouInfo更新执行失败");
				return false;
			}
		}else {
			System.out.println("不是teaCouInfo的父类");
			return false;
		}
		return true;
	}
	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof TeaCouInfo) {
			// 向下转型
			TeaCouInfo teaCouInfo = (TeaCouInfo)object;
			String sql = "update teacouinfo set teacou_id=0 where teacou_teanum=?,teacou_conum=?";
			// String sql = "delete from teacouinfo where teacou_teanum=? and teacou_conum=?";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, teaCouInfo.getTeaCouTeaNum());
			prpst.setString(2, teaCouInfo.getTeaCourCourNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("teaCouInfo删除执行失败");
				return false;
			}
		}else {
			System.out.println("不是teaCouInfo的父类");
			return false;
		}
		return true;
	}
	@Override
	public ArrayList<TeaCouInfo> findAll() throws Exception {
		String sql = "select teacou_teanum,tea_name,teacou_conum,cou_name from teacouinfo,teacherinfo,courseinfo where teacou_teanum=tea_num and cou_num=teacou_conum and teacou_id=1";
		prpst = conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的teacouin错误");
			return null;
		}
		ArrayList<TeaCouInfo> list = new ArrayList<>();
		while(rSet.next()) {
			TeaCouInfo teaCouInfo = new TeaCouInfo();
			teaCouInfo.setTeaCouTeaNum(rSet.getString("teacou_teanum"));
			teaCouInfo.setTeaCourCourNum(rSet.getString("teacou_conum"));
			teaCouInfo.setCouName(rSet.getString("cou_name"));
			teaCouInfo.setTeaName(rSet.getString("tea_name"));
			list.add(teaCouInfo);
		}
		return list;
	}
	
	@Deprecated
	public Object findByMainKey(String mainKey) throws Exception {
		return null;
	}
	
	/**
	 * 根据教师号查询课程号
	 * @param teaNum 教师号
	 * @return 返回包含课程号的list
	 */
	public ArrayList<String> findByTeaNum(String teaNum) throws Exception{
		String sql = "select teacou_conum from teacouinfo where teacou_teanum=? and teacou_id=1";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, teaNum);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询teanum->cournum错误");
			return null;
		}
		ArrayList<String> list = new ArrayList<>();
		while(rSet.next()) {
			list.add(rSet.getString("teacou_conum"));
		}
		return list;
	}
	
	/**
	 * 根据课程号查找教师
	 * @param coNum 课程号
	 * @return 包含教师号的list
	 */
	public ArrayList<TeacherInfor> findByCoNum(String coNum) throws Exception{
		String sql = "select teacou_teanum,tea_name from teacouinfo,teacherinfo where teacou_teanum=tea_num and teacou_id=1 and teacou_conum=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, coNum);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询conum->teanum错误");
			return null;
		}
		ArrayList<TeacherInfor> list = new ArrayList<>();
		while(rSet.next()) {
			TeacherInfor teacherInfor = new TeacherInfor();
			teacherInfor.setTeaNum(rSet.getString("teacou_teanum"));
			teacherInfor.setTeaName(rSet.getString("tea_name"));
			list.add(teacherInfor);
		}
		return list;
	}
	
	/**
	 * 查询教师授课信息
	 * @param teaName
	 * @param couName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<TeaCouInfo> findByMultiKey(String teaName,String couName) throws Exception {
		String sql = "select teacou_teanum,tea_name,teacou_conum,cou_name from teacouinfo,teacherinfo,courseinfo where teacou_teanum=tea_num and cou_num=teacou_conum and teacou_id=1 and tea_name=? and cou_name=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, teaName);
		prpst.setString(2, couName);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("多添加查询错误");
			return null;
		}
		ArrayList<TeaCouInfo> list = new ArrayList<>();
		while(rSet.next()) {
			TeaCouInfo teaCouInfo = new TeaCouInfo();
			teaCouInfo.setTeaCouTeaNum(rSet.getString("teacou_teanum"));
			teaCouInfo.setTeaName(rSet.getString("tea_name"));
			teaCouInfo.setTeaCourCourNum(rSet.getString("teacou_conum"));
			teaCouInfo.setCouName(rSet.getString("cou_name"));
			list.add(teaCouInfo);
		}
		return list;
	}
	
	/**
	 * 教师查询指授课信息
	 * @param teaNum 教师号
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderCourseInfo> teaFindTeaCou(String teaNum) throws Exception{
		String sql = "select teacou_conum,cou_name,cou_type,cou_xs,cou_redit from courseinfo,teacouinfo where teacou_conum=cou_num and teacou_teanum=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, teaNum);
		ResultSet rSet = prpst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(rSet.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouCouNum(rSet.getString("teacou_conum"));
			orderCourseInfo.setCouName(rSet.getString("cou_name"));
			orderCourseInfo.setCouType(rSet.getString("cou_type"));
			orderCourseInfo.setCouXs(rSet.getString("cou_xs"));
			orderCourseInfo.setCouRedit(rSet.getInt("cou_redit"));
			list.add(orderCourseInfo);
		}
		return list;
	}

	/**
	 * 根据课程号或教师号查询教师课程信息
	 * @param teaNum
	 * @param couNum
	 * @return
	 * @throws Exception
	 */
	public ArrayList<TeaCouInfo> findByTeaNumCouNum(String teaNum,String couNum) throws Exception{
		String sql = "select teacou_teanum,tea_name,teacou_conum,cou_name from teacouinfo,teacherinfo,courseinfo where teacou_teanum=tea_num and cou_num=teacou_conum and teacou_id=1";
		if(teaNum != null) {
			sql += " and teacou_teanum="+teaNum;
		}
		if(couNum != null) {
			sql += " and teacou_conum="+couNum;
		}
		
		Statement statement = conn.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		ArrayList<TeaCouInfo> list = new ArrayList<>();
		while(rSet.next()) {
			TeaCouInfo teaCouInfo = new TeaCouInfo(1, rSet.getString("teacou_teanum"), rSet.getString("teacou_conum"));
			teaCouInfo.setCouName(rSet.getString("cou_name"));
			teaCouInfo.setTeaName(rSet.getString("tea_name"));
			list.add(teaCouInfo);
		}
		return list;
		
	}
}
