package service.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class IdSearch implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("idsearch good");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
	   String name = request.getParameter("name");
	   String mailid = request.getParameter("mailid");
	   String domain = request.getParameter("domain");
	   
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = dao.idsearch(name, mailid, domain);
		
		if(member.getId() == null) {
			out.print("<script>");
			out.print("alert('잘못된 정보 입니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		// 공유설정
		request.setAttribute("member",member);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/member/idsearchAfter.jsp");
		
		return forward;
	}

}
