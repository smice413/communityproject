package service.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.QnaDAO;
import dto.QnaDTO;
import service.Action;
import service.ActionForward;

public class QnaAdd implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaAdd");
		
		String path = request.getRealPath("qnaupload");
		System.out.println("path: "+path);
		
		int size = 1024 * 1024 * 10; 	// 10MB
		
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
		
		QnaDTO qna = new QnaDTO();
		qna.setQna_subject(multi.getParameter("qna_subject"));
		qna.setQna_content(multi.getParameter("qna_content"));
		qna.setQna_file(multi.getFilesystemName("qna_file"));
		qna.setId(multi.getParameter("id"));
		
		QnaDAO dao = QnaDAO.getInstance();
		int result = dao.insert(qna);		// 문의글 작성 insert 메소드
		if(result == 1) System.out.println("글 작성 성공!");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/MyQnaListPre.do");
		
		return forward;
	}
	
}
