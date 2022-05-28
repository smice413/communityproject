<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <title>로그인 폼</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
$(document).ready(function(){
	$("form").submit(function(){
		
		if($("#passwd").val()==""){
			alert("패스워드를 입력하세요");
			$("#passwd").focus();
			return false;
		}		
	});	
});
</script>

<!-- 외부 자바스크립트 파일 불러오기 -->
<script src="<%=request.getContextPath() %>/login.js"></script>
<style>
.round3 {
  border: 2px solid red;
  border-radius: 12px;
  padding: 20px;
}
</style>
</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp"/>
<br><br>
<center>
<form method="post" action="<%=request.getContextPath() %>/BoardList.do">
	<input type=hidden  name="bl_code" value="${param.bl_code}">
	<input type=hidden  name="old_passwd" value="${param.old_passwd}">
	<!-- 테두리 회색부분 -->
	<table>
	<tr>
	<td style="background-color:#eeeeee; padding:10px;">

	<!-- 테이블 마진부분 -->
		<table border=0>
		<tr>
		<td style="background-color:#ffffff; padding:15px;">
	<!-- 본문 테이블  -->
			<table border=0 width=280 style="margin:0px; background-color:#ffffff;" >
				<tr height=50px align=center style="font-size:20px;">
					<td colspan=2>비공개 게시판</td>
				</tr>
				<tr align=center style="font-size:15px; height:30px; color:#777777">
					<td colspan=2>입장하시려면 비밀번호를 입력해주세요</td>
				</tr>
				<tr>
					<td style="font-size:15px; width:85px; valign:middle; align:center; padding:10px 0px 0px 7px">
						<label for="passwd">비밀번호</label></td>
				      	<td><input type="password" id="passwd" placeholder="비밀번호를 입력하세요" name="new_passwd" class="form-control" >
					</td>
				</tr>
				<tr height=60px align=center>
					<td colspan=2><input type="submit" class="btn btn-primary" value="확인"
						style=" width:70px; height:30px; font-size:15px; padding:3px">
						<input type="submit" class="btn btn-light" value="취소" onclick="history.go(-1); return false;"
						 style="border-color:lightgray; width:70px; height:30px; font-size:15px; padding:3px"></td>
				</tr>
			</table>
		</td>
		</tr>
		</table>

	</td>
	</tr>
	</table>
</form>
</center>
	
</body>
</html>