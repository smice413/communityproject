<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 폼</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<%-- <script src="<%=request.getContextPath() %>/member/member.js"></script> --%>
<script>
	$(document).ready(function() {
		$("form").submit(function() {

			if ($("#passwd").val() == "") {
				alert("비밀번호를 입력하세요");
				$("#passwd").focus();
				return false;
			}

		});
	});
</script>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/MemberStop.do">

		<table border=1 width=350 align=center>
			<caption>비밀번호입력</caption>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" size=30 id="passwd" name="passwd"
					autofocus="autofocus"></td>

			</tr>
			<tr>
				<td  colspan=2 align=center>
				<input type="submit" value="회원탈퇴"></td>
			</tr>
		</table>
	</form>
</body>
</html>