package com.course.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.course.bean.OrderCourseInfo;
import com.course.service.TeacherService;

/**
 * Servlet implementation class TeacherSchedulesServlet
 */
@WebServlet("/TeacherSchedulesServlet")
public class TeacherSchedulesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		TeacherService teacherService = new TeacherService("teacher", "12345");
		ArrayList<OrderCourseInfo> list = new ArrayList<>();
		try {
			list = teacherService.viewCourseTable(username);
		} catch (Exception e) {
			System.out.println("教师课表查询错误！");
			e.printStackTrace();
			session.setAttribute("message", "teaSchdlWro");
		}
		session.setAttribute("teaSchdl", list);
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
//		response.sendRedirect("/SelectCourse/html/teacher/teacher_index.jsp");
		request.getRequestDispatcher("teacher_schedules.jsp").forward(request, response);
		System.out.println("转发了请求");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
