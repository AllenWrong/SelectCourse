package com.course.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.CourseInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
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
		String courNum = jsonObject.getString("courNum");
		String courName = jsonObject.getString("courName");
		int courXs = Integer.parseInt(jsonObject.getString("courXs"));
		int courRedit = Integer.parseInt(jsonObject.getString("courRedit"));
		String courType = jsonObject.getString("courType");
		String courTerm = jsonObject.getString("courTerm");
		String courXxkNum = jsonObject.getString("courXxkNum");
		CourseInfo courseInfo = new CourseInfo(0, courNum, courName, courXs, courRedit, courType, courTerm, courXxkNum);
		
		MngService mngService = new MngService("root", "root");
		if(mngService.doAddCourseInfo(courseInfo)) {
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
