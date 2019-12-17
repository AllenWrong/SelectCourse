package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.course.bean.OrderCourseInfo;
import com.course.bean.StuGrade;

public class StuGradeDao implements TableDao{
	private Connection conn;
	private PreparedStatement prpst;
	
	public StuGradeDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof StuGrade) {
			// 向下转型
			StuGrade stuGrade = (StuGrade)object;
			String sql = "insert into student_grade (grade_cou_num,grade_stu_num,grade_c) values(?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, stuGrade.getGradeCouNum());
			prpst.setString(2, stuGrade.getGradeStuNum());
			prpst.setInt(3, stuGrade.getGrade());
			if(prpst.executeUpdate() != 1) {
				System.out.println("StuGrade添加执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是StuGrade的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof StuGrade) {
			// 向下转型
			StuGrade stuGrade = (StuGrade)object;
			String sql = "update table student_grade values(?,?,?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, stuGrade.getGradeID());
			prpst.setString(2, stuGrade.getGradeCouNum());
			prpst.setString(3, stuGrade.getGradeStuNum());
			prpst.setInt(4, stuGrade.getGrade());
			prpst.setInt(5, stuGrade.getGradeCredit());
			if(prpst.executeUpdate() != 1) {
				System.out.println("StuGrade更新执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是StuGrade的父类");
			return false;
		}
		return true;
	}

	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof StuGrade) {
			// 向下转型
			StuGrade stuGrade = (StuGrade)object;
			String sql = "delete from student_grade where grade_id=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, stuGrade.getGradeID());
			
			if(prpst.executeUpdate() != 1) {
				System.out.println("StuGrade删除执行错误");
				return false;
			}
		}else {
			System.out.println("对象不是StuGrade的父类");
			return false;
		}
		return true;
	}

	public ArrayList<StuGrade> findAll() throws Exception {
		String sql = "select * from student_grade";
		prpst = conn.prepareStatement(sql);
		ResultSet resultSet = prpst.executeQuery();
		if(resultSet == null) {
			System.out.println("student_grade查询错误");
			return null;
		}
		ArrayList<StuGrade> list = new ArrayList<>();
		while(resultSet.next()) {
			StuGrade stuGrade = new StuGrade(resultSet.getInt("grade_id"),resultSet.getString("grade_con_num"),
					resultSet.getString("grade_stu_num"),resultSet.getInt("grade"),resultSet.getInt("grade_credit"));
			list.add(stuGrade);
		}
		return list;
	}

	/**
	 * 无意义，子类不实现
	 */
	@Deprecated
	public Object findByMainKey(String mainKey) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * 查询指定学生的成绩信息
	 */
	public ArrayList<StuGrade> findGradeByStu(String stuNum) throws Exception{
		String sql = "select * from student_grade where grade_stu_num=?";
		prpst = conn.prepareStatement(sql);
		ResultSet resultSet = prpst.executeQuery();
		if(resultSet == null) {
			System.out.println("student_grade查询错误");
			return null;
		}
		ArrayList<StuGrade> list = new ArrayList<>();
		while(resultSet.next()) {
			StuGrade stuGrade = new StuGrade(resultSet.getInt("grade_id"),resultSet.getString("grade_con_num"),
					resultSet.getString("grade_stu_num"),resultSet.getInt("grade"),resultSet.getInt("grade_credit"));
			list.add(stuGrade);
		}
		return list;
	}
	
	/**
	 * 根据学年查询成绩
	 * @param year 学年
	 * @return
	 * @throws Exception
	 */
	public ArrayList<StuGrade> findByYear(String year,String stuNum) throws Exception{
		String sql="select cou_num,cou_name,cou_type,grade,grade_credit from courseinfo,student_grade,termplaninfo where grade_cou_num = cou_num and tp_year=? and grade_stu_num=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, year);
		ResultSet rSet = prpst.executeQuery();
		ArrayList<StuGrade> list = new ArrayList<>();
		while(rSet.next()) {
			StuGrade stuGrade = new StuGrade();
			stuGrade.setCouNum(rSet.getString("cou_num"));
			stuGrade.setCouName(rSet.getString("cou_name"));
			stuGrade.setCouType(rSet.getString("cou_type"));
			stuGrade.setGrade(rSet.getInt("grade"));
			stuGrade.setGradeCredit(rSet.getInt("grade_credit"));
			list.add(stuGrade);
		}
		return list;
	}
	
	/**
	 * 根据学年，学期查询成绩
	 * @param year 学年
	 * @param term 学期
	 * @return
	 * @throws Exception
	 */
	public ArrayList<StuGrade> findByYearTerm(String year,String couTerm,String stuNum) throws Exception{
		String sql="select cou_num,cou_name,cou_type,grade,grade_credit from courseinfo,student_grade,termplaninfo where tp_coursenum=grade_cou_num and grade_cou_num = cou_num and tp_year=? and cou_term=? and grade_stu_num=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, year);
		prpst.setInt(2, Integer.parseInt(couTerm));
		prpst.setString(3, stuNum);
		ResultSet rSet = prpst.executeQuery();
		ArrayList<StuGrade> list = new ArrayList<>();
		while(rSet.next()) {
			StuGrade stuGrade = new StuGrade();
			stuGrade.setCouNum(rSet.getString("cou_num"));
			stuGrade.setCouName(rSet.getString("cou_name"));
			stuGrade.setCouType(rSet.getString("cou_type"));
			stuGrade.setGrade(rSet.getInt("grade"));
			stuGrade.setGradeCredit(rSet.getInt("grade_credit"));
			list.add(stuGrade);
		}
		return list;
	}
	
	/**
	 * 教师查询指定的课程信息，进行成绩的录入
	 * @param teaNum
	 * @param couNum
	 * @param term
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public ArrayList<StuGrade> findByTeaCTY(String teaNum,String couNum,String term,String year) throws Exception {
		String sql="select selcourse_num,cou_name,stu_name,stu_num from selcourse,courseinfo,studentinfo where selcourse_num=cou_num and and selstu_num=stu_num and seltea_num=? and selcourse_num=? and select_term=? and select_year=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, teaNum);
		prpst.setString(2, couNum);
		prpst.setInt(3, Integer.parseInt(term));
		prpst.setString(4, year);
		ResultSet rSet = prpst.executeQuery();
		ArrayList<StuGrade> list = new ArrayList<>();
		while(rSet.next()) {
			StuGrade stuGrade = new StuGrade();
			stuGrade.setCouNum(rSet.getString("selcourse_num"));
			stuGrade.setCouName(rSet.getString("cou_name"));
			stuGrade.setStuName(rSet.getString("stu_name"));
			stuGrade.setGradeStuNum(rSet.getString("stu_num"));
			list.add(stuGrade);
		}
		return list;
	}
	
	/**
	 * 查询指定课程的成绩信息
	 * @param teaNum 
	 * @param couNum
	 * @param term
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public ArrayList<StuGrade> findGradeByTea(String teaNum,String couNum,String term,String year) throws Exception{
		String sql ="select selcourse_num,cou_name,stu_name,stu_num,grade from selcourse,courseinfo,studentinfo,student_grade where selcourse_num=cou_num and and selstu_num=stu_num and selstu_num=grade_stu_num "
				+ "and seltea_num=? and selcourse_num=? and select_term=? and select_year=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, teaNum);
		prpst.setString(2, couNum);
		prpst.setInt(3, Integer.parseInt(term));
		prpst.setString(4, year);
		ResultSet rSet = prpst.executeQuery();
		ArrayList<StuGrade> list = new ArrayList<>();
		while(rSet.next()) {
			StuGrade stuGrade = new StuGrade();
			stuGrade.setCouNum(rSet.getString("selcourse_num"));
			stuGrade.setCouName(rSet.getString("cou_name"));
			stuGrade.setStuName(rSet.getString("stu_name"));
			stuGrade.setGradeStuNum(rSet.getString("stu_num"));
			stuGrade.setGrade(rSet.getInt("grade"));
			list.add(stuGrade);
		}
		return list;
	}
	
	public ArrayList<OrderCourseInfo> teaFindGradeByCou(String teaNum,String courNum,String term,String year) throws Exception{
			String sql = "select select_grade,select_term,select_year,selcourse_num,cou_name,cou_type,grade_credit,stu_num,stu_name,grade_c from selcourse,studentinfo,courseinfo,student_grade where seltea_num=? and cou_num = selcourse_num and grade_cou_num=cou_num and grade_stu_num=stu_num and selstu_num = stu_num and cou_num=? and cou_term=? and select_year=?";
			prpst = conn.prepareStatement(sql);
			prpst.setString(1, teaNum);
			prpst.setString(2, courNum);
			prpst.setInt(3, Integer.parseInt(term));
			prpst.setString(4, year);
			ResultSet resultSet = prpst.executeQuery();
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
				orderCourseInfo.setCouRedit(resultSet.getInt("grade_credit"));
				orderCourseInfo.setCouXs(resultSet.getInt("grade_c")+"");
				list.add(orderCourseInfo);
			}
			System.out.println("dded"+list);
			return list;
	}
}
