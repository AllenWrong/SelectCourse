package com.course.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatabaseBackup {
	// 数据库备份与还原

	/*
	 * 数据库备份
	 */
	public static void dbBackUp(String filename) {
		String backPath = "D://BackupDB/" + filename + ".sql";
		File file = new File(backPath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("备份失败");
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("mysqldump");
		sb.append(" -hlocalhost");
		sb.append(" -uroot");
		sb.append(" -proot");
		sb.append(" course_manager" + " > ");
		sb.append(backPath);
		System.out.println("cmd命令：" + sb.toString());
		Runtime time = Runtime.getRuntime();
		try {
			time.exec("cmd /c" + sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("备份失败");
			e.printStackTrace();

		}
		System.out.println("备份成功");
	}

	/*
	 * 数据库还原
	 */
	public static void dbRestore() {
		StringBuffer sb = new StringBuffer();
		sb.append("mysql");
		sb.append(" -hlocalhost");
		sb.append(" -uroot");
		sb.append(" -proot");
		sb.append(" course_manager <");
		sb.append("D://Desktop/course_manager.sql");
		Runtime time = Runtime.getRuntime();
		try {
			Process process = time.exec("cmd /c" + sb.toString());
			InputStream is = process.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is, "utf8"));
			String line = null;
			while ((line = bf.readLine()) != null) {
				System.out.println(line);
			}
			is.close();
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库还原失败");
			e.printStackTrace();
		}
	}
}
