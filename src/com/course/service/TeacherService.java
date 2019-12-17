package com.course.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.course.bean.AskCourse;
import com.course.bean.OrderCourseInfo;
import com.course.bean.StuGrade;
import com.course.bean.TeacherInfor;
import com.course.dao.AskCourseDao;
import com.course.dao.OrderCourseInfoDao;
import com.course.dao.SelCourseDao;
import com.course.dao.StuGradeDao;
import com.course.dao.TeaCouInfoDao;
import com.course.dao.TeacherInfoDao;
import com.course.jdbc.DatabaseConnection;

public class TeacherService {
	/** 静态常量*/
	private final String dbname="course_manager";
	/** 教师连接数据库的用户名*/
	private String username;
	/** 教师连接数据库的密码*/
	private String password;
	
	public TeacherService(String username,String passwd) {
		this.username = username;
		this.password = passwd;
	}
	
	/**
	 * 成绩管理模块：查看教授的课程
	 */
	public ArrayList<OrderCourseInfo> teaFindByCou(String teaNum,String couNum,String term,String year) {
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		SelCourseDao selCourseDao = new SelCourseDao(connection);
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = selCourseDao.gradeFindByTea(teaNum, couNum,term,year);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/**
	 * 成绩管理模块：查看成绩
	 */
	public ArrayList<OrderCourseInfo> teaFindGradeByCou(String teaNum,String couNum,String term,String year) {
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		StuGradeDao stuGradeDao = new StuGradeDao(connection);
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = stuGradeDao.teaFindGradeByCou(teaNum, couNum, term, year);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/** 
	 * 查看教师的基本信息
	 * @param teaNum 教师的工号
	 * @return
	 */
	public Object viewBasicInfo(String teaNum) {
		Connection connection = DatabaseConnection.getConnection(username,password, dbname);
		TeacherInfoDao teacherInfoDao = new TeacherInfoDao(connection);
		TeacherInfor teacherInfor = new TeacherInfor();
		try {
			teacherInfor = (TeacherInfor)teacherInfoDao.findByMainKey(teaNum);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return teacherInfor;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AskCourse> viewAskCourNoProcess(String teaNum) {
		Connection connection = DatabaseConnection.getConnection(username,password, dbname);
		AskCourseDao askCourseDao = new AskCourseDao(connection);
		ArrayList<AskCourse> list = new ArrayList<>();
		try {
			list = (ArrayList<AskCourse>) askCourseDao.findByMainKey(teaNum);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/** 修改教师的基本信息，ui上并没有提供基本信息的修改，因此这里不实现该方法*/
	public Object updateBasicInfo() {
		return null;
	}
	
	/**
	 * 教师申请开课功能
	 * @param object 包含申请开课信息的对象
	 * @return
	 */
	public boolean askOpenCourse(Object object) {
		Connection connection = DatabaseConnection.getConnection(username,password, dbname);
		AskCourseDao askCourseDao = new AskCourseDao(connection);
		boolean flag = false;
		try {
			flag = askCourseDao.doCreate(object);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}finally {
			DatabaseConnection.release(connection);
		}
		return flag;
	}
	
	/**
	 * 撤销申请开课
	 * @param object 包含开课信息的对象
	 * @return
	 */
	public boolean deleteAskOpenCourse(Object object) {
		Connection connection = DatabaseConnection.getConnection(username,password, dbname);
		AskCourseDao askCourseDao = new AskCourseDao(connection);
		boolean flag = false;
		try {
			flag = askCourseDao.doDelete(object);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}finally {
			DatabaseConnection.release(connection);
		}
		return flag;
	}
	/**
	 * 查看教师的档案
	 * @param object 被查询的教师对象
	 * @return
	 */
	public Object viewAchives(Object object) {
		return null;
	}
	
	/**
	 * 查看教师的课程表
	 * @param object 查询的教师对象
	 * @return   orderCourseInfoDao对象
	 */
	public ArrayList<OrderCourseInfo> viewCourseTable(String teaNum) {
		Connection conn = DatabaseConnection.getConnection(username, password, "course_manager");
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		OrderCourseInfoDao ordDao = new OrderCourseInfoDao(conn);
		try {
			list = ordDao.findByTeacher(teaNum);
		} catch (Exception e) {
			System.out.println("业务：查询教师课程表异常");
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/**
	 * 录入成绩
	 * @param object 被录入的成绩对象
	 * @return
	 */
	public boolean insertGrade(Object object) {
		Connection connection = DatabaseConnection.getConnection(username,password, dbname);
		StuGradeDao stuGradeDao = new StuGradeDao(connection);
		boolean flag = false;
		try {
			flag = stuGradeDao.doCreate(object);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}finally {
			DatabaseConnection.release(connection);
		}
		return flag;
	}
	
	/**
	 * 查看指定的待录入成绩的课程
	 * @return
	 */
	public ArrayList<StuGrade> viewCourseByTeaCTY(String teaNum,String couNum,String term,String year) {
		Connection connection = DatabaseConnection.getConnection(username,password, dbname);
		StuGradeDao stuGradeDao = new StuGradeDao(connection);
		ArrayList<StuGrade> list = new ArrayList<>();
		try {
			list = stuGradeDao.findByTeaCTY(teaNum, couNum, term, year);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/**
	 * 查看课程成绩
	 * @return
	 */
	public ArrayList<StuGrade> viewTeaCourseGrade(String teaNum,String couNum,String term,String year) {
		Connection connection = DatabaseConnection.getConnection(username,password, dbname);
		StuGradeDao stuGradeDao = new StuGradeDao(connection);
		ArrayList<StuGrade> list = new ArrayList<>();
		try {
			list = stuGradeDao.findGradeByTea(teaNum, couNum, term, year);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/**
	 * 教师授课信息查询
	 * @param teaNum 教师号
	 * @return
	 */
	public ArrayList<OrderCourseInfo> viewTeaCouInfo(String teaNum){
		Connection connection = DatabaseConnection.getConnection(username, password, dbname);
		TeaCouInfoDao teaCouInfoDao = new TeaCouInfoDao(connection);
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = teaCouInfoDao.teaFindTeaCou(teaNum);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	
	/**
	 * 查看教师承担的科研项目
	 * @param object 被查询的教师
	 * @return
	 */
	public Object viewReaserch(Object object) {
		return null;
	}
	
	/**
	 * 查看教师的论文专利著作等
	 * @param object 被查询的教师
	 * @return
	 */
	public Object viewProp(Object object) {
		return null;
	}
}