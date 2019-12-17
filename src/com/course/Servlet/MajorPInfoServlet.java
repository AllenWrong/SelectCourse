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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MajorPInfoServlet
 */
@WebServlet("/MajorPInfoServlet")
public class MajorPInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grade = request.getParameter("grade");
		String majorName = request.getParameter("major");
		System.out.println(grade+"-"+majorName);
		
		if(grade == null || majorName == null) {
			doFindAll(response);
		}else{
			doFindByKey(response, grade, majorName);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void doFindAll(HttpServletResponse response) throws ServletException, IOException {
		MngService mngService = new MngService("root", "root");
		ArrayList<MajorPlanInfo> list = (ArrayList<MajorPlanInfo>)mngService.findAllMajorPlan();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("majorPlanID", list.get(i).getMajorPlanID());
			data.put("majorPlanNum", list.get(i).getMajorPlanNum());
			data.put("majorPlanName", list.get(i).getMajorPlanName());
			data.put("majorPlanGrade", list.get(i).getMajorPlanGrade());
			data.put("majorPlanComcre", list.get(i).getMajorPlanComcre());
			data.put("majorPlanElcre", list.get(i).getMajorPlanElcre());
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		response.setCharacterEncoding("utf-8");
		JsonTool.writeObject(response.getWriter(), jsonObject);
	}

	public void doFindByKey(HttpServletResponse response,String grade,String majorName) throws ServletException, IOException {
		MngService mngService = new MngService("root", "root");
		ArrayList<MajorPlanInfo> list = mngService.findMajorPByKey(grade, majorName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("majorPlanID", list.get(i).getMajorPlanID());
			data.put("majorPlanNum", list.get(i).getMajorPlanNum());
			data.put("majorPlanName", list.get(i).getMajorPlanName());
			data.put("majorPlanGrade", list.get(i).getMajorPlanGrade());
			data.put("majorPlanComcre", list.get(i).getMajorPlanComcre());
			data.put("majorPlanElcre", list.get(i).getMajorPlanElcre());
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		response.setCharacterEncoding("utf-8");
		JsonTool.writeObject(response.getWriter(), jsonObject);
	}
}
