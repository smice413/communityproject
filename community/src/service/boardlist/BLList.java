package service.boardlist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardListDAO;
import dto.BoardListDTO;
import service.Action;
import service.ActionForward;

public class BLList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BLList");
		
		int page = 1; //현재 페이지 번호
		int limit = 10; // 한 페이지에 출력할 데이터 갯수
		
		
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int startRow = (page -1) * limit + 1;
		int endRow = page * limit;
		
		// 총 데이터 갯수
		BoardListDAO dao = BoardListDAO.getInstance();
		int blcount = dao.getCount();
		System.out.println("blcount:"+ blcount);
		
		
		List<BoardListDTO> bllist = dao.getList(startRow, endRow);
		System.out.println("bllist:"+ bllist);
		
		
		// 총 페이지(개설승인 된것)
		int pageCount = blcount/limit + ((blcount%limit) == 0 ? 0 : 1);
		
		int startPage = ((page-1)/10) * limit + 1;
		int endPage = startPage + 10 - 1;
		
		if(endPage > pageCount) endPage = pageCount;
		
		

			
				
		request.setAttribute("page", page);
		request.setAttribute("blcount", blcount);
		request.setAttribute("bllist", bllist);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./boardlist/bl_list.jsp");
		return forward;
	}

}
