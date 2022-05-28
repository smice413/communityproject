<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%	request.setCharacterEncoding("utf-8"); %>
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/style.css"  type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<style>

	.box{
		display:flex;
		justify-content: center;
		width: 95%;
		margin-left: 32px;
	
	}
	.box1{
		width: 20%;
	
	}
	.box2{
		width: 20%;
	}
	
	.container{
		display: inline;
		justify-content: center;
		width:870px;
		background-color: #eeeeee;
		float: left;
		
		}
	.container1{
		
		width: 400px;
		margin: 10px 10px 10px 25px;
		padding: 10px;
		background-color: white;
		border:10px;
		float: left;
		}
	
	@media screen and (min-width: 1035px){
	 body{
	 	width: auto;
	 	margin-top:40px;
	 	float: none;
	 }
	}

</style>
</head>
<body>

<!-- header부분 -->
<jsp:include page="/header.jsp" />
<hr>

<center>
<h1>
	<c:set var="bl_category1" value="movie" />
	<c:set var="bl_category2" value="cook" />
	<c:set var="bl_category3" value="shopping" />
	<c:set var="bl_category4" value="review" />
<a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category1}">영화</a> 
<a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category2}">요리</a> 
<a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category3}">쇼핑</a> 
<a href= "<%=request.getContextPath() %>/BoardListPre.do?bl_category=${bl_category4}">후기</a> 

</h1>
<div id="table">
<table class="table">
	<tr>
		<td>오늘의 인기글</td>
	</tr>
	<tr>

<c:forEach var="pbl" items="${pbllist}">
	<div id="boardlist" class="container1">
			<table border=1 width=380>
				<tr>
					<td colspan=2><a href="./BoardList.do?bl_code=${pbl.bl_code}"><font size=3>${pbl.subject}</font><br></td>
				</tr>
				<tr>
					<td colspan=2><font size=2>${pbl.content}<br></font></td>
				</tr>
				<tr>
					<td><font size=1>날짜 &{pbl.writedate}</font></td>
					<td><font size=1>조회수 &{pbl.readcount}</font></td>
				</tr>
			</table>
	</div>
</c:forEach>
	</tr>
</table>
</div>
</center>
</body>
</html>