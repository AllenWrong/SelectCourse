package com.course.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.TermPlanInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TermPlanServlet
 */
@WebServlet("/TermPlanServlet")
public class TermPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String term = request.getParameter("term");
		String majorNum = request.getParameter("majorNum");
		System.out.println(term+"-"+majorNum);
		if(term == null && majorNum == null) {
			findAll(response);
		}else {
			findByKey(term, majorNum, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void findAll( HttpServletResponse response) throws IOException {
		MngService mngService = new MngService("root","root");
		ArrayList<TermPlanInfo> list = mngService.findAllTermPlanInfo();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("ID", list.get(i).getTermID());
			data.put("tpTerm", list.get(i).getTpTerm());
			data.put("tpYear", list.get(i).getTpYear());
			data.put("tpMajorName", list.get(i).getTpMajorName());
			data.put("tpCourseNum", list.get(i).getTpCourseNum());
			data.put("courName", list.get(i).getTpCourseName());
			data.put("tpGrade", list.get(i).getTpGrade());
			data.put("tpCourseNum", list.get(i).getTpCourseNum());
			data.put("tpMajorNum", list.get(i).getTpMajorNum());
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		System.out.println(jsonObject.toString());
		response.setCharacterEncoding("utf-8");
		JsonTool.writeObject(response.getWriter(), jsonObject);
	}
	
	public void findByKey(String term,String majorNum,HttpServletResponse response) throws IOException {
		MngService mngService = new MngService("root", "root");
		ArrayList<TermPlanInfo> list = mngService.findTPByKey(term, majorNum);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("count", list.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("ID", list.get(i).getTermID());
			data.put("tpTerm", list.get(i).getTpTerm());
			data.put("tpYear", list.get(i).getTpYear());
			data.put("tpMajorName", list.get(i).getTpMajorName());
			data.put("tpCourseNum", list.get(i).getTpCourseNum());
			data.put("courName", list.get(i).getTpCourseName());
			data.put("tpGrade", list.get(i).getTpGrade());
			data.put("tpMajorNum", list.get(i).getTpMajorNum());
			jsonArray.add(data);
		}
		jsonObject.put("data", jsonArray);
		response.setCharacterEncoding("utf-8");
		JsonTool.writeObject(response.getWriter(), jsonObject);
	}
}
