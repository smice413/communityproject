package service.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReplyDAO;
import service.Action;
import service.ActionForward;

public class BoardReplyDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardReplyDelete");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 상세페이지에서 넘어오는 파라미터
		String page = request.getParameter("page");
		int num = Integer.parseInt(request.getParameter("num"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int re_num = Integer.parseInt(request.getParameter("re_num"));
		int re_ref = Integer.parseInt(request.getParameter("re_ref"));
		int re_lev = Integer.parseInt(request.getParameter("re_lev"));
		int re_seq = Integer.parseInt(request.getParameter("re_seq"));

		System.out.println("page:"+page);
		System.out.println("num:"+num);
		System.out.println("limit:"+limit);
		System.out.println("re_num:"+re_num);
		System.out.println("re_ref:"+re_ref);
		System.out.println("re_lev:"+re_lev);
		System.out.println("re_seq:"+re_seq);

		//자식댓글이 있는지 확인하는 메소드 호출
		ReplyDAO redao = ReplyDAO.getInstance();
		int result = redao.childReplycheck(re_num, re_ref, re_lev, re_seq);
		System.out.println(result);
		if(result == 1) {		//딸린글이 있으면 비활성화 메소드 호출
			result = redao.deactivateReply(re_num);
			if (result == 1) System.out.println("비활성화 성공");   //비활성화 성공시 콘솔창 성공메시지 출력
		}else {					//딸린글이 없으면 삭제메소드 호출
			result = redao.deleteReply(re_num);
			if (result == 1) System.out.println("댓글삭제 성공");   //삭제성공시 콘솔창 성공메시지 출력
		}
		System.out.println("여기까지 성공");

		// 공유 설정
		request.setAttribute("num", num);
		request.setAttribute("page", page);	
		request.setAttribute("limit", limit);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);  		// dispatcher 방식으로 포워딩
		forward.setPath("/BoardDetail.do");
		return forward;
	}

}
