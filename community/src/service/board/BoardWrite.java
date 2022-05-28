package service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dto.BoardDTO;
import service.Action;
import service.ActionForward;

public class BoardWrite implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardWrite");
		
		String path = request.getRealPath("boardupload");
		System.out.println("path:" + path);
		
		int size = 1024 * 1024 * 10;	
		
		MultipartRequest multi = 
				new MultipartRequest(request,
									 path,			//업로드 디렉토리
									 size,      	//업로드 파일크기(1MB)
									 "utf-8",		//한글 인코딩
					new DefaultFileRenamePolicy()); //중복파일 문제 해결
		
		BoardDTO board = new BoardDTO();
		
		board.setHead(multi.getParameter("head"));
		board.setStar(Integer.parseInt(multi.getParameter("star")));
		board.setFix(multi.getParameter("fix"));
		board.setSubject(multi.getParameter("subject"));
		board.setId(multi.getParameter("id"));
		board.setBl_code(Integer.parseInt(multi.getParameter("bl_code")));
		board.setContent(multi.getParameter("content"));
		board.setBoard_file(multi.getFilesystemName("board_file"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.insert(board);
		if(result == 1) System.out.println("글작성 성공");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/BoardList.do?bl_code="+board.getBl_code());
		
		return forward;
	}

}
