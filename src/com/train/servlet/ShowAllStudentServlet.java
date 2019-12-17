package com.train.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.StuInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ShowAllStudentServlet
 */
@WebServlet("/ShowAllStudentServlet")
public class ShowAllStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grade = request.getParameter("grade");
		String major = request.getParameter("major");
		String stuNum = request.getParameter("studentNum");
		
		
		if(grade == null && major == null && stuNum =="") {
			findAll(response);
		}else {
			findByCondition(grade,major, stuNum,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void findAll(HttpServletResponse response) throws IOException {
		MngService mngService = new MngService("root", "root");
		ArrayList<StuInfo> list = new ArrayList<>();
		list = mngService.findAllStu();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("studentNum", list.get(i).getStuNum());
			data.put("studentName", list.get(i).getStuName());
			data.put("sex", list.get(i).getStuSex());
			data.put("studentTel", list.get(i).getStuPhone());
			data.put("birthday", list.get(i).getStuBirthday());
			data.put("address", list.get(i).getStuHome());
			data.put("major", list.get(i).getStuMajor());
			data.put("nation", list.get(i).getStuNation());
			data.put("politicCountenance", list.get(i).getStuPolicy());
			data.put("schoolTime", list.get(i).getStuInschoolDate());
			data.put("status", "在籍");
			System.out.println(data);
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		response.setCharacterEncoding("utf-8");
		JsonTool.writeObject(response.getWriter(), jsonObject);
	}

	private void findByCondition(String grade,String major,String stuNum,HttpServletResponse response) throws IOException {
		MngService mngService = new MngService("root", "root");
		ArrayList<StuInfo> list = new ArrayList<>();
		list = mngService.findStuByMutiKey(grade, major, stuNum);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("studentNum", list.get(i).getStuNum());
			data.put("studentName", list.get(i).getStuName());
			data.put("sex", list.get(i).getStuSex());
			data.put("studentTel", list.get(i).getStuPhone());
			data.put("birthday", list.get(i).getStuBirthday());
			data.put("address", list.get(i).getStuHome());
			data.put("major", list.get(i).getStuMajor());
			data.put("nation", list.get(i).getStuNation());
			data.put("politicCountenance", list.get(i).getStuPolicy());
			data.put("schoolTime", list.get(i).getStuInschoolDate());
			data.put("status", "在籍");
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		response.setCharacterEncoding("utf-8");
		JsonTool.writeObject(response.getWriter(), jsonObject);
	}
}
