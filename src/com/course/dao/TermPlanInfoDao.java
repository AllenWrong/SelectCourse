package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.course.bean.CourseInfo;
import com.course.bean.TermPlanInfo;

public class TermPlanInfoDao implements TableDao {
	private Connection conn;
	private PreparedStatement prpst;
	
	public TermPlanInfoDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof TermPlanInfo) {
			TermPlanInfo termPlanInfo = (TermPlanInfo)object;
			String sql = "insert into termplaninfo values(?,?,?,?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, termPlanInfo.getTermID());
			prpst.setInt(2, termPlanInfo.getTpTerm());
			prpst.setString(3, termPlanInfo.getTpYear());
			prpst.setString(4, termPlanInfo.getTpMajorNum());
			prpst.setString(5, termPlanInfo.getTpGrade());
			prpst.setString(6, termPlanInfo.getTpCourseNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("添加termplaninfo错误");
				return false;
			}
		}else {
			System.out.println("不是termplanInfo的父类，转型失败");
			return false;
		}
		return true;
	}

	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof TermPlanInfo) {
			TermPlanInfo termPlanInfo = (TermPlanInfo)object;
			String sql = "update termplaninfo set term_id=?,tp_term=?,tp_year=?,tp_majornum=?,tp_grade=?,tp_coursenum=? where tp_term=? and tp_coursenum=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, termPlanInfo.getTermID());
			prpst.setInt(2, termPlanInfo.getTpTerm());
			prpst.setString(3, termPlanInfo.getTpYear());
			prpst.setString(4, termPlanInfo.getTpMajorNum());
			prpst.setString(5, termPlanInfo.getTpGrade());
			prpst.setString(6, termPlanInfo.getTpCourseNum());
			prpst.setInt(7, termPlanInfo.getTpTerm());
			prpst.setString(8, termPlanInfo.getTpCourseNum());
			if(prpst.executeUpdate() != 1) {
				System.out.println("更新termplaninfo错误");
				return false;
			}
		}else {
			System.out.println("不是termplanInfo的父类，转型失败");
			return false;
		}
		return true;
	}

	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof TermPlanInfo) {
			TermPlanInfo termPlanInfo = (TermPlanInfo)object;
			String sql = "delete from termplaninfo where tp_term=? and tp_coursenum=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, termPlanInfo.getTpTerm());
			prpst.setString(2, termPlanInfo.getTpCourseNum());
			prpst.executeUpdate();
		}else {
			System.out.println("不是termplanInfo的父类，转型失败");
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<TermPlanInfo> findAll() throws Exception {
		String sql = "select * from termplaninfo";
		prpst = conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的termplaninfo错误");
			return null;
		}
		ArrayList<TermPlanInfo> list = new ArrayList<>();
		while(rSet.next()) {
			TermPlanInfo termPlanInfo = new TermPlanInfo();
			termPlanInfo.setTermID(rSet.getInt("term_id"));
			termPlanInfo.setTpTerm(rSet.getInt("tp_term"));
			termPlanInfo.setTpYear(rSet.getString("tp_year"));
			termPlanInfo.setTpMajorNum(rSet.getString("tp_majornum"));
			termPlanInfo.setTpGrade(rSet.getString("tp_grade"));
			termPlanInfo.setTpCourseNum(rSet.getString("tp_coursenum"));
			list.add(termPlanInfo);
		}
		return list;
	}
	/**
	 * 查询所有的专业集合，其中包括课程号和课程名
	 * @return
	 * @throws Exception
	 */
	public ArrayList<TermPlanInfo> findAllHasMajorName() throws Exception{
		String sql = "select term_id,tp_term,tp_year,majorplan_num,majorplan_name,tp_grade,cou_num,cou_name from termplaninfo,courseinfo,majorplaninfo where tp_majornum = majorplan_num and tp_coursenum=cou_num";
		prpst = conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的termplaninfo错误");
			return null;
		}
		ArrayList<TermPlanInfo> list = new ArrayList<>();
		while(rSet.next()) {
			TermPlanInfo termPlanInfo = new TermPlanInfo();
			termPlanInfo.setTermID(rSet.getInt("term_id"));
			termPlanInfo.setTpTerm(rSet.getInt("tp_term"));
			termPlanInfo.setTpYear(rSet.getString("tp_year"));
			termPlanInfo.setTpMajorNum(rSet.getString("majorplan_num"));
			termPlanInfo.setTpGrade(rSet.getString("tp_grade"));
			termPlanInfo.setTpCourseNum(rSet.getString("cou_num"));
			termPlanInfo.setTpCourseName(rSet.getString("cou_name"));
			termPlanInfo.setTpMajorName(rSet.getString("majorplan_name"));
			list.add(termPlanInfo);
		}
		return list;
	}
	
	/**
	 * 待定
	 */
	@Override
	public Object findByMainKey(String mainKey) throws Exception {
		return null;
	}
	
	public ArrayList<CourseInfo> findWaitArrCou(String year,String grade,String term) throws Exception{
		String sql = "select cou_term,tp_year,tp_majornum,tp_grade,cou_num,cou_name,cou_xs,cou_redit,cou_type from courseinfo,termplaninfo where cou_num=tp_coursenum and tp_grade=? and tp_year=? and cou_term=?";
		prpst = conn.prepareStatement(sql);
		prpst.setString(1, grade);
		prpst.setString(2, year);
		prpst.setInt(3, Integer.parseInt(term));
		ResultSet resultSet = prpst.executeQuery();
		if(resultSet == null) {
			System.out.println("查询所有的待排课程错误");
			return null;
		}
		ArrayList<CourseInfo> list = new ArrayList<>();
		while(resultSet.next()) {
			CourseInfo courseInfo = new CourseInfo();
			courseInfo.setCourTerm(resultSet.getString("cou_term"));
			courseInfo.setTpYear(resultSet.getString("tp_year"));
			courseInfo.setTpMajorNum(resultSet.getString("tp_majornum"));
			courseInfo.setTpGrade(resultSet.getString("tp_grade"));
			courseInfo.setCourNum(resultSet.getString("cou_num"));
			courseInfo.setCourName(resultSet.getString("cou_name"));
			courseInfo.setCourXs(resultSet.getInt("cou_xs"));
			courseInfo.setCourRedit(resultSet.getInt("cou_redit"));
			courseInfo.setCourType(resultSet.getString("cou_type"));
			list.add(courseInfo);
		}
		return list;
	}
	
	public ArrayList<TermPlanInfo> findByMainKey(String term,String majorNum) throws Exception {
		String sql = "select term_id,tp_term,tp_year,majorplan_name,tp_grade,cou_name,cou_num,majorplan_num from termplaninfo,courseinfo,majorplaninfo where tp_majornum = majorplan_num and tp_coursenum=cou_num and tp_term=? and tp_majornum=?";
		prpst = conn.prepareStatement(sql);
		prpst.setInt(1, Integer.parseInt(term));
		prpst.setString(2, majorNum);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的termplaninfo错误");
			return null;
		}
		ArrayList<TermPlanInfo> list = new ArrayList<>();
		while(rSet.next()) {
			TermPlanInfo termPlanInfo = new TermPlanInfo();
			termPlanInfo.setTermID(rSet.getInt("term_id"));
			termPlanInfo.setTpTerm(rSet.getInt("tp_term"));
			termPlanInfo.setTpYear(rSet.getString("tp_year"));
			termPlanInfo.setTpMajorNum(rSet.getString("majorplan_num"));
			termPlanInfo.setTpGrade(rSet.getString("tp_grade"));
			termPlanInfo.setTpCourseNum(rSet.getString("cou_num"));
			termPlanInfo.setTpCourseName(rSet.getString("cou_name"));
			termPlanInfo.setTpMajorName(rSet.getString("majorplan_name"));
			list.add(termPlanInfo);
		}
		return list;
	}

}
