package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.board.BoardDelete;
import service.board.BoardDetail;
import service.board.BoardList;
import service.board.BoardListPre;
import service.board.BoardRecommend;
import service.board.BoardReply;
import service.board.BoardReplyDelete;
import service.board.BoardUpdate;
import service.board.BoardUpdatePre;
import service.board.BoardWrite;
import service.Main;
import service.boardlist.BLAdd;
import service.boardlist.BLDelete;
import service.boardlist.BLList;
import service.boardlist.BLListN;
import service.boardlist.BLNameCheck;
import service.boardlist.BLRequest;
import service.boardlist.BLUpdate;
import service.boardlist.BLUpdatePre;
import service.member.IdSearch;
import service.member.Login;
import service.member.MemberList;
import service.member.MemberStop;
import service.member.PasswdSearch;
import service.member.Update;
import service.mypage.MyBoardListPre;
import service.mypage.MyProfilePre;
import service.mypage.MyQnaListPre;
import service.mypage.MyReplyListPre;
import service.mypage.MyWritingListPre;
import service.mypage.MypagePre;
import service.mypage.QnaAddPre;
import service.qna.QnaAdd;
import service.qna.QnaDelete;
import service.qna.QnaDetail;
import service.qna.QnaList;
import service.qna.QnaReply;
import service.qna.QnaReplyPre;
import service.qna.QnaUpdate;
import service.qna.QnaUpdatePre;


@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		System.out.println("requestURI: "+requestURI);
		System.out.println("contextPath: "+contextPath);
		System.out.println("command: "+command);
		
		Action action = null;
		ActionForward forward = null;
		
		
		// ## 메인 부분 
		
		
		// 메인 페이지
		if(command.equals("/Main.do")) {
			try {
				action = new Main();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		  
		  
		// ##문의글 부분  
		  
			
		// 문의글 작성폼
		}else if(command.equals("/QnaAddPre.do")) {
			try {
				action = new QnaAddPre();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
	    
	    // 문의글 작성
		}else if(command.equals("/QnaAdd.do")) {
			try {
				action = new QnaAdd();
				forward = action.execute(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		// 문의글 목록
		}else if(command.equals("/QnaList.do")) {
			try {
				action = new QnaList();
				forward = action.execute(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		// 문의글 작성 폼
		}else if(command.equals("/QnaForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);  
			forward.setPath("./qna/qna_write.jsp");
			
		// 상세페이지
		}else if(command.equals("/QnaDetail.do")) {
			try{
				action = new QnaDetail();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}	

		// 답글 폼
		}else if(command.equals("/QnaReplyPre.do")) {
			try {
				action = new QnaReplyPre();
				forward = action.execute(request, response);
			}catch(Exception e) {
			   e.printStackTrace();	
			}
			
		// 답글 작성	
		}else if(command.equals("/QnaReply.do")) {
			try {
				action = new QnaReply();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		// 수정 폼
		}else if(command.equals("/QnaUpdatePre.do")) {	
			try{
				action = new QnaUpdatePre();
				forward = action.execute(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 글 수정
		}else if(command.equals("/QnaUpdate.do")) {
			try {
				action = new QnaUpdate();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 문의글 삭제
		}else if(command.equals("/QnaDelete.do")) {
		try {
			action = new QnaDelete();
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	   // ##회원로그인/로그아웃/정보수정/탈퇴 부분 
		
	   // 회원 가입
		}else if(command.equals("/MemberInsert.do")) {
			try {
				action = new service.member.MemberInsert();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// ID중복 검사(ajax)	
		}else if(command.equals("/Idcheck.do")) {
			try {
				action = new service.member.Idcheck();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 회원가입 폼	
		}else if(command.equals("/MemberForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/member/memberform.jsp");
		
		
		// 로그인(회원인증)
		}else if(command.equals("/Login.do")) {
			try {
				action = new Login();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 로그인 폼	
		}else if(command.equals("/LoginForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/loginform.jsp");
			
		// 로그 아웃	
		}else if(command.equals("/Logout.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/logout.jsp");	
			
		// 회원정보 수정폼	
		}else if(command.equals("/UpdateMember.do")) {
			try {
				action = new service.member.UpdateMember();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 회원정보 수정	
		
		 }else if(command.equals("/Update.do")) { 
			 try { 
			   action = new Update(); 
			   forward= action.execute(request, response); 
			 }catch(Exception e) {
			   e.printStackTrace(); 
			}
			
		// 아이디 찾기 
		}else if(command.equals("/IdSearch.do")) {
			try {
				action = new IdSearch();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 아이디찾기폼 
		}else if(command.equals("/IdSearchForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/idsearch.jsp");
		
		// 비밀번호 찾기 
		}else if(command.equals("/PasswdSearch.do")) {
			try {
				action = new PasswdSearch();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
			//비밀번호찾기폼 
		}else if(command.equals("/PasswdSearchForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/passwdsearch.jsp");	
		
			// 회원 탈퇴	
		}else if(command.equals("/MemberStop.do")) {
			try {
				action = new MemberStop();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			//회원탈퇴 폼
		}else if(command.equals("/MemberStopForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/memberdelete.jsp");	
		
		
		
		
		
	// 마이페이지 부분 	
		
		
	// 마이페이지로 이동	
	}else if(command.equals("/MypagePre.do")) {
		try {
			action = new MypagePre();
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	// 회원정보보기
	}else if(command.equals("/MyProfile.do")) {
		try{
			action = new MyProfilePre();
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	// 내가 작성한 글 목록	
	}else if(command.equals("/MyWritingListPre.do")) {
		try{
			action = new MyWritingListPre();
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	// 내가 작성한 댓글 목록	
	}else if(command.equals("/MyReplyListPre.do")) {
		try {
			action = new MyReplyListPre();
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	// 나의 게시판 목록
	}else if(command.equals("/MyBoardListPre.do")) {
		try {
			action = new MyBoardListPre();
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	// 내가 작성한 문의글 목록
	}else if(command.equals("/MyQnaListPre.do")) {
		try {
			action = new MyQnaListPre();
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		// admin 페이지
		// 회원목록	
		}else if(command.equals("/MemberList.do")) {
			try{
			  action = new MemberList();
			   forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
	  // ##게시판 관련 (게시판, 추천, 댓글)
			
	  // 글작성 김창원
	   }	
	
		if(command.equals("/BoardWrite.do")) {
			try {
				action = new BoardWrite();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 글목록 리스트 출력 김창원
		}else if(command.equals("/BoardList.do")) {
			try {
				action = new BoardList();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
        //게시글 삭제
  		}else if(command.equals("/BoardDelete.do")) {
  	        try {
  	           action = new BoardDelete();
  	           forward = action.execute(request, response);
  	        }catch(Exception e) {
  	           e.printStackTrace();
  	        }  

		// 카테고리별 게시판목록- 손정은
		}else if(command.equals("/BoardListPre.do")) {
	        try {
	           action = new BoardListPre();
	           forward = action.execute(request, response);
	        }catch(Exception e) {
	           e.printStackTrace();
	        }
  	        
		// 상세보기 - 손정은
		}else if(command.equals("/BoardDetail.do")) {
	        try {
	           action = new BoardDetail();
	           forward = action.execute(request, response);
	        }catch(Exception e) {
	           e.printStackTrace();
	        }
	        
		// 추천여부조회 - 손정은
		}else if(command.equals("/BoardRecommend.do")) {
	        try {
	           action = new BoardRecommend();
	           forward = action.execute(request, response);
	        }catch(Exception e) {
	           e.printStackTrace();
	        }
		
		//댓글 입력 - 손정은
		}else if(command.equals("/BoardReply.do")) {
	        try {
	           action = new BoardReply();
	           forward = action.execute(request, response);
	        }catch(Exception e) {
	           e.printStackTrace();
	        }
	        
		//댓글 삭제 - 손정은
		}else if(command.equals("/BoardReplyDelete.do")) {
	        try {
	           action = new BoardReplyDelete();
	           forward = action.execute(request, response);
	        }catch(Exception e) {
	           e.printStackTrace();
	        }	
	        
		//게시글 수정 폼 - 손정은
		}else if(command.equals("/BoardUpdatePre.do")) {
	        try {
	           action = new BoardUpdatePre();
	           forward = action.execute(request, response);
	        }catch(Exception e) {
	           e.printStackTrace();
	        }	       
		//게시글 수정 - 손정은
		}else if(command.equals("/BoardUpdate.do")) {
	        try {
	           action = new BoardUpdate();
	           forward = action.execute(request, response);
	        }catch(Exception e) {
	           e.printStackTrace();
	        }  
	        
	        
	    //##게시판 생성/수정/삭제부분    
		//관리자 페이지 이동
		}else if(command.equals("/MasterPage.do")) {
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("./mypage/admin.jsp");
				
		}else if(command.equals("/BLRequestPre.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);		
			forward.setPath("./boardlist/bl_request.jsp");		
					
		// 입력폼 bl_request.jsp로 부터 옴
		}else if(command.equals("/BLAdd.do")) {
					try {
						action = new BLAdd();
						forward = action.execute(request, response);
						
	
					}catch(Exception e) {
						e.printStackTrace();
					}
				
		// 글목록처리와 총 데이터 갯수 구함
		}else if(command.equals("/BLList.do")) {
					try {
						action = new BLList();
						forward = action.execute(request, response);
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
		//	목록삭제
		}else if(command.equals("/BLDelete.do")) {
					try {
						action = new BLDelete();
						forward = action.execute(request, response);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
		// 게시판 개설 요청 리스트 	
		}else if(command.equals("/BLListN.do")) {
					try {
						action = new BLListN();
						forward = action.execute(request, response);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
		// 게시판 개설 요청 처리	
		}else if(command.equals("/BLRequest.do")) {
					try {
						action = new BLRequest();
						forward = action.execute(request, response);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
		// 게시판 수정폼으로 연결	
		}else if(command.equals("/BLUpdatePre.do")) {
					try {
						action = new BLUpdatePre();
						forward = action.execute(request, response);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
		// 게시판 수정 처리	
		}else if(command.equals("/BLUpdate.do")) {
					try {
						action = new BLUpdate();
						forward = action.execute(request, response);
					}catch(Exception e) {
						e.printStackTrace();
					}
					// 게시판 이름 중복검사
	     }else if(command.equals("/BLNameCheck.do")) {
	    	 try {
					action = new BLNameCheck();
					forward = action.execute(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
	     }

	
		
		
		// 포워딩 처리
		if(forward != null) {
			if(forward.isRedirect()) {	// redirect 방식
				response.sendRedirect(forward.getPath());
			}else {						// dispatcher 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		
		
	
		
		}
		
		
	}	// doProcess() end


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		
		doProcess(request, response);
	}

}
