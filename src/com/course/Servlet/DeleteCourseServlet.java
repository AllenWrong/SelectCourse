package com.course.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteCourseServlet
 */
@WebServlet("/DeleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = null;
		try {
			jsonObject = JsonTool.parseTool(request.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(jsonObject.get("courseArr"));
		JSONArray jsonArray = new JSONArray();
		jsonArray = (JSONArray) jsonObject.get("courseArr");
		ArrayList<String> list = new ArrayList<>();
		for(int i = 0;i<jsonArray.size();i++) {
			String courNum = (String) ((JSONObject)jsonArray.get(i)).get("courNum");
			list.add(courNum);
		}
		MngService mngService = new MngService("root", "root");
		if(mngService.doDeleteCourseInfo(list)) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("msg", "true");
			PrintWriter printWriter = response.getWriter();
			JsonTool.writeObject(printWriter, jsonObj);
		}else {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("msg", "false");
			PrintWriter printWriter = response.getWriter();
			JsonTool.writeObject(printWriter, jsonObj);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
