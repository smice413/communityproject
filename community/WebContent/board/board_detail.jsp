<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 환경설정  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
    
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<!-- 부트스트랩  환경설정  -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- jquery 환경설정  -->
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- 삭제된 게시판(board_activate가 N)인 경우 -->
<c:if test="${board.board_activate == 'N'}">
	<script>
		alert("삭제된 게시글입니다.");
		history.go(-1);
	</script>
</c:if>

<script>
//$(document).ready(function(){
//답글정렬 (미완성)
//    $("#replysort").change(function(){
//        if($(this).val()!=""){
//           location.href=$(this).val();
//           return false;
//        }
//	});
//});
</script>
</head>

<body>
<!-- header부분 -->
<jsp:include page="/header.jsp" />

<center>
<div class="container">

<!-- 카테고리 / 게시판 이름  -->
<!-- 개설자 아이디와 세션아이디가 같으면 게시판 관리버튼 -->
	<table border=0 style="width:100%; height:40px; font-size:18px">
		<tr>
			<td><b>${bl.bl_category} > ${bl.bl_name}&nbsp;</b>
			<c:if test="${bl.id.equals(sessionScope.id)}">	
			<img src="./img/icon-manager.png" width=15px>
			<input type="button" value="게시판관리" class="btn btn-info"
					style="width:58px; height:20px; font-size:10px; padding:2px"
					onClick="location.href='<%=request.getContextPath()%>/BLUpdatePre.do?bl_code=${board.bl_code}&page=${page}' ">
			</c:if>
			</td>
<!-- 개발시에만 필요한 출력내용
	 		<td align=right style="font-size:12px">개설자 아이디${bl.id}/글쓴이${board.id}/세션 아이디:${sessionScope.id}/bl_code:${board.bl_code}/page:${page }
			</td> --!>
		</tr>
	</table>

<!-- 글제목 + 별점 -->
	<table border=0 class="table table-striped" style="width:100%; height:25px; margin:0px; font-size:15px">
		<tr>
			<td>[${board.head}] ${board.subject}</td>
			<c:if test="${board.head == '후기'}">
			<td align=right style="text-decoration: none; color: red; font-size:18px">
				<c:if test="${board.star == 0}">☆☆☆☆☆</c:if>
				<c:if test="${board.star == 1}">★☆☆☆☆</c:if>
				<c:if test="${board.star == 2}">★★☆☆☆</c:if>
				<c:if test="${board.star == 3}">★★★☆☆</c:if>
				<c:if test="${board.star == 4}">★★★★☆</c:if>
				<c:if test="${board.star == 5}">★★★★★</c:if>
			</td>
			</c:if>
		</tr>
	</table>

<!-- 작성일 + 조회수/추천수/댓글 수  -->
	<table border=0 style="width:96%; height:25px; font-size:12px">
		<tr>
			<td align=left><fmt:formatDate value="${board.writedate}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
			<td align=right>조회 ${board.readcount} | 추천  ${recomcount} | 댓글  ${replycount} </td>
		</tr>
	</table>
	
<!-- 첨부파일이 있을때만 첨부파일명 보기 -->
	<c:if test="${board.board_file != null}">
	<table border=0 align="center" class="table" style="width:100%; margin:0px; font-size:12px">
		<tr>
			<td colspan=2>첨부 파일 : 
					<a href="./board/file_down.jsp?file_name=${board.board_file}">			
					${board.board_file}</a>	
			</td>
		</tr>
	</table>
	</c:if>

<!-- 첨부파일이 있을 때만 첨부사진 보기 + 글 상세내용 -->
	<table border=0 align="center" class="table" style="width:100%; height: 150px; margin:0px; padding:1px; font-size:15px">
		<tr>
			<td colspan=2>
				<c:if test="${board.board_file != null}">
				<img src="./boardupload/${board.board_file}" width="50%"><br>
				</c:if>
				${content}
			</td>
		</tr>
	</table>

<!-- 좋아요 기능 -->	
	<table id="recom" border=0 align="center" class="table" style="width:100%; margin:0px; padding:1px; font-size:13px">
		<tr>
			<td align=center>
			<c:if test="${recomcheck == 0 }">
				<a href="./BoardRecommend.do?page=${page}&num=${num}&recomcheck=${recomcheck}#recom"><font size=4>♡</font></a> 좋아요 ${recomcount}
			</c:if>
			<c:if test="${recomcheck == 1 }">
				<a href="./BoardRecommend.do?page=${page}&num=${num}&recomcheck=${recomcheck}#recom"
				 style="text-decoration: none; color: red;"><font size=4>♥</font></a> 좋아요 ${recomcount}
			</c:if>
			</td>
		</tr>
		
<!-- 수정/삭제/목록 버튼 모음 -->
		<tr>
			<td colspan=2 align=center>
			<!-- 세션아이디와 글 작성자가 같을 때만 출력 -->
			<c:if test="${sessionScope.id.equals(board.id)}">
				<input type="button" value="수정"  class="btn btn-secondary" 
				style="width:100px; height:30px; font-size:15px; padding:2px"
				onClick="location.href='./BoardUpdatePre.do?num=${num}&page=${page}' ">
			</c:if>
			<!-- 세션아이디와 글 작성자가 같거나 관리자일 때만 출력 -->
			<c:if test="${sessionScope.id.equals(board.id) || sessionScope.id == 'admin'}">	
				<input type="button" value="삭제"  class="btn btn-secondary"
				style="width:100px; height:30px; font-size:15px; padding:2px"
				onClick="location.href='./BoardDelete.do?num=${num}&page=${page}&bl_code=${board.bl_code}'">
			</c:if>
				<input type="button" value="목록"  class="btn btn-primary"
				style=" width:100px; height:30px; font-size:15px; padding:2px"
				onClick="location.href='./BoardList.do?page=${page}&bl_code=${board.bl_code}' ">
				
			</td>
		</tr>
	</table>

<!-- 댓글 등록  -->
<form action="./BoardReply.do#recom">
	<input type=hidden name=page value="${page}">
	<input type=hidden name=num value="${num}">
	<table id="replyview" border=0 class="table" style="width:100%; margin:0px; padding:1px; font-size:13px">
		<tr>
			<td width=65 style="padding:5px;">
				댓글작성<br><br>
				<input type=submit value="등록" class="btn btn-primary"
					style="background-color:#4682b4; width:50px; height:25px; font-size:13px; padding:2px">
			</td>
			<td style="padding:3px">
				<textarea name="content" id="reply_p" rows="3" maxlength=115 class="form-control"
				 style="background-color:mintcream; font:1.2em sans-serif;" placeholder="댓글을 입력해 주세요"></textarea>
			</td>
		</tr>
	</table>
</form>

<!-- 댓글 보기 + 새로고침 -->
	<table border=0 align="center" class="table table-striped" style="width:100%; margin:0px; padding:0px; font-size:13px">
		<tr>
			<td>
				전체 댓글 <font color=red> ${replycount}</font>개
			</td>
			<td align=right>
				<a href="./BoardDetail.do?num=${num}&page=${page}#replyview">새로고침</a>
	<!-- 댓글 정렬 기능 (아직 완성못함)
		        <select id="replysort" name="replysort">
		            <option value="">선택하세요</option>
		            <option value="./BoardDetail.do?num=${num}&replysort='desc'&page=${page}">최신순</option>
		            <option value="./BoardDetail.do?num=${num}&replysort='asc'&page=${page}">등록순</option>
		        </select> -->
			</td>
		</tr>
	</table>

	<table border=0 align="center" style="width:100%; margin:0px; padding:0px;">
		<tr>
			<td>
			  <div id="accordion">
				<c:forEach var="re" items="${replylist}">
<!--  댓글 내용 출력 (클릭하면 재댓글 입력창을 보여줌)   -->
			      <div class="card-header" style="margin:0px; padding:7px;">
			      	<table id="replyview${re.re_num}" border=0 width=100% style="font-size:14px;">
			      		<tr>
			      		<!-- 재댓글 빈공간 -->
			      		<c:if test="${re.re_lev > 1}">
			      		<c:forEach var="i" begin="1" end="${re.re_lev-1}" step="1">
			      			<td width=20px></td>
			      		</c:forEach>
			      		</c:if>
			      		<!-- 재댓글 이미지 -->
			      		<c:if test="${re.re_lev >= 1}">
			      			<td width=20px valign=top><img src="./img/reply2.png" width=20></td>
			      		</c:if>
			      		<!-- 내용(삭제된 글과 정상글) -->
						<c:if test="${re.re_activate=='N'}">
							<td style="color:gray;">삭제된 글입니다.</td>
						</c:if>
						<c:if test="${re.re_activate=='Y'}">
							<td>
			      				<a class="card-link" data-toggle="collapse" href="#replywrite${re.re_num}">
			      				<!-- 개발자를 위한 (부모글번호/내 글번호) ${re.re_ref}/${re.re_num} -->
									<font size=2 color=darkgray>익명이</font>
									${re.re_content}<br></a>
									<font size=1><fmt:formatDate value="${re.re_writedate}" pattern="yyyy-MM-dd HH:mm:ss"/></font>
			      			</td>
			      		</c:if>
			      		<!-- 관리자와 작성자를 위한 삭제버튼 -->
			      		<c:if test="${sessionScope.id.equals(re.id) || sessionScope.id == 'admin'}">
			      			<td width=20px valign=top>
			      			<a href="./BoardReplyDelete.do?page=${page}&num=${num}&limit=${limit}&re_num=${re.re_num}&re_ref=${re.re_ref}&re_lev=${re.re_lev}&re_seq=${re.re_seq}#bottom">ｘ</td>
			      		</c:if>
			      		</tr>
			      	</table>
			      </div>
<!--  재댓글 입력창    -->
			      <div id="replywrite${re.re_num}" class="collapse hide" data-parent="#accordion">
			        <div class="card-body" style="margin:0px; padding:0px;">
					<form action="./BoardReply.do#replyview${re.re_num}">
					<input type=hidden name=page value="${page}">
					<input type=hidden name=num value="${num}">
					<input type=hidden name=limit value="${limit}">
					<input type=hidden name=re_ref value="${re.re_ref}">
					<input type=hidden name=re_lev value="${re.re_lev}">
					<input type=hidden name=re_seq value="${re.re_seq}">
					<table border=0 width=500 align="center" class="table" style="width:100%; margin:0px; padding:1px; font-size:13px">
						<tr>
							<td width=65 style="padding:4px">댓글작성<br><br>
								<input type=submit value="등록" class="btn btn-primary"
									style="background-color:#4682b4; width:50px; height:25px; font-size:13px; padding:2px">
							</td>
							<td style="padding:3px">
								<textarea name="content" id="reply_c" rows="3" maxlength=118 class="form-control"
								 style="background-color:linen; font:1.2em sans-serif;" placeholder="댓글을 입력해 주세요"></textarea>
							</td>
						</tr>
					</table>
					</form>
			        </div>
			    </div>
			    </c:forEach>
			  </div>
			</td>
		</tr>
	</table>
<!--  댓글이 10개 이하일 경우 (전체댓글개수/본문보기 링크)  -->
	<table id="bottom" border=0 align="center" class="table table-striped" style="width:100%; margin:0px; padding:1px; font-size:13px">
		<tr>
		<c:if test="${replycount <= 10}">
			<td>전체 댓글 <font color=red> ${replycount}</font>개</td>
		</c:if>
<!--  댓글이 10개 이상일 경우 (전체보기/더보기 추가 링크)  -->
		<c:if test="${replycount > 10}">
			<td>
				전체 <a href="./BoardDetail.do?num=${num}&page=${page}&limit=${replycount}#bottom"><font color=red> ${replycount}</font></a>개<c:if test="${replycount > limit}"> 중 ${limit}개</c:if>
			</td>
			<c:if test="${replycount > limit}">
			<td>
				<a href="./BoardDetail.do?num=${num}&page=${page}&limit=${limit+10}#bottom">더보기>></a>
			</td>
			</c:if>
		</c:if>
			<td align=right>
				<a href="./BoardDetail.do?num=${num}&page=${page}">본문보기</a>
			</td>
		</tr>
	</table>

</center>
</div>
<br><br>
</body>
</html>