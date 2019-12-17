package com.course.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	/** 加载驱动并连接数据库*/
	public static Connection getConnection(String username,String password,String dbname) {
		// 加载驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success loading..");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error loading..");
		}
		
		// 会在第一次连接数据的时候开启线程，在以后连接时，会跳过这里
		SingleDataBase singleDataBase = SingleDataBase.getSingleBase();
		if(!singleDataBase.isAlive()) {
			singleDataBase.start();
		}
		
		// 连接数据库
		try {
			String url = "jdbc:mysql://localhost:3306/"+dbname+"?useUnicode=yes&characterEncoding=utf-8&useSSL=false&serverTimezone = UTC";
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connection success..");
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Connection fail..");
			return null;
		}
	}
	
	/** 释放资源*/
	public static void release(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
				System.out.println("conn has been released...");
			}catch(Exception e) {
				System.out.println("conn releasing error...");
			}
			conn=null;
		}
	}
}
