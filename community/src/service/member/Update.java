package service.member;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.MemberDAO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class Update implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Update");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setPhone1(request.getParameter("phone1"));
		member.setPhone2(request.getParameter("phone2"));
		member.setPhone3(request.getParameter("phone3"));
		member.setMailid(request.getParameter("mailid"));
		member.setDomain(request.getParameter("domain"));
		member.setMember_activate(request.getParameter("member_activate"));
		member.setRegdate(Timestamp.valueOf(request.getParameter("regdate")));
	
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO old = dao.getDetail(member.getId()); // 1명 상세정보 구하기
		
		// 비번 비교
		if(old.getPasswd().equals(member.getPasswd())) { // 비번 일치시
			int result = dao.update(member);	// update SQL문 실행
			if(result == 1) System.out.println("회원 수정 성공");
			
			request.setAttribute("member", member);
			
		}else {	// 비번 불일치시
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./mypage/myprofile.jsp");
		
		return forward;
	}

}
