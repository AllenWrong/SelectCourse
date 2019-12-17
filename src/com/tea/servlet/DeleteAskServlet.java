package com.tea.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.bean.AskCourse;
import com.course.service.TeacherService;
import com.course.tool.JsonTool;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteAskServlet
 */
@WebServlet("/DeleteAskServlet")
public class DeleteAskServlet extends HttpServlet {
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
        
        TeacherService teacherService = new TeacherService("teacher", "12345");
        boolean flag = true;
        for(int i = 0;i<teaNum.length;i++) {
	        if(!teacherService.deleteAskOpenCourse(new AskCourse(0, teaNum[i], couNum[i]))) {
	        	flag = false;
	        	break;
	        }
        }
        JSONObject data = JsonTool.writeMsg(flag);
        response.setCharacterEncoding("utf-8");
        JsonTool.writeObject(response.getWriter(), data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
