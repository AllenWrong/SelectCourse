package com.course.jdbc;

import java.util.Calendar;

public class SingleDataBase extends Thread{
	private static SingleDataBase dataBase;	
	
	//单例模式
	private SingleDataBase() {
		
	}	
	public static synchronized SingleDataBase getSingleBase() {
		if(dataBase==null) {
			dataBase = new SingleDataBase();
		}		
		return dataBase;
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				// 10分钟备份一次
				Thread.sleep(600000);
				System.out.println("开始备份");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR);
			int minutes = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			String filename = year+"-"+month+"-"+day+"_"+hour+"h"+minutes+"m"+second+"s";
			System.out.println(filename);

			DatabaseBackup.dbBackUp(filename);
		}
	}
}


