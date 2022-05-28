<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- 검색부분 유효성 검사 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function(){
		$("form").submit(function(){
			if($("select").val()==""){
				alert("검색할 항목을 선택하세요.");
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

<%
	int count = ((Integer)request.getAttribute("qnacount")).intValue();
	int fcount = ((Integer)request.getAttribute("fcount")).intValue();
	String sel = (String)request.getAttribute("sel");
	String find = (String)request.getAttribute("find");
	System.out.println("sel:"+sel);
	System.out.println("find:"+find);
%>


</head>
<body>

<!-- header부분 -->
<jsp:include page="/header.jsp"/>

<!-- 관리자페이지 네비게이션바 -->
<jsp:include page="/mypage/admin.jsp"/>



<br>

<center>
<div class="container">
<h2>신고/문의 목록</h2>
<table class="table table-striped"  >
<c:if test="${qnacount > 0 and fcount == 0}">
    <thead> 
    <tr>
      <td colspan="3" align="left" style="border:none">
	    Total : ${qnacount}
	     </td>
	     <td align="right" style="border:none; width:120px;">
	     Page : ${page}/${pageCount}
	  </td>
    </tr> 
	<tr>
		<th>번호</th>
		<th>ID</th>
		<th>제목</th>
		<th>작성일</th>
	</tr>

	</thead>
	<tobody>
	<c:set var="num" value="${qnacount - (page - 1) * 10 }"/>
	<c:forEach var="q" items="${qnalist }">
	<tr>
		<td>${num }
			<c:set var="num" value="${num - 1 }"/>
		</td>
		<td>${q.id }</td>
		<td>
		<!-- 답글 제목 앞 여백 처리 -->
		<c:if test="${q.qna_lev > 0 }">
			<img src="./img/reply2.png" width=20 height=20>
		</c:if>
		
		<a style="color:#000;" href="./QnaDetail.do?qna_num=${q.qna_num }&page=${page}">
			${q.qna_subject }
		</a>
		</td>
		<td>
			<fmt:formatDate value="${q.qna_writedate }" pattern="yyyy-MM-dd "/>
		</td>
	</tr>
	</c:forEach>
	</c:if>
	
	
	
	<c:if test="${qnacount > 0 and fcount != 0}">
	
	<thead> 
    <tr>
      <td colspan="3" align="left" style="border:none">
	    Total : ${fcount}
	     </td>
	     <td align="right" style="border:none">
	     Page : ${page} / ${pageCount}
	  </td>
    </tr> 
	<tr>
		<th>번호</th>
		<th>ID</th>
		<th>제목</th>
		<th>작성일</th>
	</tr>
	</tr>
	</thead>
	
	<c:set var="num" value="${fcount - (page - 1) * 10 }"/>
	<c:forEach var="qf" items="${fqnalist }">

	<tr>
		<td>${num }
			<c:set var="num" value="${num - 1 }"/>
		</td>
		<td>${qf.id }</td>
		<td>
		<!-- 답글 제목 앞 여백 처리 -->
		<c:if test="${qf.qna_lev > 0 }">
			<img src="./img/reply2.png" width=20 height=20>
		</c:if>
		
		<a style="color:#000;" href="./QnaDetail.do?qna_num=${qf.qna_num }&page=${page}">
			${qf.qna_subject }
		</a>
		</td>
		<td>
			<fmt:formatDate value="${qf.qna_writedate }" pattern="yyyy-MM-dd "/>
		</td>
	</tr>
	</c:forEach>
	</c:if>
	
	</tobody>
</table>
</div>
</center>

<center>
<div class="container" >
		<div class="row">
			<form method="post" name="search" action="<%=request.getContextPath()%>/QnaList.do">
				<table class="pull-right" style="margin-left:15px">
					<tr>
						<td align="center">
						<select class="form-control" name="sel">
							<option value="">선택</option>
							<option value="id">아이디</option>
							<option value="qna_subject">제목</option>
							<option value="qna_content">내용</option>
						</select>
						</td>
						<td align="center">
						   <input type="text" class="form-control"
							      placeholder="검색어 입력" name="find" id="find" maxlength="90">
					    </td>
						<td align="center">
						  <input type="submit" class="btn btn-primary">
						</td>
					</tr>

				</table>
			</form>
		</div>
	</div>
</center>





<!-- 페이징 처리 -->
<center>
<div style="float:left; width:100%; text-align:center;" >
<div style="display:inline-block;  margin-top:20px">
  
   <c:if test="${qnacount > 0 and fcount == 0}">
   
   <ul class="pagination pagination-sm" >
	   <!-- 1 페이지 -->
	   <li class="page-item">
		  <a class="page-link" href="./QnaList.do?page=1" style="color:#000;"> 
  			 << 
     	  </a>
       </li>

	    <!-- 이전 블럭 -->
		<c:if test="${startPage > 10 }">
		<li class="page-item">
 		   <a class="page-link" href="./QnaList.do?page=${startPage - 10 }" style="color:#000;">
 			  이전
 		   </a>
		</li> 
        </c:if>

		<!-- 각 블럭에 페이지 10개씩 출력 -->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:if test="${i == page }">  <!-- 현재 페이지 -->
		<li class="page-item">
		  <a class="page-link" style="color:#000;">
		    ${i}
		  </a>
		</li>  
		</c:if>
		<c:if test="${i != page }"> <!-- 현재 페이지가 아닌 경우 -->
	    <li class="page-item">
	      <a class="page-link" href="./QnaList.do?page=${i}" style="color:#000;">
		     ${i}
	      </a>
	 	</li> 
		</c:if>
		</c:forEach>

		<!-- 다음 블럭으로 이동 -->
		<c:if test="${endPage < pageCount }">
  		<li class="page-item">
		  <a class="page-link" href="./QnaList.do?page=${startPage + 10 }" style="color:#000;">
	                다음 
	      </a>
        </li>	
        </c:if>

		<!-- 마지막 페이지로 이동 -->
  		<li class="page-item">
  		  <a class="page-link" href="./QnaList.do?page=${pageCount }" style="text-decoration:none; color:#000;"">
   		    >> 
          </a>
 	    </li>  
    </ul>
  </div>
  </div>  
  </c:if>
</center>


<center>
<div style="float:left; width:100%; text-align:center;" >
<div style="display:inline-block;">

   <c:if test="${fcount > 0}">
   <ul class="pagination pagination-sm"" >
	  <!-- 1 페이지 -->
	  <li class="page-item">
		 <a class="page-link" href="./QnaList.do?page=1&sel=${sel}&find=${find}" style="text-decoration:none; color:#000;"> 
  			 << 
     	 </a>
      </li>

	  <!-- 이전 블럭 -->
	<c:if test="${startPage > 10 }">
	<li class="page-item">
 	   <a class="page-link" href="./QnaList.do?page=${startPage - 10 }&sel=${sel}&find=${find}" style="color:#000">
 		     이전
 	   </a>
	</li> 
    </c:if>

	<!-- 각 블럭에 페이지 10개씩 출력 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:if test="${i == page }">  <!-- 현재 페이지 -->
	<li class="page-item">
	   <a class="page-link" style="color:#000">
		   ${i}
	   </a>
	</li>  
	</c:if>
	<c:if test="${i != page }"> <!-- 현재 페이지가 아닌 경우 -->
	<li class="page-item">
	   <a class="page-link" style="color:#000" href="./QnaList.do?page=${i}&sel=${sel}&find=${find}" >
		   ${i}
	   </a>
	</li> 
	</c:if>
	</c:forEach>

	<!-- 다음 블럭으로 이동 -->
	<c:if test="${endPage < pageCount }">
  	<li class="page-item">
	   <a class="page-link" style="color:#000" href="./QnaList.do?page=${startPage + 10 }&sel=${sel}&find=${find}">
	            다음 
	   </a>
     </li>	
     </c:if>

	 <!-- 마지막 페이지로 이동 -->
  	 <li class="page-item">
  		<a class="page-link" href="./QnaList.do?page=${pageCount }&sel=${sel}&find=${find}" style="text-decoration:none; color:#000;">
   		   >> 
        </a>
 	  </li>  
    </ul>    
   </c:if>

</div>
</div>
</center>


<br><br>

</body>
</html>