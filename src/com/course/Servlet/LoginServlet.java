package com.course.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.course.bean.Notice;
import com.course.bean.StuInfo;
import com.course.bean.TeacherInfor;
import com.course.bean.UserInfo;
import com.course.service.MngService;
import com.course.service.StudentService;
import com.course.service.TeacherService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 因为用户名和密码不允许中文，所以这里不可以不设置编码方式*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String role = request.getParameter("role");
		
		HttpSession session = request.getSession();
		if(role.equals("student")) {
			StudentService studentService = new StudentService("student", "12345");
			StuInfo stuInfo = (StuInfo)studentService.viewBasicInfo(username);
			if(null == stuInfo) {
				session.setAttribute("msg", "nouser");
				request.getRequestDispatcher("/html/login.jsp").forward(request, response);
			}else {
				if(passwd.equals(stuInfo.getStuPwd())) {
					session.setAttribute("username", username);
					session.setAttribute("user", stuInfo);
					session.setAttribute("noticeList", returnNotice());
					response.sendRedirect(request.getContextPath()+"/html/student/student_index.jsp");
				}else {
					session.setAttribute("msg", "passwdwrong");
					request.getRequestDispatcher("/html/login.jsp").forward(request, response);
				}
			}
		}else if(role.equals("teacher")) {
			TeacherService teacherService = new TeacherService("teacher", "12345");
			TeacherInfor teacherInfor = (TeacherInfor)teacherService.viewBasicInfo(username);
			System.out.println(teacherInfor.toString());
			if(null == teacherInfor) {
				session.setAttribute("msg", "nouser");
				request.getRequestDispatcher("/SelectCourse/html/login.jsp").forward(request, response);
			}else {
				if(passwd.equals(teacherInfor.getTeaPwd())) {
					session.setAttribute("user", teacherInfor);
					session.setAttribute("username", username);
					response.sendRedirect(request.getContextPath()+"/html/teacher/teacher_index.jsp");
				}else {
					request.setAttribute("msg", "passwdwrong");
					session.setAttribute("noticeList", returnNotice());
					request.getRequestDispatcher("/html/login.jsp").forward(request, response);;
				}
			}
		}else if(role.equals("manager")){
			MngService mngService = new MngService("root","root");
			UserInfo userInfo = mngService.findUser(username);
			System.out.println(userInfo.toString());
			if(null == userInfo) {
				session.setAttribute("msg", "nouser");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				if(passwd.equals(userInfo.getUserPwd())) {
					session.setAttribute("username", username);
					session.setAttribute("user", userInfo);
					session.setAttribute("noticeList", returnNotice());
					response.sendRedirect(request.getContextPath()+"/html/admin/admin_index.jsp");
				}else {
					session.setAttribute("msg", "passwdwrong");
					request.getRequestDispatcher("/html/login.jsp").forward(request, response);
				}
			}
		}else {
			System.out.println("未选择role");
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 查询通知
	 * @param mngService 服务接口
	 * @return 返回通知列表
	 */
	public ArrayList<Notice> returnNotice(){
		MngService mngService = new MngService("root", "root");
		ArrayList<Notice> list = mngService.findNotice();
		return list;
	}
	
	/*public void test() {
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("data"));
		System.out.println(jsonObject.toString());
		String username = jsonObject.getString("username");
		String passwd = jsonObject.getString("passwd");
		String userType = jsonObject.getString("role");
	
		HttpSession session = request.getSession();
		PrintWriter print = response.getWriter();
		// 根据相应的数据类型进行连接
		if("student".equals(userType)) {
			StudentService studentService = new StudentService("student", "12345");
			StuInfo stuInfo = (StuInfo)studentService.viewBasicInfo(username);
			System.out.println(stuInfo.toString());
			if(stuInfo == null) {
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("code", 0);
				jsonObject2.put("data", "nouser");
				print.print(jsonObject2);
			}else {
				if(passwd.equals(stuInfo.getStuPwd())) {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("code", 0);
					jsonObject2.put("data", "yes");
					print.print(jsonObject2);
					session.setAttribute("username", username);
					session.setAttribute("user", stuInfo);
					print.flush();
					response.sendRedirect(request.getContextPath()+"/teacher/teacher_index.jsp");
					print.close();
				}else {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("code", 0);
					jsonObject2.put("data", "wrongpasswd");
					print.print(jsonObject2);
				}
			}
			print.flush();
			print.close();
			
		}else if("teacher".equals(userType)){
			TeacherService teacherService = new TeacherService("teacher", "12345");
			TeacherInfor teacherInfor = (TeacherInfor)teacherService.viewBasicInfo(username);
			System.out.println(teacherInfor.toString());
			if(null == teacherInfor) {
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("code", 0);
				jsonObject2.put("data", "nouser");
				print.print(jsonObject2);
			}else {
				if(passwd.equals(teacherInfor.getTeaPwd())) {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("code", 0);
					jsonObject2.put("data", "yes");
					print.print(jsonObject2);
					session.setAttribute("username", username);
					session.setAttribute("user", teacherInfor);
				}else {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("code", 0);
					jsonObject2.put("data", "wrongpasswd");
					print.print(jsonObject2);
				}
			}
			request.getRequestDispatcher("/SelectCourse/html/login.jsp").forward(request, response);
			print.flush();
			print.close();
		}else if ("manager".equals(userType)) {
			MngService mngService = new MngService("root","root");
			UserInfo userInfo = mngService.findUser(username);
			System.out.println(userInfo.toString());
			if(null == userInfo) {
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("code", 0);
				jsonObject2.put("data", "nouser");
				print.print(jsonObject2);
			}else {
				if(passwd.equals(userInfo.getUserPwd())) {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("code", 0);
					jsonObject2.put("data", "yes");
					print.print(jsonObject2);
					session.setAttribute("username", username);
					session.setAttribute("user", userInfo);
				}else {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("code", 0);
					jsonObject2.put("data", "wrongpasswd");
					print.print(jsonObject2);
				}
			}
			print.flush();
			print.close();
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			
		}		
	}*/

}
