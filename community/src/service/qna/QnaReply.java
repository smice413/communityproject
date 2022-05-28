package service.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QnaDAO;
import dto.QnaDTO;
import service.Action;
import service.ActionForward;

public class QnaReply implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("QnaReply");
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		int qna_ref = Integer.parseInt(request.getParameter("qna_ref"));
		int qna_lev = Integer.parseInt(request.getParameter("qna_lev"));
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));
		String page = request.getParameter("page");
		String id = (String)session.getAttribute("id");
		
	    System.out.println("id:"+id);
		
		QnaDTO qna = new QnaDTO();
		qna.setQna_num(qna_num);
		qna.setQna_ref(qna_ref);
	    qna.setQna_lev(qna_lev);
	    qna.setQna_seq(qna_seq);
	    qna.setId(id);
	    qna.setQna_subject(request.getParameter("qna_subject"));
	    qna.setQna_content(request.getParameter("qna_content"));
	    qna.setQna_file(request.getParameter("qna_file"));
	  
	    QnaDAO dao = QnaDAO.getInstance();
	    int result = dao.qnaReply(qna);
	    if(result == 1) System.out.println("답글 작성 성공");
		
	    if(id.equals("admin")) {
			ActionForward forward = new ActionForward();
	        forward.setRedirect(false);
			forward.setPath("/QnaList.do?page="+page);
        
			return forward;
	    }else {
	    	ActionForward forward = new ActionForward();
	        forward.setRedirect(false);
			forward.setPath("/MyQnaListPre.do?page="+page);
        
			return forward;
	    }
	    
	}

}
