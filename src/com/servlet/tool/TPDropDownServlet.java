package com.servlet.tool;

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

import net.sf.json.JSONObject;

/**
 * Servlet implementation class TPDorpDownServlet
 */
@WebServlet("/TPDropDownServlet")
public class TPDropDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MngService mngService = new MngService("root", "root");
		ArrayList<TermPlanInfo> list = mngService.findAllTermPlanInfo();
		ArrayList<String> numList = new ArrayList<>();
		ArrayList<String> nameList = new ArrayList<>();
		for(int i = 0;i<list.size();i++) {
			numList.add(list.get(i).getTpMajorNum());
			nameList.add(list.get(i).getTpMajorName());
		}
		numList = JsonTool.toReduce(numList);
		nameList = JsonTool.toReduce(nameList);
		
		JSONObject jsonObject = new JSONObject();
		for(int i = 0;i<numList.size();i++) {
			JSONObject data = new JSONObject();
			data.put("majornum", numList.get(i));
			data.put("name", nameList.get(i));
			jsonObject.put("code", 0);
			jsonObject.put("data"+i, data);
		}
		System.out.println(jsonObject.toString());
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
