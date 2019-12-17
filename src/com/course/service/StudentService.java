package com.course.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.course.bean.OrderCourseInfo;
import com.course.bean.SelCourse;
import com.course.bean.StuGrade;
import com.course.bean.StuInfo;
import com.course.bean.StudentStruction;
import com.course.bean.TrainPlan;
import com.course.dao.MajorPlanInfoDao;
import com.course.dao.OrderCourseInfoDao;
import com.course.dao.SelCourseDao;
import com.course.dao.StuGradeDao;
import com.course.dao.StuInfoDao;
import com.course.dao.StudentStructionDao;
import com.course.jdbc.DatabaseConnection;

/**
 * 学生用户的业务处理
 * @author Thingcor
 *
 */
public class StudentService {
	private final String dbname="course_manager";
	/** 连接数据库的用户名*/
	private String username;
	/** 连接数据库的密码*/
	private String password;
	
	public StudentService(String username, String passwd) {
		this.username = username;
		this.password = passwd;
	}
	
	/**
	 * 查看指定学生的学籍信息
	 * @param mainKey 查看的学生的学号
	 * @return 返回学籍信息
	 */
	public Object viewStatusInfo(String mainKey) {
		Connection conn = DatabaseConnection.getConnection(username, password, dbname);
		ArrayList<StudentStruction> list = new ArrayList<>();
		StudentStructionDao stuDao = new StudentStructionDao(conn);
		try {
			list = stuDao.findByMainKey(mainKey);
		} catch (Exception e) {
			System.out.println("查看指定学生的学籍信息异常");
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查看学生的基本信息
	 * @param mainkKey 查询的学生的学号
	 * @return 返回基本信息对象
	 */
	public Object viewBasicInfo(String mainKey) {
		Connection conn = DatabaseConnection.getConnection(username, password, "course_manager");
		StuInfoDao stuInfoDao = new StuInfoDao(conn);
		StuInfo stuInfo = new StuInfo();
		try {
			stuInfo = (StuInfo)stuInfoDao.findByMainKey(mainKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return stuInfo;
	}
	
	/**
	 * 修改学生的基本信息
	 * @param object 修改的基本信息对象
	 * @return 返回修改的结构，正确或否
	 */
	public boolean updateBasicInfo(Object object) {
		Connection conn = DatabaseConnection.getConnection("", "", dbname);
		StuInfoDao stuInfoDao = new StuInfoDao(conn);
		boolean flag = false;
		try {
			flag = stuInfoDao.doUpdate(object);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	
	/**
	 * 查看学生的课表
	 * @param mainKey 被查看的学生的学号
	 * @return 返回表list，之后再转型
	 */
	public ArrayList<SelCourse> viewCourseTable(String mainKey) {
		Connection conn = DatabaseConnection.getConnection(username, password, "course_manager");
		SelCourseDao selCourseDao = new SelCourseDao(conn);
		ArrayList<SelCourse> list = new ArrayList<>();
		try {
			list = selCourseDao.findByMainKey(mainKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/**
	 * 查询该学生所在年级，所在专业的培养计划
	 * @param object 查询的学生对象
	 * @return 返回培养计划列表
	 */
	public ArrayList<TrainPlan> viewTrainPlan(String majorPlanName,String grade) {
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		ArrayList<TrainPlan> list = new ArrayList<>();
		MajorPlanInfoDao majorPlanInfoDao = new MajorPlanInfoDao(connection);
		try {
			list = majorPlanInfoDao.findMajorPlanByGrade(grade, majorPlanName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 按学年和学期查询成绩
	 * @param year 学年
	 * @param term 学期
	 * @return
	 */
	public ArrayList<StuGrade> viewGrade(String year,String couTerm,String stuNum) {
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		StuGradeDao stuGradeDao = new StuGradeDao(connection);
		ArrayList<StuGrade> list = new ArrayList<>();
		if(null == couTerm) {
			try {
				list = stuGradeDao.findByYear(year,stuNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				list = stuGradeDao.findByYearTerm(year, couTerm, stuNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 查看学生的某个学期的选课信息
	 * @param stuNum 学生号
	 * @param grade 学期
	 * @return 选修的课程信息
	 */
	public ArrayList<OrderCourseInfo> viewSelectedCourse(String stuNum,String year,String term) {
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		SelCourseDao selCourseDao = new SelCourseDao(connection);
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = selCourseDao.stuFindSelCouBy(stuNum, year, term);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 提交选课信息
	 * @param object 封装有选课信息的对象
	 * @return
	 */
	public boolean selectCourse(Object object) {
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		SelCourseDao selCourseDao = new SelCourseDao(connection);
		boolean flag = false;
		try {
			flag = selCourseDao.doCreate(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 查看待选课程
	 * @param year 学年
	 * @param couTerm 学期
	 * @param majorName 专业名
	 * @return
	 */
	public ArrayList<OrderCourseInfo> viewCourseCanSelect(String year,String couTerm,String grade){
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		OrderCourseInfoDao orderCourseInfoDao = new OrderCourseInfoDao(connection);
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = orderCourseInfoDao.stuSearchSelectCourse(year, couTerm, grade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 删除选课信息
	 * @param object 封装有关键信息的对象
	 * @return
	 */
	public boolean deleteSelectCourse(Object object) {
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		SelCourseDao selCourseDao = new SelCourseDao(connection);
		boolean flag = false;
		try {
			flag = selCourseDao.doDelete(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 查询时间和地点
	 * @param grade
	 * @param year
	 * @param term
	 * @param couNum
	 * @param teaNum
	 * @return
	 */
	public ArrayList<OrderCourseInfo> findTimePlace(String grade,String year,String term,String couNum,String teaNum){
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		OrderCourseInfoDao orderCourseInfoDao = new OrderCourseInfoDao(connection);
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = orderCourseInfoDao.findTimePlace(grade, year, term, couNum, teaNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<OrderCourseInfo> findGrade(String year,String term,String stuNum){
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		SelCourseDao selCourseDao = new SelCourseDao(connection);
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = selCourseDao.stuFindGradeByCou(stuNum, year, term);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
