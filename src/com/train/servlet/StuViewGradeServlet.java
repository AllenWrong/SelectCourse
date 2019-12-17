package com.train.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.OrderCourseInfo;
import com.course.service.StudentService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class StuViewGradeServlet
 */
@WebServlet("/StuViewGradeServlet")
public class StuViewGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuNum = request.getParameter("stuNum");
		String year = request.getParameter("year");
		String term = request.getParameter("term");
		System.out.println(stuNum);
		System.out.println(year);
		System.out.println(term);
		StudentService studentService = new StudentService("student", "12345");
		ArrayList<OrderCourseInfo> list = studentService.findGrade(year, term, stuNum);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("tpCourseNum", list.get(i).getOrdCouCouNum());
			data.put("courName", list.get(i).getCouName());
			data.put("courType", list.get(i).getCouType());
			data.put("courRedit", list.get(i).getCouXs());
			data.put("studentNum",list.get(i).getCouRedit());
			data.put("sumGrade", list.get(i).getOrdCouMax());
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
