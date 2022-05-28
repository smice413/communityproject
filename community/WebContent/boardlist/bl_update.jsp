<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<title>게시판 수정</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="<%=request.getContextPath()%>/boardlist/boardlist.js"></script>
	
<script>


	
</script>
</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp" />
<br><br>

<div class="container">
<form action="<%=request.getContextPath() %>/BLUpdate.do" method="post">
<input type="hidden" id="bl_code" name="bl_code" value="<%=request.getParameter("bl_code") %>">
<table align=center class="table table-striped">
	<tr align="center" valign="middle">
		<td colspan="5" style="font-size: 20px;">게시판 수정</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">카테고리</div>
		</td>
		<td>
			<select name="bl_category" id="bl_category">
				<option value="">카테고리 선택</option>
				<option value="후기" 
					<c:if test="${bl.bl_category == '후기' }">${'selected'}</c:if>
				>후기</option>
				<option value="요리" 
					<c:if test="${bl.bl_category == '요리' }">${'selected'}</c:if>
				>요리</option>
				<option value="영화" 
					<c:if test="${bl.bl_category == '영화' }">${'selected'}</c:if>
				>영화</option>
				<option value="쇼핑" 
					<c:if test="${bl.bl_category == '쇼핑' }">${'selected'}</c:if>
				>쇼핑</option>
				<option value="중고" 
					<c:if test="${bl.bl_category == '중고' }">${'selected'}</c:if>
				>중고</option>
				<option value="기타" 
					<c:if test="${bl.bl_category == '기타' }">${'selected'}</c:if>
				>기타</option>
			</select>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">게시판 이름</div>
		</td>
		<td>
			
			<input name="bl_name" id="bl_name" type="text" size="10" maxlength="10" 
				value="${bl.bl_name }" class="form-control"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">프라이빗여부</div>
		</td>
		<td>
			<c:if test="${bl.bl_private == 'Y' }">
			<input name="bl_private" id="Y" type="radio" size="10" maxlength="10" value="Y" checked onClick="return(false)"/>Y
			<input name="bl_private" id="N" type="radio" size="10" maxlength="10" value="N" onClick="return(false)"/>N
			</c:if>
			<c:if test="${bl.bl_private == 'N' }">
			<input name="bl_private" id="Y" type="radio" size="10" maxlength="10" value="Y" onClick="return(false)"/>Y
			<input name="bl_private" id="N" type="radio" size="10" maxlength="10" value="N" checked onClick="return(false)"/>N
			</c:if>
			<div style="color:red; font-size: 12px;">프라이빗 여부는 수정할 수 없습니다.</div>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">회원 비밀번호</div>
		</td>
		<td>
			<input name="passwd" id="passwd" type="text" size="50" maxlength="100" 
				value="" class="form-control"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">설명</div>
		</td>
		<td>
			<textarea name="bl_exp" id="bl_exp" cols="67" rows="15" class="form-control">${content}</textarea>
		</td>
	</tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">			
			<input type=submit value="수정" class="btn btn-primary">
				&nbsp;
			<input type=reset value="취소" class="btn btn-light" style="border-color: #E5E8F0;">
		</td>
	</tr>
</table>
</form>
</div>

</body>
</html>