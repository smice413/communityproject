package service.mypage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Action;
import service.ActionForward;

public class QnaAddPre implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("QnaAddPre");
		
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String id = (String)session.getAttribute("id");
		
	    if(id != null) {     // 로그인O
	    	
	    	request.setAttribute("id", id);
	    	
	       ActionForward forward = new ActionForward();
		    forward.setRedirect(false);
			forward.setPath("./qna/qna_write.jsp");
		   return forward;
		}else {              // 로그인X
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
	      
		}
	}
}
