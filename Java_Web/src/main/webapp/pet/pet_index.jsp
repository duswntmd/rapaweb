<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pet Management Home</title>
<style type="text/css">
    img { width: 50%; }
    main div:last-child { display: flex; justify-content: center; margin-top: 5em; }
    li { display: inline-block; width: 7em; text-align: center; }
    nav ul { list-style-type: none; padding: 0; }
    nav ul li { margin: 10px; }
    nav ul li a { text-decoration: none; color: #000; font-weight: bold; }
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">        
    function logout() {
        $.ajax({
            url: 'user',
            method: 'get',
            cache: false,
            data: {cmd: 'logout'},
            dataType: 'json',
            success: function(res) {
                alert(res.logout ? '로그아웃 성공' : '로그아웃 실패');
            },
            error: function(xhr, status, err) {
                alert('에러:' + err);
            }
        });
    }
</script>
</head>
<body>
<main>
    <nav>
        <ul>
            <li><a href="pet?cmd=index">HOME</a></li>
            <li><a href="pet?cmd=list">PET LIST</a></li>
            <li><a href="pet?cmd=inputForm">ADD PET</a></li>
            <!--<li><a href="javascript:logout();">LOGOUT</a></li>-->
        </ul>
    </nav>
    <div><img src="img/pet/Five.jpg" alt="Pet Home"></div>
</main>
</body>
</html>
