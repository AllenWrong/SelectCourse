package com.servlet.tool;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.TeacherInfor;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DPDWTeaServelt
 */
@WebServlet("/DPDWTeaServelt")
public class DPDWTeaServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String couNum = request.getParameter("courNum");
		System.out.println(couNum);
		MngService mngService = new MngService("root", "root");
		ArrayList<TeacherInfor> list = mngService.findTeaByCou(couNum);
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("code", 0);
		jsonObject2.put("msg", "");
		jsonObject2.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("teaNum", list.get(i).getTeaNum());
			data.put("teaName", list.get(i).getTeaName());
			jsonArray.add(data);
		}
		jsonObject2.put("data", jsonArray);
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
