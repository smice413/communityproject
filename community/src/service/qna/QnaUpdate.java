package service.qna;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.QnaDAO;
import dto.QnaDTO;
import service.Action;
import service.ActionForward;

public class QnaUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaUpdate");
		
		String path = request.getRealPath("qnaupload");
		System.out.println("path: "+path);
		
		int size = 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request, path, size,"utf-8", new DefaultFileRenamePolicy());
		
		int qna_num = Integer.parseInt(multi.getParameter("qna_num"));
		System.out.println("qna_num: "+qna_num);
		
		String page = multi.getParameter("page");
		
		QnaDTO qna = new QnaDTO();
		
		qna.setQna_subject(multi.getParameter("qna_subject"));
		qna.setQna_content(multi.getParameter("qna_content"));
		qna.setQna_num(qna_num);
		
		String qna_file = multi.getFilesystemName("qna_file");
		String old_qna_file = multi.getParameter("old_qna_file");
		
		System.out.println("수정 파일: "+qna_file);
		System.out.println("기존 파일: "+old_qna_file);
		
		  
		if(qna_file == null) {		
			qna.setQna_file(old_qna_file);
		}else {						
			qna.setQna_file(qna_file);
		}
		
		QnaDAO dao = QnaDAO.getInstance();	  
		int result = dao.update(qna);
		if(result == 1) System.out.println("수정 성공!");
		
		 // 첨부파일 있을 경우, 이름 다르면 첨부파일 삭제 
		  if(old_qna_file != "") {
			File file = new File(path); 
			file.mkdir();
		  
		  // qnaupload 디렉토리의 모든 첨부파일 이름 구해오기 
			File[] f = file.listFiles(); 
			for(int i =0; i < f.length; i++) { 
				if(f[i].getName().equals(old_qna_file)) {
					System.out.println("f[i]: "+f[i].getName());
				  	f[i].delete();
				} 	
			}
			
		  }
	

			
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/QnaDetail.do?qna_num="+qna_num+"&page="+page);
		
		return forward;
	}

}
