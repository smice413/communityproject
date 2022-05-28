package service.boardlist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardListDAO;
import dto.BoardListDTO;
import service.Action;
import service.ActionForward;

public class BLListN implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BLListN");
		
		int page = 1; //현재 페이지 번호
		int limit = 10; // 한 페이지에 출력할 데이터 갯수
		
		
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int startRow = (page -1) * limit + 1;
		int endRow = page * limit;
		
		
	
				BoardListDAO dao = BoardListDAO.getInstance();
				
				// 총 데이터 갯수(개설승인 안된 것만)
				int blcountN = dao.getCountN();
				System.out.println("blcountN:"+ blcountN);
				
				
				List<BoardListDTO> bllistN = dao.getListN(startRow, endRow);
				System.out.println("bllistN:"+ bllistN);
				
				// 총 페이지(개설승인 안된것)
				int pageCountN = blcountN/limit + ((blcountN%limit) == 0 ? 0 : 1);
				int startPage = ((page-1)/10) * limit + 1;
				int endPage = startPage + 10 - 1;
				if(endPage > pageCountN) endPage = pageCountN;
		
				request.setAttribute("page", page);
				request.setAttribute("blcountN", blcountN);
				request.setAttribute("bllistN", bllistN);
				request.setAttribute("pageCountN", pageCountN);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				
				
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./boardlist/bl_listN.jsp");
		return forward;
	}

}
