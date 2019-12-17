package com.tea.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.AskCourse;
import com.course.bean.TeacherInfor;
import com.course.service.TeacherService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ViewAskServlet
 */
@WebServlet("/ViewAskServlet")
public class ViewAskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teaNum = ((TeacherInfor)request.getSession().getAttribute("user")).getTeaNum();
		TeacherService teacherService = new TeacherService("teacher", "12345");
		ArrayList<AskCourse> list = teacherService.viewAskCourNoProcess(teaNum);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("teachNum", list.get(i).getAskCouTeaNum());
			data.put("teachName", list.get(i).getTeaName());
			data.put("tpCourseNum", list.get(i).getAskCouCouNum());
			data.put("tpCourseName", list.get(i).getCouName());
			data.put("state", "未处理");
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
