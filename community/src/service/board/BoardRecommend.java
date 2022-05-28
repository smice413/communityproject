package service.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dto.BoardDTO;
import service.Action;
import service.ActionForward;

public class BoardRecommend implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardRecommend");

		//알림창을 위한 인코딩/out객체 생성
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// 리스트에서 넘어오는 파라미터
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		int recomcheck = Integer.parseInt(request.getParameter("recomcheck"));
		System.out.println("num:"+num);
		System.out.println("page:"+page);
		System.out.println("recomcheck:"+recomcheck);
		
		// 세션 아이디를 받아옴
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("id:"+id);

		// 로그인 여부 확인
		if(id == null) {			//로그인 된 경우
			out.println("<script>");
			out.println("alert('로그인 이후 추천이 가능합니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}else {						//로그인 된 경우
			BoardDAO dao = BoardDAO.getInstance();
			int result = dao.recomUpdate(id, num, recomcheck);		//추천수 추가
			
			if(result == 1) System.out.println("추천수 변경 성공");
		}

		// 공유 설정
		request.setAttribute("num", num);
		request.setAttribute("page", page);	
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);  		// dispatcher 방식으로 포워딩
		forward.setPath("/BoardDetail.do");
		return forward;
	}

}
