$(document).ready(function(){
	
			$("form").submit(function(){
				if($("#bl_name").val()==""){
					alert("게시판 이름을 입력 하세요");
					$("#bl_name").focus();
					return false;
				}
				if($("#bl_private").val()==""){
					alert("공개여부를 선택 하세요?");
					$("#bl_private").focus();
					return false;
				}
				if($("#bl_private").val()=="Y"){
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
			});	
			
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
						url:"./BLNameCheck.do",
						data:{"bl_name":bl_name},
						datatype:"text",
						success:function(data){
//							alert(data);
							
						if(data==1){	// 중복 ID
							$("#mybl_name").text("중복되는 게시판 이름 입니다.");
							$("#bl_name").val("").focus();
						}else{			// 사용 가능한 ID
							$("#mybl_name").text("사용 가능한 게시판 이름 입니다.");
							}					
						}
					});			
				}		
			});
});	