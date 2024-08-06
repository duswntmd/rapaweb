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
</head>
<body>
<h1>이용자 목록보기</h1>
<% 
   for(int i=0;i<list.size();i++) {
		User u = list.get(i);%>
		<div><a href="user?cmd=detail&uid=<%=u.getUid()%>"><%=u.getUid()%></a> / <%=u.getPwd()%> </div>
		
<%	  }
%>
</body>
</html>