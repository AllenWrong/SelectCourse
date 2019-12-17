package com.selcourse.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.OrderCourseInfo;
import com.course.bean.SelCourse;
import com.course.bean.StuInfo;
import com.course.service.StudentService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectCourseServlet
 */
@WebServlet("/SelectCourseServlet")
public class SelectCourseServlet extends HttpServlet {
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
		String[] year = JsonTool.paserToArr(jsonObject.getString("year"));
		String[] term = JsonTool.paserToArr(jsonObject.getString("term"));
		String[] couNum = JsonTool.paserToArr(jsonObject.getString("couNum"));
		String[] teaNum = JsonTool.paserToArr(jsonObject.getString("teaNum"));
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		StudentService studentService = new StudentService("student", "12345");
		boolean flag = true;
		for(int i = 0;i<grade.length;i++) {
			list = studentService.findTimePlace(grade[i], year[i], term[i], couNum[i], teaNum[i]);
			for(int k = 0;k<list.size();k++) {
				SelCourse selCourse = new SelCourse();
				selCourse.setSelectYear(year[i]);
				selCourse.setSelectGrade(grade[i]);
				selCourse.setSelectTerm(term[i]);
				selCourse.setSelectTeaNum(teaNum[i]);
				selCourse.setSelectCourseNum(couNum[i]);
				selCourse.setSelectStuNum(((StuInfo)request.getSession().getAttribute("user")).getStuNum());
				selCourse.setSelTeaTime(list.get(k).getOrdCouTeaTime());
				selCourse.setSelTeaPlace(list.get(k).getOrdCouTeaPlace());
				if(!studentService.selectCourse(selCourse)) {
					flag = false;
					break;
				}
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
