<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성 댓글 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- header부분 -->
<jsp:include page="/header.jsp"/>


<!-- 마이페이지 네비게이션바 -->
<jsp:include page="/mypage/mypage.jsp"/>

<br>

<center>
<div class="container">
<h2>작성 댓글</h2>
<table class="table table-striped" >
    <thead> 
    <tr >
	  <td colspan="2" style="border:none" align="left">
	    Total : ${myreplycount}
	  </td>
	  <td style="border:none" align="right" align="right">
	     Page : ${page} / ${pageCount}
	  </td>
	</tr>
	<tr>
		<td width="80px" align="center"><b>번호</b></td>
		<td width="" align="center"><b>댓글 </b></td>
		<td width="150px" align="center"><b>작성일</b></td>
		
	</tr>
	</thead>
	<tobody>
	<c:set var="num" value="${myreplycount - (page - 1) * 10 }"/>
	<c:forEach var="myreply" items="${myreplylist }" varStatus="status">

	<tr>
		<td align="center">${num }
			<c:set var="num" value="${num - 1 }"/>
		</td>
		<td >
		  <a href="./BoardDetail.do?num=${myreply.num }&page=${page}" style="text-decoration:none; color:#000;">
		  &nbsp;${myreply.re_content }
		  </a> 
		  <br>
		  &nbsp; <label style="font-size:13px">[${myreply.head}] ${myreply.subject}</label>
		</td>
		<td align="center">
		  <fmt:formatDate value="${myreply.re_writedate }" pattern="yyyy-MM-dd"/>
	    </td>
	</tr>
	</c:forEach>
	</tobody>
	
</table>
</div>
</center>


<!-- 페이징 처리 -->
<center>
<div style="float:left; width:100%; text-align:center;" >
<div style="display:inline-block;">
 <ul class="pagination pagination-sm" >

   <!-- 1 페이지 -->
   <li class="page-item">
     <a class="page-link" href="./MyReplyListPre.do?page=1" style="text-decoration:none; color:#000;"> 
       << 
     </a>
   </li>

   <!-- 이전 블럭 -->
   <c:if test="${startPage > 10 }">
   <li class="page-item">
     <a class="page-link" style="color:#000;" href="./MyReplyListPre.do?page=${startPage - 1 }">
             이전
     </a>
   </li> 
   </c:if>

   <!-- 각 블럭에 페이지 10개씩 출력 -->
   <c:forEach var="i" begin="${startPage }" end="${endPage }">
   <c:if test="${i == page }">  <!-- 현재 페이지 -->
   <li class="page-item">
	 <a class="page-link" style="color:#000;">
	   ${i}
	 </a>
   </li>  
   </c:if>
   <c:if test="${i != page }">
	 <li class="page-item">
	   <a class="page-link" style="color:#000;" href="./MyReplyListPre.do?page=${i}">
		 ${i}
	   </a>
	 </li> 
   </c:if>
   </c:forEach>
   <!-- 다음 블럭으로 이동 -->
   <c:if test="${endPage < pageCount }">
   <li class="page-item" >
	<a class="page-link" style="color:#000;" href="./MyReplyListPre.do?page=${startPage + 10 }">
	   다음 
	</a>
   </li>	
   </c:if>
   <!-- 마지막 페이지로 이동 -->
   <li class="page-item">
    <a class="page-link"  href="./MyReplyListPre.do?page=${pageCount }" style="text-decoration:none; color:#000;">
      >> 
    </a>
   </li>  
  </ul>
</center>
</div>
</div>

</body>
</html>