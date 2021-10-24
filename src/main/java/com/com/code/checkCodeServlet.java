package com.com.code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class checkCode
 */
@WebServlet("/checkCodeServlet")
public class checkCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String code = request.getParameter("code");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;Charset=UTF-8");
	        // 验证验证码
	        String sessionCode = request.getSession().getAttribute("code").toString();
	        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
	            if (code.equalsIgnoreCase(sessionCode)) {
	                response.getWriter().println("验证通过！");
	            } else {
	                response.getWriter().println("验证失败！");
	            }
	        } else {
	            response.getWriter().println("验证失败！");
	        }
	    }
}
