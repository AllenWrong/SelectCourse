package com.opencou.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.TeaCouInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgreeAskServlet
 */
@WebServlet("/AgreeAskServlet")
public class AgreeAskServlet extends HttpServlet {
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
		String[] couNum = JsonTool.paserToArr(jsonObject.getString("couNum"));
		String[] teaNum = JsonTool.paserToArr(jsonObject.getString("teaNum"));
		
		MngService mngService = new MngService("root", "root");
		boolean flag = true;
		for(int i = 0;i<couNum.length;i++) {
			if(!mngService.doApplyAskCourse(new TeaCouInfo(0, teaNum[i],couNum[i]))) {
				flag = false;
				break;
			}
		}
		JSONObject msg = JsonTool.writeMsg(flag);
		JsonTool.writeObject(response.getWriter(), msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
