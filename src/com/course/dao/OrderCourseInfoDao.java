package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.course.bean.OrderCourseInfo;

public class OrderCourseInfoDao implements TableDao{
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rst ;
	public OrderCourseInfoDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof OrderCourseInfo) {
			// 向下转型
			OrderCourseInfo info = (OrderCourseInfo)object;
			String sql = "insert into ordercourseinfo values(?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, info.getOrdCouID());
			pst.setString(2, info.getOrdCouMajorNum());
			pst.setString(3, info.getOrdCouGrade());
			pst.setString(4, info.getOrdCouYear());
			pst.setInt(5, info.getOrdCouTerm());
			pst.setString(6, info.getOrdCouCouNum());
			pst.setString(7, info.getOrdCouTeaNum());
			pst.setInt(8, info.getOrdCouMax());
			pst.setString(9, info.getOrdCouQzz());
			pst.setString(10, info.getOrdCouTeaTime());
			pst.setString(11, info.getOrdCouTeaPlace());
			int row = pst.executeUpdate();
			if(row!= 1) {
				System.out.println("OrderCourseInfo添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是OrderCourseInfo的父类");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean doUpdate(Object object) throws Exception {
		boolean flag = false;
		if(object instanceof OrderCourseInfo) {
			// 向下转型
			OrderCourseInfo info = (OrderCourseInfo)object;
			if(doDelete(info) && doCreate(info)) {
				flag = true;
			}
		}else {
			System.out.println("转型错误，ordercourseinfo");
			flag = false;
		}
		return flag;
	}
//			String sql = "updata ordercourseinfo set ordcou_id= ?,ordcou_major_num=?,ordcou_grade=?,ordcou_year = ?,ordcou_term = ?,ordcou_cou_num= ?,ordcou_tea_num=?,ordcou_max=?,ordcou_qzz=?,ordcou_teatime=?,ordcou_teaplace=?";
//	    	pst = conn.prepareStatement(sql);
//	    	pst.setInt(1, info.getOrdCouID());
//			pst.setString(2, info.getOrdCouMajorNum());
//			pst.setString(3, info.getOrdCouGrade());
//			pst.setString(4, info.getOrdCouYear());
//			pst.setInt(5, info.getOrdCouTerm());
//			pst.setString(6, info.getOrdCouCouNum());
//			pst.setString(7, info.getOrdCouTeaNum());
//			pst.setInt(8, info.getOrdCouMax());
//			pst.setString(9, info.getOrdCouQzz());
//			pst.setString(10, info.getOrdCouTeaTime());
//			pst.setString(11, info.getOrdCouTeaPlace());
	
	
	@Override
	public boolean doDelete(Object object) throws Exception {
	
		if(object instanceof OrderCourseInfo) {
			// 向下转型
			OrderCourseInfo info = (OrderCourseInfo)object;
			String sql = "delete from ordercourseinfo where ordcou_tea_num=? and ordcou_cou_num=? and ordcou_grade=? and ordcou_term=? and ordcou_year=? and ordcou_teaTime=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getOrdCouTeaNum());
			pst.setString(2, info.getOrdCouCouNum());
			pst.setString(3, info.getOrdCouGrade());
			pst.setInt(4, info.getOrdCouTerm());
			pst.setString(5, info.getOrdCouYear());		
			pst.setString(6, info.getOrdCouTeaTime());
			int row = pst.executeUpdate();
			if(row!= 1) {
				System.out.println("OrderCourseInfo删除执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是OrderCourseInfo的父类");
			return false;
		}
		return true;
	
	}
	
	
	@Override
	public ArrayList<OrderCourseInfo> findAll() throws Exception {
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		String sql = "select * from ordercourseinfo";
		pst = conn.prepareStatement(sql);
		rst = pst.executeQuery();
		while(rst.next()) {
			OrderCourseInfo n = new OrderCourseInfo();
			n.setOrdCouID(rst.getInt("ordcou_id"));
			n.setOrdCouMajorNum(rst.getString("ordcou_major_num"));
			n.setOrdCouGrade(rst.getString("ordcou_grade"));
			n.setOrdCouYear(rst.getString("ordcou_year"));
			n.setOrdCouTerm(rst.getInt("ordcou_term"));
			n.setOrdCouCouNum(rst.getString("ordcou_cou_num"));
			n.setOrdCouTeaNum(rst.getString("ordcou_tea_num"));
			n.setOrdCouMax(rst.getInt("ordcou_max"));
			n.setOrdCouQzz(rst.getString("ordcou_qzz"));
			n.setOrdCouTeaTime(rst.getString("ordcou_teatime"));
			n.setOrdCouTeaPlace(rst.getString("ordcou_teaplace"));
			list.add(n);
		}
			return list;
	}
	
	
	@Override
	public Object findByMainKey(String mainKey) throws Exception {
		OrderCourseInfo n = new OrderCourseInfo();
		String sql = "select * from ordercourseinfo where ordcou_id =?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.parseInt(mainKey));
		rst = pst.executeQuery();
		while(rst.next()) {
			n.setOrdCouID(rst.getInt("ordcou_id"));
			n.setOrdCouMajorNum(rst.getString("ordcou_major_num"));
			n.setOrdCouGrade(rst.getString("ordcou_grade"));
			n.setOrdCouYear(rst.getString("ordcou_year"));
			n.setOrdCouTerm(rst.getInt("ordcou_term"));
			n.setOrdCouCouNum(rst.getString("ordcou_cou_num"));
			n.setOrdCouTeaNum(rst.getString("ordcou_tea_num"));
			n.setOrdCouMax(rst.getInt("ordcou_max"));
			n.setOrdCouQzz(rst.getString("ordcou_qzz"));
			n.setOrdCouTeaTime(rst.getString("ordcou_teatime"));
			n.setOrdCouTeaPlace(rst.getString("ordcou_teaplace"));
		}
		return n;
	}
	
	/**
	 * 根据学期学年和年级查看排课信息
	 * @param grade 年级
	 * @param term 学期
	 * @param year 学年
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderCourseInfo> findByGreadTY(String grade,String term,String year) throws Exception {
		String sql = "select ordcou_major_num,majorplan_name,ordcou_cou_num,cou_name,ordcou_tea_num,tea_name,ordcou_teatime,ordcou_teaplace,ordcou_qzz,ordcou_max "
				+ "from ordercourseinfo,teacherinfo,majorplaninfo,courseinfo where ordcou_cou_num=cou_num and ordcou_major_num= majorplan_num and ordcou_tea_num=tea_num and ordcou_grade=? and ordcou_term=? and ordcou_year=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, grade);
		pst.setInt(2, Integer.parseInt(term));
		pst.setString(3, year);
		rst = pst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(rst.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouMajorNum(rst.getString("ordcou_major_num"));
			orderCourseInfo.setMajorPlanName(rst.getString("majorplan_name"));
			orderCourseInfo.setOrdCouCouNum(rst.getString("ordcou_cou_num"));
			orderCourseInfo.setCouName(rst.getString("cou_name"));
			orderCourseInfo.setOrdCouTeaNum(rst.getString("ordcou_tea_num"));
			orderCourseInfo.setTeaName(rst.getString("tea_name"));
			orderCourseInfo.setOrdCouTeaTime(rst.getString("ordcou_teatime"));
			orderCourseInfo.setOrdCouTeaPlace(rst.getString("ordcou_teaplace"));
			orderCourseInfo.setOrdCouQzz(rst.getString("ordcou_qzz"));
			orderCourseInfo.setOrdCouMax(rst.getInt("ordcou_max"));
			list.add(orderCourseInfo);
		}
		return list;
	}
	
	/**
	 * 通过教师号查询安排的课程<br/>
	 * 这里只需要知道课程名，时间，地点，起止周即可
	 * @param teaNum
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderCourseInfo> findByTeacher(String teaNum) throws Exception{
		String sql = "select cou_name,ordcou_teatime,ordcou_teaplace,ordcou_qzz,tea_name from ordercourseinfo,courseinfo,teacherinfo where ordcou_tea_num=? and ordcou_cou_num = cou_num and tea_num=ordcou_tea_num";
		pst = conn.prepareStatement(sql);
		pst.setString(1, teaNum);
		rst = pst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(rst.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouCouNum(rst.getString("cou_name"));
			orderCourseInfo.setOrdCouTeaTime(rst.getString("ordcou_teatime"));
			orderCourseInfo.setOrdCouTeaPlace(rst.getString("ordcou_teaplace"));
			orderCourseInfo.setOrdCouQzz(rst.getString("ordcou_qzz"));
			orderCourseInfo.setOrdCouTeaNum(rst.getString("tea_name"));
			list.add(orderCourseInfo);
		}
		return list;
	}
	
	/**
	 * 查询可选的课程
	 * @param year 学年
	 * @param couTerm 学期
	 * @param majorName 专业名
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderCourseInfo> findByYearTerm(String year,String couTerm,String majorName) throws Exception{
		String sql = "select cou_num,cou_name,cou_type,cou_redit,cou_xs,tea_name,ordcou_teatime,ordcou_teaplace from majorplaninfo,ordercourseinfo,courseinfo,teacherinfo where "
				+ "ordercou_cou_num = cou_num and ordcou_tea_num=tea_num and majorplan_num=ordcou_major_num and ordcou_term=? and ordcou_year=? and majorplan_name=?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.parseInt(couTerm));
		pst.setString(2, year);
		pst.setString(3, majorName);
		rst = pst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(rst.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouCouNum(rst.getString("ordcou_cou_num"));
			orderCourseInfo.setCouName(rst.getString("cou_name"));
			orderCourseInfo.setCouType(rst.getString("cou_type"));
			orderCourseInfo.setCouRedit(rst.getInt("cou_redit"));
			orderCourseInfo.setCouXs(rst.getString("cou_xs"));
			orderCourseInfo.setTeaName(rst.getString("tea_name"));
			orderCourseInfo.setOrdCouTeaTime(rst.getString("ordcou_teatime"));
			orderCourseInfo.setOrdCouTeaPlace(rst.getString("ordcou_teaplace"));
			list.add(orderCourseInfo);
		}
		return list;
	}
	
	/**
	 * 学生查询可选的课程
	 * @param year
	 * @param couTerm
	 * @param grade
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderCourseInfo> stuSearchSelectCourse(String year,String couTerm,String grade) throws Exception{
		String sql = "select distinct ordcou_cou_num,cou_name,cou_type,cou_redit,cou_xs,ordcou_tea_num,tea_name,ordcou_max from ordercourseinfo,teacherinfo,majorplaninfo,courseinfo where ordcou_cou_num=cou_num and ordcou_tea_num=tea_num and ordcou_grade=? and ordcou_term=? and ordcou_year=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, grade);
		pst.setInt(2, Integer.parseInt(couTerm));
		pst.setString(3, year);
		rst = pst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(rst.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouCouNum(rst.getString("ordcou_cou_num"));
			orderCourseInfo.setCouName(rst.getString("cou_name"));
			orderCourseInfo.setCouType(rst.getString("cou_type"));
			orderCourseInfo.setCouRedit(rst.getInt("cou_redit"));
			orderCourseInfo.setCouXs(rst.getString("cou_xs"));
			orderCourseInfo.setOrdCouTeaNum(rst.getString("ordcou_tea_num"));
			orderCourseInfo.setTeaName(rst.getString("tea_name"));
			orderCourseInfo.setOrdCouMax(rst.getInt("ordcou_max"));
			list.add(orderCourseInfo);
		}
		return list;
		
	}
	
	
	/**
	 * 查询上课时间和上课地点
	 * @param grade
	 * @param year
	 * @param term
	 * @param couNum
	 * @param teaNum
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderCourseInfo> findTimePlace(String grade,String year,String term,String couNum,String teaNum) throws Exception{
		String sql = "select ordcou_teatime,ordcou_teaplace from ordercourseinfo where ordcou_grade=? and ordcou_year=? and ordcou_term=? and ordcou_cou_num=? and ordcou_tea_num=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, grade);
		pst.setString(2, year);
		pst.setInt(3, Integer.parseInt(term));
		pst.setString(4, couNum);
		pst.setString(5, teaNum);
		rst = pst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(rst.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouTeaTime(rst.getString("ordcou_teatime"));
			orderCourseInfo.setOrdCouTeaPlace(rst.getString("ordcou_teaplace"));
			list.add(orderCourseInfo);
		}
		return list;
	}

}
