package com.test.sku.servlet;

import java.io.IOException;
import java.util.List;

import com.test.sku.Gugu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gugu")
public class GuguServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Gugu g = new Gugu(request);
		List<String> list = g.getGugu();
		request.setAttribute("list", list);
		
		getServletContext().getRequestDispatcher("/jsp/gugu.jsp").forward(request, response);
	}

}
