<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<String> list = (List<String>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 보기</title>
</head>
<body>
<h3>구구단 보기</h3>
<%
	for(int i=0; i<list.size(); i++) {%>
		<%=list.get(i)%><br>
<%	}
%>
</body>
</html>