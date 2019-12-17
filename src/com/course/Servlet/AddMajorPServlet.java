package com.course.Servlet;

import java.io.IOException;
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
 * Servlet implementation class AddMajorPServlet
 */
@WebServlet("/AddMajorPServlet")
public class AddMajorPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = JsonTool.parseTool(request.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int majorPlanID = Integer.parseInt(jsonObject.getString("majorPlanID"));
		String majorPlanNum = jsonObject.getString("majorPlanNum");
		String majorPlanName = jsonObject.getString("majorPlanName");
		String majorPlanGrade = jsonObject.getString("majorPlanGrade");
		int majorPlanComcre = Integer.parseInt(jsonObject.getString("majorPlanComcre"));
		int majorPlanElcre = Integer.parseInt(jsonObject.getString("majorPlanElcre"));
		
		MajorPlanInfo majorPlanInfo = new MajorPlanInfo(majorPlanID, majorPlanNum, majorPlanName, majorPlanGrade, majorPlanComcre, majorPlanElcre);
		MngService mngService = new MngService("root", "root");
		if(mngService.doAddMajorPlan(majorPlanInfo)) {
			JSONObject msg = new JSONObject();
			msg.put("msg", "true");
			JsonTool.writeObject(response.getWriter(), msg);
		}else {
			JSONObject msg = new JSONObject();
			msg.put("msg", "false");
			JsonTool.writeObject(response.getWriter(), msg);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
