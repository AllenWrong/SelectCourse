package com.course.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.TermPlanInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddTermPlanServlet
 */
@WebServlet("/AddTermPlanServlet")
public class AddTermPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("请求了adddTerm");
		JSONObject jsonObject = new JSONObject();
		MngService mngService = new MngService("root", "root");
		try {
			jsonObject = JsonTool.parseTool(request.getInputStream());
			int tpTerm = Integer.parseInt(jsonObject.getString("tpTerm"));
			String tpYear = jsonObject.getString("tpYear");
//			String tpMajorName = jsonObject.getString("tpMajorName");
			String tpCourseNum = jsonObject.getString("tpCourseNum");
//			String tpCourName = jsonObject.getString("courName");
			String tpGrade = jsonObject.getString("tpGrade");
			String tpMajorNum = jsonObject.getString("tpMajorNum");
			TermPlanInfo termPlanInfo = new TermPlanInfo(0, tpTerm, tpYear, tpMajorNum, tpGrade, tpCourseNum);
			JsonTool.justExec(mngService.doAddTermPlan(termPlanInfo), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
