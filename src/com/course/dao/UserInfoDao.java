package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.course.bean.UserInfo;

public class UserInfoDao implements TableDao{
	private Connection conn;
	private PreparedStatement prpst;
	
	public UserInfoDao(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Object object) throws Exception {
		if(object instanceof UserInfo) {
			UserInfo userInfo = (UserInfo)object;
			String sql = "insert into userinto values(?,?,?,?,?)";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, userInfo.getUserID());
			prpst.setString(2, userInfo.getUserName());
			prpst.setString(3, userInfo.getUserNum());
			prpst.setString(4, userInfo.getUserPwd());
			prpst.setString(5, userInfo.getUserRole());
			if(prpst.executeUpdate() != 1) {
				System.out.println("添加userinfo异常");
				return false;
			}
		}else {
			System.out.println("不是userinfo的父类");
			return false;
		}
		return true;
	}
	@Override
	public boolean doUpdate(Object object) throws Exception {
		if(object instanceof UserInfo) {
			UserInfo userInfo = (UserInfo)object;
			String sql = "update table userinto set user_id=?,user_name=?,user_num=?,user_pwd=?,user_role=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, userInfo.getUserID());
			prpst.setString(2, userInfo.getUserName());
			prpst.setString(3, userInfo.getUserNum());
			prpst.setString(4, userInfo.getUserPwd());
			prpst.setString(5, userInfo.getUserRole());
			if(prpst.executeUpdate() != 1) {
				System.out.println("更新userinfo异常");
				return false;
			}
		}else {
			System.out.println("不是userinfo的父类");
			return false;
		}
		return true;
	}
	@Override
	public boolean doDelete(Object object) throws Exception {
		if(object instanceof UserInfo) {
			UserInfo userInfo = (UserInfo)object;
			String sql = "delete from userinto where user_ud=?";
			prpst = conn.prepareStatement(sql);
			prpst.setInt(1, userInfo.getUserID());
		}else {
			System.out.println("不是userinfo的父类");
			return false;
		}
		return true;
	}
	@Override
	public ArrayList<?> findAll() throws Exception {
		String sql = "select * from userinfo";
		prpst = conn.prepareStatement(sql);
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询所有的用户信息错误");
			return null;
		}
		ArrayList<UserInfo> list = new ArrayList<>();
		while(rSet.next()) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserID(rSet.getInt("user_id"));
			userInfo.setUserName(rSet.getString("user_name"));
			userInfo.setUserNum(rSet.getString("user_num")); // user_num的意思
			userInfo.setUserPwd(rSet.getString("user_pwd"));
			userInfo.setUserRole(rSet.getString("user_role"));
			list.add(userInfo);
		}
		return list;
	}
	
	/**
	 * 根据user_id查
	 */
	@Override
	public Object findByMainKey(String mainKey) throws Exception {
		String sql = "select * from userinfo where user_num=?";
		prpst = conn.prepareStatement(sql);
		prpst.setInt(1, Integer.parseInt(mainKey));
		ResultSet rSet = prpst.executeQuery();
		if(rSet == null) {
			System.out.println("查询执行用户信息异常");
			return false;
		}
		UserInfo userInfo = new UserInfo();
		while(rSet.next()) {
			userInfo.setUserID(rSet.getInt("user_id"));
			userInfo.setUserName(rSet.getString("user_name"));
			userInfo.setUserNum(rSet.getString("user_num"));
			userInfo.setUserPwd(rSet.getString("user_pwd"));
			userInfo.setUserRole(rSet.getString("user_role"));
		}
		return userInfo;
	}
	
}
