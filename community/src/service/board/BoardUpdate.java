package service.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dao.MemberDAO;
import dto.BoardDTO;
import dto.MemberDTO;
import service.Action;
import service.ActionForward;

public class BoardUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardUpdate");

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
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
		board.setContent(multi.getParameter("content"));
		board.setNum(Integer.parseInt(multi.getParameter("num")));
		
		String id = multi.getParameter("id");			
		String passwd = multi.getParameter("passwd");
		String page = multi.getParameter("page");
		String board_file = multi.getFilesystemName("board_file");
		String old_board_file = multi.getParameter("old_board_file");
		
		System.out.println("id:"+id);
		System.out.println("passwd:"+passwd);
		System.out.println("page:"+page);
		System.out.println("old_board_file:"+old_board_file);

		if (board_file == null) {
			board.setBoard_file(old_board_file);
		} else {
			board.setBoard_file(board_file);
		}

		// 비번 비교
		MemberDAO memdao = MemberDAO.getInstance();
		MemberDTO old = memdao.getDetail(id);		// 상세정보 구하기
		
		if(old.getPasswd().equals(passwd)) {    // 비번 일치시
			BoardDAO dao = BoardDAO.getInstance();
			int result = dao.update(board);	// delete SQL문 실행
			if(result == 1) System.out.println("수정 성공");
			
			// 첨부파일이 있을 경우에 첨부파일 삭제
			if(old_board_file != "") {

				File file = new File(path);
				file.mkdir();
				
				// boardupload 디렉토리의 모든 첨부파일 구해오기
				File[] f = file.listFiles();
				for(int i=0; i<f.length; i++) {
					System.out.println(f[i].getName());
					if(f[i].getName().equals(old_board_file)) {
						f[i].delete();
					}
				}				
			}			
			
		}else {		// 비번 불일치시
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}		
		
		// 공유 설정
		request.setAttribute("num", board.getNum());
		request.setAttribute("page", page);
		System.out.println("BoardUpdate num:"+ board.getNum());
		System.out.println("BoardUpdate page:"+ page);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);  		// dispatcher 방식으로 포워딩
		forward.setPath("/BoardDetail.do?num="+board.getNum()+"&page="+page);
		return forward;
	}

}
