<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
	<title>문의글 답변 페이지</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="<%=request.getContextPath() %>/qna/qna.js"></script>
</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp" />
<br><br>

<div class="container">
<form action="<%=request.getContextPath() %>/QnaReply.do" method="post">
<input type="hidden" name="qna_num" value="${qna.qna_num}">
<input type="hidden" name="qna_ref" value="${qna.qna_ref}">
<input type="hidden" name="qna_lev" value="${qna.qna_lev}">
<input type="hidden" name="qna_seq" value="${qna.qna_seq}">
<input type="hidden" name="page" value="${page}">

<table  align=center class="table table-striped">
	<tr align="center" valign="middle">
		<td colspan="5" style="font-size: 20px">신고/문의 답글</td>
	</tr>	
	<tr>
		<td style="font-family:돋음; font-size:18" height="16">
			<div align="center">제 목</div>
		</td>
		<td>
			<input name="qna_subject" id="qna_subject" type="text" size="50" maxlength="100" 
				class="form-control" value=""/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:18">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="qna_content" id="qna_content" cols="67" rows="15" class="form-control"></textarea>
		</td>
	</tr>
	<tr align="center" valign="middle">
		<td colspan="5">			
			<input type=submit value="답글 등록" class="btn btn-primary">
			<input type=button value="취소" class="btn btn-light" style="border-color: #E5E8F0;" onClick="location.href='<%=request.getContextPath()%>/QnaDetail.do?qna_num=${qna.qna_num }&page=${page }'">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>