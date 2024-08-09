<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.test.sku.pet.PetVO"%>
<%@page import="java.util.List"%>
<%
    List<PetVO> list = (List<PetVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 목록</title>
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
                location.reload(); // 페이지를 새로고침하여 삭제 결과를 반영
            }
        },
        error: function(xhr, status, err) {
            alert('에러: ' + err);
        }
    });

    return false; // 기본 제출 동작을 막음
}

function search() {
    var category = $('#category').val();
    var keyword = $('#keyword').val();

    if (keyword === "") {
        alert('검색어를 입력해주세요');
        return false;
    }

    var obj = {
        cmd: 'search',
        category: category,
        keyword: keyword
    };

    $.ajax({
        url: 'pet',
        method: 'post',
        data: obj,
        dataType: 'json',
        success: function(res) {
            if (res.list) {
                // 기존 테이블 내용 삭제
                $('table tr:gt(0)').remove();

                // 새로운 검색 결과 추가
                res.list.forEach(function(pet) {
                    $('table').append(
                        '<tr>' +
                        '<td>' + pet.no + '</td>' +
                        '<td>' + pet.name + '</td>' +
                        '<td>' + pet.origin + '</td>' +
                        '<td>' + pet.weight + '</td>' +
                        '<td>' + pet.birth + '</td>' +
                        '<td>' + pet.price + '</td>' +
                        '<td>' +
                        '<a href="pet?cmd=detail&no=' + pet.no + '">상세보기</a> ' +
                        '<a href="pet?cmd=editForm&no=' + pet.no + '">수정</a> ' +
                        '<a href="#" onclick="return deletePet(' + pet.no + ');">삭제</a>' +
                        '</td>' +
                        '</tr>'
                    );
                });
            } else {
                alert('검색 결과가 없습니다.');
            }
        },
        error: function(xhr, status, err) {
            alert('에러: ' + err);
        }
    });

    return false;
}
</script>
</head>
<body>
<h1>펫 목록</h1>

<form action="pet" method="post" onsubmit="return search();">
    <input type="hidden" name="cmd" id="cmd" value="search">
    검색 카테고리
    <select name="category" id="category">
       <option value="no">번호</option>
       <option value="name">이름</option>
    </select>
    <input type="text" name="keyword" id="keyword">
    <button type="submit">검색</button>
</form>

<table border="1">
    <tr>
        <th>번호</th>
        <th>이름</th>
        <th>원산지</th>
        <th>무게</th>
        <th>생일</th>
        <th>가격</th>
        <!--<th>사진</th>-->
        <th>액션</th>
    </tr>
    <%
        for (PetVO pet : list) {
    %>
    <tr>
        <td><%= pet.getNo() %></td>
        <td><%= pet.getName() %></td>
        <td><%= pet.getOrigin() %></td>
        <td><%= pet.getWeight() %></td>
        <td><%= pet.getBirth() %></td>
        <td><%= pet.getPrice() %></td>
        <!--<td><img src="img/pet/<%= pet.getPic() %>" alt="<%= pet.getName() %>" width="100"></td>-->
        <td>
            <a href="pet?cmd=detail&no=<%= pet.getNo() %>">상세보기</a>
            <a href="pet?cmd=editForm&no=<%= pet.getNo() %>">수정</a>
            <!-- <a href="pet?cmd=delete&no=<%= pet.getNo() %>">삭제</a> -->
            <a href="#" onclick="return deletePet(<%= pet.getNo() %>);">삭제</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<a href="pet?cmd=inputForm">펫 추가</a>
<a href="pet?cmd=index">HOME</a>
</body>
</html>
