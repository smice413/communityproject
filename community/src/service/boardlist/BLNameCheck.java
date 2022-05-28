package service.boardlist;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardListDAO;
import service.Action;
import service.ActionForward;

public class BLNameCheck implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BLNameCheck");
		
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		String bl_name = request.getParameter("bl_name");
		System.out.println("bl_name:"+bl_name);
		
		BoardListDAO dao = BoardListDAO.getInstance();
		int result = dao.bl_namecheck(bl_name);		// 게시판 제목 중복 검사	
		System.out.println("result:" + result);	 // 1 : 중복 제목
		                                         //-1 : 사용 가능한 제목
		// 웹브라우저에 출력되는 값이 callback 함수로 리턴된다.
		out.println(result);
		
		return null;
	}
		
}
