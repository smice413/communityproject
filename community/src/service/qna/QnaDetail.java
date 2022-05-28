package service.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QnaDAO;
import dto.QnaDTO;
import service.Action;
import service.ActionForward;

public class QnaDetail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("QnaDetail");
		
		HttpSession session = request.getSession();
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String page = request.getParameter("page");
		String id = (String)session.getAttribute("id");
		
		QnaDAO dao = QnaDAO.getInstance();    
		QnaDTO qna = dao.getDetail(qna_num);  // 상세정보 구하기
		System.out.println("qna:"+ qna);
		System.out.println("content:"+ qna.getQna_content());
		
		if(qna.getQna_content() != null) {
			// 글내용에서 줄바꿈
			String content = qna.getQna_content().replace("\n","<br>");
			request.setAttribute("content", content);
		}
		
		// 공유설정
		request.setAttribute("qna", qna);
		request.setAttribute("page", page);
		request.setAttribute("id", id);
			
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);      // dispatcher 방식으로 포워딩
	    forward.setPath("./qna/qna_view.jsp");    
		return forward;
	 
	}
}	
