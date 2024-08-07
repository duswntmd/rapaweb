package com.test.sku.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        }else if (cmd.equals("edit")) {
            String uid = request.getParameter("uid");
            request.setAttribute("uid", uid);
            viewPath = "/jsp/editPwd.jsp";
        } else if (cmd.equals("updatePwd")) {
            String uid = request.getParameter("uid");
            String newPwd = request.getParameter("pwd");
            UserDAO dao = new UserDAO();
            boolean updated = dao.updatePwd(uid, newPwd);
//            System.out.println("업데이트 결과" + updated);
            PrintWriter out = response.getWriter();
            out.print("{\"updated\": " + updated + "}");
            out.flush();
//            viewPath = "/jsp/editPwd.jsp";
        } else if (cmd.equals("delete")) {
            String uid = request.getParameter("uid");
            UserDAO dao = new UserDAO();
            boolean deleted = dao.deleteUser(uid);
            PrintWriter out = response.getWriter();
            out.print("{\"deleted\": " + deleted + "}");
            out.flush();
        }
		
		if(viewPath!=null) {
		getServletContext().getRequestDispatcher(viewPath).forward(request, response);
		}
	}
}
