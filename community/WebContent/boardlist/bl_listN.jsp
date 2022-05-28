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


<script>
function check(bl_code){
//	alert(bl_code);

	var result = confirm('승인 하시겠습니까?')
	if(result){
		location.href="./BLRequest.do?bl_code="+bl_code
	}else{
		alert("취소");
		location.reload();
	}
}
</script>
</head>
<body>

<!-- header부분 -->
<jsp:include page="/header.jsp"/>

<!-- 관리자페이지 네비게이션바 -->
<jsp:include page="/mypage/admin.jsp"/>


<br>

<center>
<div class="container">
<h2>게시판 승인 요청 목록</h2>
<table class="table table-striped"  >
    <thead> 
    	<tr>
      <td colspan="2" style="border:none" align="left">
         Total : ${blcountN }
      </td>
      <td style="border:none" align="right">
         Page : ${page}/${pageCountN}
      </td>
    </tr> 
	<tr>
		<td align="center" width="60px"><b>승인</b></td>
		<td align="center"><b>게시판 </b></td>
		<td align="center" width="120px"><b>개설일</b></td>
	</tr>
	</thead>
	<tobody>
	<c:set var="num1" value="${blcountN - (page-1) * 10}"/> <!-- *10은 limit값이다. 단지 공유하지 않았기 때문에 10을 넣은것 -->
	<c:forEach var="b1" items="${bllistN}">
	<tr >
		<td align="center"><input type="radio" onClick="check(${b1.bl_code})"></td>
		<td>
			<b>[${b1.bl_category}] ${b1.bl_name}</b>
			<br>${b1.bl_exp}
			<br><label style="font-size:12px">신청회원 : ${b1.id} </label>
			      <c:if test="${b1.bl_private == 'Y'}"> 
			        <label style="font-size:12px;">/ 비밀번호 : ${b1.bl_passwd} </label>
		          </c:if>  
		</td>
		<td align="center">
		<fmt:formatDate value="${b1.bl_regdate}"
						pattern="yyyy-MM-dd"/>
		</td>
		</tr>
	</c:forEach>
	</tobody>	
</table> 
</div>
</center>


<!-- 페이징 처리 -->
<center>
<div style="float:left; width:100%; text-align:center;" >
<div style="display:inline-block;  margin-top:20px">
   <ul class="pagination pagination-sm" >
	   <!-- 1 페이지 -->
	   <li class="page-item">
		  <a class="page-link" href="./BLListN.do?page=1" style="text-decoration:none; color:#000;"> 
  			 << 
     	  </a>
       </li>

	    <!-- 이전 블럭 -->
		<c:if test="${startPage > 10 }">
		<li class="page-item">
 		   <a class="page-link" style="color:#000" href="./BLListN.do?page=${startPage - 10 }">
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
		<c:if test="${i != page }">
	    <li class="page-item">
	      <a class="page-link" style="color:#000" href="./BLListN.do?page=${i}">
		     ${i}
	      </a>
	 	</li> 
		</c:if>
		</c:forEach>

		<!-- 다음 블럭으로 이동 -->
		<c:if test="${endPage < pageCount }">
  		<li class="page-item">
		  <a class="page-link" style="color:#000" href="./BLListN.do?page=${startPage + 10 }">
	                다음 
	      </a>
        </li>	
        </c:if>

		<!-- 마지막 페이지로 이동 -->
  		<li class="page-item">
  		  <a class="page-link"  href="./BLListN.do?page=${pageCount }" style="text-decoration:none; color:#000;">
   		    >> 
          </a>
 	    </li>  
    </ul>
   </div>
   </div> 
</center>
</body>
</html>