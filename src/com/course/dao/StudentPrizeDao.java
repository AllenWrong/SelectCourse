package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.course.bean.StudentPrize;

public class StudentPrizeDao implements TableDao{
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rst ;
	public StudentPrizeDao(Connection conn) {
		this.conn = conn;
	}
	
	
////添加
//	public boolean doInsert(StudentPrize info) throws Exception{	
//		String sql = "insert into studentprize values(?,?,?,?,?)";
//		pst = conn.prepareStatement(sql);
//		pst.setInt(1, info.getStuPrizeID());
//		pst.setString(2, info.getStuPrizeStuNum());
//		pst.setString(3, info.getStuPrizeNum());
//		pst.setString(4, info.getStuPrizeType());
//		pst.setString(5, info.getStuPrizeDes());		
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////修改表数据
//	public boolean doUpdate(StudentPrize info) throws SQLException {
//		sql = "updata studentprize set stprize_stu_num  = ?,stprize_num=?,stprize_type=?,stprize_des = ?";
//		pst = conn.prepareStatement(sql);
//		pst.setString(1, info.getStuPrizeStuNum());
//		pst.setString(2, info.getStuPrizeNum());
//		pst.setString(3, info.getStuPrizeType());
//		pst.setString(4, info.getStuPrizeDes());		
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////删除
//	public boolean doDelete(StudentPrize info) throws SQLException {
//		sql = "delete from studentprize where stprize_id = ?";
//		pst = conn.prepareStatement(sql);
//		pst.setInt(1, info.getStuPrizeID());
//		int row = pst.executeUpdate();
//		if(row!=0) {
//			return true;
//		}else {
//		    return false;
//		}
//	}
////查找表的全部数据
//	public List<StudentPrize> findAll(StudentPrize info) throws SQLException {
//		List<StudentPrize> list = new ArrayList<>();
//		sql = "select * from studentprize";
//		pst = conn.prepareStatement(sql);
//		rst = pst.executeQuery();
//		while(rst.next()) {
//			StudentPrize n = new StudentPrize();
//			n.setStuPrizeID(rst.getInt("stprize_id"));
//			n.setStuPrizeStuNum(rst.getString("stprize_stu_num"));
//			n.setStuPrizeNum(rst.getString("stprize_num"));
//			n.setStuPrizeType(rst.getString("stprize_type"));
//			n.setStuPrizeDes(rst.getString("stprize_des"));
//			list.add(n);
//		}
//			return list;
//	}
////通过主键查找
//	public StudentPrize findByMainKey(int mainKey) throws SQLException {
//		StudentPrize n = new StudentPrize();
//		sql = "select * from studentprize where stprize_id = ?";
//		pst = conn.prepareStatement(sql);
//		pst.setInt(1, mainKey);
//		rst = pst.executeQuery();
//		while(rst.next()) {
//			n.setStuPrizeID(rst.getInt("stprize_id"));
//			n.setStuPrizeStuNum(rst.getString("stprize_stu_num"));
//			n.setStuPrizeNum(rst.getString("stprize_num"));
//			n.setStuPrizeType(rst.getString("stprize_type"));
//			n.setStuPrizeDes(rst.getString("stprize_des"));
//		}
//		return n;
//		
//	}


@Override
public boolean doCreate(Object object) throws Exception {
	if(object instanceof StudentPrize) {
		// 向下转型
		StudentPrize info = (StudentPrize)object;
		String sql = "insert into studentprize values(?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, info.getStuPrizeID());
		pst.setString(2, info.getStuPrizeStuNum());
		pst.setString(3, info.getStuPrizeNum());
		pst.setString(4, info.getStuPrizeType());
		pst.setString(5, info.getStuPrizeDes());		
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("StudentPrize添加执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是StudentPrize的父类");
		return false;
	}
	return true;
}


@Override
public boolean doUpdate(Object object) throws Exception {

	if(object instanceof StudentPrize) {
		// 向下转型
		StudentPrize info = (StudentPrize)object;
		String sql = "updata studentprize set stprize_stu_num  = ?,stprize_num=?,stprize_type=?,stprize_des = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, info.getStuPrizeStuNum());
		pst.setString(2, info.getStuPrizeNum());
		pst.setString(3, info.getStuPrizeType());
		pst.setString(4, info.getStuPrizeDes());		
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("StudentPrize添加执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是StudentPrize的父类");
		return false;
	}
	return true;

}


@Override
public boolean doDelete(Object object) throws Exception {

	if(object instanceof StudentPrize) {
		// 向下转型
		StudentPrize info = (StudentPrize)object;
		String sql = "delete from studentprize where stprize_id = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, info.getStuPrizeID());
		int row = pst.executeUpdate();
		if(row!=0) {
			System.out.println("StudentPrize添加执行错误");
			return false;
		}
	}else {
		System.out.println("对象不是StudentPrize的父类");
		return false;
	}
	return true;

}


@Override
public ArrayList<StudentPrize> findAll() throws Exception {
	ArrayList<StudentPrize> list = new ArrayList<>();
	String sql = "select * from studentprize";
	pst = conn.prepareStatement(sql);
	rst = pst.executeQuery();
	while(rst.next()) {
		StudentPrize n = new StudentPrize();
		n.setStuPrizeID(rst.getInt("stprize_id"));
		n.setStuPrizeStuNum(rst.getString("stprize_stu_num"));
		n.setStuPrizeNum(rst.getString("stprize_num"));
		n.setStuPrizeType(rst.getString("stprize_type"));
		n.setStuPrizeDes(rst.getString("stprize_des"));
		list.add(n);
	}
		return list;
}


@Override
public Object findByMainKey(String mainKey) throws Exception {
	StudentPrize n = new StudentPrize();
	String sql = "select * from studentprize where stprize_id = ?";
	pst = conn.prepareStatement(sql);
	pst.setInt(1, Integer.parseInt(mainKey));
	rst = pst.executeQuery();
	while(rst.next()) {
		n.setStuPrizeID(rst.getInt("stprize_id"));
		n.setStuPrizeStuNum(rst.getString("stprize_stu_num"));
		n.setStuPrizeNum(rst.getString("stprize_num"));
		n.setStuPrizeType(rst.getString("stprize_type"));
		n.setStuPrizeDes(rst.getString("stprize_des"));
	}
	return n;
	
}


}
