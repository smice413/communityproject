package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;
import service.Action;
import service.ActionForward;

public class Main implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Main");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardDTO> pbllist = dao.getPopBoardList();
		System.out.println("pbllist:" + pbllist);
		
		request.setAttribute("pbllist", pbllist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);    // dispatcher 방식으로 포워딩
		forward.setPath("./main.jsp");
		
		return forward;
	}

}
