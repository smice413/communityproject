<!-- 아이디찾기폼 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <meta charset="UTF-8">


<title>아이디 찾기 폼</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<%-- <script src="<%=request.getContextPath() %>/member/member.js"></script> --%>
<script>
$(document).ready(function(){
	$("form").submit(function(){
		
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
<!-- header부분 -->
<jsp:include page="/header.jsp" />

<div class="container mt-3">
  <h2>아이디, 비밀번호 찾기 </h2>
  <br>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs">
   
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#menu1">ID찾기</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#menu2">비밀번호찾기</a>
    </li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content" style="visivility:hidden">
   
    <div id="menu1" class="container tab-pane fade"><br>
   		<form method="post" action="<%=request.getContextPath()%>/IdSearch.do">

		<table border=0 width=350 align=center class="table-hover" style="width: 364px;">
			<tr><th>이    름</th>
					<td class="golbeng">
		    <input type=text autofocus="autofocus" id="name" name="name" size=30
		           class="form-control" style=" margin-top:10px;" onClick="button()">
			</tr>
			<tr>
				<th>E-Mail</th>
					<td class="golbeng" style="display:flex;"><input type=text size=10 id="mailid" name="mailid" size=30
		           class="form-control" style="width:50%; margin-top:10px;">
		       <span style="position: relative; top: 15px;">&nbsp; @ &nbsp;</span>
		    <input type=text size=10 id="domain" name="domain"
		           class="form-control" style="width:50%; margin-top : 10px;"  >
			<tr>
				</td>
				<td colspan=2 align=center>
				<input type="submit" value="search" style="background-color:#06335a; margin-top: 10px; color: #f7fafc; border-radius: 10%;"></td>
				
			</tr>
		</table>
	</form>
    </div>
    
    <div id="menu2" class="container tab-pane fade"><br>
      <form method="post" action="<%=request.getContextPath()%>/PasswdSearch.do">
		<table border=0 width=350 align=center  class="table-hover" style="width: 364px;">
			<tr>
				<th>아이디</th>
				<td class="golbeng">
				<input type=text size=30 id="id" name="id"	autofocus="autofocus" class="form-control" style="margin-top: 10px"></td>
				
			</tr>
			
			<tr>
				<th>이  름</th>
				<td><input type=text size=30 id="name" name="name"	autofocus="autofocus" class="form-control" style="margin-top: 10px"></td>
			</tr>
			
			<tr>
				<th>E-Mail</th>
				<td class="golbeng" style="display:flex;"><input type=text size=10 id="mailid" name="mailid" size=30
		           class="form-control" style="width:50%; margin-top:10px;">
		       <span style="position: relative; top: 15px;">&nbsp; @ &nbsp;</span>
		    <input type=text size=10 id="domain" name="domain"
		           class="form-control" style="width:50%; margin-top : 10px;"  ></td></tr>
			<tr>
				<td colspan=2 align=center>
				<input type="submit" value="search" style="background-color:#06335a; margin-top: 10px; color: #f7fafc; border-radius: 10%;"></td>
			</tr>
		</table>
	</form>
	    
    </div>
    
  </div>

	
</body>
</html>