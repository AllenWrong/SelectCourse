package com.tea.servlet;

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
 * Servlet implementation class SearchBySelCouServlet
 */
@WebServlet("/SearchBySelCouServlet")
public class SearchBySelCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teaNum = request.getParameter("teaNum");
		String couNum = request.getParameter("couNum");
		String term = request.getParameter("couTerm");
		String year = request.getParameter("year");
		System.out.println(teaNum);
		System.out.println(term);
		System.out.println(couNum);
		System.out.println(year);
		TeacherService teacherService = new TeacherService("teacher", "12345");
		ArrayList<OrderCourseInfo> list = teacherService.teaFindByCou(teaNum, couNum, term, year);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("tpGrade", list.get(i).getOrdCouGrade());
			data.put("tpTerm", list.get(i).getOrdCouTerm());
			data.put("tpYear", list.get(i).getOrdCouYear());
			data.put("couNum", list.get(i).getOrdCouCouNum());
			data.put("courName", list.get(i).getCouName());
			data.put("courType", list.get(i).getCouType());
			data.put("studentNum", list.get(i).getOrdCouTeaNum());
			data.put("studentName", list.get(i).getTeaName());
			data.put("courRedit", list.get(i).getCouRedit());
			data.put("courseGrades", list.get(i).getCouXs());
			System.out.println(data);
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
