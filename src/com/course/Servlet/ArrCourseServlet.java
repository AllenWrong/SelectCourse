package com.course.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.CourseInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ArrCourseServlet
 */
@WebServlet("/ArrCourseServlet")
public class ArrCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  grade = request.getParameter("tpGrade");
		String term = request.getParameter("tpTerm");
		String year = request.getParameter("tpYear");
		MngService mngService = new MngService("root", "root");
		System.out.println(grade+term+year);
		ArrayList<CourseInfo> list = mngService.findWaitArrCourse(grade, term, year);
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("code", 0);
		jsonObject2.put("msg", "");
		jsonObject2.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
            JSONObject data = new JSONObject();
            data.put("tpTerm", list.get(i).getCourTerm());
            data.put("tpYear", list.get(i).getTpYear());
            data.put("tpMajorNum", list.get(i).getTpMajorNum());
            data.put("tpGrade", list.get(i).getTpGrade());
            data.put("tpCourseNum", list.get(i).getCourNum());
            data.put("courName", list.get(i).getCourName());
            data.put("courXs", list.get(i).getCourXs());
            data.put("courRedit", list.get(i).getCourRedit());
            data.put("courType", list.get(i).getCourType());
			jsonArray.add(data);
		}
		jsonObject2.put("data", jsonArray);
        System.out.println(jsonObject2);
        response.setCharacterEncoding("utf-8");
        JsonTool.writeObject(response.getWriter(), jsonObject2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
