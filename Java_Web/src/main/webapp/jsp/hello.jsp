<%@page import="java.util.List"%>
<%@page import="com.test.sku.Gugu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="g" class=com.test.sku.Gugu/>
<%
	List<String> list = g.getGugu(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>처음 만드는 JSP</title>
</head>

<body>
<h1>구구단 보기</h1>
<p>
<% // Scriptlet
   for(int i=1;i<list.size();i++) { %>
   <%=list.get(i)%>
      <br>
<%   }
%>
<p>
<a href="hello.jsp?dan=2">2</a>
<a href="hello.jsp?dan=3">3</a>
<a href="hello.jsp?dan=4">4</a>
<a href="hello.jsp?dan=5">5</a>
<a href="hello.jsp?dan=6">6</a>
<a href="hello.jsp?dan=7">7</a>
<a href="hello.jsp?dan=8">8</a>
<a href="hello.jsp?dan=9">9</a>

</body>
</html>