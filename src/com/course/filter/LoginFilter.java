package com.course.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
//@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest)request;
		HttpServletResponse response2 = (HttpServletResponse)response;
		
		String uri = request2.getRequestURI();
		System.out.println("uri"+uri);
		String[] strings = uri.split("\\.");
		System.out.println(uri);
		if(strings[strings.length-1].equals("css") || strings[strings.length-1].equals("js") || uri.contains(".png")|| uri.contains(".jpg")){
			//如果发现是css或者js文件，直接放行
			chain.doFilter(request, response);
		}
		
		if(!uri.equals("/SelectCourse/html/login.jsp")&&!uri.equals("/SelectCourse/LoginServlet")) {
			Object user = request2.getSession().getAttribute("user");
			if(user == null) {
				response2.sendRedirect("/SelectCourse/html/login.jsp");
				System.out.println("重定向");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
