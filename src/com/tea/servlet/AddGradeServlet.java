package com.tea.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.StuGrade;
import com.course.service.TeacherService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddGradeServlet
 */
@WebServlet("/AddGradeServlet")
public class AddGradeServlet extends HttpServlet {
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
		StuGrade stuGrade = new StuGrade(1, jsonObject.getString("couNum"), jsonObject.getString("studentNum"), Integer.parseInt(jsonObject.getString("courseGrades")), 0);
		TeacherService teacherService = new TeacherService("teacher", "12345");

		JSONObject data = new JSONObject();
		if(teacherService.insertGrade(stuGrade)) {
			data.put("msg", "true");
			JsonTool.writeObject(response.getWriter(), data);
		}else {
			data.put("msg","false");
			JsonTool.writeObject(response.getWriter(), data);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
