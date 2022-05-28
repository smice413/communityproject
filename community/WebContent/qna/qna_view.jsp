<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<!-- 부트스트랩  환경설정  -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- jquery 환경설정  -->
<script src="http://code.jquery.com/jquery-latest.js"></script>

<title>상세페이지</title>

<script>

function check(n){
	var result = confirm("삭제하시겠습니까?")
	if(result){
		alert("삭제되었습니다.");
	}
}

<%
   String id = (String)session.getAttribute("id");
%>

</script>
</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp"/>
<br><br>

<div class="container">

<!-- 글제목  -->
<table border=0 class="table table-striped">
	<tr>
		<td style="font-size: 18px;">${qna.qna_subject}</td>
		<td style="text-align: right;">작성일: <fmt:formatDate value="${qna.qna_writedate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
</table>

<!-- 글 상세내용 -->
<table border=0 align="center" class="table">
	<tr>
		<td colspan=2>
			<c:if test="${qna.qna_file != null}">
			<img src="./qnaupload/${qna.qna_file}" width="50%"><br>
			</c:if>
			${content}
		</td>
	</tr>
</table>

<table border=0 align="center" class="table" style="margin-top: 30px;">
<!-- 첨부파일이 있을때만 첨부파일 출력 -->
	<c:if test="${qna.qna_file != null}">
	<tr>
		<td colspan=2>첨부 파일 : 
				<a href="./qna/qna_file_down.jsp?file_name=${qna.qna_file}">			
				${qna.qna_file}</a>	
		</td>
	</tr>
	</c:if>
</table>

	
<!-- 수정/삭제/목록 버튼 모음 -->
<table border=0 align="center" class="table">
	<tr>
		<c:if test="${qna.qna_lev > 0 }">
		<td colspan=2 align=center>
		<c:choose>
			<c:when test="${id != 'admin' }">
			<input type = "button" value="재문의" class="btn btn-light" style="border-color: #E5E8F0;"
			onClick="location.href='./QnaReplyPre.do?qna_num=${qna.qna_num}&page=${page}'">
				&nbsp;
			</c:when>
			<c:otherwise>
				<input type = "button" value="답글" class="btn btn-light" style="border-color: #E5E8F0;"
				onClick="location.href='./QnaReplyPre.do?qna_num=${qna.qna_num}&page=${page}'">
					&nbsp;
			</c:otherwise>
		</c:choose>
		
			<c:if test="${id == qna.id }">
			<input type = "button" value="수정" class="btn btn-light" style="border-color: #E5E8F0;"
			onClick="location.href='<%=request.getContextPath()%>/QnaUpdatePre.do?qna_num=${qna.qna_num}&page=${page}'">
				&nbsp;
			</c:if>
			
			<c:if test="${id == qna.id || id == 'admin' }">
			<input type = "button" value="삭제" class="btn btn-light" style="border-color: #E5E8F0;"
			onClick="location.href='<%=request.getContextPath()%>/QnaDelete.do?qna_num=${qna.qna_num}&page=${page}'">
				&nbsp;
			</c:if>
				
			<c:choose>
			<c:when test="${id == 'admin'}">
				<input type = "button" value="목록" class="btn btn-primary"
				onClick="location.href='<%=request.getContextPath()%>/QnaList.do?page=${page}'">
					&nbsp;
			</c:when>
			<c:when test="${id != 'admin'}">
				<input type = "button" value="목록" class="btn btn-primary"
				onClick="location.href='<%=request.getContextPath()%>/MyQnaListPre.do?page=${page}'">
					&nbsp;
			</c:when>
			</c:choose>
		</td>
	</c:if>
	
	<c:if test="${qna.qna_lev == 0 }">
	<td colspan=2 align=center>
		<c:if test="${id == 'admin' }">
			<input type = "button" value="답글" class="btn btn-light" style="border-color: #E5E8F0;"
			onClick="location.href='./QnaReplyPre.do?qna_num=${qna.qna_num}&page=${page}'">
				&nbsp;
		</c:if>
		
			<c:if test="${id == qna.id }">
			<input type = "button" value="수정" class="btn btn-light" style="border-color: #E5E8F0;"
			onClick="location.href='<%=request.getContextPath()%>/QnaUpdatePre.do?qna_num=${qna.qna_num}&page=${page}'">
				&nbsp;
			</c:if>
	
			<c:if test="${id == qna.id || id == 'admin' }">			
			<input type = "button" value="삭제" class="btn btn-light" style="border-color: #E5E8F0;"
			onClick= "check(location.href='<%=request.getContextPath()%>/QnaDelete.do?qna_num=${qna.qna_num}&page=${page}')">
				&nbsp;
			</c:if>
			
			<c:choose>
			<c:when test="${id == 'admin'}">
				<input type = "button" value="목록" class="btn btn-primary"
				onClick="location.href='<%=request.getContextPath()%>/QnaList.do?page=${page}'">
					&nbsp;
			</c:when>
			<c:when test="${id != 'admin'}">
				<input type = "button" value="목록" class="btn btn-primary"
				onClick="location.href='<%=request.getContextPath()%>/MyQnaListPre.do?page=${page}'">
					&nbsp;
			</c:when>
			</c:choose>
		</td>
	</c:if>	
	</tr>
</table>

</div>
</body>
</html> 