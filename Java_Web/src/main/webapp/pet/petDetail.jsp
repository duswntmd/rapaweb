<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.test.sku.pet.PetVO"%>
<%
    PetVO pet = (PetVO) request.getAttribute("pet");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 상세보기</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
function deletePet(no) {
    if (!confirm('정말로 삭제하시겠습니까?')) return false;

    var obj = {
        cmd: 'delete',
        no: no
    };

    $.ajax({
        url: 'pet',
        method: 'post',
        data: obj,
        dataType: 'json',
        success: function(res) {
            alert(res.deleted ? '펫 삭제 성공' : '펫 삭제 실패');
            if (res.deleted) {
                location.href = "pet?cmd=list";
            }
        },
        error: function(xhr, status, err) {
            alert('에러: ' + err);
        }
    });

    return false; // 폼의 기본 제출 동작을 막음
}
</script>
</head>
<body>
<div id="main">
    <h3>펫 상세보기</h3>
    <table border="1">
        <tr><th>번호</th><td><%= pet.getNo() %></td></tr>
        <tr><th>이름</th><td><%= pet.getName() %></td></tr>
        <tr><th>원산지</th><td><%= pet.getOrigin() %></td></tr>
        <tr><th>무게</th><td><%= pet.getWeight() %></td></tr>
        <tr><th>생일</th><td><%= pet.getBirth() %></td></tr>
        <tr><th>가격</th><td><%= pet.getPrice() %></td></tr>
        <tr><th>사진</th><td><img src="img/pet/<%= pet.getPic() %>" alt="<%= pet.getName() %>" width="100"></td></tr>
    </table>
    <div>
        <button onclick="location.href='pet?cmd=editForm&no=<%= pet.getNo() %>'">수정</button>
        <button onclick="return deletePet(<%= pet.getNo() %>);">삭제</button>
        <button onclick="location.href='pet?cmd=list'">목록보기</button>
    </div>
    <a href="pet?cmd=index">HOME</a>
</div>
</body>
</html>
