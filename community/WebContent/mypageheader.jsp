<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/style.css" rel="stylesheet">
<title>MypageHeader</title>

<style>
.table-table{
	width: 50%;
	margin-left: auto;
    margin-right: auto;
 
}





.pagination{
	justify-content: center;
}

/* mypageheader부분 */

.headerbody{
	font-family: Arial, Helvetica, sans-serif;
	list-style: none;
	text-decoration: none;
	border-collapse: collapse;
	margin: 0px;
	padding: 0px;
	color: #fff;
	
}

.intro_bg {
	/* background-image: url("green.jpg");*/
	background-color: #E5E8F0;
	width: 100%;
	height: 300px;
	text-align: center;
	
}

.intro_bg h1 {
	color: rgba(6, 51, 90);
}

.header {
	display: flex;
	justify-content: center;
	witdh: 1280px;
	height: 86px;
	margin: auto;
	background-color: rgba(6, 51, 90);
}

.logo {
	witdh: 300px;
	height: 40px;
	/* line-height: 80px; */
	/* align-items: center; */
	position : relative;
	top : 25px;
	left : 86px;
}

.logo a {
	color: #fff;
	font-size: 30px;
	text-decoration: none;
}

.nav {
	display: flex;
	justify-content: flex-end;
	/* line-height: 80px; */
	align-items: center;
	width: 980px;
	list-style:none;
}

.nav> #navli {
	margin-left: 10px;
	
}

 .nav > #navli > #nava:link, #nava:visited {
	
	color: white;
	font-size: 15px;
	padding: 10px 10px;
	text-align: center;
	text-decoration: none;
	
}

.nav > #navli > #nava:hover, #nava:active {
	color: #2BA5BA;
	font-size: 15px;
	border: 2px solid #2BA5BA;
	border-radius: 5px;
}
.main{
	display: flex;
	justify-content: center;
	
}
.main > .category {
	display: flex;
	list-style:none;
}
.main > .category {
	list-style:none;
}
.main > .category> #mainli > #maina {
	color: black;
	width: 80px;
	height: 80px;
	margin: 5px;

}
</style>

</head>
<body class="headerbody">
	<div class="wrap">
		<div class="intro_bg">
			<div class="header">
				<div class="logo">
					<a href="./mypageheader.jsp">LOGO</a>
				</div>
				<ul class="nav">
					<li id="navli"><a id="nava" href="<%=request.getContextPath()%>/QnaAddPre.do">문의 게시판</a></li>
					<li id="navli"><a id="nava" href="<%=request.getContextPath()%>/BLRequestForm.do">게시판 개설 신청</a></li>
					<li id="navli"><a id="nava" href="<%=request.getContextPath()%>/MypagePre.do">마이페이지</a></li>
					
					<!-- 세션이 있는 경우 -->
					<!-- 로그아웃 버튼 -->
                    <c:if test="${sessionScope.id != null}">  
					<li id="navli"><a id="nava" href="<%=request.getContextPath()%>/Logout.do">로그아웃</a></li>
					</c:if>
					<!-- 세션이 없는 경우 -->
					<!-- 로그인 버튼 -->					
					<c:if test="${sessionScope.id == null }">
					<li id="navli"><a id="nava" href="<%=request.getContextPath()%>/LoginForm.do">로그인</a></li>
					</c:if>
				</ul>
		</div>
	</div>
</div>
</body>
</html>