<%@page import="com.test.sku.servlet.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
   User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 상세 정보</title>
</head>
<body>
<h1>사용자 상세 정보</h1>
<% if (user != null) { %>
    <p>아이디: <%= user.getUid() %></p>
    <p>암호: <%= user.getPwd() %></p>
<% } else { %>
    <p>사용자 정보를 찾을 수 없습니다.</p>
<% } %>
<a href="user?cmd=list">목록으로 돌아가기</a>
</body>
</html>
