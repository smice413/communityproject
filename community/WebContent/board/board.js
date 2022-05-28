$(document).ready(function(){
			$("form").submit(function(){
				if($("#head").val()==""){
					alert("말머리를 선택 하세요?");
					$("#head").focus();
					return false;
				}
				if($("#star").val()==""){
					alert("별점을 선택 하세요?");
					$("#star").focus();
					return false;
				}
				if($("#fix").val()==""){
					$("#fix").val("N");
					return false;
				}
				if($("#passwd").val()==""){
					alert("비밀번호를  입력 하세요?");
					$("#passwd").focus();
					return false;
				}
				if($("#subject").val()==""){
					alert("글제목을 입력 하세요?");
					$("#subject").focus();
					return false;
				}
				if($("#content").val()==""){
					alert("내용을 입력 하세요?");
					$("#content").focus();
					return false;
				}
				if($("#reply_p").val()==""){
					alert("댓글을 입력해 주세요");
					$("#content").focus();
					return false;
				}
				if($("#reply_c").val()==""){
					alert("댓글을 입력해 주세요");
					$("#content").focus();
					return false;
				}
			});		
});	