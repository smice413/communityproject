<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 수정</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<%=request.getContextPath() %>/qna/qna.js"></script>

</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp" />
<br><br>

<form action="<%=request.getContextPath() %>/QnaUpdate.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="qna_num" value="${qna.qna_num}" >
<input type="hidden" name="page" value="${page}" >

<div class="container">
<table align="center" class="table table-striped">
	<tr align="center" valign="middle">
		<td colspan="5" style="font-size: 20px">문의글 수정</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제목</div>
		</td>
		<td>
			<input name="qna_subject" id="qna_subject" type="text" size="50" maxlength="100" class="form-control" value="${qna.qna_subject }"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="qna_content" id="qna_content" cols="67" rows="15" class="form-control">${qna.qna_content }</textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">파일 첨부</div>
		</td>
		<td>
		<!-- 수정전 첨부파일명을 hidden타입(old_qna_file)으로 딸려보냄 -->
		<c:if test="${qna.qna_file != null}">
			${qna.qna_file}<br>
			<input type=hidden name="old_qna_file" value="${qna.qna_file }">
			<div style="font-size: 13px; color: red;">첨부파일은 5MB 이하 1개만 등록 가능합니다!</div>
			<div style="font-size: 13px; color: red;">새로운 파일 저장 시 기존 파일은 삭제 됩니다.<div>
		</c:if>
			<input name="qna_file" type="file" class="form-control"/>
		</td>
	</tr>
	<tr align="center" valign="middle">
		<td colspan="5">			
			<input type=submit value="수정" class="btn btn-primary">
				&nbsp;
			<input type=reset value="취소" class="btn btn-light" style="border-color: #E5E8F0;" 
			onClick="location.href='<%=request.getContextPath()%>/QnaDetail.do?qna_num=${qna.qna_num }&page=${page }'">
		</td>
	</tr>
</table>
</div>
</form>


</body>
</html>