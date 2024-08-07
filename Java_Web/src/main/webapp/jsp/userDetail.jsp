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
<style type="text/css">
	table { border: 1px solid black; padding: 10px; border-collapse: collapse; 
		border-spacing: 0; margin: 0.2em auto; 
	}
	th:first-child { background-color: #ddd; border-right: 3px double black; }
	th,td { border: 1px solid black; padding: 0.2em 1em; }
	td>a { text-decoration: none; color: blue; }
	main { width: fit-content; margin: auto; }
	h3 { text-align: center; text-decoration: underline; }
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
    function goList() {
        location.href = "user?cmd=list";
    }

    function deleteUser() {   
        if (!confirm('정말로 삭제하시겠습니까?')) return;  // 수정된 부분

        let obj = {};
        obj.cmd = 'delete';
        obj.uid = '<%= user.getUid() %>';
        

        $.ajax({
            url: 'user',
            method: 'post',
            cache: false,
            data: obj,
            dataType: 'json',
            success: function(res) {
                alert(res.deleted ? '삭제 성공' : '삭제 실패');
                if (res.deleted) {
                    location.href = "user?cmd=list";  // 삭제 성공 시 목록 페이지로 이동
                }
            },
            error: function(xhr, status, err) {
                alert('에러: ' + err);
            }
        });
    }
</script>
</head>
<body>
<main>
<h3>사용자 상세 정보</h3>
<table>
<% if (user != null) { %>
    <tr><th>아이디:</th> <th><%= user.getUid() %></th></tr>
    <tr><th>암호:</th> <th><%= user.getPwd() %></th></tr>
<% } else { %>
    <p>사용자 정보를 찾을 수 없습니다.</p>
<% } %>
</table>
<a href="user?cmd=edit&uid=<%= user.getUid() %>">수정</a>
<a href="javascript:deleteUser();">삭제</a>
<a href="user?cmd=list">목록보기</a>
<a href="user?cmd=list"><button>목록보기</button></a>
<a href="user?cmd=list"><button onclick="goList();">목록보기2</button></a>
</main>
</body>
</html>
