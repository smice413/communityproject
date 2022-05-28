<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<meta charset="UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="./css/style.css"  type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
</head>
<body>
<div class="contain" style="background-color: #E5E8F0; ">
	
   <ul class="nav justify-content-center" >
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/MemberList.do" style="color: #06335a;"> 회원 목록 </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/BLList.do" style="color: #06335a;">게시판 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/BLListN.do" style="color: #06335a;">게시판 승인 요청 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/QnaList.do" style="color: #06335a;">신고/문의 목록</a>
    </li>
    </ul>
</div>

   

		
		<%-- <div class="main">
			<ul class="category">
				<li id="mainli"><a id="maina" href="<%=request.getContextPath()%>/MyProfile.do"> 회원정보 보기</a></li>
				<li id="mainli"><a id="maina" href="<%=request.getContextPath()%>/MyWritingListPre.do">작성 글 보기</a></li>
				<li id="mainli"><a id="maina" href="<%=request.getContextPath()%>/MyReplyListPre.do">작성 댓글 보기</a></li>
				<li id="mainli"><a id="maina" href="<%=request.getContextPath()%>/MyBoardListPre.do">나의 게시판</a></li>
				<li id="mainli"><a id="maina" href="<%=request.getContextPath()%>/MyQnaListPre.do"> 문의글 보기</a></li>
			</ul>
		</div> --%>








</body>
</html>