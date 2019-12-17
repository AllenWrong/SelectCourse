package com.course.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class JsonTool {
	
	/**
	 * 返回处理的结果，比如删除或添加成功，失败
	 * @param flag
	 * @return
	 */
	public static JSONObject writeMsg(boolean flag) {
		JSONObject msg = new JSONObject();
		if(flag) {
			msg.put("msg", "true");
		}else {
			msg.put("msg", "false");
		}
		return msg;
	}
	
	/**
	 * 去重处理，只能是(string)list
	 * @param list
	 * @return
	 */
	public static ArrayList<String> toReduce(ArrayList<String> list){
		Set<String> set = new HashSet<>();
		for(int i = 0;i<list.size();i++) {
			set.add(list.get(i));
		}
		return new ArrayList<>(set);
	}
	/**
	 * 输出JSON对象
	 * @param printWriter
	 * @param jsonObject
	 */
	public static void writeObject(PrintWriter printWriter,JSONObject jsonObject) {
		printWriter.print(jsonObject);
		printWriter.flush();
		printWriter.close();
	}
	
	/**
	 * 从流中拿到json数据，解析成json对象
	 * @param inputStream 流
	 * @return 返回json对象
	 * @throws Exception
	 */
	public static JSONObject parseTool(InputStream inputStream) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
		StringBuffer string = new StringBuffer();
		String line = null;
		while((line=reader.readLine())!=null) {
			string.append(line);
		}
		JSONObject jsonObject = JSONObject.fromObject(string.toString());
		return jsonObject;
	}
	
	public static void justExec(Boolean bool,HttpServletResponse response) throws IOException {
		if(bool == true) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "true");
			JsonTool.writeObject(response.getWriter(), jsonObject);
		}else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "false");
			JsonTool.writeObject(response.getWriter(), jsonObject);
		}
	}
	
	/**
	 * 转换时间
	 * @param time
	 * @return
	 */
	public static String transferTeaTime(String time) {
		String[] strings = time.split("-");
		return transferWeek(strings[0])+transferTime(strings[1]);
	}
	private static String transferWeek(String week) {
		switch (week) {
		case "1":
			return "周一，";
		case "2":
			return "周二，";
		case "3":
			return "周三，";
		case "4":
			return "周四，";
		case "5":
			return "周五，";
		default:
			return "";
		}
	}
	private static String transferTime(String time) {
		switch (time) {
		case "1":
			return "第1大节";
		case "2":
			return "第2大节";
		case "3":
			return "第3大节";
		case "4":
			return "第4大节";
		default:
			return "";
		}
	}
	
	/**
	 * 将中文表示转换成字符串
	 * @param time
	 * @return
	 */
	public static String unTransTeaTime(String time) {
		String[] strings = time.split("，");
		return unTransWeek(strings[0])+"-"+unTransTime(strings[1]);
	}
	private static String unTransTime(String time) {
		switch (time) {
		case "第1大节":
			return "1";
		case "第2大节":
			return "2";
		case "第3大节":
			return "3";
		case "第4大节":
			return "4";
		default:
			return "";
		}
	}
	private static String unTransWeek(String week) {
		switch (week) {
		case "周一":
			return "1";
		case "周二":
			return "2";
		case "周三":
			return "3";
		case "周四":
			return "4";
		case "周五":
			return "5";
		default:
			return "";
		}
	}
	
	/**
	 * 将字符串转化成数组
	 * @param paramter
	 * @return
	 */
	public static String[] paserToArr(String paramter) {
		String string = paramter.substring(1, paramter.length()-1);
		String[] arr = string.split(",");
		for(int i = 0;i<arr.length;i++) {
			arr[i] = arr[i].substring(1, arr[i].length()-1);
		}
		return arr;
	}
}
