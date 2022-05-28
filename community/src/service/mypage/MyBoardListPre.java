package service.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MypageDAO;
import dto.BoardListDTO;
import service.Action;
import service.ActionForward;

public class MyBoardListPre implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyBoardListPre");
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		
		MypageDAO dao = MypageDAO.getInstance();
		int myboardcount = dao.getBoardCount(id);
		System.out.println("myboardcount:"+myboardcount);
		
		List<BoardListDTO> myboardlist = dao.getBoardList(id, startRow, endRow);
		System.out.println("myboardlist:"+myboardlist);
		
		// 총 페이지
		int pageCount = myboardcount/limit + ((myboardcount%10==10)? 0:1);
		
		int startPage = ((page-1)/10) * limit + 1;
		int endPage = startPage + 10 - 1;
		
		if(endPage > pageCount) endPage = pageCount;
		
		// 공유설정
		request.setAttribute("page", page);
		request.setAttribute("myboardcount", myboardcount);
		request.setAttribute("myboardlist", myboardlist);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		ActionForward forward = new ActionForward();
		  forward.setRedirect(false);
		  forward.setPath("./mypage/myboardlist.jsp");
		return forward;
	}

}
