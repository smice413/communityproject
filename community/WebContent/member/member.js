$(document).ready(function(){	
	
	// ID 중복검사
	$("#idcheck").click(function(){
		if($("#id").val()==""){
			alert("ID를 입력하세요");
			$("#id").focus();
			return false;
		}else{
			
			var id = $("#id").val();	
			
			$.ajax({
				type:"post",
				url:"/Idcheck.do",
				data:{"id":id},
				datatype:"text",
				success:function(data){
//					alert(data);
					
					if(data==1){	// 중복 ID
						$("#myid").text("중복ID");
						$("#id").val("").focus();
						$("#submit").attr("disabled", true);
					}else{			// 사용 가능한 ID
						$("#myid").text("사용 가능한 ID");
						$("#submit").attr("disabled", false);
						$("#passwd").focus();
					}					
				}
			});			
		}		
	});
	
	
	/*$("#id").keyup(function(){
		var id = $("#id").val();
		
		$.ajax({
			type:"post",
			url:"idcheck1.jsp",
			data:{"id":id},
			datatype:"text",
			success:function(data){
//				alert(data);
				
				if(data==1){	// 중복 ID
					$("#myid").text("중복ID");
//					$("#id").val("").focus();
				}else{			// 사용 가능한 ID
					$("#myid").text("사용 가능한 ID");
//					$("#passwd").focus();
				}					
			}
		});			
	});*/
	
	
	

	
	
	// 도메인 선택
	$("#email").change(function(){
		if($("#email").val() == ""){	// 직접 입력 선택	
//			$("#domain").attr("readonly", false);
			$("#domain").removeAttr("readonly");
			$("#domain").val("").focus();			
		}else{							// 도메인명 선택
			$("#domain").val($("#email").val());
		    $("#domain").attr("readonly","readonly");
		}
	});
	
	
	// 유효성 검사
	$("form").submit(function(){
		
		if($("#id").val() == ""){
			alert("ID를 입력하세요");
			$("#id").focus();
			return false;
		}		
		if($("#passwd").val()==""){
			alert("비밀번호를 입력하세요");
			$("#passwd").focus();
			return false;
		}		
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		
		if($("#phone1").val()==""){
			alert("핸드폰 번호 앞자리를 선택하세요");			
			return false;
		}
		if($("#phone2").val()==""){
			alert("핸드폰 번호 중간자리를 입력하세요");
			$("#phone2").focus();
			return false;
		}
		if(isNaN($("#phone2").val())){
			alert("숫자만 입력하세요");
			$("#phone2").val("").focus();
			return false;
		}
		if($("#phone3").val()==""){
			alert("핸드폰 번호 끝자리를 입력하세요");
			$("#phone3").focus();
			return false;
		}
		if(isNaN($("#phone3").val())){
			alert("숫자만 입력하세요");
			$("#phone3").val("").focus();
			return false;
		}
		if($("#mailid").val()==""){
			alert("EMail 주소를 입력하세요");
			$("#mailid").focus();
			return false;
		}
		if($("#domain").val()==""){
			alert("도메인을 입력하세요");
			$("#domain").focus();
			return false;
		}
		
	}); // submit() end		
	
});  // ready() end