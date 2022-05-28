package service.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dao.ReplyDAO;
import dto.BoardDTO;
import dto.ReplyDTO;
import service.Action;
import service.ActionForward;

public class BoardReply implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardReply");
		
		//알림창을 위한 인코딩/out객체 생성
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		// 폼에서 넘어오는 파라미터
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num:"+num);
		
		int re_ref = 0;
		int re_lev = 0;
		int re_seq = 0;
		if (request.getParameter("re_ref") != null) {
			re_ref = Integer.parseInt(request.getParameter("re_ref"));
			re_lev = Integer.parseInt(request.getParameter("re_lev"));
			re_seq = Integer.parseInt(request.getParameter("re_seq"));
		}
		System.out.println("re_ref:"+re_ref);
		System.out.println("re_lev:"+re_lev);
		System.out.println("re_lev:"+re_seq);
		
		String page = request.getParameter("page");
		String re_content = request.getParameter("content");
		System.out.println("page:"+page);
		System.out.println("re_content:"+re_content);

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("id:"+id);

		// 로그인 여부 확인
		if(id == null) {			//로그인이 안 된 경우
			out.println("<script>");
			out.println("alert('로그인 이후 댓글작성이 가능합니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
			
		// 댓글 내용 확인
		}else if(re_content.equals("")) {			//내용이 없는 경우
			out.println("<script>");
			out.println("alert('댓글을 입력해 주세요');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
			
		}else {						//로그인 되고 내용이 있는 경우
		
			ReplyDTO reply = new ReplyDTO();
			
			reply.setRe_content(re_content);
			reply.setId(id);
			reply.setNum(num);

			ReplyDAO dao = ReplyDAO.getInstance();
			
			if (re_ref == 0) {					//부모글인 경우
				int result = dao.insert(reply);
				if(result == 1) System.out.println("댓글작성 성공");		
			} else {							//자식글인 경우
				reply.setRe_ref(re_ref);
				reply.setRe_lev(re_lev);
				reply.setRe_seq(re_seq);
				int result = dao.insertReply(reply);
				if(result == 1) {
					System.out.println("재댓글작성 성공");		
				} else {
					System.out.println("재댓글작성 실패");	
				}
			}


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
