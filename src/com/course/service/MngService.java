package com.course.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.course.bean.AskCourse;
import com.course.bean.CourseInfo;
import com.course.bean.MajorPlanInfo;
import com.course.bean.Notice;
import com.course.bean.OrderCourseInfo;
import com.course.bean.SelCourse;
import com.course.bean.ShowPlace;
import com.course.bean.StuGrade;
import com.course.bean.StuInfo;
import com.course.bean.TeaCouInfo;
import com.course.bean.TeachProp;
import com.course.bean.TeacherInfor;
import com.course.bean.TeacherPrize;
import com.course.bean.TermPlanInfo;
import com.course.bean.UserInfo;
import com.course.dao.AskCourseDao;
import com.course.dao.CourseInfoDao;
import com.course.dao.MajorPlanInfoDao;
import com.course.dao.NoticeDao;
import com.course.dao.OrderCourseInfoDao;
import com.course.dao.SelCourseDao;
import com.course.dao.ShowPlaceDao;
import com.course.dao.StuGradeDao;
import com.course.dao.StuInfoDao;
import com.course.dao.StudentStructionDao;
import com.course.dao.TeaCouInfoDao;
import com.course.dao.TeachPropDao;
import com.course.dao.TeacherInfoDao;
import com.course.dao.TeacherPrizeDao;
import com.course.dao.TermPlanInfoDao;
import com.course.dao.UserInfoDao;
import com.course.jdbc.DatabaseConnection;

/**
 * 管理员业务逻辑实现
 * @author Thingcor
 * 业务层实现模式：通过构造方法初始化用户名和密码，然后使用用户名和密码连接数据库。
 * 将连接对象传入dao对象，进行连接。
 * 最后无论正确还是错误都要进行连接资源的释放
 */
public class MngService {
	/** 连接数据库的用户名*/
	private String dbUserName;
	/** 连接数据库的密码*/
	private String dbPasswd;
	

	/** 连接数据库的时候要指定用户名和密码*/
	public MngService(String dbUerName, String dbPasswd) {
		this.dbUserName = dbUerName;
		this.dbPasswd = dbPasswd;
	}
	
	/** 查找所有的地点*/
	public ArrayList<ShowPlace> findAllPlace(){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		ShowPlaceDao showPlaceDao = new ShowPlaceDao(connection);
		ArrayList<ShowPlace> list = new ArrayList<>();
		try {
			list = showPlaceDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/** 查询教师和课程*/
	public ArrayList<TeacherInfor> findTeaByCou(String couNum){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TeaCouInfoDao teaCouInfoDao = new TeaCouInfoDao(connection);
		ArrayList<TeacherInfor> list = new ArrayList<>();
		try {
			list = teaCouInfoDao.findByCoNum(couNum);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
		
	}
	
	/**************专业计划管理***************/
	public boolean doAddMajorPlan(MajorPlanInfo majorPlanInfo) {
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		MajorPlanInfoDao majorPlanInfoDao = new MajorPlanInfoDao(connection);
		boolean flag = true;
		try {
			flag = majorPlanInfoDao.doCreate(majorPlanInfo);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("业务：查询所有的专业计划异常");
			flag = false;
		}finally {
			DatabaseConnection.release(connection);
		}
		return flag;
	}
	
	/** 查询所有的专业计划**/
	public ArrayList<CourseInfo> findAllMajorPlan(String stuNum,String grade) {
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		MajorPlanInfoDao majorPlanInfoDao = new MajorPlanInfoDao(connection);
		ArrayList<CourseInfo> list = new ArrayList<>();
		try {
			 list = majorPlanInfoDao.findAll(stuNum,grade);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("业务：查询所有的专业计划异常");
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/**
	 * 根据年级和专业名查询专业计划
	 * @param grade 年级
	 * @param majorName 专业名
	 * @return
	 */
	public ArrayList<MajorPlanInfo> findMajorPByKey(String grade,String majorName){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		MajorPlanInfoDao majorPlanInfoDao = new MajorPlanInfoDao(connection);
		ArrayList<MajorPlanInfo> list = new ArrayList<>();
		try {
			 list = majorPlanInfoDao.findByKey(grade, majorName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("业务：查询指定的专业计划异常");
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	public ArrayList<MajorPlanInfo> findAllMajorPlan() {
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		MajorPlanInfoDao majorPlanInfoDao = new MajorPlanInfoDao(connection);
		ArrayList<MajorPlanInfo> list = new ArrayList<>();
		try {
			 list = majorPlanInfoDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("业务：查询所有的专业计划异常");
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/*************** 学期计划管理****************************/
	/** 对学期计划的增加操作*/
	public boolean doAddTermPlan(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
			TermPlanInfoDao termPlanInfoDao = new TermPlanInfoDao(conn);
			try {
				termPlanInfoDao.doCreate(obj);
			} catch (Exception e) {
				System.out.println("业务：添加专业计划异常");
				e.printStackTrace();
				flag = false;
			}finally {
				DatabaseConnection.release(conn);
			}
		return flag;
	}
	
	/** 对学期计划的删除操作*/
	public boolean doDeletTermPlan(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
			TermPlanInfoDao termPlanInfoDao = new TermPlanInfoDao(conn);
			try {
				termPlanInfoDao.doDelete(obj);
			} catch (Exception e) {
				System.out.println("业务：删除专业计划异常");
				e.printStackTrace();
				flag = false;
			}finally {
				DatabaseConnection.release(conn);
			}
		return flag;
	}
	
	/** 对学期计划的修改操作*/
	public boolean doUpdateTermPlan(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
			TermPlanInfoDao termPlanInfoDao = new TermPlanInfoDao(conn);
			try {
				termPlanInfoDao.doUpdate(obj);
			} catch (Exception e) {
				System.out.println("业务：更新专业计划异常");
				e.printStackTrace();
				flag = false;
			}finally {
				DatabaseConnection.release(conn);
			}
		return flag;
	}
	
	/** 对学期计划的查询操作*/
	public ArrayList<TermPlanInfo> findAllTermPlanInfo() {
		// 连接数据库
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TermPlanInfoDao termPlanInfoDao = new TermPlanInfoDao(conn);
		ArrayList<TermPlanInfo> list = null;
		try {
			list = (ArrayList<TermPlanInfo>) termPlanInfoDao.findAllHasMajorName();
		} catch (Exception e) {
			System.out.println("业务：查询专业计划异常");
			e.printStackTrace();
			list = null;
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	public ArrayList<TermPlanInfo> findTPByKey(String term,String majorNum){
		// 连接数据库
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TermPlanInfoDao termPlanInfoDao = new TermPlanInfoDao(conn);
		ArrayList<TermPlanInfo> list = null;
		try {
			list = termPlanInfoDao.findByMainKey(term, majorNum);
		} catch (Exception e) {
			System.out.println("业务：查询专业计划异常");
			e.printStackTrace();
			list = null;
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	public ArrayList<TermPlanInfo> findByKey(String term,String major){
		// 连接数据库
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TermPlanInfoDao termPlanInfoDao = new TermPlanInfoDao(conn);
		ArrayList<TermPlanInfo> list = null;
		try {
			list = (ArrayList<TermPlanInfo>) termPlanInfoDao.findByMainKey(term, major);
		} catch (Exception e) {
			System.out.println("业务：查询专业计划异常");
			e.printStackTrace();
			list = null;
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/************************************课程管理***************************************/
	/** 对课程信息的增加操作*/
	public boolean doAddCourseInfo(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		if(obj instanceof CourseInfo) {
			CourseInfoDao courseInfoDao = new CourseInfoDao(conn);
			try {
				courseInfoDao.doCreate(obj);
			} catch (Exception e) {
				System.out.println("业务：添加课程信息异常");
				e.printStackTrace();
				flag = false;
			}finally {
				DatabaseConnection.release(conn);
			}
		}else {
			System.out.println("业务：非父类，添加课程信息错误");
			flag = false;
		}
		return flag;
	}

	/** 对课程信息的删除操作*/
	public boolean doDeleteCourseInfo(ArrayList<String> list) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		CourseInfoDao courseInfoDao = new CourseInfoDao(conn);
		try {
			for(int i = 0;i<list.size();i++) {
				courseInfoDao.doDelete(list.get(i));
			}
		} catch (Exception e) {
			System.out.println("业务：删除课程信息异常");
			e.printStackTrace();
			flag = false;
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	
	/** 对课程信息的修改操作*/
	public boolean doUpdateCourseInfo(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
			CourseInfoDao courseInfoDao = new CourseInfoDao(conn);
			try {
				courseInfoDao.doUpdate(obj);
			} catch (Exception e) {
				System.out.println("业务：修改课程信息异常");
				e.printStackTrace();
				flag = false;
			}finally {
				DatabaseConnection.release(conn);
			}
		return flag;
	}
	
	/** 对课程信息的查询操作*/
	public ArrayList<CourseInfo> findAllCourseInfo(){
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		CourseInfoDao courseInfoDao = new CourseInfoDao(conn);
		ArrayList<CourseInfo> list = null;
		try {
			list = (ArrayList<CourseInfo>)courseInfoDao.findAll();
		} catch (Exception e) {
			System.out.println("业务：查询课程信息异常");
			e.printStackTrace();
			list = null;
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/**
	 * 查询指定的课程信息
	 * @param mainKey 课程号
	 * @return
	 */
	public ArrayList<CourseInfo> findCouInfoByKey(String mainKey) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		CourseInfoDao courseInfoDao = new CourseInfoDao(conn);
		ArrayList<CourseInfo> list = new ArrayList<>();
		try {
			list = courseInfoDao.findByMainKey(mainKey);
		} catch (Exception e) {
			System.out.println("业务：查询课程信息异常");
			e.printStackTrace();
			list = null;
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/*********************学期计划管理***********************/
	/** 创建视图后操作？？？？？？？好像不需要创建视图*/
	
	/*********************学生管理***************************/
	/** 添加某个学生*/
	public boolean doAddStudentInfo(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		StuInfoDao stuInfoDao = new StuInfoDao(conn);
		try {
			stuInfoDao.doCreate(obj);
		} catch (Exception e) {
			System.out.println("业务：添加某个学生异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	/** 批量添加学生,调用上面的方法实现*/
	public boolean doBitchAddStudent(ArrayList<StuInfo> stuList) {
		return false;
	}
	
	/** 删除某个学生*/
	public boolean doDeleteStudentInfo(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
			StuInfoDao stuInfoDao = new StuInfoDao(conn);
			try {
				stuInfoDao.doDelete(obj);
			} catch (Exception e) {
				System.out.println("业务：删除某个学生异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}
		return flag;
	}
	/** 批量删除某个学生*/
	public boolean doBitchDeleteStudent(ArrayList<StuInfo> stuList) {
		return false;
	}
	/** 修改某个学生的信息*/
	public boolean doUpdateStudentInfo(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		StuInfoDao stuInfoDao = new StuInfoDao(conn);
		try {
			stuInfoDao.doUpdate(obj);
		} catch (Exception e) {
			System.out.println("业务：修改某个学生异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	
	/**
	 * 添加一条学生变动信息
	 * @param object
	 * @return
	 */
	public boolean doAddStuStruction(Object object) {
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		StudentStructionDao studentStructionDao = new StudentStructionDao(connection);
		boolean flag = true;
		try {
			flag = studentStructionDao.doCreate(object);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return flag;
	}
	
	/**
	 * 删除一条异动信息
	 * @param object
	 * @return
	 */
	public boolean deleteStuStruction(Object object) {
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		StudentStructionDao studentStructionDao = new StudentStructionDao(connection);
		boolean flag = true;
		try {
			flag = studentStructionDao.doDelete(object);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return flag;
	}
	
	/**
	 * 修改变动信息暂时放弃
	 * @param obj
	 * @return
	 */
	@Deprecated
	public boolean doUpdateStuStruction(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
			StudentStructionDao studentStructionDao = new StudentStructionDao(conn);
			try {
				studentStructionDao.doUpdate(obj);
			} catch (Exception e) {
				System.out.println("业务：修改某个学生的异动信息异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}
		return flag;
	}
	/*********************教师管理***************************/
	/** 添加教师信息*/
	public boolean doAddTeaInfo(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_mananger");
		boolean flag = true;
		TeacherInfoDao teacherInfoDao = new TeacherInfoDao(conn);
		try {
			teacherInfoDao.doCreate(obj);
		} catch (Exception e) {
			System.out.println("业务：添加教师信息异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	
	/** 批量添加教师信息*/
	public boolean doBitchAddTea(ArrayList<TeacherInfor> teaList) {
		return false;
	}
	
	/** 删除教师的信息*/
	public boolean doDeleteTeaInfo(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		TeacherInfoDao teacherInfoDao = new TeacherInfoDao(conn);
		try {
			teacherInfoDao.doDelete(obj);
		} catch (Exception e) {
			System.out.println("业务：删除教师信息异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	
	/** 批量删除教师的信息*/
	public boolean doBitchDeleteTea(ArrayList<TeacherInfor> teaList) {
		return false;
	}
	
	/** 修改教师的信息*/
	public boolean doUpdateTeaInfo(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		TeacherInfoDao teacherInfoDao = new TeacherInfoDao(conn);
		try {
			teacherInfoDao.doUpdate(obj);
		} catch (Exception e) {
			System.out.println("业务：修改教师信息异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	
	/** 查询某个教师的信息*/
	public TeacherInfor findByTea(String mainKey) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TeacherInfoDao teacherInfoDao = new TeacherInfoDao(conn);
		TeacherInfor teacherInfor = null;
		try {
			teacherInfor =  (TeacherInfor) teacherInfoDao.findByMainKey(mainKey);
		} catch (Exception e) {
			System.out.println("业务：查询课程信息异常");
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return teacherInfor;
	}
	
	/** 查询所有教师的信息*/
	public ArrayList<TeacherInfor> findAllTea(){
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TeacherInfoDao teacherInfoDao = new TeacherInfoDao(conn);
		ArrayList<TeacherInfor> list = null;
		try {
			list = teacherInfoDao.findAll();
		} catch (Exception e) {
			System.out.println("业务：查询课程信息异常");
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/**
	 * 根据专业名查询教师信息
	 * @param majorName
	 * @return
	 */
	public ArrayList<TeacherInfor> findTeaByMajor(String majorName){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		ArrayList<TeacherInfor> list = new ArrayList<>();
		TeacherInfoDao teacherInfoDao = new TeacherInfoDao(connection);
		try {
			list = teacherInfoDao.findByMajor(majorName);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(connection);
		}
		return list;
	}
	
	/** 增加奖项*/
	public boolean doAddPrize(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		TeacherPrizeDao teacherPrizeDao = new TeacherPrizeDao(conn);
		try {
			teacherPrizeDao.doCreate(obj);
		} catch (Exception e) {
			System.out.println("业务：添加教师教师奖项异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}			
		return flag;
	}
	
	/** 删除奖项*/
	public boolean doDeletePrize(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		TeacherPrizeDao teacherPrizeDao = new TeacherPrizeDao(conn);
		try {
			teacherPrizeDao.doDelete(obj);
		} catch (Exception e) {
			System.out.println("业务：删除教师奖项异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	
	/** 修改奖项信息*/
	public boolean doUpdatePrize(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		//程序执行情况的标志
		boolean flag = true;
		TeacherPrizeDao teacherPrizeDao = new TeacherPrizeDao(conn);
		try {
			teacherPrizeDao.doUpdate(obj);
		} catch (Exception e) {
			System.out.println("业务：删除教师奖项异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}
		return flag;
	}
	
	/** 查询某个教师的奖项信息*/
	@SuppressWarnings("unchecked")
	public ArrayList<TeacherPrize> findPrizeByTea(String mainKey){
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TeacherPrizeDao teacherPrizeDao = new TeacherPrizeDao(conn);
		ArrayList<TeacherPrize> list = new ArrayList<>();
		try {
			list = (ArrayList<TeacherPrize>)teacherPrizeDao.findByMainKey(mainKey);
		} catch (Exception e) {
			System.out.println("业务：查寻某个师奖项异常");
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
		
	}
	
	/** 查询所有教师的奖项信息*/
	public ArrayList<TeacherPrize> findAllPrize(){
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TeacherPrizeDao teacherPrizeDao = new TeacherPrizeDao(conn);
		ArrayList<TeacherPrize> list = null;
		try {
			list = teacherPrizeDao.findAll();
		} catch (Exception e) {
			System.out.println("业务：查询所有教师奖项异常");
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/** 增加一个著作信息*/
	public boolean doAddTeaProp(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
			TeachPropDao teachPropDao = new TeachPropDao(conn);
			try {
				teachPropDao.doCreate(obj);
			} catch (Exception e) {
				System.out.println("业务：添加教师著作信息异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}			
		return flag;
	}
	
	/** 删除一个科研信息(著作信息)*/
	public boolean doDeleteTeaProp(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
		TeachPropDao teachPropDao = new TeachPropDao(conn);
		try {
			teachPropDao.doDelete(obj);
		} catch (Exception e) {
			System.out.println("业务：删除一个教师著作信息异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}			
		return flag;
	}
	
	/** 修改某个科研信息（著作）*/
	public boolean doUpdateTeaProp(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
			TeachPropDao teachPropDao = new TeachPropDao(conn);
			try {
				teachPropDao.doUpdate(obj);
			} catch (Exception e) {
				System.out.println("业务：修改教师著作信息异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}			
		return flag;
	}
	/** 查询某个教师的科研信息*/
	public TeachProp findTeaPropByTea(String mainKey){
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		TeachPropDao teachPropDao = new TeachPropDao(conn);
		TeachProp teachProp = null;
		try {
				teachProp = (TeachProp) teachPropDao.findByMainKey(mainKey);
						
		} catch (Exception e) {
						
			System.out.println("业务：查询某个教师著作信息异常");
			e.printStackTrace();
					
		}finally {
			DatabaseConnection.release(conn);
		}		
		
		return teachProp;
	}
	
	/** 查询所有教师的科研信息*/
	public ArrayList<TeachProp> findAllTeaProp(){
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		TeachPropDao teachPropDao = new TeachPropDao(conn);
		ArrayList<TeachProp> list = null;
		try {
				list = (ArrayList<TeachProp>) teachPropDao.findAll();
						
		} catch (Exception e) {
						
			System.out.println("业务：修改教师著作信息异常");
			e.printStackTrace();
					
		}finally {
			DatabaseConnection.release(conn);
		}		
		
		return list;
	}
	
	/*********************教学管理***************************/
	/** 审核教师申请的开课*/
	public boolean doApplyAskCourse(Object object) {
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
//		AskCourseDao askCourseDao = new AskCourseDao(connection);
		TeaCouInfoDao teaCouInfoDao = new TeaCouInfoDao(connection);
		boolean flag = true;
		try {
			flag = teaCouInfoDao.doCreate(object);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 查询所有申请开课信息
	 * @return
	 */
	public ArrayList<AskCourse> findAllAskCourse(){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		AskCourseDao askCourseDao = new AskCourseDao(connection);
		ArrayList<AskCourse> list = new ArrayList<>();
		try {
			list = askCourseDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询所有的教师-课程信息
	 * @return
	 */
	public ArrayList<TeaCouInfo> findAllTeaCouInfo(){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TeaCouInfoDao teaCouInfoDao = new TeaCouInfoDao(connection);
		ArrayList<TeaCouInfo> list = new ArrayList<>();
		try {
			list = teaCouInfoDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据课程号或教师号查询开课信息
	 * @param teaNum
	 * @param couNum
	 * @return
	 */
	public ArrayList<TeaCouInfo> findByteaNumCouNum(String teaNum,String couNum){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TeaCouInfoDao teaCouInfoDao = new TeaCouInfoDao(connection);
		ArrayList<TeaCouInfo> list = new ArrayList<>();
		try {
			list = teaCouInfoDao.findByTeaNumCouNum(teaNum, couNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/** 添加一条排课信息*/
	public boolean doAddArrCourInfo(OrderCourseInfo orderCourseInfo) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		OrderCourseInfoDao orderCourseInfoDao = new OrderCourseInfoDao(conn);
		boolean flag = false;
		try {
			flag = orderCourseInfoDao.doCreate(orderCourseInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 删除一条排课信息
	 * @param obj 封装有必要的信息
	 * @return
	 */
	public boolean doDeleteArrangeCourse(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		OrderCourseInfoDao orderCourseInfoDao = new OrderCourseInfoDao(conn);
		try {
			orderCourseInfoDao.doDelete(obj);
		} catch (Exception e) {
			System.out.println("业务：删除一条排课信息异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}			
		return flag;
	}

	/** 
	 * 修改一条排课信息
	 * @param obj 封装有必要的信息
	 * @return
	 */
	public boolean doUpdateArrangeCourse(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		boolean flag = true;
		OrderCourseInfoDao orderCourseInfoDao = new OrderCourseInfoDao(conn);
		try {
			orderCourseInfoDao.doUpdate(obj);
		} catch (Exception e) {
			System.out.println("业务：修改一条排课信息异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}			
		return flag;
	}
	
	/**
	 * 查询待排课程
	 * @param grade
	 * @param couTerm
	 * @param year
	 * @return
	 */
	public ArrayList<CourseInfo> findWaitArrCourse(String grade,String couTerm,String year){
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		TermPlanInfoDao termPlanInfoDao = new TermPlanInfoDao(conn);
		ArrayList<CourseInfo> list = null;
		try {
			list = termPlanInfoDao.findWaitArrCou(year, grade, couTerm);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/** 
	 * 查询特定年级，学期，学年的排课信息
	 * @param grade 年级
	 * @param term
	 * @param year
	 * @return
	 */
	public ArrayList<OrderCourseInfo> findArrangeCourseByKey(String grade,String term,String year) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		OrderCourseInfoDao orderCourseInfoDao = new OrderCourseInfoDao(conn);
		ArrayList<OrderCourseInfo> list = null;
		try {
			list = orderCourseInfoDao.findByGreadTY(grade, term, year);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}
		return list;
	}
	
	/** 查询所有排课信息 */
	public ArrayList<OrderCourseInfo> findAllOrderCourseInfo() {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		OrderCourseInfoDao orderCourseInfoDao = new OrderCourseInfoDao(conn);
		ArrayList<OrderCourseInfo> list = null;
		try {
			list = (ArrayList<OrderCourseInfo>) orderCourseInfoDao.findAll();
		} catch (Exception e) {
			System.out.println("业务：查询所有的排课信息异常");
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}		
		return list;
	}
	
	/** 添加一条成绩*/
	public boolean doAddGrade(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
			StuGradeDao stuGradeDao = new StuGradeDao(conn);
			try {
				stuGradeDao.doCreate(obj);
			} catch (Exception e) {
				System.out.println("业务：添加一条成绩信息异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}			
		return flag;
	}
	
	/** 批量添加成绩???????????????*/
	public boolean doBitchAddGrade(ArrayList<StuGrade> stuGradeList) {
		return false;
	}
	
	/** 成绩的修改*/
	public boolean doUpdateStuGrade(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
			StuGradeDao stuGradeDao = new StuGradeDao(conn);
			try {
				stuGradeDao.doUpdate(obj);
			} catch (Exception e) {
				System.out.println("业务：修改一条成绩信息异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}			
		return flag;
	}
	
	/**
	 * 根据多个添加查看学生的选课信息
	 * @param grade
	 * @param couName
	 * @param teaName
	 * @param stuName
	 * @return
	 */
	public ArrayList<SelCourse> findSelCouByMultiKey(String grade,String couName,String teaName,String stuName){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		SelCourseDao selCourseDao = new SelCourseDao(connection);
		ArrayList<SelCourse> list = new ArrayList<>();
		try {
			list = selCourseDao.findByMultiKey(grade, couName, teaName, stuName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/** 成绩的删除，参数待定*/
	public boolean doDeleteGrade(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
			StuGradeDao stuGradeDao = new StuGradeDao(conn);
			try {
					stuGradeDao.doDelete(obj);
			} catch (Exception e) {
				System.out.println("业务：删除一条成绩信息异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}			
		return flag;
	}
	
	/** 成绩的批量删除，参数待定*/
	public boolean doBitchDeleteGrade() {
		return false;
	}
	
	/** 成绩的查询*/
	public StuGrade findGradeByStu(String stuNum) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		StuGradeDao stuGradeDao = new StuGradeDao(conn);
		StuGrade stuGrade = null;

		try {
				stuGradeDao.findGradeByStu(stuNum);
						
		} catch (Exception e) {
						
			System.out.println("业务：查询一条成绩信息异常");
			e.printStackTrace();
					
		}finally {
			DatabaseConnection.release(conn);
		}			
		return stuGrade;
	}
	
	/** 成绩的批量查询*/
	public ArrayList<StuGrade> findAllGrade() {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		StuGradeDao stuGradeDao = new StuGradeDao(conn);
		ArrayList<StuGrade> list = null;

		try {
				list = (ArrayList<StuGrade>) stuGradeDao.findAll();
						
		} catch (Exception e) {
						
			System.out.println("业务：批量查询成绩信息异常");
			e.printStackTrace();
					
		}finally {
			DatabaseConnection.release(conn);
		}			
		return list;
	}
	/*********************教务办公管理***********************/
	/** 添加公告*/
	public boolean doAdd(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
		NoticeDao noticeDao = new NoticeDao(conn);
		try {
			noticeDao.doCreate(obj);
		} catch (Exception e) {
			System.out.println("业务：添加一条公告信息异常");
			e.printStackTrace();
			flag=false;
		}finally {
			DatabaseConnection.release(conn);
		}			
		return flag;
	}
	
	/** 删除公告*/
	public boolean doDelete(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
			NoticeDao noticeDao = new NoticeDao(conn);
			try {
					noticeDao.doDelete(obj);
			} catch (Exception e) {
				System.out.println("业务:删除一条公告信息异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}			
		return flag;
	}
	/** 修改公告*/
	public boolean doUpdate(Object obj) {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "de");
		boolean flag = true;
			NoticeDao noticeDao = new NoticeDao(conn);
			try {
				noticeDao.doUpdate(obj);
			} catch (Exception e) {
				System.out.println("业务：修改一条公告信息异常");
				e.printStackTrace();
				flag=false;
			}finally {
				DatabaseConnection.release(conn);
			}			
		return flag;
	}
	
	/** 批量查询公告*/
	public ArrayList<Notice> findNotice() {
		Connection conn = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		NoticeDao noticeDao = new NoticeDao(conn);
		ArrayList<Notice> list = null;

		try {
			list = noticeDao.findAll();
		} catch (Exception e) {
			System.out.println("业务：批量查询公告信息异常");
			e.printStackTrace();
		}finally {
			DatabaseConnection.release(conn);
		}			
		return list;
	}
	
	/**
	 * 用于查找指定的管理员信息
	 * @param mainKey 管理员的工号，也是账号
	 * @return 查到的管理员信息
	 */
	public UserInfo findUser(String mainKey) {
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		UserInfoDao userInfoDao = new UserInfoDao(connection);
		UserInfo userInfo = new UserInfo();
		try {
			userInfo = (UserInfo) userInfoDao.findByMainKey(mainKey);
		} catch (Exception e) {
		}finally {
			DatabaseConnection.release(connection);
		}
		return userInfo;
	}
	
	public ArrayList<StuInfo> findAllStu(){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		StuInfoDao stuInfoDao = new StuInfoDao(connection);
		ArrayList<StuInfo> list = new ArrayList<>();
		try {
			list = stuInfoDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<StuInfo> findStuByMutiKey(String grade,String major,String stuNum){
		Connection connection = DatabaseConnection.getConnection(dbUserName, dbPasswd, "course_manager");
		StuInfoDao stuInfoDao = new StuInfoDao(connection);
		ArrayList<StuInfo> list = new ArrayList<>();
		try {
			list = stuInfoDao.findByMultiKey(grade, major, stuNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
