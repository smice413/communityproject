package service.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class MemberInsert implements Action {

	@Override
	public ActionForward execute(HttpServletRequest requst, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberInsert");

		
		requst.setCharacterEncoding("utf-8");

		MemberDTO member = new MemberDTO();
		member.setId(requst.getParameter("id"));
		member.setPasswd(requst.getParameter("passwd"));
		member.setName(requst.getParameter("name"));
		member.setPhone1(requst.getParameter("phone1"));
		member.setPhone2(requst.getParameter("phone2"));
		member.setPhone3(requst.getParameter("phone3"));
		member.setMailid(requst.getParameter("mailid"));
		member.setDomain(requst.getParameter("domain"));
		

		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.insert(member); // 회원 가입
		if (result == 1) {
			System.out.println(" wow 0o0 welcome! 가입을 환영해요! ");
			
			
			
		}
		

		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // dispatcher 방식 포워딩
		forward.setPath("./member/loginform.jsp"); // 포워딩할 파일명

		return forward;
	}

}
