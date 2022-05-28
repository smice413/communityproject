<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script>
$(function() {
	$("#sel1").hide();
	$("#sel3").hide();
	
    $("select").change(function() {
       var c = $("select").val();
       if (c == '후기') {
    	   $("#sel1").show();
    	   $("#sel2").hide();
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
    	   $("#sel4").show();
       }
    });

});

</script>
<!-- header부분 -->
<jsp:include page="/header.jsp" />
<br>
<form action="<%=request.getContextPath() %>/BoardWrite.do" method="post" 
	  enctype="multipart/form-data">
<input type="hidden" name="bl_code" value="${param.bl_code}">
<input type="hidden" name="id" value="${sessionScope.id}">
<table cellpadding="0" cellspacing="0" align=center border=0 class="table table-striped" style="width:98%;">
	<tr align="center" valign="middle">
		<td colspan="5"><b>커뮤니티 게시판</b></td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16" width=80px>
			<b>말머리</b>
		</td>
		<td>
			<select id="head" name="head">
				<option value="">선택하세요</option>
				<option value="일반">일반</option>
				<option value="공지">공지</option>
				<option value="후기">후기</option>
			</select>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<b>별점</b>
		</td>
		<td>
			<div id = "sel1">
			<select id="star" name="star">
				<option value="">선택하세요</option>
				<option value="0">☆☆☆☆☆</option>
				<option value="1">★☆☆☆☆</option>
				<option value="2">★★☆☆☆</option>
				<option value="3">★★★☆☆</option>
				<option value="4">★★★★☆</option>
				<option value="5">★★★★★</option>
			</select>	
			</div>
			<div id = "sel2" style="font-family:돋음; font-size:12; color:darkred">
			※별점은 말머리가 후기일 경우에만 선택가능합니다.
			</div>
		</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12; padding-right:0px" height="16">
			<b>상단고정</b>
		</td>
		<td>
			<div id = "sel3">
			<input type="checkbox" name="fix" id="fix" value="Y"> 체크박스에 체크를 하면 상단 고정이 됩니다.
			</div>
			<div id = "sel4" style="font-family:돋음; font-size:12; color:darkred">
			※상단고정은 말머리가 공지일 경우에만 선택가능합니다.
			</div>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<b>제 목</b>
		</td>
		<td>
			<input name="subject" id="subject" type="text" size="70" maxlength="70" 
				value="" class="form-control"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<b>내 용</b>
		</td>
		<td>
			<textarea name="content" id="content" cols="67" rows="15" class="form-control"></textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12; padding-right:0px">
			<b>파일첨부</b>
		</td>
		<td style="font-family:돋음; font-size:12; color:darkred">
			<input name="board_file" type="file" class="form-control"/>
			※ 첨부파일은 5MB 이하 1개만 등록 가능합니다.
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">			
			<input type=submit value="등록" class="btn btn-primary"
			style="width:100px; height:30px; font-size:15px; padding:2px">
			<input type=reset value="취소" class="btn btn-secondary"
			style="width:100px; height:30px; font-size:15px; padding:2px">
		</td>
	</tr>
</table>
</form>

</body>
</html>