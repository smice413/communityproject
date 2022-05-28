<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  
<script src="http://code.jquery.com/jquery-latest.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp" />
<center>
<table border=0><tr><td style="font-size:32px; padding:2px; height:80px">${fn:toUpperCase(bl_category)} CATEGORY</td></tr></table>

<div class="container">
  	<div class="row" style="margin:5px; padding:5px; background-color:#eeeeee;">
<c:forEach var="bl" items="${bllist}">

    	<div class="col" id="boardlist${bl.bl_code}" style="margin:5px 5px 5px 5px; padding:5px; background-color:#ffffff;">
			<table border=0 width=290>
				<tr>
					<td colspan=2>
					<c:if test="${bl.bl_private == 'Y'}">
						<a href="./board/board_password.jsp?bl_code=${bl.bl_code}&old_passwd=${bl.bl_passwd}"></c:if>
					<c:if test="${bl.bl_private != 'Y'}">
						<a href="./BoardList.do?bl_code=${bl.bl_code}"></c:if>
						<font size=3>${bl.bl_name}</font></a></td>
				</tr>
				<tr>
					<td colspan=2 height="40px" valign="top"><font size=2>${bl.bl_exp}<br></font></td>
				</tr>
				<tr>
					<td><font size=1 color="darkblue">개설일
					 <fmt:formatDate value="${bl.bl_regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></font></td>
					<td align=right>
						<%-- <c:set var="check" value="Y" /> --%>
						<c:if test="${bl.bl_private == 'Y'}">
						<img src="./img/free-icon-padlock-345535.png" width=18px>
						</c:if>
					</td>
				</tr>
			</table>
		</div>
</c:forEach>

	</div>
</div>
</center>
</body>
</html>