package service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dto.BoardDTO;
import service.Action;
import service.ActionForward;

public class BoardUpdatePre implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardUpdatePre");
		
		// 리스트에서 넘어오는 파라미터
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("id:"+id);
		System.out.println("num:"+num);
		System.out.println("page:"+page);
		
		//조회수를 증가시키고 상세정보를 구하는 부분
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO board = dao.getDetail(num); 			// 상세정보 구하기
		System.out.println("board:"+board);
		
		// 공유 설정
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);  		// dispatcher 방식으로 포워딩
		forward.setPath("/board/board_update.jsp");
		return forward;
	}

}
