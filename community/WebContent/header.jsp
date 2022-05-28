<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
 * { 
            margin:0; 
            padding: 0;
        }
</style>
<body>

<div class="contain" style="background-color: #8fb0c6;">
<%-- 	<span class="nav-link" href="<%=request.getContextPath() %>/homeheader.jsp?id=${param.id}" style="font-size: 50px; color: #fff;"> --%>
	<span class="nav-link" style="font-size: 50px; color: #fff;">
		<img src="<%=request.getContextPath() %>/img/home.png"  style="width: 80px; position: relative; top: 7px; left:10px;" onClick="location.href='<%=request.getContextPath() %>/Main.do' ">
	</span>
  
  
   <ul class="nav justify-content-end" style="position: relative;">
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/QnaAddPre.do" style="color: #fff;">신고/문의</a>
    </li>
    <c:if test="${sessionScope.id != 'admin' and sessionScope.id  != null}">
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/BLRequestPre.do" style="color: #fff;">게시판 개설 신청</a>
    </li>
   </c:if>
    <c:if test="${sessionScope.id != 'admin' and sessionScope.id  != null}">
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/MypagePre.do" style="color: #fff;">마이페이지</a>
    </li>
    </c:if>
    <c:if test="${sessionScope.id == 'admin'}">
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/MypagePre.do?id=${param.id}" style="color: #fff;">관리자 페이지</a>
    </li>
    </c:if>
    <c:if test="${sessionScope.id == null}">
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/MemberForm.do?" style="color: #fff;">회원가입</a>
    </li>
    </c:if>
    
    <!-- 세션이 있는 경우 -->
	<!-- 로그아웃 버튼 -->
	 <c:if test="${sessionScope.id != null}">
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/Logout.do" style="color: #fff;">로그아웃</a>
    </li>
    </c:if>
    
    <!-- 세션이 없는 경우 -->
					<!-- 로그인 버튼 -->	
    <c:if test="${sessionScope.id == null}">
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/LoginForm.do" style="color: #fff;">로그인</a>
    </li>
    </c:if>
  </ul>
</div>


</body>
</html>