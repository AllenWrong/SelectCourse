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
import com.course.service.MngService;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("请求了Servlet");
		
		MngService mngService = new MngService("root", "root");
		ArrayList<Notice> list = new ArrayList<>();
		list = mngService.findNotice();
		for(int i = 0;i<list.size();i++) {
			System.out.print(list.get(i).getNoticeTime()+"-");
			System.out.println(list.get(i).getNoticeContent());
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("noticeList", list);
		session.setAttribute("size", list.size());
		request.getRequestDispatcher("announcement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
