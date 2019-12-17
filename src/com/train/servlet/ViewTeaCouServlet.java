package com.train.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.OrderCourseInfo;
import com.course.service.TeacherService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ViewTeaCouServlet
 */
@WebServlet("/ViewTeaCouServlet")
public class ViewTeaCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teaNum = request.getParameter("teaNum");
		TeacherService teacherService = new TeacherService("teacher", "12345");
		ArrayList<OrderCourseInfo> list = teacherService.viewTeaCouInfo(teaNum);
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
//			data.put("tpGrade", list.get(i).getOrdCouGrade());
			data.put("tpCourseNum",list.get(i).getOrdCouCouNum());
			data.put("courName", list.get(i).getCouName());
			data.put("courType", list.get(i).getCouType());
			data.put("courXs", list.get(i).getCouXs());
			data.put("courRedit", list.get(i).getCouRedit());
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
