<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 입력</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">		
    function add() {
    	var ser = $('#addForm').serialize();
    	   $.ajax({
    	      url:'user',
    	      method:'post',
    	      cache:'post',
    	      data:ser,
    	      dataType:'json',
    	      success:function(res){

    	         alert(res.added ? '이용자 정보 추가 성공':'추가실패');
    	         if((!res.added) && res.cause!=""){
    	            alert(res.cause);
    	            location.href="user?cmd=loginForm";
    	         }
    	         if(res.added) location.href='user?cmd=detail&uid=' + $('#uid').val();

    	      },
    	      error:function(xhr,status,err){
    	         alert('에러:' + err);
    	      }
    	   });
    	}

</script>
</head>
<body>
<h2>사용자 입력</h2>
<form id="addForm" action="user" method="post">
	<input type="hidden" name="cmd" id="cmd" value="add">
    아이디: <input type="text" id="uid" name="uid"><br>
    비밀번호: <input type="password" id="pwd" name="pwd"><br>
   <div>
      <button type="reset">취소</button>
      <button type="button" onclick="add();">저장</button>
   </div>
</form>
</body>
</html>
