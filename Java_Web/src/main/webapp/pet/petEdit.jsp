<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.test.sku.pet.PetVO"%>
<%
    PetVO pet = (PetVO) request.getAttribute("pet");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 수정</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
function editPet() {
    var no = document.querySelector("#no").value;
    var name = document.querySelector("#name").value;
    var origin = document.querySelector("#origin").value;
    var weight = document.querySelector("#weight").value;
    var birth = document.querySelector("#birth").value;
    var price = document.querySelector("#price").value;
    var pic = document.querySelector("#pic").value;

    if (name == "" || origin == "" || weight == "" || birth == "" || price == "" || pic == "") {
        alert('모든 필드를 채워주세요');
        return false;
    }

    var obj = {
        cmd: 'edit',
        no: no,
        name: name,
        origin: origin,
        weight: weight,
        birth: birth,
        price: price,
        pic: pic
    };

    $.ajax({
        url: 'pet',
        method: 'post',
        data: obj,
        dataType: 'json',
        success: function(res) {
            alert(res.updated ? '펫 수정 성공' : '펫 수정 실패');
            if (res.updated) {
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
    <h3>펫 수정</h3>
    <form onsubmit="return editPet();">
        <input type="hidden" id="no" name="no" value="<%= pet.getNo() %>">
        <div><label for="name">이름</label>
            <input type="text" id="name" name="name" value="<%= pet.getName() %>">
        </div>
        <div><label for="origin">원산지</label>
            <input type="text" id="origin" name="origin" value="<%= pet.getOrigin() %>">
        </div>
        <div><label for="weight">무게</label>
            <input type="text" id="weight" name="weight" value="<%= pet.getWeight() %>">
        </div>
        <div><label for="birth">생일</label>
            <input type="date" id="birth" name="birth" value="<%= pet.getBirth() %>">
        </div>
        <div><label for="price">가격</label>
            <input type="text" id="price" name="price" value="<%= pet.getPrice() %>">
        </div>
        <div><label for="pic">사진</label>
            <input type="text" id="pic" name="pic" value="<%= pet.getPic() %>">
        </div>
        <div>
            <button type="reset">취소</button>
            <button type="submit">저장</button>
        </div>
    </form>
    <a href="pet?cmd=index">HOME</a>
</div>
</body>
</html>
