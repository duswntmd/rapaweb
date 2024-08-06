package com.test.sku.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		String viewPath = null;
		
		if(cmd==null || cmd.equals("loginForm")) {
			viewPath = "/jsp/loginForm.jsp";
		}else if(cmd.equals("login")) {
			// 로그인 절차 수행
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			User user = new User(uid, pwd);
			UserDAO dao = new UserDAO();
			boolean ok = dao.login(user);
			
			request.setAttribute("login", ok);
			viewPath = "/jsp/loginResult.jsp";
		}else if (cmd.equals("list")) {
            UserDAO dao = new UserDAO();
            List<User> list = dao.getList();
            request.setAttribute("list", list);
            viewPath = "/jsp/loginList.jsp";
        }
		else if (cmd.equals("list")) {
            UserDAO dao = new UserDAO();
            List<User> list = dao.getList();
            request.setAttribute("list", list);
            viewPath = "/jsp/loginList.jsp";
        }else if (cmd.equals("detail")) {
        	UserDAO dao = new UserDAO();
        	String uid = request.getParameter("uid");
            User user = dao.getUserDetail(uid);
            request.setAttribute("user", user);
            viewPath = "/jsp/userDetail.jsp";
        }
		if(viewPath!=null) {
		getServletContext().getRequestDispatcher(viewPath).forward(request, response);
		}
	}
}
