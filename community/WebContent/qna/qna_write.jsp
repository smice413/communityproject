<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 작성</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<%=request.getContextPath() %>/qna/qna.js"></script>

</head>

<body>
<!-- header -->
<jsp:include page="/header.jsp"/>
<br><br>

<!-- 본문 -->
<div class="container">
<form action="<%=request.getContextPath() %>/QnaAdd.do" method="post" enctype="multipart/form-data"">
<input type="hidden" name="id" value=${id }>
<table align="center" cellpadding="0" cellspacing="0" class="table table-striped">
	<tr align="center" valign="middle">
		<td colspan="5" style="font-size: 20px;">신고/문의글 작성</td>
	</tr>	
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제목</div>
		</td>
		<td>
			<input name="qna_subject" id="qna_subject" type="text" size="50" maxlength="100" class="form-control"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="qna_content" id="qna_content" cols="67" rows="15" class="form-control"></textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">파일 첨부</div>
		</td>
		<td>
			<input name="qna_file" type="file"/>
			<div style="font-size: 13px; color: red;">첨부파일은 5MB 이하 1개만 등록 가능합니다! </div>
		</td>
	</tr>
	<tr align="center" valign="middle">
		<td colspan="5">			
			<input type=submit value="등록" class="btn btn-primary">
				&nbsp;
			<input type=button value="취소" class="btn btn-light" style="border-color: #E5E8F0;" onClick="location.href='<%=request.getContextPath()%>/Main.do'">
		</td>
	</tr>
</table>

</form> 
</div>

</body>
</html>