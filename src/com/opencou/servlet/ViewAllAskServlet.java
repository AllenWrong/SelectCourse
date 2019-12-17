package com.opencou.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.AskCourse;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ViewAskServlet
 */
@WebServlet("/ViewAllAskServlet")
public class ViewAllAskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MngService mngService = new MngService("root", "root");
		ArrayList<AskCourse> list = mngService.findAllAskCourse();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("courNum", list.get(i).getAskCouCouNum());
			data.put("courName", list.get(i).getCouName());
			data.put("teacherNum", list.get(i).getAskCouTeaNum());
			data.put("teacherName", list.get(i).getTeaName());
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
