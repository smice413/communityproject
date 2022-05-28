<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header부분 -->
<jsp:include page="/header.jsp" /> 



<html>
<head>
	<title>게시판 개설 신청</title>
	<meta charset="utf-8">

  
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script> 
  
  
<script>
	function check(n){
	  var result = n;
	  if(result){
		  $('#bl_passwd').val("").attr("disabled", true);
	  }
	}
	
	function check1(n){
		  var result = n;
		  if(result){
		  $('#bl_passwd').attr("disabled", false);
		  }
	}
	
	
	
	$(document).ready(function(){	
		
		
		// 게시판 이름 중복검사
		$("#bl_namecheck").click(function(){
			if($("#bl_name").val()==""){
				alert("게시판 이름을 입력하세요");
				$("#bl_name").focus();
				return false;
			}else{
				var bl_name = $("#bl_name").val();	
				
				$.ajax({
					type:"post",
					url:"/BLNameCheck.do",
					data:{"bl_name":bl_name},
					datatype:"text",
					success:function(data){
					
			        var result = data;
				//	alert(result);
					
						
					if(result==1){	// 중복 ID
						$("#mybl_name").text("중복되는 게시판 이름 입니다.");
						$("#bl_name").val("").focus();
						$("#submit").attr("disabled", true);
					}else{			// 사용 가능한 ID
						$("#mybl_name").text("사용 가능한 게시판 이름 입니다.");
						$("#submit").attr("disabled", false);
							
					}					
				   }
				});			
			}
		});
		
	});	
	function button(){
		$("#submit").attr("disabled", true);
		alert("게시판 이름 중복 검사를 해주세요");
		
	} 
	
	
		 
	function checkcheck(){
		
			/* if($("#bl_name").val()==""){
				alert("게시판 이름을 입력 하세요");
				$("#bl_name").focus();
				return false;
			} */
			if($("#bl_category").val()==""){
				alert("카테고리를 선택하세요")
				return false;
			}
			
			if($("#Y").is(":checked")==false &&
			  $("#N").is(":checked")==false){
			  alert("프라이빗 여부를 선택하세요");
			  return false;
			}
			if($("#Y").is(":checked")==true){
				if($("#bl_passwd").val()==""){
					alert("비밀번호을 입력 하세요?");
					$("#bl_passwd").focus();
					return false;
				}
			}
			if($("#bl_exp").val()==""){
				alert("내용을 입력 하세요?");
				$("#bl_exp").focus();
				return false;
			}
			if(confirm("개설신청하시겠습니까?")){
				alert("신청이 완료되었습니다");
//				$("#form").submit();
 				<%-- location.href="<%=request.getContextPath()%>/Main.do"; --%>
			}
			
	}	

</script>
	
</head>
<body>
<br><br>
<div class="container">
<form id="form" name="form" action="<%=request.getContextPath() %>/BLAdd.do" method="post" onSubmit="return checkcheck() ">
<input type="hidden" id="id" name="id" value="${sessionScope.id}">
<input type="hidden" id="bl_activate" name="bl_activate" value="N">
<table align=center class="table table-striped">
	<tr align="center" valign="middle">
		<td colspan="5" style="font-size:20px;">게시판 개설 신청</td>
		
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:15; font-weight: bold;" height="16">
			<div align="center">카테고리</div>
		</td>
		
	
	<td>
			<select name="bl_category" id="bl_category">
				<option value="">선택</option>
				<option value="후기">후기</option>
				<option value="요리">요리</option>
				<option value="영화">영화</option>
				<option value="쇼핑">쇼핑</option>
				<option value="중고">중고</option>
			</select>
			
		</td>
		</tr>
	<tr>
		<td style="font-family:돋음; font-size:15; font-weight: bold;" height="16">
			<div align="center">게시판 이름</div>
		</td>
		<td>
			<input name="bl_name" id="bl_name"  type="text" size="50" maxlength="100" 
				class="form-control" onClick="button()"/>
			<input type=button class="btn btn-danger" value="게시판 이름 중복검사" id="bl_namecheck" style="margin-top: 10px;" >
			<div id="mybl_name"></div>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:15; font-weight: bold;" height="16">
			<div align="center">프라이빗여부</div>
		</td>
		<td>
			<input name="bl_private" id="Y" type="radio" size="10" maxlength="10" 
				value="Y" onClick = "check1('Y')"/>Y
			<input name="bl_private" id="N" type="radio" size="10" maxlength="10" 
				value="N" onClick = "check('N')"/>N
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:15; font-weight: bold;" height="16">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input name="bl_passwd" id="bl_passwd" type="text" size="15" maxlength="15" 
				value="" class="form-control"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:15; font-weight: bold;">
			<div align="center">설명</div>
		</td>
		<td>
			<textarea name="bl_exp" id="bl_exp" cols="67" rows="15" class="form-control"></textarea>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">			
			<input type="submit" class="btn btn-primary" value="등록" id="submit" disabled="disabled" >
			<input type="button" class="btn btn-light" value="취소" style="border-color: #E5E8F0;" onClick="location.href='<%=request.getContextPath()%>/Main.do' ">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>