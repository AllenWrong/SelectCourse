package com.train.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.TrainPlan;
import com.course.service.StudentService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchTrainPlanServlet
 */
@WebServlet("/SearchTrainPlanServlet")
public class SearchTrainPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grade = request.getParameter("grade");
		String majorName = request.getParameter("majorName");
		System.out.println(grade);
		System.out.println(majorName);
		StudentService studentService = new StudentService("student", "12345");
		ArrayList<TrainPlan> list = studentService.viewTrainPlan(majorName, grade);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("majorName", list.get(i).getMajorPlanName());
			data.put("tpGrade", list.get(i).getMajorPlanGrade());
			data.put("tpCourseNum", list.get(i).getTpCoureNum());
			data.put("courName", list.get(i).getCouName());
			data.put("courType", list.get(i).getCouType());
			data.put("courRedit", list.get(i).getCouRedit());
			data.put("courXs", list.get(i).getCou_xs());
			data.put("tpTerm", list.get(i).getTpTerm());
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
