package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.course.bean.OrderCourseInfo;
import com.course.bean.SelCourse;

public class SelCourseDao implements TableDao{
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rst ;
	public SelCourseDao(Connection conn) {
		this.conn = conn;
	}


	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof SelCourse) {
			// 向下转型
			SelCourse info = (SelCourse)object;
			String sql = "insert into selcourse values(null,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getSelectYear());
			pst.setString(2, info.getSelectGrade());
			pst.setInt(3, Integer.parseInt(info.getSelectTerm()));
			pst.setString(4, info.getSelectTeaNum());
			pst.setString(5, info.getSelectStuNum());
			pst.setString(6, info.getSelectCourseNum());
			pst.setString(7, info.getSelTeaTime());
			pst.setString(8, info.getSelTeaPlace());
			int row = pst.executeUpdate();
			if(row != 1) {
				System.out.println("SelCourse添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是SelCourse的父类");
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public boolean doUpdate(Object object) throws Exception {
	
		if(object instanceof SelCourse) {
			// 向下转型
			SelCourse info = (SelCourse)object;
			String sql = "updata selcourse set select_year= ?,select_grade =?,select_term = ?,seltea_num =?,selstu_num =?,selcourse_num =?,sel_des=?";
			pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getSelectYear());
			pst.setString(2, info.getSelectGrade());
			pst.setString(3, info.getSelectTerm());
			pst.setString(4, info.getSelectTeaNum());
			pst.setString(5, info.getSelectStuNum());
			pst.setString(6, info.getSelectCourseNum());
			int row = pst.executeUpdate();
			if(row != 1) {
				System.out.println("SelCourse修改执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是SelCourse的父类");
			return false;
		}
		return true;
	
	}
	
	@Override
	public boolean doDelete(Object object) throws Exception {
		boolean flag = true;
		if(object instanceof SelCourse) {
			// 向下转型
			SelCourse info = (SelCourse)object;
			String sql = "delete from selcourse where selstu_num=? and selcourse_num=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, info.getSelectStuNum());
			pst.setString(2, info.getSelectCourseNum());
			if(pst.executeUpdate() == 0) {
				flag=false;
			}
		}else {
			System.out.println("对象不是SelCourse的父类");
			flag =  false;
		}
		return flag;
	}
	
	@Override
	public ArrayList<SelCourse> findAll() throws Exception {
		ArrayList<SelCourse> list = new ArrayList<>();
		String sql = "select * from selcourse";
		pst = conn.prepareStatement(sql);
		rst = pst.executeQuery();
		while(rst.next()) {
			SelCourse n = new SelCourse();
			n.setSelectID(rst.getInt("select_id"));
			n.setSelectYear(rst.getString("select_year"));
			n.setSelectGrade(rst.getString("select_grade"));
			n.setSelectTerm(rst.getString("select_term"));
			n.setSelectTeaNum(rst.getString("seltea_mum"));
			n.setSelectStuNum(rst.getString("selstu_num"));
			n.setSelectCourseNum(rst.getString("selcourse_num"));
			list.add(n);
		}
			return list;
	}
	
	@Override
	public ArrayList<SelCourse> findByMainKey(String mainKey) throws Exception {
		
		// 完整语句后续完善
		/*select cou_name,sel_teatime,tea_name,sel_teaplace from teacherinfo,selcourse,courseinfo where tea_num = seltea_num and selcourse_num = cou_num and term=? and year=?*/
		String sql = "select cou_name,sel_teatime,tea_name,sel_teaplace from teacherinfo,selcourse,courseinfo where tea_num = seltea_num and selcourse_num = cou_num and selstu_num=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, mainKey);
		rst = pst.executeQuery();
		ArrayList<SelCourse> list = new ArrayList<>();
		while(rst.next()) {
			SelCourse n = new SelCourse();
			n.setSelectCourseNum(rst.getString("cou_name"));
			n.setSelTeaTime(rst.getString("sel_teatime"));
			n.setSelectTeaNum(rst.getString("tea_name"));
			n.setSelTeaPlace(rst.getString("sel_teaplace"));
			list.add(n);
		}
		return list;
	}
	
	/**
	 * 多条件查询，拼接条件
	 * @param grade
	 * @param couName
	 * @param teaName
	 * @param stuName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SelCourse> findByMultiKey(String grade,String couName,String teaName,String stuName) throws Exception {
		String sql = "select distinct select_term,select_year,select_grade,stu_major,cou_name,tea_name,stu_name from selcourse,courseinfo,teacherinfo,studentinfo "
				+ "where selstu_num = stu_num and selcourse_num = cou_num and tea_num = seltea_num";
		if(null != grade) {
			sql += " and select_grade="+grade;
		}
		if(null != couName) {
			sql += " and cou_name='"+couName+"'";
		}
		if(null != teaName) {
			sql += " and tea_name='"+teaName+"'";
		}
		if(null != stuName) {
			sql += " and stu_name='"+stuName+"'";
		}
		
		Statement statement = conn.createStatement();
		rst = statement.executeQuery(sql);
		ArrayList<SelCourse> list = new ArrayList<>();
		while(rst.next()) {
			SelCourse selCourse = new SelCourse();
			selCourse.setSelectTerm(rst.getString("select_term"));
			selCourse.setSelectYear(rst.getString("select_year"));
			selCourse.setSelectGrade(rst.getString("select_grade"));
			selCourse.setStuMajor(rst.getString("stu_major"));
			selCourse.setCouName(rst.getString("cou_name"));
			selCourse.setTeaName(rst.getString("tea_name"));
			selCourse.setStuName(rst.getString("stu_name"));
			list.add(selCourse);
		}
		return list;
	}
	
	/**
	 * 查询指定学生指定学年学期内的选修的课程
	 * @param stuNum
	 * @param year
	 * @param term
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderCourseInfo> stuFindSelCouBy(String stuNum,String year,String term) throws Exception{
		String sql = "select distinct selcourse_num,cou_name,cou_type,cou_redit,cou_xs,seltea_num,tea_name from selcourse,courseinfo,teacherinfo where selcourse_num=cou_num and seltea_num=tea_num and selstu_num=? and select_year=? and select_term=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, stuNum);
		pst.setString(2, year);
		pst.setInt(3, Integer.parseInt(term));
		rst = pst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(rst.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouCouNum(rst.getString("selcourse_num"));
			orderCourseInfo.setCouName(rst.getString("cou_name"));
			orderCourseInfo.setCouType(rst.getString("cou_type"));
			orderCourseInfo.setCouRedit(rst.getInt("cou_redit"));
			orderCourseInfo.setCouXs(rst.getString("cou_xs"));
			orderCourseInfo.setOrdCouTeaNum(rst.getString("seltea_num"));
			orderCourseInfo.setTeaName(rst.getString("tea_name"));
//			orderCourseInfo.setOrdCouTeaTime(rst.getString("sel_teatime"));
//			orderCourseInfo.setOrdCouTeaPlace(rst.getString("sel_teaplace"));
			list.add(orderCourseInfo);
		}
		return list;
	}
	
	public ArrayList<OrderCourseInfo> gradeFindByTea(String teaNum,String courNum,String term,String year) throws Exception{
		String sql = "select select_grade,select_term,select_year,selcourse_num,cou_name,cou_type,stu_num,stu_name from selcourse,studentinfo,courseinfo where seltea_num=? and cou_num = selcourse_num and selstu_num = stu_num and cou_num=? and cou_term=? and select_year=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, teaNum);
		pst.setString(2, courNum);
		pst.setInt(3, Integer.parseInt(term));
		pst.setString(4, year);
		ResultSet resultSet = pst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(resultSet.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouGrade(resultSet.getString("select_grade"));
			orderCourseInfo.setOrdCouTerm(resultSet.getInt("select_term"));
			orderCourseInfo.setOrdCouYear(resultSet.getString("select_year"));
			orderCourseInfo.setOrdCouCouNum(resultSet.getString("selcourse_num"));
			orderCourseInfo.setCouName(resultSet.getString("cou_name"));
			orderCourseInfo.setCouType(resultSet.getString("cou_type"));
			orderCourseInfo.setOrdCouTeaNum(resultSet.getString("stu_num"));
			orderCourseInfo.setTeaName(resultSet.getString("stu_name"));
			list.add(orderCourseInfo);
		}
		System.out.println("dded"+list);
		return list;
	}
	
	public ArrayList<OrderCourseInfo> stuFindGradeByCou(String stuNum,String year,String term) throws Exception{
		String sql = "select selcourse_num,cou_name,cou_type,grade_c,grade_credit,Sum_grade(selstu_num) from selcourse,courseinfo,student_grade where selstu_num=? and select_year=? and select_term=? and selcourse_num = cou_num and selcourse_num=grade_cou_num and grade_stu_num=selstu_num;";
		pst = conn.prepareStatement(sql);
		pst.setString(1, stuNum);
		pst.setString(2, year);
		pst.setInt(3, Integer.parseInt(term));
		ResultSet resultSet = pst.executeQuery();
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		while(resultSet.next()) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouCouNum(resultSet.getString("selcourse_num"));
			orderCourseInfo.setCouName(resultSet.getString("cou_name"));
			orderCourseInfo.setCouType(resultSet.getString("cou_type"));
			orderCourseInfo.setCouXs(resultSet.getInt("grade_c")+"");
			orderCourseInfo.setCouRedit(resultSet.getInt("grade_credit"));
			orderCourseInfo.setOrdCouMax(resultSet.getInt("Sum_grade(selstu_num)"));
			list.add(orderCourseInfo);
		}
		System.out.println("dded"+list);
		return list;
	}
	
}
