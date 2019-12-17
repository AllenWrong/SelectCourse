package com.course.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.CourseInfo;
import com.course.service.MngService;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CourseInfoServlet
 */
@WebServlet("/CourseInfoServlet")
public class CourseInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("请求了CourseinforServlet");
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		MngService mngService = new MngService("root","root");
		ArrayList<CourseInfo> list = mngService.findAllCourseInfo();
		
		JSONObject jsonObject = new JSONObject();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("id", list.get(i).getCourNum());
			data.put("name", list.get(i).getCourName());
			data.put("type", list.get(i).getCourType());
			jsonObject.put("code", 0);
			jsonObject.put("data"+i, data);
			
		}
		System.out.println(jsonObject.toString());
		out.print(jsonObject);
		
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
