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
		
		if($("#id").val()==""){
			alert("아이디를 입력하세요");
			$("#id").focus();
			return false;
		}
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
.button{
	display: flex;
	margin-left: 110px;
	margin-top: 20px;
    width:400px;
}
.form-group{
 display: flex;
 margin: 0px;
}
</style>



</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp"/>


  

  <div class="container" style="width: 400px;height: 300px;">
	
  <br><br>
  <center><h2>Login</h2></center>
  <br>
  <form method="post" action="<%=request.getContextPath() %>/Login.do">
    <div class="form-group" style="margin-top: 18px; margin-right:20px; ">
      <label for="id" style="font-size:18px; margin-top:5px; margin-right:25px">아이디</label>
      <input type="text" id="id" placeholder="ID를 입력하세요" name="id" class="form-control" style="width:70%">
    </div>
    <div class="form-group" style="margin-right:20px;  ">
      <label for="passwd" style="font-size:18px; margin-top:5px; margin-right:6px">비밀번호</label>
      <input type="password" id="passwd" placeholder="비밀번호를 입력하세요" name="passwd" class="form-control" style="width:70%" >
    </div>
  
    <center>
    <div class="button" >
      <center><button type="submit" class="btn btn-primary" >로그인</button></center>
        &nbsp;&nbsp;
      <center><input type="button" class="btn btn-light" style="background-color:#fff; border-color:#E5E8F0; width:70px"
                      onClick="location.href='<%=request.getContextPath()%>/Main.do'" value="취소">
      </center>
    </div>
    </center>
  </form>
  
  <div class="menu" style="width:400px; margin-top:20px;  margin-left: 60px;">
  	  <a href="<%=request.getContextPath()%>/IdSearchForm.do" style="text-decoration:none; color:#000;">아이디/비밀번호 찾기</a> 
         &nbsp;
      <a href="<%=request.getContextPath()%>/MemberForm.do" style="text-decoration:none; color:#000;">회원가입</a>
  </div>
</div>
	
</body>
</html>