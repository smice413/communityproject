package service.boardlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardListDAO;
import dto.BoardListDTO;
import service.Action;
import service.ActionForward;

public class BLAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BLAdd");
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String id =(String) session.getAttribute("id");
		
		//입력받은 데이터 DTO 저장
		BoardListDTO bl = new BoardListDTO();
		bl.setId(id);
		bl.setBl_name(request.getParameter("bl_name"));
		bl.setBl_private(request.getParameter("bl_private"));
		bl.setBl_passwd(request.getParameter("bl_passwd"));
		bl.setBl_exp(request.getParameter("bl_exp"));
		bl.setBl_category(request.getParameter("bl_category"));
	
		System.out.println("id: "+id);
		
		BoardListDAO dao = BoardListDAO.getInstance();
		int result = dao.insert(bl);
		if(result == 1) System.out.println("개설신청입력 성공");
		  
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/Main.do");
		return forward;
	}

}
