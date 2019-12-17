package com.arrcourse.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.OrderCourseInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddArrCourseServlet
 */
@WebServlet("/AddArrCourseServlet")
public class AddArrCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = JsonTool.parseTool(request.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String ordMajorNum = jsonObject.getString("tpMajorNum");
		String ordGrade = jsonObject.getString("tpGrade");
		String ordYear = jsonObject.getString("tpYear");
		int ordTerm = Integer.parseInt(jsonObject.getString("tpTerm"));
		String ordCouNum = jsonObject.getString("tpCourseNum");
		String ordTeaNum = jsonObject.getString("teachName");
		int ordMaxSize = Integer.parseInt(jsonObject.getString("course_size"));
		String ordQZZ = jsonObject.getString("startAndEnd");
		String week = jsonObject.getString("week");
		String time = jsonObject.getString("course_time");
		String ordTeaPlace = jsonObject.getString("course_place");
		String ordTeaTime = week+"-"+time;
		OrderCourseInfo orderCourseInfo = new OrderCourseInfo(0, ordMajorNum, ordGrade, ordYear, ordTerm, ordCouNum, ordTeaNum, ordMaxSize, ordQZZ, ordTeaTime, ordTeaPlace);
		MngService mngService = new MngService("root", "root");
		JSONObject message = new JSONObject();
		if(mngService.doAddArrCourInfo(orderCourseInfo)) {
			message.put("msg", "true");
		}else {
			message.put("msg", "false");
		}
		response.setCharacterEncoding("utf-8");
		JsonTool.writeObject(response.getWriter(),message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
