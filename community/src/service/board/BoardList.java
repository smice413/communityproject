package service.board;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.BoardListDAO;
import dto.BoardDTO;
import dto.BoardListDTO;
import service.Action;
import service.ActionForward;

public class BoardList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardList");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		int page = 1;		// 현재 페이지 번호
		int limit = 10;		// 한 페이지에 출력할 데이터 갯수
		int pageCount = 0;
		String head = "";
		String sort = "writedate";
		List<BoardDTO> fboardlist = null;
		int fcount = 0;
		String sel = "";
		String find = "";
		int bl_code = 1; 
		
		if(request.getParameter("bl_code") != null) {
			bl_code = Integer.parseInt(request.getParameter("bl_code"));
		}
		
		if(request.getParameter("head") != null) {
			head = request.getParameter("head");
		}
		if(request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		System.out.println("sort:"+sort);
		
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
		
		BoardListDAO bldao = BoardListDAO.getInstance();
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardListDTO bl = bldao.getDetail(bl_code);
		System.out.println("bl:"+bl);
		
		int count = dao.getCount(bl_code,head,sort);		// 총 데이터 갯수
		System.out.println("count:" + count);
		
		if(sel != "" && find != "") {
			fcount = dao.getFcount(bl_code, sel, find);
			System.out.println("fcount:" + fcount);
			if(fcount == 0) {
				out.println("<script>");
				out.println("alert('검색결과가 존재하지 않습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				
				return null;
			}
		}
		
		List<BoardDTO> boardfixlist = dao.getFixList(bl_code, startRow, endRow);
		System.out.println("boardfixlist:" + boardfixlist);
		
		List<BoardDTO> boardlist = dao.getList(bl_code, head, sort, startRow, endRow);
		System.out.println("boardlist:" + boardlist);
		
		if (fcount > 0) {
			fboardlist = dao.selectFboard(bl_code, startRow, endRow, sel, find);
			System.out.println("fboardlist:" + fboardlist);
		}
				
		// 총 페이지
		if(count > 0 && fcount == 0) {
			pageCount = count / limit+((count%limit==0) ? 0:1);
		}else if(fcount > 0) {
			pageCount = fcount / limit+((fcount%limit==0) ? 0:1);
		}
		int startPage = ((page-1)/10) * limit + 1;	// 1,  11, 21...
		int endPage = startPage + 10 - 1;			// 10, 20, 30...
		
		if(endPage > pageCount) endPage = pageCount;
		
		request.setAttribute("bl_code", bl_code);
		request.setAttribute("page", page);
		request.setAttribute("count", count);
		request.setAttribute("boardfixlist", boardfixlist);
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("head", head);
		request.setAttribute("sort", sort);
		request.setAttribute("fcount", fcount);
		request.setAttribute("sel", sel);
		request.setAttribute("find", find);
		request.setAttribute("fboardlist", fboardlist);
		request.setAttribute("bl", bl);
		
		// request 객체로 공유한 경우에는 dispatcher 방식으로 포워딩 되어야,
		// view 페이지에서 공유한 값에 접근 할 수 있다.
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);    // dispatcher 방식으로 포워딩
		forward.setPath("./board/board_list.jsp");
		
		return forward;
	}

}

