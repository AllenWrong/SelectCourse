package com.course.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.course.bean.CourseInfo;
import com.course.service.MngService;

/**
 * Servlet implementation class StudentTrainPlanServlet
 */
@WebServlet("/StudentTrainPlanServlet")
public class StudentTrainPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		String grade = request.getParameter("nianji");
		System.out.println(username+"-"+grade);
		MngService mngService = new MngService("root", "root");
		ArrayList<CourseInfo> list = mngService.findAllMajorPlan(username, grade);
		session.setAttribute("majorplanlist", list);
		request.getRequestDispatcher("/html/student/student_trainManage/train_plan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
