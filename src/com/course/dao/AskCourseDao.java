package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.course.bean.AskCourse;

/**
 * AskCourse的访问对象?
 * 
 * @author Thingcor 管理员对某一个特定表的增删改查 传入TableHead，获取对应表的info
 */
public class AskCourseDao implements TableDao {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rst;

	public AskCourseDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		AskCourse info = null;
		if (object instanceof AskCourse) {
			info = (AskCourse) object;
			String sql = "insert into askcourse values(0,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getAskCouTeaNum());
			pst.setString(2, info.getAskCouCouNum());
			int row = pst.executeUpdate();
			if (row != 1) {
				System.out.println("askcourse添加执行错误");
				return false;
			}
		} else {
			System.out.println("对象不是askcourse的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doUpdate(Object object) throws Exception {
		AskCourse info = null;
		if (object instanceof AskCourse) {
			info = (AskCourse) object;
			String sql = "updata askcourse set askcou_tea_num =? and set askcou_cou_num= ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getAskCouTeaNum());
			pst.setString(2, info.getAskCouCouNum());
			int row = pst.executeUpdate();
			if (row != 1) {
				System.out.println("askcourse修改执行错误");
				return false;
			}
		} else {
			System.out.println("对象不是askcourse的父类");
			return false;
		}

		return true;
	}

	@Override
	public boolean doDelete(Object object) throws Exception {
		AskCourse info = null;
		if (object instanceof AskCourse) {
			info = (AskCourse) object;
			String sql = "delete from askcourse where askcou_tea_num=? and askcou_cou_num=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getAskCouTeaNum());
			pst.setString(2, info.getAskCouCouNum());
			pst.executeUpdate();
		} else {
			System.out.println("对象不是askcourse的父类");
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<AskCourse> findAll() throws Exception {
		ArrayList<AskCourse> list = new ArrayList<>();
		String sql = "select askcou_tea_num,askcou_cou_num,tea_name,cou_name from askcourse,courseinfo,teacherinfo where askcou_tea_num=tea_num and askcou_cou_num=cou_num";
		pst = conn.prepareStatement(sql);
		rst = pst.executeQuery();
		while (rst.next()) {
			AskCourse course = new AskCourse();
			course.setAskCouCouNum(rst.getString("askcou_cou_num"));
			course.setAskCouTeaNum(rst.getString("askcou_tea_num"));
			course.setCouName(rst.getString("cou_name"));
			course.setTeaName(rst.getString("tea_name"));
			list.add(course);
		}
		return list;
	}

	@Override
	public Object findByMainKey(String teaNum) throws Exception {
		String sql = "select askcou_tea_num,askcou_cou_num,tea_name,cou_name from askcourse,courseinfo,teacherinfo where askcou_tea_num=tea_num and askcou_cou_num=cou_num and tea_num=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, teaNum);
		rst = pst.executeQuery();
		ArrayList<AskCourse> list = new ArrayList<>();
		while (rst.next()) {
			AskCourse course = new AskCourse();
			course.setAskCouCouNum(rst.getString("askcou_cou_num"));
			course.setCouName(rst.getString("cou_name"));
			course.setAskCouTeaNum(rst.getString("askcou_tea_num"));
			course.setTeaName(rst.getString("tea_name"));
			list.add(course);
		}
		return list;
	}

	/**
	 * 通过指定的条件查询<br>考虑废弃
	 * @param couNum
	 * @param teaNum
	 * @return
	 * @throws Exception
	 */
	public ArrayList<AskCourse> findByMultiKey(String couNum,String teaNum) throws Exception {
		ArrayList<AskCourse> list = new ArrayList<>();
		String sql = "select askcou_tea_num,askcou_cou_num,tea_name,cou_name from askcourse,courseinfo,teacherinfo where askcou_tea_num=tea_num,and askcou_cou_num=cou_num and cou_num=? and tea_num=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, couNum);
		pst.setString(2, teaNum);
		rst = pst.executeQuery();
		while (rst.next()) {
			AskCourse course = new AskCourse();
			course.setAskCouID(rst.getInt("askcou_id"));
			course.setAskCouCouNum(rst.getString("askcou_cou_num"));
			course.setAskCouTeaNum(rst.getString("askcou_tea_num"));
			course.setCouName(rst.getString("cou_name"));
			course.setTeaName(rst.getString("tea_name"));
			list.add(course);
		}
		return list;
	}
}
