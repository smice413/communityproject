package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class UpdateMember implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UpdateMember");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = dao.getDetail(id);	// 1명의 상세정보 구하기
		System.out.println("수정폼:"+ member);
		

		
		// 공유 설정
		request.setAttribute("member", member);
	
		ActionForward forward = new ActionForward();
		
		
		forward.setRedirect(false);   		// dispatcher방식으로 포워딩
		forward.setPath("./member/memberupdate.jsp");
		
		return forward;
	}

}
