package service.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class MyProfilePre implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyProfilePre");
		
		HttpSession session = request.getSession();
		
		String id =(String) session.getAttribute("id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = dao.getDetail(id);
		
		request.setAttribute("member", member);
		
		ActionForward forward = new ActionForward();
		  forward.setRedirect(false);
		  forward.setPath("./mypage/myprofile.jsp");
		return forward;
	}

}
