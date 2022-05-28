<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- header부분 -->
<jsp:include page="/header.jsp" />

<!DOCTYPE html>
<html>
<head>
<title>회원 가입</title>

<!-- 부트스트랩  환경설정  -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function openDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				
				document.getElementById('post').value = data.zonecode;
				document.getElementById('address').value = data.address;
				
			}
		}).open();
	}
	
	function button(){
		$("#submit").attr("disabled", true);
		alert("아이디 중복검사를 해주세요");
		
	}
	
</script>


<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script> 

<!-- 외부 자바스크립트 파일 불러오기 -->
<script src="<%=request.getContextPath() %>/member/member.js"></script>

<style>
div {
  font-style: normal;
}
.golbeng{
	display: flex;
	margin= 0;
}
</style>


</head>
<body>



<br>

<form method="post" action="<%=request.getContextPath() %>/MemberInsert.do"> 

<center>
<div class="container" >
    <h1>회원 가입</h1>
    <br>
<table class="table table-striped" >
	<tr><th>ID</th>
		<td class="golbeng">
		    <input type=text autofocus="autofocus" id="id" name="id"
		           class="form-control" style="width:30%" onClick="button()"> 
		      &nbsp;       
			<input type=button class="btn btn-danger" value="ID중복검사" id="idcheck">
			<div id="myid"></div>
		</td>
	</tr>
	<tr><th>비밀번호</th>
		<td><input type=password id="passwd" name="passwd"
		           class="form-control" style="width:30%" ></td>
	</tr>
	<tr><th>성명</th>
		<td><input type=text id="name" name="name" class="form-control" style="width:30%"></td>
	</tr>

	<tr><th>E-Mail</th>
		<td class="golbeng"><input type=text size=10 id="mailid" name="mailid"
		           class="form-control" style="width:30%">
		       &nbsp; @ &nbsp;
		    <input type=text size=10 id="domain" name="domain"
		           class="form-control" style="width:30%" >
		       &nbsp;    
		    <select id="email" class="form-control" style="width:30%">
		    	<option value="">직접입력</option>
		    	<option value="naver.com">네이버</option>
		    	<option value="daum.net">다음</option>
		    	<option value="nate.com">네이트</option>
		    	<option value="gmail.com">gmail</option>
		    </select>		    
		 </td>
	</tr>
		<tr><th>핸드폰</th>
		<td class="golbeng">
		   <select id="phone1" name="phone1" class="form-control" style="width:20%">
				<option value="">번호선택</option>
				<option value="010">010</option>
			</select>
			  &nbsp;-&nbsp;
			<input type=text size=4 id="phone2" name="phone2" maxlength=4
			       class="form-control" style="width:20%">
			  &nbsp;-&nbsp;
			<input type=text size=4 id="phone3" name="phone3" maxlength=4
			       class="form-control" style="width:20%">
		</td>
	</tr>
</div>	
	<tr><td colspan=2 align=center>
			<input type=submit class="btn btn-primary" id="submit" disabled="disabled" value="회원가입">
			<input type=reset class="btn btn-light" value="취소" style ="border-color:E5E8F0;"
			       onClick="location.href='<%=request.getContextPath()%>/Main.do'">
		</td>
	</tr>		
</table>
</center>

</form>



</body>
</html>