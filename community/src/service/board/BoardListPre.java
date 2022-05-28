package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardListDTO;
import service.Action;
import service.ActionForward;

public class BoardListPre implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardListPre");
		
		BoardDAO dao = BoardDAO.getInstance();
		String bl_category = request.getParameter("bl_category");
		
		List<BoardListDTO> bllist = dao.getBoardList(bl_category);
		System.out.println("bllist:"+ bllist);
		

		request.setAttribute("bllist", bllist);
		request.setAttribute("bl_category", bl_category);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);    // dispatcher 방식으로 포워딩
		forward.setPath("./board/board_list_pre.jsp");
		
		return forward;
	}

}
