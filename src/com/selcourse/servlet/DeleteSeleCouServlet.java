package com.selcourse.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.SelCourse;
import com.course.bean.StuInfo;
import com.course.service.StudentService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteSeleCouServlet
 */
@WebServlet("/DeleteSeleCouServlet")
public class DeleteSeleCouServlet extends HttpServlet {
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
		String[] teaNum = JsonTool.paserToArr(jsonObject.getString("teaNum"));
		String[] couNum = JsonTool.paserToArr(jsonObject.getString("couNum"));
		StudentService studentService = new StudentService("student", "12345");
		boolean flag = true;
		for(int i = 0;i<teaNum.length;i++) {
			SelCourse selCourse = new SelCourse();
			selCourse.setSelectTeaNum(teaNum[i]);
			selCourse.setSelectCourseNum(couNum[i]);
			selCourse.setSelectStuNum(((StuInfo)request.getSession().getAttribute("user")).getStuNum());
			if(!studentService.deleteSelectCourse(selCourse)) {
				flag = false;
				break;
			}
		}
		JSONObject msg = new JSONObject();
		msg = JsonTool.writeMsg(flag);
		JsonTool.writeObject(response.getWriter(), msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
