package com.opencou.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.TeaCouInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchTeaCouServlet
 */
@WebServlet("/SearchTeaCouServlet")
public class SearchTeaCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teaNum = request.getParameter("teacherName1");
		String couNum = request.getParameter("courName1");
		
		MngService mngService = new MngService("root", "root");
		ArrayList<TeaCouInfo> list = new ArrayList<>();
		list = mngService.findByteaNumCouNum(teaNum, couNum);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("courNum", list.get(i).getTeaCourCourNum());
			data.put("courName", list.get(i).getCouName());
			data.put("teacherNum", list.get(i).getTeaCouTeaNum());
			data.put("teacherName", list.get(i).getTeaName());
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		response.setCharacterEncoding("utf-8");
		JsonTool.writeObject(response.getWriter(), jsonObject);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
