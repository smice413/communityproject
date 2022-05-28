<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  
<script src="http://code.jquery.com/jquery-latest.js"></script>



<c:if test="${param.old_passwd != param.new_passwd}">
	<script>
		alert("비밀번호가 다릅니다");
		history.go(-1);
	</script>
</c:if>

<script>
$(function() {

	$("#sort").change(function(){
		if($("#sort").val()!=""){
			location.href=$("#sort").val();
			return false;
		}
	});
	$("#head").change(function(){
		if($("#head").val()!=""){
			location.href=$("#head").val();
			return false;
		}
	});
});
</script>

<style type="text/css">
      #tmTable tr:nth-child(even){background-color: #f2f2f2;}
</style>

<%
	//response.setContentType("text/html; charset=utf-8");
	request.setCharacterEncoding("utf-8");

	int count = ((Integer)request.getAttribute("count")).intValue();
	int fcount = ((Integer)request.getAttribute("fcount")).intValue();
	
	String head = (String)request.getAttribute("head");
	String sort = (String)request.getAttribute("sort");
	System.out.println("head:"+head);
	System.out.println("sort:"+sort);
	String sel = (String)request.getAttribute("sel");
	String find = (String)request.getAttribute("find");
	System.out.println("sel:"+sel);
	System.out.println("find:"+find);
%>

<!-- header부분 -->
<jsp:include page="/header.jsp" />
<br>  
<center>
<div class="container">
<h4>${bl.bl_name}</h4>
<h6>${bl.bl_exp}</h6><br>

<table border=0 width=100% >
	<tr>
		<td>정렬방법 
			<c:set var="sort_new" value="writedate" />
			<c:set var="sort_read" value="readcount" />
			<select id="sort" name="sort">
				<option value="">선택하세요</option>
				<option value="./BoardList.do?bl_code=${bl_code}&head=${head}&sort=${sort_new}">최신순</option>
				<option value="./BoardList.do?bl_code=${bl_code}&head=${head}&sort=${sort_read}">조회수순</option>
			</select>
		</td>

		<td align="right">게시판 유형 
			<c:set var="head_all" value="" />
			<c:set var="head_normal" value="일반" />
			<c:set var="head_notice" value="공지" />
			<c:set var="head_star" value="후기" />
			
			<select id="head" name="head">
				<option value="">선택하세요</option>
				<option value="./BoardList.do?bl_code=${bl_code}&head=${head_all}&sort=${sort}">전체보기</option>
				<option value="./BoardList.do?bl_code=${bl_code}&head=${head_normal}&sort=${sort}">일반</option>
				<option value="./BoardList.do?bl_code=${bl_code}&head=${head_notice}&sort=${sort}">공지</option>
				<option value="./BoardList.do?bl_code=${bl_code}&head=${head_star}&sort=${sort}">후기</option>
			</select>
		</td>
	</tr>
</table>
<table border=0 id="tmTable" class="table" style="width:100%; padding:0px; font-size:13px">
	<tr align="center">
		<th style="padding:10px 0px 10px 0px;" width=35px>번호</th>
		<th style="padding:10px 10px 10px 10px;">글제목</th>
		<th style="padding:10px 10px 10px 10px;" width=97px>날짜</th>
		<th style="padding:10px 0px 10px 0px;" width=39px>조회수</th>
	</tr>
	
	<c:forEach var="f" items="${boardfixlist}">
	<tr>
		<td style="padding:10px 0px 10px 0px;" bgcolor="lavender" align=center>${f.num}
			<c:set var="num" value="${num-1}" />
		</td>
		
		<td style="padding:10px 0px 10px 0px;" bgcolor="lavender">
<a href="./BoardDetail.do?num=${f.num}&page=${page}">			
			[${f.head}]${f.subject}  <font color="red">(${f.re_count})</font>
		</a>	
		</td>	
		<td style="padding:11px 0px 11px 0px; font-size:12px" bgcolor="lavender">
			<fmt:formatDate value="${f.writedate}"
				            pattern="yyyy-MM-dd HH:mm"/>
		</td>
		<td style="padding:10px 5px 10px 0px;" align=right bgcolor="lavender">${f.readcount}</td>
	</tr>
	</c:forEach>
	
	<c:if test="${count > 0 and fcount == 0}">
	<c:set var="num" value="${count - (page-1) * 10}" />
	<c:forEach var="f" items="${boardlist}">
	<tr>
		<td style="padding:10px 0px 10px 0px;" align=center>${f.num}
			<c:set var="num" value="${num-1}" />
		</td>
		
		<td style="padding:10px 0px 10px 0px;">
<a href="./BoardDetail.do?num=${f.num}&page=${page}">
		<c:if test="${f.head=='후기' and f.star=='0'}">			
			<font color="red">☆☆☆☆☆</font>${f.subject} <font color="red">(${f.re_count})</font>
		</c:if> 
		<c:if test="${f.head=='후기' and f.star=='1'}">			
			<font color="red">★☆☆☆☆</font>${f.subject} <font color="red">(${f.re_count})</font>
		</c:if> 
		<c:if test="${f.head=='후기' and f.star=='2'}">			
			<font color="red">★★☆☆☆</font>${f.subject} <font color="red">(${f.re_count})</font>
		</c:if> 
		<c:if test="${f.head=='후기' and f.star=='3'}">			
			<font color="red">★★★☆☆</font>${f.subject} <font color="red">(${f.re_count})</font>
		</c:if> 
		<c:if test="${f.head=='후기' and f.star=='4'}">			
			<font color="red">★★★★☆</font>${f.subject} <font color="red">(${f.re_count})</font>
		</c:if>  
		<c:if test="${f.head=='후기' and f.star=='5'}">			
			<font color="red">★★★★★</font>${f.subject} <font color="red">(${f.re_count})</font>
		</c:if>  
		<c:if test="${f.head!='후기'}">			
			[${f.head}]${f.subject}  <font color="red">(${f.re_count})</font>
		</c:if> 	
		</a>	
		</td>
		<td style="padding:11px 0px 11px 0px; font-size:12px">
			<fmt:formatDate value="${f.writedate}"
				            pattern="yyyy-MM-dd HH:mm"/>
		</td>
		<td style="padding:10px 5px 10px 0px;" align=right>${f.readcount}</td>
	</tr>
	</c:forEach>
	</c:if>
	
	<c:if test="${count > 0 and fcount != 0}">
	<c:set var="num" value="${fcount - (page-1) * 10}" />
	<c:forEach var="f" items="${fboardlist}">
	<tr>
		<td style="padding:10px 0px 10px 0px;">${f.num}
			<c:set var="num" value="${num-1}" />
		</td>
		
		<td style="padding:10px 0px 10px 0px;">
		<a href="./BoardDetail.do?num=${f.num}&page=${page}">
			<c:if test="${f.head=='후기' and f.star=='0'}">			
				<font color="red">☆☆☆☆☆</font>${f.subject} <font color="red">(${f.re_count})</font>
			</c:if> 
			<c:if test="${f.head=='후기' and f.star=='1'}">			
				<font color="red">★☆☆☆☆</font>${f.subject} <font color="red">(${f.re_count})</font>
			</c:if> 
			<c:if test="${f.head=='후기' and f.star=='2'}">			
				<font color="red">★★☆☆☆</font>${f.subject} <font color="red">(${f.re_count})</font>
			</c:if> 
			<c:if test="${f.head=='후기' and f.star=='3'}">			
				<font color="red">★★★☆☆</font>${f.subject} <font color="red">(${f.re_count})</font>
			</c:if> 
			<c:if test="${f.head=='후기' and f.star=='4'}">			
				<font color="red">★★★★☆</font>${f.subject} <font color="red">(${f.re_count})</font>
			</c:if>  
			<c:if test="${f.head=='후기' and f.star=='5'}">			
				<font color="red">★★★★★</font>${f.subject} <font color="red">(${f.re_count})</font>
			</c:if>  
			<c:if test="${f.head!='후기'}">			
				[${f.head}]${f.subject}  <font color="red">(${f.re_count})</font>
			</c:if> 	
		</a>	
		</td>
		<td style="padding:11px 0px 11px 0px; font-size:12px">
			<fmt:formatDate value="${f.writedate}"
				            pattern="yyyy-MM-dd HH:mm"/>
		</td>
		<td style="padding:10px 5px 10px 0px;" align=right>${f.readcount}</td>
	</tr>
	</c:forEach>
	</c:if>
	
	<tr>
		<td colspan=4 align=right>
		<c:if test="${bl.id.equals(sessionScope.id)}">
			<input type="button" value="게시판 관리" class="btn btn-secondary"
			style="width:100px; height:30px; font-size:15px; padding:2px" 
			onClick="location.href='<%=request.getContextPath()%>/BLUpdatePre.do?bl_code=${bl_code}' "/>
		</c:if>	
		<c:if test="${sessionScope.id != null}">
			<input type="button" value="글쓰기"  class="btn btn-primary"
			style="width:100px; height:30px; font-size:15px; padding:2px"
			onClick="location.href='./board/board_write.jsp?bl_code=${bl_code}' ">
		</c:if>	
		</td>
	</tr>
	
</table>

<!-- 페이지 처리 -->
<center>

<div style="float:left; width:100%; text-align:center;" >
<div style="display:inline-block;">
<ul class="pagination pagination-sm" >
<c:if test="${count > 0 and fcount == 0}">

<!-- 1페이지로 이동 -->
<li class="page-item">
<a class="page-link" href="./BoardList.do?page=1&head=${head}&sort=${sort}&bl_code=${bl_code}" style="text-decoration:none"> << </a>
</li>
<!-- 이전 블럭으로 이동 -->
<c:if test="${startPage > 10}">
<li class="page-item">
	<a class="page-link" href="./BoardList.do?page=${startPage-10}&head=${head}&sort=${sort}&bl_code=${bl_code}">이전</a>
</li>
</c:if>

<!-- 각 블럭에 10개의 페이지 출력 -->
<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<c:if test="${i == page}">	<!-- 현재 페이지 -->
	<li class="page-item">
	<a class="page-link">
		${i}
	</a>
	</li>	
	</c:if>
	<c:if test="${i != page}">	<!-- 현재 페이지가 아닌 경우 -->
	<li class="page-item">
		<a class="page-link" href="./BoardList.do?page=${i}&head=${head}&sort=${sort}&bl_code=${bl_code}">${i}</a>
	</li>
	</c:if>
</c:forEach>

<!-- 다음 블럭으로 이동  -->
<c:if test="${endPage < pageCount}">
<li class="page-item">
	<a class="page-link" href="./BoardList.do?page=${startPage+10}&head=${head}&sort=${sort}&bl_code=${bl_code}">다음</a>
</li>
</c:if>

<!-- 마지막 페이지로 이동 -->
<li class="page-item">
<a class="page-link" href="./BoardList.do?page=${pageCount}&head=${head}&sort=${sort}&bl_code=${bl_code}"
	style="text-decoration:none"> >> </a>
</li>
</c:if>

<c:if test="${fcount > 0}">

<!-- 1페이지로 이동 -->
<li class="page-item">
<a class="page-link" href="./BoardList.do?page=1&head=${head}&sort=${sort}&sel=${sel}&find=${find}&bl_code=${bl_code}" style="text-decoration:none"> << </a>
</li>
<!-- 이전 블럭으로 이동 -->
<c:if test="${startPage > 10}">
<li class="page-item">
	<a class="page-link" href="./BoardList.do?page=${startPage-10}&head=${head}&sort=${sort}&sel=${sel}&find=${find}&bl_code=${bl_code}">이전</a>
</li>
</c:if>

<!-- 각 블럭에 10개의 페이지 출력 -->
<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<c:if test="${i == page}">	<!-- 현재 페이지 -->
	<li class="page-item">
	<a class="page-link">
		${i}
	</a>	
	</li>	
	</c:if>
	<c:if test="${i != page}">	<!-- 현재 페이지가 아닌 경우 -->
	<li class="page-item">
		<a class="page-link" href="./BoardList.do?page=${i}&head=${head}&sort=${sort}&sel=${sel}&find=${find}&bl_code=${bl_code}">${i}</a>
	</li>
	</c:if>
</c:forEach>

<!-- 다음 블럭으로 이동  -->
<c:if test="${endPage < pageCount}">
<li class="page-item">
	<a class="page-link" href="./BoardList.do?page=${startPage+10}&head=${head}&sort=${sort}&sel=${sel}&find=${find}&bl_code=${bl_code}">다음</a>
</li>
</c:if>

<!-- 마지막 페이지로 이동 -->
<li class="page-item">
<a class="page-link" href="./BoardList.do?page=${pageCount}&head=${head}&sort=${sort}&sel=${sel}&find=${find}&bl_code=${bl_code}"
	style="text-decoration:none"> >> </a>
</li>
</c:if>
</ul>
</div>
</div>


<form method=post action="<%=request.getContextPath() %>/BoardList.do">
	<input type=hidden name="bl_code" value="${bl_code}">
	<select name="sel" id="sel">
		<option value="">검색</option>
		<option value="subject">제목</option>
		<option value="content">내용</option>
	</select>
	<input type=text name="find" id="find">
	<input type=submit value="검색">
</form>
</div>
</center>
<script>
	$(document).ready(function(){
		$("form").submit(function(){
			if($("#sel").val()==""){
				alert("검색할 항목을 선택하세요?");
				return false;
			}
			if($("#find").val()==""){
				alert("검색어를 입력하세요");
				$("#find").focus();
				return false;
			}				
		});
	});
</script>