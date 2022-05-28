package service.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QnaDAO;
import dto.QnaDTO;
import service.Action;
import service.ActionForward;

public class QnaUpdatePre implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("QnaUpdatePre");
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String page = request.getParameter("page");
		
		QnaDAO dao = QnaDAO.getInstance();
		QnaDTO qna = dao.getDetail(qna_num);  // 상세정보 구하기
		
		// 공유 설정
		request.setAttribute("qna", qna);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
          forward.setRedirect(false); 		
		  forward.setPath("./qna/qna_update.jsp");
		return forward;
	}

}
