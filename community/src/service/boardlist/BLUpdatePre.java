package service.boardlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardListDAO;
import dto.BoardListDTO;
import service.Action;
import service.ActionForward;

public class BLUpdatePre implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BLUpdatePre");
		int bl_code = Integer.parseInt(request.getParameter("bl_code"));
		String page = request.getParameter("page");
		
		BoardListDAO dao = BoardListDAO.getInstance();
		BoardListDTO bl = dao.getDetail(bl_code);
		System.out.println("bl:"+bl);
		
		String content = bl.getBl_exp();
		
		request.setAttribute("content", content);
		request.setAttribute("bl", bl);
		request.setAttribute("page", page);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./boardlist/bl_update.jsp");
		return forward;
	}

}
