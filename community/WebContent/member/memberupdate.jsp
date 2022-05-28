
<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정 폼</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/style.css"  type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function openDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 우편번호와 주소 정보를 해당 필드에 넣고, 커서를 상세주소 필드로 이동한다.
//				document.getElementById('join_zip1').value = data.postcode1;
//				document.getElementById('join_zip2').value = data.postcode2;
				document.getElementById('post').value = data.zonecode;
				document.getElementById('address').value = data.address;
				
			}
		}).open();
	}
</script>


<!-- 외부 자바스크립트 파일 불러오기 -->
<script src="./member/member.js"></script>

<script>
$(document).ready(function(){
	
	<c:forEach var="s" items="${h}">
		$("input:checkbox[name='hobby'][value='${s}']").attr("checked", true);
	</c:forEach>
	
});
</script>


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


<!-- header -->
<jsp:include page="/mypage/mypage.jsp"></jsp:include>


<form method="post" action="<%=request.getContextPath() %>/Update.do"> 
<input type="hidden" name="id" value="${member.id}">
<input type="hidden" name="regdate" value="${member.regdate}">

<center>
<div class="container" >
<table class="table table-striped"  >
  <thead> 
	<tr style="border:none">
	<td colspan="2" align="center" ><h1>회원정보 수정</h1></td>
	</tr>
  </thead>
	<tr><th>ID</th>
		<td>	
			${member.id}	
		</td>
	</tr>
	<tr><th>비밀번호</th>
		<td><input type=password id="passwd" name="passwd" class="form-control" style="width:30%" ></td>
	</tr>
	<tr><th>성명</th>
		<td><input type=text id="name" name="name" class="form-control" style="width:30%"
		           value="${member.name}"></td>
	</tr>
	
	<tr><th>E-Mail</th>
		<td class="golbeng">	
		<input type=text size=10 id="mailid" class="form-control" style="width:40%" 
					name="mailid" value="${member.mailid}">&nbsp;
					@&nbsp;
		<input type=text size=10 id="domain"  class="form-control" style="width:40%"
		    		name="domain" value="${member.domain}">
		    		&nbsp;
		<select id="email" class="form-control" style="width:45%" >
		    	<option value="">직접입력</option>
		    	<option value="naver.com">네이버</option>
		    	<option value="daum.net">다음</option>
		    	<option value="nate.com">네이트</option>
		    	<option value="gmail.com">gmail</option>
		</select>		    
		</td>
    
	</tr>
	
	<tr><th>핸드폰</th>
		<td class="golbeng"><input type ="text"size=3 id="phone1" name="phone1" value="010" class="form-control" style="width:20%">		
			&nbsp;-&nbsp;
			<input type=text size=4 id="phone2" name="phone2" class="form-control" style="width:20%"  
					maxlength=4 value="${member.phone2}">&nbsp;-&nbsp;
			<input type=text size=4 id="phone3" name="phone3" class="form-control" style="width:20%"
					maxlength=4 value="${member.phone3}">
		</td>
	</tr>
<div class="container">	
	<tr>
	<td colspan="2" align="center">
	<input type="submit" class="btn btn-primary" value="회원정보수정">
	<input type="button" class="btn btn-default" value="취소" onClick="history.go(-1)">
	</td>
	</tr>
</div>	
</table>
</div>
</center>
</form>


</body>
</html>