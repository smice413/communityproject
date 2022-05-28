package service.board;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardListDAO;
import dao.BoardDAO;
import dao.ReplyDAO;
import dto.BoardListDTO;
import dto.BoardDTO;
import dto.ReplyDTO;
import service.Action;
import service.ActionForward;

public class BoardDetail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardDetail");

		// 리스트에서 넘어오는 파라미터
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		System.out.println("num:"+num);
		System.out.println("page:"+page);
		
		// 세션 아이디를 받아옴
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("id:"+id);
		
		// 댓글 정렬 (미완성)		
//		String replysort = request.getParameter("replysort");
//		System.out.println("replysort:"+replysort);


		//DAO 객체생성
		BoardDAO dao = BoardDAO.getInstance();		
		BoardListDAO bldao = BoardListDAO.getInstance();
		ReplyDAO redao = ReplyDAO.getInstance();
		
		//조회수를 증가시키고 상세정보를 구하는 부분
		dao.readcountUpdate(num);						// 조회수 증가
		BoardDTO board = dao.getDetail(num); 			// 상세정보 구하기
		String content = board.getContent().replace("\n","<br>"); // 글내용에서 줄바꿈
		System.out.println("board:"+board);

		//게시판 정보 구하기
		BoardListDTO bl = bldao.getDetail(board.getBl_code());
		System.out.println("bl:"+bl);

		//추천수와 추천여부를 구하는 부분
		int recomcount = dao.getRecomCount(num);		//추천수 구하기
		System.out.println("recomcount:"+recomcount);
		int recomcheck = dao.recomCheck(id,num);		//추천여부 조회
		System.out.println("recomcheck:"+recomcheck);

		//댓글을 불러옴(댓글 수, 댓글 리스트)
		int limit = 10;			// 한 페이지에 출력할 데이터 갯수
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("limit:"+limit);
		int startRow = 1;
		int endRow = limit;
		
		int replycount = redao.getReplyCount(num);
		List<ReplyDTO> replylist = redao.getReplyList(startRow, endRow, num);
		System.out.println("replycount:"+replycount);
		System.out.println("replylist:"+replylist);
		
		// 공유 설정
		request.setAttribute("num", num);
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("board", board);
		request.setAttribute("bl", bl);
		request.setAttribute("content", content);
		request.setAttribute("recomcount", recomcount);
		request.setAttribute("recomcheck", recomcheck);
		request.setAttribute("replycount", replycount);
		request.setAttribute("replylist", replylist);
//		request.setAttribute("replysort", replysort);

		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);  		// dispatcher 방식으로 포워딩
		forward.setPath("./board/board_detail.jsp");
		return forward;
	}

}
