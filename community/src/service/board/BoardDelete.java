package service.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import service.Action;
import service.ActionForward;

public class BoardDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardDelete");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 리스트에서 넘어오는 파라미터
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num:"+num);
		String page = request.getParameter("page");
		
		String path = request.getRealPath("boardupload");
		System.out.println("path:"+ path);
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.delete(num);
		
		if(result == 1) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("삭제 실패");
		}

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/BoardList.do");
		
		return forward;		
	}
}
