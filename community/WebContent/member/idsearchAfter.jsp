<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디가 뿅 나타납니다</title>
</head>
<body>

<h3>회원님의 id는 ${member.id } 입니다.</h3>
<a href="<%=request.getContextPath() %>/LoginForm.do" style="text-decoration:none">
   로그인
</a>

</body>
</html>