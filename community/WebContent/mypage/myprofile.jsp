<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 보기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<% 
 String id = (String)session.getAttribute("id");
%>

<!-- header부분 -->
<jsp:include page="/header.jsp"/>

<!-- 관리자페이지 네비게이션바 -->
<%
 if(id.equals("admin")){
%>
<jsp:include page="/mypage/admin.jsp"/>

<% }else{ %>
<!-- 마이페이지 네비게이션바 -->
<jsp:include page="/mypage/mypage.jsp"/>
<% } %>

<br>

<center>
<div class="container">
<h2>회원정보</h2>
<table class="table table-striped" style="width:90%">
  <tobody>
   <tr>
     <th align="center" width="160px"> ID</th>
     <td> &nbsp; ${member.id }</td>
     <td rowspan="5" align="center" >
        <br>
        <img src="./img/profile.png" width="150" height="150"> 
        <br><br><br>
        <a href="<%=request.getContextPath() %>/UpdateMember.do" style="text-decoration:none; color:black;">
                  <h5>프로필수정</h5>
        </a> 
        <br>
       <% if(!id.equals("admin")){ %> 

        <a href="<%=request.getContextPath() %>/MemberStopForm.do" style="text-decoration:none; color:black;">
                  <h5 style="color:red">회원 탈퇴</h5>
        </a> <br>    
     <%   } %>
    
     </td>
   </tr>
   <tr>
     <th align="center">이름</th>
     <td > &nbsp; ${member.name }</td>   
   </tr>
    <tr>
     <th align="center">핸드폰</th>
     <td> &nbsp; ${member.phone1}-${member.phone2}-${member.phone3}</td>   
   </tr>
    <tr >
     <th align="center">E-Mail</th>
     <td> &nbsp; ${member.mailid}@${member.domain}</td>   
   </tr>
    <tr>
     <th align="center">가입날짜</th>
     <td>
     &nbsp; <fmt:formatDate value="${member.regdate}" pattern="yyyy-MM-dd"/>
     </td>   
   </tr>
   </tobody>
</table>
</div>
</center>

<br><br><br><br>

</body>
</html>