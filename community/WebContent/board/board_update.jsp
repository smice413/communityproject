<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<title>커뮤니티 게시판</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<%=request.getContextPath()%>/board/board.js"></script>

<!--  DB에서 불러온 head값에 따라 기본 화면세팅 -->
<c:if test="${board.head =='일반'}">
	<script>
		$("#sel1").hide();
 	    $("#star").val("0");
		$("#sel2").show();
		$("#sel3").hide();
 	    $("#fix").attr("checked", false);
		$("#sel4").show();
	</script>
</c:if>
<c:if test="${board.head =='후기'}">
	<script>
		$("#sel1").show();
		$("#sel2").hide();
		$("#sel3").hide();
		$("#fix").attr("checked", false);
		$("#sel4").show();
	</script>
</c:if>
<c:if test="${board.head =='공지'}">
	<script>
		$("#sel1").hide();
 	    $("#star").val("0");
		$("#sel2").show();
		$("#sel3").show();
		$("#sel4").hide();
	</script>
</c:if>


<script>
$(function() {
	<!--  DB에서 불러온 head값에 따라 기본 화면세팅 -->
	<c:if test="${board.head =='일반'}">
			$("#sel1").hide();
	 	    $("#star").val("0");
			$("#sel2").show();
			$("#sel3").hide();
	 	    $("#fix").attr("checked", false);
			$("#sel4").show();
	</c:if>
	<c:if test="${board.head =='후기'}">
			$("#sel1").show();
			$("#sel2").hide();
			$("#sel3").hide();
			$("#fix").attr("checked", false);
			$("#sel4").show();
	</c:if>
	<c:if test="${board.head =='공지'}">
			$("#sel1").hide();
	 	    $("#star").val("0");
			$("#sel2").show();
			$("#sel3").show();
			$("#sel4").hide();
	</c:if>
	
	<!-- head값이 변경되면 보여지는 내용을 바꿔줌 -->
    $("select").change(function() {
       var c = $("select").val();
       if (c == '후기') {
    	   $("#sel1").show();
    	   $("#sel2").hide();
    	   $("#fix").attr("checked", false);
       } else {
    	   $("#sel1").hide();
    	   $("#star").val("0");
    	   $("#sel2").show();
       }
       if (c == '공지'){
    	   $("#sel3").show();
    	   $("#sel4").hide();
       }else {
    	   $("#sel3").hide();
    	   $("#fix").attr("checked", false);
    	   $("#sel4").show();
       }
    });
});

</script>
</head>
<body>
<!-- header부분 -->
<jsp:include page="/header.jsp" />
<br>
<center>
<div class="container">
<form action="<%=request.getContextPath() %>/BoardUpdate.do" method="post" 
	  enctype="multipart/form-data">
<!-- hidden 파라미터 -->
	<input type="hidden" name="id" value="${sessionScope.id}">
	<input type="hidden" name="num" value="${board.num}">
	<input type="hidden" name="page" value="${page}">
	<h3>게시글 수정</h3>
	<table border=0 class="table" style="width:100%; font-size:14px; font-family:굴림;">
		<tr>
			<td width=85 bgcolor="eeeeee">말머리</td>
			<td>
				<select id="head" name="head">
					<option value="">선택하세요</option>
					<option value="일반"
						<c:if test="${board.head =='일반'}"> selected</c:if>>일반</option>
					<option value="공지"
						<c:if test="${board.head =='공지'}"> selected</c:if>>공지</option>
					<option value="후기"
						<c:if test="${board.head =='후기'}"> selected</c:if>>후기</option>
				</select>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				별점
			</td>
			<td>
				<div id = "sel1">
				<select id="star" name="star">
					<option value="">선택하세요</option>
					<option value="0" <c:if test="${board.star == 0}"> selected</c:if>>☆☆☆☆☆</option>
					<option value="1" <c:if test="${board.star == 1}"> selected</c:if>>★☆☆☆☆</option>
					<option value="2" <c:if test="${board.star == 2}"> selected</c:if>>★★☆☆☆</option>
					<option value="3" <c:if test="${board.star == 3}"> selected</c:if>>★★★☆☆</option>
					<option value="4" <c:if test="${board.star == 4}"> selected</c:if>>★★★★☆</option>
					<option value="5" <c:if test="${board.star == 5}"> selected</c:if>>★★★★★</option>
				</select>	
				</div>
				<div id = "sel2" style="font-family:돋음; font-size:12px; color:darkblue">
				별점은 말머리가 후기일 경우에만 선택가능합니다.
				</div>
			</td>
		</tr>
		
		<tr>
			<td bgcolor="eeeeee">
				상단고정
			</td>
			<td>
				<div id = "sel3">
				<input type="checkbox" name="fix" id="fix" value="Y" <c:if test="${board.fix =='Y'}"> checked</c:if>> 체크박스에 체크를 하면 상단 고정이 됩니다.
				</div>
				<div id = "sel4" style="font-family:돋음; font-size:12px; color:darkblue">
				상단고정은 말머리가 공지일 경우에만 선택가능합니다.
				</div>
			</td>
		<tr>
			<td bgcolor="eeeeee">
				비밀번호
			</td>
			<td style="font-family:돋음; font-size:12px; color:darkblue">
				<input name="passwd" id="passwd" type="password" maxlength="20"><div style="display:inline-block; border: 1px">회원 비밀번호를 입력해주세요</div>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				제 목
			</td>
			<td>
				<input name="subject" id="subject" type="text" size="50" maxlength="100" 
					value="${board.subject}" class="form-control">
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				내 용
			</td>
			<td>
				<textarea name="content" id="content" cols="67" rows="10" class="form-control">${board.content}</textarea>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				파일첨부
			</td>
			<td>
			<!-- 수정전 첨부파일명을 hidden타입(old_board_file)으로 딸려보냄 -->
			<c:if test="${board.board_file != null}">
				${board.board_file}<br>
				<input type=hidden name="old_board_file" value="${board.board_file}">
				<font size=1 color=blue>첨부파일은 5MB이하 1개만 등록 가능합니다.<br>새로운 파일을 선택하시면 예전 파일은 삭제됩니다.</font>
			</c:if>
				<input name="board_file" type="file" class="form-control"/>
			</td>
		</tr>
	
		<tr align="center" valign="middle">
			<td colspan="5">			
				<input type=submit value="수정" class="btn btn-primary"
				style="width:100px; height:30px; font-size:15px; padding:2px">
				<input type=reset value="취소" class="btn btn-secondary"
				style="width:100px; height:30px; font-size:15px; padding:2px">
			</td>
		</tr>
	</table>
</form>
</div>
</center>
</body>
</html>