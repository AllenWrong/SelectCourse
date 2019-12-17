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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CInformationServlet
 */
@WebServlet("/CInformationServlet")
public class CInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String key = request.getParameter("coursename");
		System.out.println(key);
		if(null == key) {
			doFindAll(response.getWriter());
		}else {
			doFindByKey(response.getWriter(), key);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void doFindAll(PrintWriter out) {
		MngService mngService = new MngService("root","root");
		ArrayList<CourseInfo> list = mngService.findAllCourseInfo();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("courID", list.get(i).getCourID());
			data.put("courNum", list.get(i).getCourNum());
			data.put("courName", list.get(i).getCourName());
			data.put("courXs", list.get(i).getCourXs());
			data.put("courRedit", list.get(i).getCourRedit());
			data.put("courType", list.get(i).getCourType());
			data.put("courTerm", list.get(i).getCourTerm());
			data.put("courXxkNum", list.get(i).getCourXxkNum());
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		out.print(jsonObject);
		
		out.flush();
		out.close();
	}
	
	private void doFindByKey(PrintWriter out,String key) {
		MngService mngService = new MngService("root","root");
		ArrayList<CourseInfo> list = mngService.findCouInfoByKey(key);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("courID", list.get(i).getCourID());
			data.put("courNum", list.get(i).getCourNum());
			data.put("courName", list.get(i).getCourName());
			data.put("courXs", list.get(i).getCourXs());
			data.put("courRedit", list.get(i).getCourRedit());
			data.put("courType", list.get(i).getCourType());
			data.put("courTerm", list.get(i).getCourTerm());
			data.put("courXxkNum", list.get(i).getCourXxkNum());
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		out.print(jsonObject);
		
		out.flush();
		out.close();
	}
}
