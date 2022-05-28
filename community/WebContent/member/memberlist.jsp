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
function check(n){
//	alert(n);

	var result = confirm("정말로 강퇴시키겠습니까?");
	if(result){
		location.href="./MemberStop.do?id="+n;
	}

}
</script>

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
	int count = ((Integer)request.getAttribute("membercount")).intValue();
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
<h2>회원목록</h2>
<table class="table table-striped"  >
    <c:if test="${membercount > 0 and fcount == 0}">
    <thead> 
	<tr>
	  <td colspan="5" style="border:none" align="left">
         Total : ${membercount }
	  </td>
      <td style="border:none" align="right">
         Page : ${page}/${pageCount}
      </td>
    </tr> 
	<tr>
		<th>강퇴</th>
		<th>ID</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th width="150px">가입일</th>
	</tr>
	</thead>
	</tobody>
	<c:forEach var="m" items="${memberlist }">
	<tr>
		<td><input type="radio" name="id" onClick="check('${m.id}')"></td>
		<td>${m.id }</td>
		<td>${m.name }</td>
		<td>${m.phone1 }-${m.phone2 }-${m.phone3 }</td>
		<td>${m.mailid }@${m.domain }</td>
		<td><fmt:formatDate value="${m.regdate}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tobody>
</c:if>

<c:if test="${membercount > 0 and fcount != 0}">
    <thead> 
	<tr>
	  <td colspan="5" style="border:none" align="left">
         Total : ${fcount }
	  </td>
      <td style="border:none" align="right">
         Page : ${page} / ${pageCount}
      </td>
	<tr>
		<th>강퇴</th>
		<th>이름</th>
		<th>ID</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th width="150px">가입일</th>
	</tr>
	</thead>
	</tobody>
	<c:forEach var="f" items="${fmemberlist }">
	<tr>
		<td><input type="radio" name="id" onClick="check('${f.id}')"></td>
		<td>${f.name }</td>
		<td>${f.id }</td>
		<td>${f.phone1 }-${f.phone2 }-${f.phone3 }</td>
		<td>${f.mailid }@${f.domain }</td>
		<td><fmt:formatDate value="${f.regdate }" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tobody>
</c:if>

</table>
</div>
</center>

<center>
<div class="container" >
		<div class="row">
			<form method="post" name="search" action="<%=request.getContextPath()%>/MemberList.do">
				<table class="pull-right" style="margin-left:15px">
					<tr>
						<td align="center">
						<select class="form-control" name="sel">
							<option value="">선택</option>
							<option value="id">아이디</option>
							<option value="name">이름</option>
						</select>
						</td>
						<td align="center">
						   <input type="text" class="form-control"
							      placeholder="검색어 입력" name="find" id="find" maxlength="90">
					    </td>
						<td align="center">
						  <input type="submit" class="btn btn-primary" value="검색">
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
 
 <c:if test="${membercount > 0 and fcount == 0}">
   
   <ul class="pagination pagination-sm" >
	   <!-- 1 페이지 -->
	   <li class="page-item">
		  <a class="page-link" href="./MemberList.do?page=1" style="text-decoration:none; color:#000""> 
  			 << 
     	  </a>
       </li>

	    <!-- 이전 블럭 -->
		<c:if test="${startPage > 10 }">
		<li class="page-item">
 		   <a class="page-link" style="color:#000" href="./MemberList.do?page=${startPage - 10 }">
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
	      <a class="page-link" style="color:#000" href="./MemberList.do?page=${i}">
		     ${i}
	      </a>
	 	</li> 
		</c:if>
		</c:forEach>

		<!-- 다음 블럭으로 이동 -->
		<c:if test="${endPage < pageCount }">
  		<li class="page-item">
		  <a class="page-link" style="color:#000" href="./MemberList.do?page=${startPage + 10 }">
	                다음 
	      </a>
        </li>	
        </c:if>

		<!-- 마지막 페이지로 이동 -->
  		<li class="page-item">
  		  <a class="page-link"  href="./MemberList.do?page=${pageCount }" style="text-decoration:none; color:#000">
   		    >> 
          </a>
 	    </li>  
    </ul>
 </c:if>  
 
 <c:if test="${fcount > 0}">
   <ul class="pagination pagination-sm"" >
	  <!-- 1 페이지 -->
	  <li class="page-item">
		 <a class="page-link" href="./MemberList.do?page=1&sel=${sel}&find=${find}" style="text-decoration:none; color:#000;"> 
  			 << 
     	 </a>
      </li>

	  <!-- 이전 블럭 -->
	<c:if test="${startPage > 10 }">
	<li class="page-item">
 	   <a class="page-link" href="./MemberList.do?page=${startPage - 10 }&sel=${sel}&find=${find}" style="color:#000">
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
	   <a class="page-link" style="color:#000" href="./MemberList.do?page=${i}&sel=${sel}&find=${find}" >
		   ${i}
	   </a>
	</li> 
	</c:if>
	</c:forEach>

	<!-- 다음 블럭으로 이동 -->
	<c:if test="${endPage < pageCount }">
  	<li class="page-item">
	   <a class="page-link" style="color:#000" href="./MemberList.do?page=${startPage + 10 }&sel=${sel}&find=${find}">
	            다음 
	   </a>
     </li>	
     </c:if>

	 <!-- 마지막 페이지로 이동 -->
  	 <li class="page-item">
  		<a class="page-link" href="./MemberList.do?page=${pageCount }&sel=${sel}&find=${find}" style="text-decoration:none; color:#000;">
   		   >> 
        </a>
 	  </li>  
    </ul>    
   </c:if>
 
 
 
  </div>
  </div>
</center>


</body>
</html>
