<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 찾기 폼</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(document).ready(function(){
	$("form").submit(function(){
		
		if($("#id").val() == ""){
			alert("ID를 입력하세요");
			$("#id").focus();
			return false;
		}	
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		if($("#mailid").val()==""){
			alert("EMail 주소를 입력하세요");
			$("#mailid").focus();
			return false;
		}
		if($("#domain").val()==""){
			alert("도메인을 입력하세요");
			$("#domain").focus();
			return false;
		}		
	});	
});
</script>



</head>
<body>
	<form method="post"
		action="<%=request.getContextPath()%>/PasswdSearch.do">

		<table border=1 width=350 align=center>
			<caption>비밀번호찾기</caption>
			<tr>
				<td>아이디</td>
				<td><input type=text size=30 id="id" name="id"
					autofocus="autofocus"></td>
			</tr>
			
			<tr>
				<td>이름</td>
				<td><input type=text size=30 id="name" name="name"
					autofocus="autofocus"></td>
			</tr>
			
			<tr>
				<td>E-Mail</td>
				<td><input type=text size=10 id="mailid" name="mailid">@
					<input type=text size=10 id="domain" name="domain">
			<tr>
			</td>
				<td colspan=2 align=center>
				<input type="submit" value="search"></td>
			</tr>
		</table>
	</form>
</body>
</html>