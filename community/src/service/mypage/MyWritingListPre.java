package service.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.BoardDAO;
import dao.MemberDAO;
import dao.MypageDAO;
import dto.BoardDTO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class MyWritingListPre implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyWritingListPre");
		
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
		int mywritingcount = dao.getWritingCount(id);
		System.out.println("mywritingcount:"+mywritingcount);
		
		List<BoardDTO> mywritinglist = dao.getWritingList(id, startRow, endRow);
		System.out.println("mywritinglist:"+mywritinglist);
		


		// 총 페이지
		int pageCount = mywritingcount/limit +((mywritingcount%10==10)? 0:1);
		
		int startPage = ((page-1)/10) * limit + 1; 
		int endPage = startPage	+ 10 - 1;
		
		if(endPage > pageCount) endPage = pageCount;
		
		request.setAttribute("page", page );
		request.setAttribute("mywritingcount",mywritingcount );
		request.setAttribute("mywritinglist",mywritinglist );
		request.setAttribute("pageCount",pageCount );
		request.setAttribute("startPage",startPage );
		request.setAttribute("endPage",endPage );
		
		
		ActionForward forward = new ActionForward();
		  forward.setRedirect(false);
		  forward.setPath("./mypage/mywritinglist.jsp");
		
		return forward;
	}
 
}
