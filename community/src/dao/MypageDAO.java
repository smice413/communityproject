package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
import dto.BoardListDTO;
import dto.MypageDTO;
import dto.QnaDTO;
import dto.ReplyDTO;

public class MypageDAO {

	private static MypageDAO instance = new MypageDAO();
	
    public static MypageDAO getInstance() {
    	return instance;
    }
    
    private Connection getConnection() throws Exception {
    	Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
  		return ds.getConnection();
    }
    
    // 특정 id로 작성한 글 갯수
    public int getWritingCount(String id) {
    	int result = 0;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = getConnection();
    		
    		String sql = "select count(*) from board where id=? and board_activate='Y'";
    		
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			result = rs.getInt(1);
    		}
    		      
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		if(rs != null)try {rs.close();}catch(Exception e) {}
    		if(pstmt != null)try {pstmt.close();}catch(Exception e) {}
    		if(con != null)try {con.close();}catch(Exception e) {}
    	}
    	
    	return result;
    }
    
    
    // 특정 id로 작성한 글 목록 
    public List getWritingList(String id, int startRow, int endRow) {
    	List mywritinglist = new ArrayList();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs1 = null;
    	ResultSet rs2 = null;
    	ResultSet rs3 = null;
    	ResultSet rs4 = null;
    	
    	try {
    		con = getConnection();
    	           
    		String sql = "select * from (select rownum rnum, b.* from ";
			   sql += " (select * from board where id=? and board_activate='Y' order by num desc) b) ";
			   sql += " where rnum >= ? and rnum <= ?"; 

    	    pstmt = con.prepareStatement(sql);
    	    pstmt.setString(1 , id);
    	    pstmt.setInt(2 , startRow);
    	    pstmt.setInt(3 , endRow);
    	    rs1 = pstmt.executeQuery();
    	    
    	    while(rs1.next()) {
    	    	MypageDTO mywriting = new MypageDTO();
    	    	mywriting.setNum(rs1.getInt("num"));
    	    	mywriting.setHead(rs1.getString("head"));
    	    	mywriting.setStar(rs1.getInt("star"));
    	    	mywriting.setFix(rs1.getString("fix"));
    	    	mywriting.setSubject(rs1.getString("subject"));
    	    	mywriting.setId(rs1.getString("id"));
    	    	mywriting.setContent(rs1.getString("content"));
    	    	mywriting.setBoard_file(rs1.getString("board_file"));
    	    	mywriting.setWritedate(rs1.getTimestamp("writedate"));
    	    	mywriting.setReadcount(rs1.getInt("readcount"));
    	    	mywriting.setBl_code(rs1.getInt("bl_code"));
    	    	mywriting.setBoard_activate(rs1.getString("board_activate"));
    	    	
    	    	// 해당 글의 댓글 갯수 구하기
    	    	sql = "select count(*) from reply where num=?";
    	    
    	    	pstmt = con.prepareStatement(sql);
    	    	pstmt.setInt(1, mywriting.getNum());
    	    	rs2 = pstmt.executeQuery();
    	    	
    	    	if(rs2.next()) {
    	    	   mywriting.setRe_count(rs2.getInt("count(*)"));   
    	    	}
    	    	
    	    	sql = "select count(*) from recom where num=?";
    	    	
    	    	pstmt = con.prepareStatement(sql);
    	    	pstmt.setInt(1, mywriting.getNum());
    	    	rs3 = pstmt.executeQuery();
    	    	
    	    	if(rs3.next()) {
    	    		mywriting.setRecomcount(rs3.getInt("count(*)"));
    	    	}
    	    	
    	    	sql = "select bl_name from boardlist where bl_code=?";
    	    	
    	    	pstmt = con.prepareStatement(sql);
    	    	pstmt.setInt(1, mywriting.getBl_code());
    	    	rs4 = pstmt.executeQuery();
    	    	
    	    	if(rs4.next()) {
    	    		mywriting.setBl_name(rs4.getString("bl_name"));
    	    	}
    	    	
    	    	mywritinglist.add(mywriting);	
    	    }
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		if(rs3 != null)try {rs3.close();}catch(Exception e) {}
    		if(rs2 != null)try {rs2.close();}catch(Exception e) {}
    		if(rs1 != null)try {rs1.close();}catch(Exception e) {}
    		if(pstmt != null)try {pstmt.close();}catch(Exception e) {}
    		if(con != null)try {con.close();}catch(Exception e) {}
    	}
    	
    	return mywritinglist;
    }
    
    
    
    // 특정 id로 작성한 댓글 갯수
    public int getReplyCount(String id) {
    	int result = 0;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = getConnection();
    		
    		String sql = "select count(*) from reply where id=? and re_activate='Y'";
    		
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			result = rs.getInt(1);
    		}
    		      
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		if(rs != null)try {rs.close();}catch(Exception e) {}
    		if(pstmt != null)try {pstmt.close();}catch(Exception e) {}
    		if(con != null)try {con.close();}catch(Exception e) {}
    	}
    	
    	return result;
    }
    
    
    // 특정 id로 작성한 댓글 목록
    public List<ReplyDTO> getReplyList(String id, int startRow, int endRow) {
    	List myreplylist = new ArrayList();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = getConnection();
    			       			   
    	    String sql = "select * from (select rownum rnum, rb.* from ";
    	           sql+= " (select r.*, b.head, b.subject from reply r full outer join board b on r.num = b.num "; 
    	    	   sql+= " where r.id = ? and r.re_activate='Y' order by re_ref desc, re_seq) rb) ";		   
    			   sql+= " where rnum >= ? and rnum <= ?";
    			   
    	           
    	    pstmt = con.prepareStatement(sql);
    	    pstmt.setString(1 , id);
    	    pstmt.setInt(2, startRow);
    	    pstmt.setInt(3, endRow);
    	    rs = pstmt.executeQuery();
    	    
    	    while(rs.next()) {
    	    	MypageDTO myreply = new MypageDTO();
    	    	myreply.setRe_num(rs.getInt("re_num"));
    	    	myreply.setRe_content(rs.getString("re_content"));
    	    	myreply.setId(rs.getString("id"));
    	    	myreply.setRe_ref(rs.getInt("re_ref"));
    	    	myreply.setRe_lev(rs.getInt("re_lev"));
    	    	myreply.setRe_seq(rs.getInt("re_seq"));
    	    	myreply.setRe_writedate(rs.getTimestamp("re_writedate"));
    	    	myreply.setNum(rs.getInt("num"));
    	    	myreply.setRe_activate(rs.getString("re_activate"));
    	    	myreply.setHead(rs.getString("head"));
    	    	myreply.setSubject(rs.getString("subject"));
    	    	
    	    	myreplylist.add(myreply);	
    	    }
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		if(rs != null)try {rs.close();}catch(Exception e) {}
    		if(pstmt != null)try {pstmt.close();}catch(Exception e) {}
    		if(con != null)try {con.close();}catch(Exception e) {}
    	}
    	
    	return myreplylist;
    }
    
    
    // 특정 id로 생성된 게시판 갯수
    public int getBoardCount(String id) {
    	int result = 0;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = getConnection();
    		
    		String sql = "select count(*) from boardlist where id=?";
    		
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1 , id);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			result = rs.getInt(1);
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		if(rs != null)try {rs.close();}catch(Exception e) {}
    		if(pstmt != null)try {pstmt.close();}catch(Exception e) {}
    		if(con != null)try {con.close();}catch(Exception e) {}
    	}
    	
    	return result;
    }
    
    
    // 특정 id로 생성된 게시판 목록
    public List<BoardListDTO> getBoardList(String id, int startRow, int endRow){
    	List<BoardListDTO> myboardlist = new ArrayList<BoardListDTO>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        	con = getConnection();
        	
        	String sql = "select * from (select rownum rnum, bl.* from ";
        	       sql+= " (select * from boardlist where id=?";
        	       sql+= " order by bl_code desc ) bl) ";
        	       sql+= " where rnum >= ? and rnum <= ?";

        	
        	pstmt = con.prepareStatement(sql);
        	pstmt.setString(1 , id);
        	pstmt.setInt(2, startRow);
        	pstmt.setInt(3, endRow);
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
        		BoardListDTO myboard = new BoardListDTO();
        		myboard.setBl_code(rs.getInt("bl_code"));
        		myboard.setBl_name(rs.getString("bl_name"));
        		myboard.setBl_exp(rs.getString("bl_exp"));
        		myboard.setBl_private(rs.getString("bl_private"));
        		myboard.setBl_passwd(rs.getString("bl_passwd"));
        		myboard.setBl_regdate(rs.getTimestamp("bl_regdate"));
        		myboard.setBl_activate(rs.getString("bl_activate"));
        		myboard.setBl_category(rs.getString("bl_category"));
        		myboard.setId(rs.getString("id"));
        		
        		myboardlist.add(myboard);
        	}
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	if(rs != null)try {rs.close();}catch(Exception e) {}
    		if(pstmt != null)try {pstmt.close();}catch(Exception e) {}
    		if(con != null)try {con.close();}catch(Exception e) {}
        }
    	
    	return myboardlist;
    }
    
    
    
    // 특정 id로 작성한 문의글 갯수
    public int getQnaCount(String id) {
    	int result = 0;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = getConnection();
    		
    		 String sql = "select count(*) from qna a,";  
      		  		sql+= " (select qna_num from qna where id = ? ) b ";
      		  		sql+= " where a.qna_ref = b.qna_num and qna_activate='Y' ";
      		  		 
    		
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			result = rs.getInt(1);
    		}
    		      
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		if(rs != null)try {rs.close();}catch(Exception e) {}
    		if(pstmt != null)try {pstmt.close();}catch(Exception e) {}
    		if(con != null)try {con.close();}catch(Exception e) {}
    	}
    	
    	return result;
    }
    
    
 
    
    
    // 특정 id로 작성한 문의글 목록
    public List<QnaDTO> getQnaList(String id, int startRow, int endRow) {
    	List myqnalist = new ArrayList();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = getConnection();
    		
           String sql = "select * from (select rownum rnum, a.* from qna a,";  
           		  sql+= " (select qna_num from qna where id = ? ) b ";
           		  sql+= " where a.qna_ref = b.qna_num and qna_activate='Y' order by qna_ref desc, qna_seq asc) ";
           		  sql+= " where rnum >= ? and rnum <= ?"; 
                    
    	    pstmt = con.prepareStatement(sql);
    	    pstmt.setString(1, id);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
    	    rs = pstmt.executeQuery();
    	    
    	    while(rs.next()) {
    	    	QnaDTO myqna = new QnaDTO();
    	    	myqna.setQna_num(rs.getInt("qna_num"));
    	    	myqna.setId(rs.getString("id"));
    	    	myqna.setQna_subject(rs.getString("qna_subject"));
    	    	myqna.setQna_content(rs.getString("qna_content"));
    	    	myqna.setQna_file(rs.getString("qna_file"));
				myqna.setQna_ref(rs.getInt("qna_ref"));
				myqna.setQna_lev(rs.getInt("qna_lev"));
				myqna.setQna_seq(rs.getInt("qna_seq"));
				myqna.setQna_writedate(rs.getTimestamp("qna_writedate"));
				myqna.setQna_activate(rs.getString("qna_activate"));
				
    	        myqnalist.add(myqna);	
    	    }
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		if(rs != null)try {rs.close();}catch(Exception e) {}
    		if(pstmt != null)try {pstmt.close();}catch(Exception e) {}
    		if(con != null)try {con.close();}catch(Exception e) {}
    	}
    	
    	return myqnalist;
    }
    
    
    
    
  
    
}
