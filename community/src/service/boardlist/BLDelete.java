package service.boardlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardListDAO;
import service.Action;
import service.ActionForward;

public class BLDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BLDelete");
		
		int bl_code= Integer.parseInt(request.getParameter("bl_code"));
		BoardListDAO dao = BoardListDAO.getInstance();
		int result = dao.delete(bl_code);
		if(result == 1) {
			System.out.println("삭제 성공");
			
		}else {
			System.out.println("삭제 실패");
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/BLList.do");
		
		return forward;
	}

}
