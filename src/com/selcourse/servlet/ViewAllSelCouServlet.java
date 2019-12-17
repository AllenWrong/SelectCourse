package com.selcourse.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.SelCourse;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ViewAllSelCouServlet
 */
@WebServlet("/ViewAllSelCouServlet")
public class ViewAllSelCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grade = request.getParameter("tpGrade2"); 
		String couName = request.getParameter("courName");
		String teaName = request.getParameter("teachName");
		String stuName = request.getParameter("studentName");
		if(grade == "") {
			grade = null;
		}
		if(couName == "") {
			couName = null;
		}
		if(teaName == "") {
			teaName = null;
		}
		if(stuName == "") {
			stuName = null;
		}
		MngService mngService = new MngService("root", "root");
		ArrayList<SelCourse> list = mngService.findSelCouByMultiKey(grade, couName, teaName, stuName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<list.size();i++) {
			JSONObject data = new JSONObject();
			data.put("tpTerm", list.get(i).getSelectTerm());
			data.put("tpYear", list.get(i).getSelectYear());
			data.put("tpMajorName", list.get(i).getStuMajor());
			data.put("tpGrade", list.get(i).getSelectGrade());
			data.put("courName", list.get(i).getCouName());
			data.put("teachName", list.get(i).getTeaName());
			data.put("stuName",list.get(i).getStuName() );
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
