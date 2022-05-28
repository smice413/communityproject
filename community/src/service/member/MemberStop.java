package service.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class MemberStop implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberStop");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		String passwd = request.getParameter("passwd");
		
		ActionForward forward = new ActionForward();
		
		if(!id.equals("admin")) {
			MemberDAO dao = MemberDAO.getInstance();
			MemberDTO old = dao.getDetail(id);	// 1명의 상세정보 구하기
			
			System.out.println("oldpasswd: " + old.getPasswd());
			
			// 비번 비교
			if(old.getPasswd().equals(passwd)) {	//  비번 일치시
				int result = dao.delete(id);		// delete SQL문 실행
				if(result == 1) System.out.println("회원탈퇴 성공");
				
				session.invalidate();               // 세션 삭제
				
			}else {		// 비번 불일치시
				out.println("<script>");
				out.println("alert('비번이 일치하지 않습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				
				return null;
			}
			
			forward.setRedirect(false);
			forward.setPath("/Main.do");
			
			
		}else if(id.equals("admin")) {
			MemberDAO dao = new MemberDAO();
			int result = dao.delete(request.getParameter("id"));
	
			forward.setRedirect(false);
			forward.setPath("/MemberList.do");

		}
		
		return forward;
	}

}
