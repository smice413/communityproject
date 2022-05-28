$(document).ready(function(){
	$("form").submit(function(){
		if($("#qna_subject").val()==""){
			alert("제목을 입력 하세요.");
			$("#qna_subject").focus();
			return false;
		}
		if($("#qna_content").val()==""){
			alert("내용을 입력 하세요.");
			$("#qna_content").focus();
			return false;
		}	
		if($("#pass").val()==""){
			alert("비밀번호를 입력하세요.");
			$("#pass").focus();
			return false;
		}
	});			 
	
	
});