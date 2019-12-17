package com.arrcourse.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.OrderCourseInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchArrCouServlet
 */
@WebServlet("/SearchArrCouServlet")
public class SearchArrCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grade = request.getParameter("tpGrade1");
		String term = request.getParameter("tpTerm1");
		String year = request.getParameter("tpYear1");
		
		MngService mngService = new MngService("root", "root");
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		list = mngService.findArrangeCourseByKey(grade, term, year);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("tpMajorNum", list.get(i).getOrdCouMajorNum());
			data.put("tpMajorName", list.get(i).getMajorPlanName());
			data.put("tpCourseNum", list.get(i).getOrdCouCouNum());
			data.put("courName", list.get(i).getCouName());
			data.put("teacherNum", list.get(i).getOrdCouTeaNum());
			data.put("teachName", list.get(i).getTeaName());
			data.put("course_time", JsonTool.transferTeaTime(list.get(i).getOrdCouTeaTime()));
			data.put("course_place" ,list.get(i).getOrdCouTeaPlace());
			data.put("course_size", list.get(i).getOrdCouMax());
			data.put("startAndEnd", list.get(i).getOrdCouQzz());
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
