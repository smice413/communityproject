package service.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MypageDAO;
import dao.QnaDAO;
import dto.BoardDTO;
import dto.QnaDTO;
import service.Action;
import service.ActionForward;

public class MyQnaListPre implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyQnaListPre");
		
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
		int myqnacount = dao.getQnaCount(id);
		System.out.println("myqnacount:"+myqnacount);
		
		List<QnaDTO> myqnalist = dao.getQnaList(id,startRow,endRow);
		System.out.println("myqnalist:"+myqnalist);
		
	
		
		// 총 페이지
		int pageCount = myqnacount/limit +((myqnacount%10==10)? 0:1);
		
		int startPage = ((page-1)/10) * limit + 1; 
		int endPage = startPage	+ 10 - 1;
		
		if(endPage > pageCount) endPage = pageCount;
		
		// 공유설정
		request.setAttribute("page", page );
		request.setAttribute("myqnacount",myqnacount );
		request.setAttribute("myqnalist",myqnalist );
		request.setAttribute("pageCount",pageCount );
		request.setAttribute("startPage",startPage );
		request.setAttribute("endPage",endPage );
		
		
		
		ActionForward forward = new ActionForward();
		 forward.setRedirect(false);
		 forward.setPath("./mypage/myqnalist.jsp");
		return forward;
	}

}
