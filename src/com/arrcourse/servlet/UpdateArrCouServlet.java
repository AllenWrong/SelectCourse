package com.arrcourse.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateArrCouServlet
 */
@WebServlet("/UpdateArrCouServlet")
public class UpdateArrCouServlet extends HttpServlet {
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
		
		jsonObject.getString("majorNum");
		jsonObject.getString("tpGrade");
		jsonObject.getString("tpYear");
		jsonObject.getString("tpTerm");
		jsonObject.getString("tpCourseNum");
		jsonObject.getString("teacherNum");
		jsonObject.getString("course_size");
		jsonObject.getString("startAndEnd");
		jsonObject.getString("course_time");
		jsonObject.getString("course_place");
		
		System.out.println(jsonObject);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
