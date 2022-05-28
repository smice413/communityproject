package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
import dto.BoardListDTO;


public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 컨넥션풀에서 컨넥션을 구해오는 메소드 
	private Connection getConnection() throws Exception{
		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
  		return ds.getConnection();
	}
	
	// 글작성 : 글작성 - 김창원
	public int insert(BoardDTO board) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
String sql="insert into board values(board_seq.nextval,?,?,?,?,?,";
       sql+="?,?,sysdate,0,?,'Y')";
       
       		pstmt = con.prepareStatement(sql);
       		pstmt.setString(1, board.getHead());
       		pstmt.setInt(2, board.getStar());
       		pstmt.setString(3, board.getFix());
       		pstmt.setString(4, board.getSubject());
       		pstmt.setString(5, board.getId());
			pstmt.setString(6, board.getContent());		
			pstmt.setString(7, board.getBoard_file());
			pstmt.setInt(8, board.getBl_code());
			result = pstmt.executeUpdate();		// SQL문 실행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
 		}		
		return result;
	}
	
	// 총 데이터 갯수 구하기 - 김창원
	public int getCount(int bl_code, String head, String sort) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = getConnection();
			
			if (head == "") {
				sql="select count(*) from board where bl_code="+bl_code+" and board_activate='Y' "+head+" order by "+sort+" desc";
			}else {
				sql="select count(*) from board where bl_code="+bl_code+" and board_activate='Y' and head='"+head+"' order by "+sort+" desc";
			}
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();		// SQL문 실행
			
			if(rs.next()) {
//					result = rs.getInt(1);
				result = rs.getInt("count(*)");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}		
		return result;
	}
	
	// 검색된 데이터 갯수 - 김창원
	public int getFcount(int bl_code, String sel, String find) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select count(*) from board where bl_code="+bl_code+" and board_activate='Y' and "+sel+" like '%"+find+"%'";    
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql);
			if(rs.next()) {
				result = rs.getInt(1);
//				result = rs.getInt("count(*)");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}
		return result;
	}	
	
	// 검색 목록 - 김창원
	public List<BoardDTO> selectFboard(int bl_code, int start, int end, String sel, String find) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		ResultSet rprs = null;
		
		try {
			con=getConnection();

			String sql = "select * from ( select rownum rnum, b.* from ";
			sql += " (select * from board where bl_code="+bl_code+" and board_activate='Y' and "+sel+" like '%"+find+"%' order by num desc) b ) ";
			sql += " where rnum >= ? and rnum <= ?";			
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start);
	   		pstmt.setInt(2, end);
	   		rs = pstmt.executeQuery();		// SQL문 실행
			
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setNum(rs.getInt("num"));
	   			board.setHead(rs.getString("head"));
	   			board.setStar(rs.getInt("star"));
	   			board.setFix(rs.getString("fix"));
	   			board.setSubject(rs.getString("subject"));
	   			board.setId(rs.getString("id"));
	   			board.setContent(rs.getString("content"));
	   			board.setBoard_file(rs.getString("board_file"));
	   			board.setWritedate(rs.getTimestamp("writedate"));
	   			board.setReadcount(rs.getInt("readcount"));
	   			board.setBl_code(rs.getInt("bl_code"));
	   			board.setBoard_activate(rs.getString("board_activate"));
	   			
	   			sql="select count(*) from reply where num=?";
	   			pstmt = con.prepareStatement(sql);
	   			pstmt.setInt(1,rs.getInt("num"));
	   			rprs = pstmt.executeQuery();
	   			
	   			if(rprs.next()) {
	   				board.setRe_count(rprs.getInt("count(*)"));
	   			}
				list.add(board);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rprs != null) try { rprs.close();}catch(Exception e) {}
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}
		return list;
	}
	
	// 글목록(고정) - 김창원
	public List<BoardDTO> getFixList(int bl_code, int start, int end){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rprs = null;
		
		try {
			con = getConnection();

String sql="select * from ( select rownum rnum, b.* from ";			
	   sql+=" ( select * from board where fix='Y' and bl_code="+bl_code+" and board_activate='Y' order by num desc) b ) ";
	   sql+=" where rnum >= ? and rnum <= ?";
	   
	   		pstmt = con.prepareStatement(sql);
	   		pstmt.setInt(1, start);
	   		pstmt.setInt(2, end);
	   		rs = pstmt.executeQuery();		// SQL문 실행
	   		
	   		while(rs.next()) {
	   			BoardDTO board = new BoardDTO();
	   			
	   			board.setNum(rs.getInt("num"));
	   			board.setHead(rs.getString("head"));
	   			board.setStar(rs.getInt("star"));
	   			board.setFix(rs.getString("fix"));
	   			board.setSubject(rs.getString("subject"));
	   			board.setId(rs.getString("id"));
	   			board.setContent(rs.getString("content"));
	   			board.setBoard_file(rs.getString("board_file"));
	   			board.setWritedate(rs.getTimestamp("writedate"));
	   			board.setReadcount(rs.getInt("readcount"));
	   			board.setBl_code(rs.getInt("bl_code"));
	   			board.setBoard_activate(rs.getString("board_activate"));
	   			
	   			sql="select count(*) from reply where num=?";
	   			pstmt = con.prepareStatement(sql);
	   			pstmt.setInt(1,rs.getInt("num"));
	   			rprs = pstmt.executeQuery();
	   			
	   			if(rprs.next()) {
	   				board.setRe_count(rprs.getInt("count(*)"));
	   			}
	   			list.add(board);
	   		}
	   
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rprs != null) try { rprs.close();}catch(Exception e) {}
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}		
		return list;
	}
	
	// 글목록(최신순) - 김창원
   public List<BoardDTO> getList(int bl_code, String head, String sort, int start, int end){
      List<BoardDTO> list = new ArrayList<BoardDTO>();
      Connection con  = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ResultSet rprs = null;
      String sql = "";
      
      try {
         con = getConnection();
         
         if (head == "") {
        	   sql="select * from ( select rownum rnum, b.* from ";         
               sql+=" ( select * from board where bl_code="+bl_code+" and board_activate='Y' "+head+" order by "+sort+" desc) b ) ";
               sql+=" where rnum >= ? and rnum <= ?";
               
         }else {
        	   sql="select * from ( select rownum rnum, b.* from ";         
               sql+=" ( select * from board where bl_code="+bl_code+" and board_activate='Y' and head='"+head+"' order by "+sort+" desc) b ) ";
               sql+=" where rnum >= ? and rnum <= ?";
         }
         
         	pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, start);
	        pstmt.setInt(2, end);
	        rs = pstmt.executeQuery();      // SQL문 실행
         
            while(rs.next()) {
               BoardDTO board = new BoardDTO();
               
               board.setNum(rs.getInt("num"));
               board.setHead(rs.getString("head"));
               board.setStar(rs.getInt("star"));
               board.setFix(rs.getString("fix"));
               board.setSubject(rs.getString("subject"));
               board.setId(rs.getString("id"));
               board.setContent(rs.getString("content"));
               board.setBoard_file(rs.getString("board_file"));
               board.setWritedate(rs.getTimestamp("writedate"));
               board.setReadcount(rs.getInt("readcount"));
               board.setBl_code(rs.getInt("bl_code"));
               board.setBoard_activate(rs.getString("board_activate"));
               
               sql="select count(*) from reply where num=?";
	   			pstmt = con.prepareStatement(sql);
	   			pstmt.setInt(1,rs.getInt("num"));
	   			rprs = pstmt.executeQuery();
	   			
	   			if(rprs.next()) {
	   				board.setRe_count(rprs.getInt("count(*)"));
	   			}
               
               list.add(board);
            }
      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
    	 if(rprs != null) try { rprs.close();}catch(Exception e) {} 
         if(rs != null) try { rs.close();}catch(Exception e) {}
         if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
         if(con != null) try { con.close();}catch(Exception e) {}
      }      
      return list;
   }
	
	// 글목록(공지) - 김창원
	public List<BoardDTO> getNoticeList(String head, int start, int end){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rprs = null;
		
		try {
			con = getConnection();

String sql="select * from ( select rownum rnum, b.* from ";			
	   sql+=" ( select * from board where head="+head+" and board_activate='Y') b ) ";
	   sql+=" where rnum >= ? and rnum <= ?";
	   
	   		pstmt = con.prepareStatement(sql);
	   		pstmt.setInt(1, start);
	   		pstmt.setInt(2, end);
	   		rs = pstmt.executeQuery();		// SQL문 실행
	   		
	   		while(rs.next()) {
	   			BoardDTO board = new BoardDTO();
	   			
	   			board.setNum(rs.getInt("num"));
	   			board.setHead(rs.getString("head"));
	   			board.setStar(rs.getInt("star"));
	   			board.setFix(rs.getString("fix"));
	   			board.setSubject(rs.getString("subject"));
	   			board.setId(rs.getString("id"));
	   			board.setContent(rs.getString("content"));
	   			board.setBoard_file(rs.getString("board_file"));
	   			board.setWritedate(rs.getTimestamp("writedate"));
	   			board.setReadcount(rs.getInt("readcount"));
	   			board.setBl_code(rs.getInt("bl_code"));
	   			board.setBoard_activate(rs.getString("board_activate"));
	   			
	   			sql="select count(*) from reply where num=?";
	   			pstmt = con.prepareStatement(sql);
	   			pstmt.setInt(1,rs.getInt("num"));
	   			rprs = pstmt.executeQuery();
	   			
	   			if(rprs.next()) {
	   				board.setRe_count(rprs.getInt("count(*)"));
	   			}
	   			list.add(board);
	   		}
	   
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rprs != null) try { rprs.close();}catch(Exception e) {}
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}		
		return list;
	}

	
	
	
	// 조회수 증가 - 손정은
	public void readcountUpdate(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
	  
		try {
			con = getConnection();
         
		String sql="update board set readcount=readcount+1 ";         
        		sql+=" where num = ?";
                
        pstmt = con.prepareStatement(sql);      
        pstmt.setInt(1, num);
        pstmt.executeUpdate();      // SQL문 실행
          
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}      
	}
   
	// 상세 페이지 - 손정은
	public BoardDTO getDetail(int num) {
		BoardDTO board = new BoardDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
      
		try {
			con = getConnection();
         
			String sql="select * from board where num=? ";
         
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();      // SQL문 실행
         
		if(rs.next()) {
			board.setNum(rs.getInt("num"));
        	board.setHead(rs.getString("head"));
        	board.setStar(rs.getInt("star"));
        	board.setFix(rs.getString("fix"));
        	board.setSubject(rs.getString("subject"));
            board.setId(rs.getString("id"));
            board.setContent(rs.getString("content"));
            board.setBoard_file(rs.getString("board_file"));
            board.setWritedate(rs.getTimestamp("writedate"));
            board.setReadcount(rs.getInt("readcount"));
            board.setBl_code(rs.getInt("bl_code"));
            board.setBoard_activate(rs.getString("board_activate"));
         }         
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         if(rs != null) try { rs.close();}catch(Exception e) {}
         if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
         if(con != null) try { con.close();}catch(Exception e) {}
      }      
      return board;
	}
	  
	//추천수 구하기 - 손정은
	public int getRecomCount(int num) {
		int recomcount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql="select count(*) from recom where num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();		// SQL문 실행
			
			if(rs.next()) {
				recomcount = rs.getInt("count(*)");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}		
		return recomcount;
	}

	// 추천여부 조회 - 손정은
	public int recomCheck(String id,int num) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql="select * from recom where id=? and num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();     // SQL문 실행
			
			if(rs.next()) {		// 추천한 적이 있으면
				result = 1;
			}else {				// 추천한 적이 없으면
				result = 0;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}
		return result;
	}
	
	// 추천하기+추천 취소하기- - 손정은
	public int recomUpdate(String id, int num, int recomcheck) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql= null;
		
		try {
			con = getConnection();
			
			if (recomcheck == 1) {
				sql="delete from recom where id=? and num=?";		
			} else {
				sql="insert into recom values(?,?,1)";
			}
			
       		pstmt = con.prepareStatement(sql);
       		pstmt.setString(1, id);
       		pstmt.setInt(2, num);

			result = pstmt.executeUpdate();		// SQL문 실행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
 		}		
		return result;
	}
	
	//게시판 수정 - 손정은
	public int update(BoardDTO board) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql="update board set head=?, star=?, fix=?, subject=?,";
			       sql+="content=?, board_file=? where num=?";
		       
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, board.getHead());
		    pstmt.setInt(2, board.getStar());
		    pstmt.setString(3, board.getFix());
		    pstmt.setString(4, board.getSubject());
		    pstmt.setString(5, board.getContent());
		    pstmt.setString(6, board.getBoard_file());
		    pstmt.setInt(7, board.getNum());
		    result = pstmt.executeUpdate();		// SQL문 실행
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}	
		return result;
	}

	
	// 게시판 목록 - 손정은
	public List<BoardListDTO> getBoardList(String bl_category){
		List<BoardListDTO> bllist = new ArrayList<BoardListDTO>();
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			con = getConnection();

			if (bl_category.equals("movie")) {
				sql="select * from boardlist where bl_activate='Y' and bl_category='영화'";
			}else if (bl_category.equals("cook")) {
				sql="select * from boardlist where bl_activate='Y' and bl_category='요리'";
			}else if (bl_category.equals("shopping")) {
				sql="select * from boardlist where bl_activate='Y' and bl_category='쇼핑'";
			}else if (bl_category.equals("review")) {
				sql="select * from boardlist where bl_activate='Y' and bl_category='후기'";
			}else if (bl_category.equals("etc")) {
				sql="select * from boardlist where bl_activate='Y' and bl_category='기타'";
			}
	   		pstmt = con.prepareStatement(sql);

	   		rs = pstmt.executeQuery();		// SQL문 실행
	   		
	   		while(rs.next()) {
	   			BoardListDTO bl = new BoardListDTO();

	   			bl.setBl_code(rs.getInt("bl_code"));
	   			bl.setBl_name(rs.getString("bl_name"));
	   			bl.setBl_exp(rs.getString("bl_exp"));
	   			bl.setBl_private(rs.getString("bl_private"));
	   			bl.setBl_passwd(rs.getString("bl_passwd"));
	   			bl.setBl_regdate(rs.getTimestamp("bl_regdate"));
	   			bl.setBl_activate(rs.getString("bl_activate"));
	   			bl.setId(rs.getString("id"));

	   			bllist.add(bl);
	   			System.out.println("bl");
	   		}
	   
		}catch(Exception e) {
			e.printStackTrace();
		}finally {

			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}		
		return bllist;
	}
	
	// 인기글 리스트 - 김창원
	public List<BoardDTO> getPopBoardList(){
		List<BoardDTO> pbllist = new ArrayList<BoardDTO>();
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = getConnection();
			sql = "select * from ( select rownum rnum, b.* from "; 
			sql += " ( select * from board where board_activate='Y' ";
			sql += " and head!='공지' and board_file is not null ";
			sql += " order by readcount desc) b ) ";
			sql += " where rnum >= 1 and rnum <= 8";
			
			pstmt = con.prepareStatement(sql);
	   		rs = pstmt.executeQuery();		// SQL문 실행
	   		
	   		while(rs.next()) {
	   			BoardDTO board = new BoardDTO();
	   			
	   			board.setNum(rs.getInt("num"));
	        	board.setHead(rs.getString("head"));
	        	board.setStar(rs.getInt("star"));
	        	board.setFix(rs.getString("fix"));
	        	board.setSubject(rs.getString("subject"));
	            board.setId(rs.getString("id"));
	            board.setContent(rs.getString("content"));
	            board.setBoard_file(rs.getString("board_file"));
	            board.setWritedate(rs.getTimestamp("writedate"));
	            board.setReadcount(rs.getInt("readcount"));
	            board.setBl_code(rs.getInt("bl_code"));
	            board.setBoard_activate(rs.getString("board_activate"));
	            
	            pbllist.add(board);
	   		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}
		return pbllist;
	}
	
	// 게시판 글삭제 - 김창원
	public int delete(int num) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String sql = "update board set board_activate = 'N' where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result= pstmt.executeUpdate();	//SQL문 실행
			
			sql = "update reply set re_activate = 'N' where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result= pstmt.executeUpdate();  //SQL문 실행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}
		return result;
	}
}