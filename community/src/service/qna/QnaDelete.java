package service.qna;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QnaDAO;
import dto.QnaDTO;
import service.Action;
import service.ActionForward;

public class QnaDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("QnaDelete");

		  request.setCharacterEncoding("utf-8");
		  response.setContentType("text/html; charset=utf-8");
		  HttpSession session = request.getSession();
		  
		  String id = (String)session.getAttribute("id");
		  int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		  String page = request.getParameter("page");
		  String path = request.getRealPath("qnaupload");
		  System.out.println("path:"+path);
		
		  QnaDAO dao = QnaDAO.getInstance();
		  QnaDTO qna = dao.getDetail(qna_num);     // 상세정보 구하기
		  
		  int result = dao.delete(qna_num);
		  if(result != 0) System.out.println("글삭제 (답글 포함) 성공");
		  if(result == 0) System.out.println("글 삭제 (원문만) 성공");
		  
		// 첨부파일이 있을 경우에 첨부파일 삭제
		  if(qna.getQna_file() != null) {
			  File file = new File(path);
			  file.mkdir();
			  
			  // boardupload 디렉토리의 모든 첨부파일 구해오기
			  File[] f = file.listFiles();
			  for(int i=0; i<f.length; i++) {
				  if(f[i].getName().equals(qna.getQna_file())) {
					  f[i].delete();
				  }
			  }
		  }
		
		  System.out.println("result:"+result);
		 
		if(id.equals("admin")) {       // 관리자일 때
			ActionForward forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("/QnaList.do");
			return forward;	
		}else{                         // 일반회원일 때
			ActionForward forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("/MyQnaListPre.do");
			return forward;
		}
	}

}
