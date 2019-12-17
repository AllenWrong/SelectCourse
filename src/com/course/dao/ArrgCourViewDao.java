package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.course.bean.ArrgCourView;

public class ArrgCourViewDao implements TableDao{
	private Connection conn;
	private PreparedStatement pst;
	
	public ArrgCourViewDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Object object) throws Exception {
		
		if(object instanceof ArrgCourView) {
			ArrgCourView arrgCourView = (ArrgCourView)object;
			String sql = "insert into arrg_cour_view values(?,?,?,?,?,?,?,?)";
			
		}else {
			System.out.println("不是ArrgCourView的实例，转型错误");
			return false;
		}
		return true;
	}

	@Override
	public boolean doUpdate(Object object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doDelete(Object object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<?> findAll() throws Exception {
		
		return null;
	}

	@Override
	public Object findByMainKey(String mainKey) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
