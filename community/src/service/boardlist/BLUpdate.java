package service.boardlist;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardListDAO;
import dao.MemberDAO;
import dto.BoardListDTO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class BLUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BLUpdate");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		
		String id = (String) session.getAttribute("id");
		System.out.println("id:"+id);
		int bl_code = Integer.parseInt(request.getParameter("bl_code"));
		System.out.println("bl_code:"+bl_code);
		String passwd = (String) request.getParameter("passwd"); 
		System.out.println("passwd:"+passwd);
//		String page = request.getParameter("page");
		
		BoardListDTO bl = new BoardListDTO();
		bl.setBl_code(bl_code);
		bl.setBl_name(request.getParameter("bl_name"));
		bl.setBl_private(request.getParameter("bl_private"));
		bl.setBl_exp(request.getParameter("bl_exp"));
		bl.setBl_category(request.getParameter("bl_category"));
		// 회원 비밀번호 비교 
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO old = dao.getDetail(id); // 나중에 바꿔야함////////////////////////////////////////////////////////////
		if(old.getPasswd().equals(passwd)) {	// 비번 일치시
			BoardListDAO dao1 = BoardListDAO.getInstance();
			int result = dao1.update(bl);	//update SQL문 실행
			if(result == 1) System.out.println("글수정 성공");
			
		}else {		//비번 불일치시
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}	
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/BoardList.do"); //////////////////////////////////////./board/board_list.jsp이걸로 바꿔
		return forward;
	}

 }

