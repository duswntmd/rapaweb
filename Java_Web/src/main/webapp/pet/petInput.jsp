<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 추가</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
function addPet() {
    var no = document.querySelector("#no").value;
    var name = document.querySelector("#name").value;
    var origin = document.querySelector("#origin").value;
    var weight = document.querySelector("#weight").value;
    var birth = document.querySelector("#birth").value;
    var price = document.querySelector("#price").value;
    var pic = document.querySelector("#pic").value;

    if (no == "" || name == "" || origin == "" || weight == "" || birth == "" || price == "" || pic == "") {
        alert('모든 필드를 채워주세요');
        return false;
    }

    var obj = {
        cmd: 'add',
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
            alert(res.added ? '펫 추가 성공' : '펫 추가 실패');
            if (res.added) {
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
    <h3>펫 추가</h3>
    <form onsubmit="return addPet();">
        <div><label for="no">번호</label>
            <input type="text" id="no" name="no">
        </div>
        <div><label for="name">이름</label>
            <input type="text" id="name" name="name">
        </div>
        <div><label for="origin">원산지</label>
            <input type="text" id="origin" name="origin">
        </div>
        <div><label for="weight">무게</label>
            <input type="text" id="weight" name="weight">
        </div>
        <div><label for="birth">생일</label>
            <input type="date" id="birth" name="birth">
        </div>
        <div><label for="price">가격</label>
            <input type="text" id="price" name="price">
        </div>
        <div><label for="pic">사진</label>
            <input type="text" id="pic" name="pic">
        </div>
        <div>
            <button type="reset">취소</button>
            <button type="submit">추가</button>
        </div>
    </form>
    <a href="pet?cmd=index">HOME</a>
</div>
</body>
</html>
