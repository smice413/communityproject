package service.qna;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QnaDAO;
import dto.QnaDTO;
import service.Action;
import service.ActionForward;

public class QnaList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaList");

		response.setContentType("text/html; charset=utf-8"); 
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String id = (String)session.getAttribute("id");
		int page = 1;
		int limit = 10;
		int pageCount = 0;
		int fcount = 0;
		List<QnaDTO> fqnalist = null;
		String sel = "";
		String find = "";
		

		if(request.getParameter("sel") != null) {
			sel = request.getParameter("sel");
		}
		if(request.getParameter("find") != null) {
			find = request.getParameter("find");
		}
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		
		QnaDAO dao = QnaDAO.getInstance();
		int qnacount = dao.getCount();
		System.out.println("qnacount: "+qnacount);
		
		List<QnaDTO> qnalist = dao.getList(startRow,endRow);
		System.out.println("qnalist: "+qnalist);
		
		if(sel != "" && find != "") {
			fcount = dao.getFount(sel, find);
			System.out.println("fcount:" + fcount);
			if(fcount == 0) {
				out.print("<script>");
				out.print("alert('검색결과가 존재하지 않습니다.');");
				out.print("history.go(-1);");
				out.print("</script>");
				out.close();
				
				return null;
			}
		}
		
		if(fcount > 0) {
			fqnalist = dao.selectFqna(startRow, endRow, sel, find);
			System.out.println("fqnalist:"+fqnalist);
		}
		
		// 총 페이지 수
		if(qnacount > 0 && fcount == 0) {     // 전체 페이지
			pageCount = qnacount / limit+((qnacount%limit==0) ? 0:1);
		}else if(fcount > 0) {             // 검색된 페이지  
			pageCount = fcount / limit+((fcount%limit==0) ? 0:1);
		}
		int startPage = ((page-1)/10) * limit + 1;	// 1,  11, 21...
		int endPage = startPage + 10 - 1;			// 10, 20, 30...
		
		if(endPage > pageCount) endPage = pageCount;
		
		// jsp 파일에서 쓸 값 공유
		request.setAttribute("page", page);
		request.setAttribute("qnacount", qnacount);
		request.setAttribute("qnalist", qnalist);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("fcount", fcount);
		request.setAttribute("sel", sel);
		request.setAttribute("find", find);
		request.setAttribute("fqnalist", fqnalist);
			
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./qna/qna_list.jsp");	
		
		return forward;
    }
			
}
