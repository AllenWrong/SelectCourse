package com.course.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.MajorPlanInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class MPMajorServlet
 */
@WebServlet("/MPMajorServlet")
public class MPMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MngService mngService = new MngService("root", "root");
		ArrayList<MajorPlanInfo> list = mngService.findAllMajorPlan();
		ArrayList<String> majorList = new ArrayList<>();
		for(int i = 0;i<list.size();i++) {
			majorList.add(list.get(i).getMajorPlanName());
		}
		majorList = JsonTool.toReduce(majorList);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		for(int i = 0;i<majorList.size();i++) {
			JSONObject data = new JSONObject();
			data.put("majorName", majorList.get(i));
			jsonObject.put("data"+i, data);
		}
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
