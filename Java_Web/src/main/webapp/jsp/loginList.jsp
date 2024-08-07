<%@page import="java.util.ArrayList"%>
<%@page import="com.test.sku.servlet.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
   List<User> list = (List<User>)request.getAttribute("list");
if (list == null) {
    list = new ArrayList<>(); // null을 빈 리스트로 초기화
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용자 목록보기</title>
<style type="text/css">
	table { border: 1px solid black; padding: 10px; border-collapse: collapse; border-spacing: 0; }
	tr:first-child>th { background-color: #ddd; border-bottom: 3px double black; }
	th,td { border: 1px solid black; padding: 0.2em 1em; }
	td>a { text-decoration: none; color: blue; }
	tr:nth-child(odd) { background-color: #cde; }
	main { width: fit-content; margin: auto; }
	h3 { text-align: center; text-decoration: underline; }
</style>
</head>
<body>
<main>
<h3>이용자 목록보기</h3>
<table>
<tr><th>아이디</th><th>암호</th></tr>
<% 
   for(int i=0;i<list.size();i++) {
		User u = list.get(i);%>
		<tr>
			<td><a href="user?cmd=detail&uid=<%=u.getUid()%>"><%=u.getUid()%></a></td> 
			<td><%=u.getPwd()%></td> 
		</tr>		
<%	  }
%>
</table>
</main>
</body>
</html>