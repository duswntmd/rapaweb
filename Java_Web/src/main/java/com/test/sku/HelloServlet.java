package com.test.sku;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String _dan = request.getParameter("dan");
        List<String> list = new ArrayList<>();        
        int dan = Integer.parseInt(_dan);
        for (int i = 1; i <= 9; i++) {
        	list.add(String.format("%d * %d = %d", dan, i, dan*i));
        }
        PrintWriter out = response.getWriter();
        for(int i=0; i<list.size(); i++) {
        	out.println(list.get(i) + "<br>");
        }
        out.println("<p>");
        for(int i=2; i<10; i++) {
        	out.print(String.format("<a href= 'hello?dan=%d'>%d</a>", i,i));
        }
        
        out.println("<p>");
        String form = "<form action='hello' method= 'post'>";
        form += "<input type= 'text' name='dan' value='2'>";
        form += "<button type='submit'>확인</button>";
        form += "</from>";
        out.println(form);
    }
}
