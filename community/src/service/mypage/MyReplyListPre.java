package service.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MypageDAO;
import dto.BoardDTO;
import dto.ReplyDTO;
import service.Action;
import service.ActionForward;

public class MyReplyListPre implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyReplyListPre");
		
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
		int myreplycount = dao.getReplyCount(id);
		System.out.println("myreplycount:"+myreplycount);
		
		List<ReplyDTO> myreplylist = dao.getReplyList(id, startRow, endRow);
		System.out.println("myreplylist:"+myreplylist);
		
		List<BoardDTO> mywritinglist = dao.getWritingList(id, startRow, endRow);
		System.out.println("mywritinglist");
		
		// 총 페이지
		int pageCount = myreplycount/limit +((myreplycount%10==10)? 0:1);
				
		int startPage = ((page-1)/10) * limit + 1; 
		int endPage = startPage	+ 10 - 1;
				
		if(endPage > pageCount) endPage = pageCount;
				
		request.setAttribute("page", page );
		request.setAttribute("myreplycount",myreplycount );
		request.setAttribute("myreplylist",myreplylist );
		request.setAttribute("mywritinglist",mywritinglist );
		request.setAttribute("pageCount",pageCount );
		request.setAttribute("startPage",startPage );
		request.setAttribute("endPage",endPage );
				
		
		
		ActionForward forward = new ActionForward();
          forward.setRedirect(false);
          forward.setPath("./mypage/myreplylist.jsp");
		return forward;
	}

}
