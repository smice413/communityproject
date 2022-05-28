<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("utf-8");
%>
<title>Insert title here</title>
<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- jQuery -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<!-- Google Font -->
<!-- <style>
@import url('https://fonts.googleapis.com/css2?family=Hi+Melody&family=Nanum+Pen+Script&family=Single+Day&display=swap');
</style> -->
</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp" />

<!-- 카테고리 목록 -->
<center>
<div class="container" style="margin:0px; padding:0px;  background-color:#ffffff;">
   <c:set var="bl_category1" value="movie" />
   <c:set var="bl_category2" value="cook" />
   <c:set var="bl_category3" value="shopping" />
   <c:set var="bl_category4" value="review" />
   <c:set var="bl_category5" value="etc" />
   <table border=0 align=center width=100% height=120px style="text-decoration:none; font-size:16px;">
      <tr align=center>
         <td>
            <a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category1}">
            <img src="./img/movie2.JPG" class="rounded-circle" width="70" height="70" alt="게시판 목록"><br>MOVIE</a> 
         </td>
         <td>
            <a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category2}">
            <img src="./img/cook.JPG" class="rounded-circle"  width="70" height="70" alt="게시판 목록"><br>COOK</a> 
         </td>
         <td>
            <a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category3}">
            <img src="./img/shopping.PNG" class="rounded-circle" width="70" height="70" alt="게시판 목록"><br>SHOPPING</a>  
         </td>
         <td>
            <a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category4}">
            <img src="./img/review.PNG" class="rounded-circle" width="70" height="70" alt="게시판 목록"><br>REVIEW</a>  
         </td>
         <td>
            <a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category5}">
            <img src="./img/etc.png" class="rounded-circle" width="70" height="70" alt="게시판 목록"><br>ETC</a>  
         </td>
      </tr>
	</table>

<!-- 오늘의 인기글 (title) -->	
	<table border=0 width=100%>
      <tr>
      	<td colspan=5 style="font-size: 20px;">&nbsp;
      		<img src="./img/free-icon-trending-topic-3391039.png" width=20px> 오늘의 인기글</td>
      </tr>
	</table>
<!-- 오늘의 인기글 (본문) -->	
  	<div class="row" style="margin:0px; padding:0px;">
	<c:set var="i" value="0"/>
	  	<c:forEach var="pbl" items="${pbllist}">
	    <div class="col" style="margin:0px; padding:5px;  background-color:#ffffff;">
			<table border=0 width=100%>

				<tr bgcolor="lavender">
					<td colspan=2 height=27px style="font-size: 14px;">
						<a href="<%=request.getContextPath() %>/BoardDetail.do?num=${pbl.num}&page=1&bl_code=${pbl.bl_code}" >
						<c:if test="${pbl.head=='후기'}">
							${pbl.subject} <font color="red">
							<c:if test="${pbl.star=='0'}">☆☆☆☆☆</c:if>
							<c:if test="${pbl.star=='1'}">★☆☆☆☆</c:if>
							<c:if test="${pbl.star=='2'}">★★☆☆☆</c:if>
							<c:if test="${pbl.star=='3'}">★★★☆☆</c:if>
							<c:if test="${pbl.star=='4'}">★★★★☆</c:if>
							<c:if test="${pbl.star=='5'}">★★★★★</c:if></font>
						</c:if>	
						<c:if test="${pbl.head!='후기'}">
							${pbl.subject}<br>
						</c:if></a>
					</td>
				</tr>
				<tr>
					<td height=75px style="font-size:13px">
						<c:set var="str" value="${pbl.content}"/>
						${fn:substring(str,0,42)}...<br>
					</td>
					<td width=80px>
						<a href="<%=request.getContextPath() %>/BoardDetail.do?num=${pbl.num}&page=1&bl_code=${pbl.bl_code}" style="text-decoration:none">
						<img src="<%=request.getContextPath() %>/boardupload/${pbl.board_file}" width="80" height="80"></a>
					</td>
				</tr>
				<tr bgcolor="#ccdfeb">
					<td style="font-size:12px; padding:0px"><fmt:formatDate value="${pbl.writedate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="right" style="font-size:12px; padding:0px">조회수 ${pbl.readcount}&nbsp;&nbsp;</td>
				</tr>
			</table>
		</div>
	<c:set var="i" value="${i+1}"/>
	<c:if test="${i%2 == 0}">
	</div>
  	<div class="row" style="margin:0px; padding:0px;">
  	</c:if>
	</c:forEach>
	</div>

<!-- 전체 게시판 한꺼번에 보기 -->

</div>
</center>
</body>
</html>