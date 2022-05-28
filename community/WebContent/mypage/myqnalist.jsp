<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 목록</title>
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
<h2>신고/문의</h2>
<table class="table table-striped"  >
    <thead> 
    <tr>
	  <td style="border:none"  colspan="2" align="left">
	    Total : ${myqnacount}
	  </td>
	  <td style="border:none" align="right" align="right">
	     Page : ${page} / ${pageCount}
	  </td>
	</tr>
	<tr>
		<td width="100px" align="center"><b>번호</b></td>
		<td width="" align="center"><b>제목</b></td>
		<td width="150px" align="center"><b>작성일</b></td>
	</tr>
	</thead>
	<tobody>
	<c:set var="num" value="${myqnacount - (page - 1) * 10 }"/>
	<c:forEach var="myqna" items="${myqnalist }">
	<tr>
		<td align="center">${num }
			<c:set var="num" value="${num - 1 }"/>
		</td>
		<td >

		<!-- 답글 제목 앞 여백 처리 -->
		<c:if test="${myqna.qna_lev > 0 }">
            <img src="./img/reply2.png" width=20 height=20>
		</c:if>	
		   <a href="./QnaDetail.do?qna_num=${myqna.qna_num }&page=${page}" style="text-decoration:none;color:#000;">
			   ${myqna.qna_subject }
		   </a> 	
		</td>
		<td align="center">
			<fmt:formatDate value="${myqna.qna_writedate }" pattern="yyyy-MM-dd"/>
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
	  <a class="page-link" href="./MyQnaListPre.do?page=1" style="text-decoration:none; color:#000;"> 
	    << 
	  </a>
	</li>
	<!-- 이전 블럭 -->
	<c:if test="${startPage > 10 }">
	<li class="page-item">
 	   <a class="page-link" style="color:#000;" href="./MyQnaListPre.do?page=${startPage - 1 }">
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
	   <a class="page-link" style="color:#000;" href="./MyQnaListPre.do?page=${i}">
		 ${i}
	   </a>
	</li> 
	</c:if>
    </c:forEach>
	<!-- 다음 블럭으로 이동 -->
	<c:if test="${endPage < pageCount }">
    <li class="page-item">
	  <a class="page-link" style="color:#000;" href="./MyQnaListPre.do?page=${startPage + 10 }">
	       다음 
      </a>
 	</li>	
    </c:if>
	<!-- 마지막 페이지로 이동 -->
    <li class="page-item">
      <a class="page-link" style="color:#000;" href="./MyQnaListPre.do?page=${pageCount }" style="text-decoration:none; color:#000;">
        >> 
      </a>
    </li>  
  </ul>
  </div>
  </div>
</center>


</body>
</html>