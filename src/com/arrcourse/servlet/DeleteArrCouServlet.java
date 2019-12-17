package com.arrcourse.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.OrderCourseInfo;
import com.course.service.MngService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteArrCouServlet
 */
@WebServlet("/DeleteArrCouServlet")
public class DeleteArrCouServlet extends HttpServlet {
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
		String[] grade = JsonTool.paserToArr(jsonObject.getString("grade"));
		String[] term = JsonTool.paserToArr(jsonObject.getString("term"));
		String[] year = JsonTool.paserToArr(jsonObject.getString("year"));
		String[] couNum = JsonTool.paserToArr(jsonObject.getString("couNum"));
		String[] teaNum = JsonTool.paserToArr(jsonObject.getString("teaNum"));
		String[] teaTime = JsonTool.paserToArr(jsonObject.getString("teaTime"));
		
		MngService mngService = new MngService("root", "root");
		boolean flag = true;
		for(int i = 0;i<grade.length;i++) {
			OrderCourseInfo orderCourseInfo = new OrderCourseInfo();
			orderCourseInfo.setOrdCouGrade(grade[i]);
			orderCourseInfo.setOrdCouTerm(Integer.parseInt(term[i]));
			orderCourseInfo.setOrdCouYear(year[i]);
			orderCourseInfo.setOrdCouCouNum(couNum[i]);
			orderCourseInfo.setOrdCouTeaNum(teaNum[i]);
			orderCourseInfo.setOrdCouTeaTime(JsonTool.unTransTeaTime(teaTime[i]));
			System.out.println(orderCourseInfo);
			if(!mngService.doDeleteArrangeCourse(orderCourseInfo)) {
				flag = false;
				break;
			}
		}
		JSONObject msg = new JSONObject();
		if(flag) {
			msg.put("msg", "true");
		}else {
			msg.put("msg", "false");
		}
		JsonTool.writeObject(response.getWriter(), msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
