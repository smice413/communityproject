package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.QnaDTO;

public class QnaDAO {

	private static QnaDAO instance = new QnaDAO();
	
	public static QnaDAO getInstance(){    
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
  		return ds.getConnection();
	}
	
	// 문의글 작성 (원문)
			public int insert(QnaDTO qna) {
				int result = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = getConnection();
					
					String sql = "insert into qna values ";
						   sql+= "(qna_seq.nextval,?,?,?,?,qna_seq.nextval,?,?,sysdate,?)";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, qna.getId());
					pstmt.setString(2, qna.getQna_subject());
					pstmt.setString(3, qna.getQna_content());
					pstmt.setString(4, qna.getQna_file());
					pstmt.setInt(5,qna.getQna_lev());
					pstmt.setInt(6, qna.getQna_seq());
					pstmt.setString(7, "Y");
					
					result = pstmt.executeUpdate();
						   
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
					if(con != null) try { con.close(); } catch(Exception e) {}
				}
				
				return result;
			}
			
			
		// 총 문의글 개수 구하기
			public int getCount() {
				int qnacount = 0;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = getConnection();
					
					String sql = "select count(*) from qna where qna_activate='Y' and id is not null";
					
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						qnacount = rs.getInt(1);
					}
						   
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(rs != null) try { rs.close(); } catch(Exception e) {}
					if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
					if(con != null) try { con.close(); } catch(Exception e) {}
				}
				
				return qnacount;
			}
			
		// 문의글 목록 구해오기 (List 객체)
			public List<QnaDTO> getList(int start, int end){
				List<QnaDTO> qnalist = new ArrayList<QnaDTO>();
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = getConnection();
					
					String sql = "select * from (select rownum rnum, board.* from ";
						   sql += " (select * from qna where qna_activate='Y' order by qna_ref desc, ";
						   sql += "qna_seq asc) board ) where (rnum >= ? and rnum <= ?) and id is not null";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
					
					
					while(rs.next()) {
						QnaDTO qna = new QnaDTO();
						
						qna.setQna_num(rs.getInt("qna_num"));
						qna.setId(rs.getString("id"));
						qna.setQna_subject(rs.getString("qna_subject"));
						qna.setQna_content(rs.getString("qna_content"));
						qna.setQna_file(rs.getString("qna_file"));
						qna.setQna_ref(rs.getInt("qna_ref"));
						qna.setQna_lev(rs.getInt("qna_lev"));
						qna.setQna_seq(rs.getInt("qna_seq"));
						qna.setQna_writedate(rs.getTimestamp("qna_writedate"));
						qna.setQna_activate(rs.getString("qna_activate"));
					
						qnalist.add(qna);
					}
						   
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(rs != null) try { rs.close(); } catch(Exception e) {}
					if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
					if(con != null) try { con.close(); } catch(Exception e) {}
				}
				
				return qnalist;
			}
			
			
	// 검색된 데이터 갯수
			public int getFount(String sel, String find) {
				int result = 0;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = getConnection();
					
//					String sql = "select count(*) from (select rownum rnum, a.* from qna a, ";
//						   sql+= " (select qna_num from qna where "+sel+" like '%"+find+"%' and qna_activate='Y')b "; 
//						   sql+= " where a.qna_ref = b.qna_num )";
					
					String sql = "select count(*) from qna a, ";
					       sql+= " (select qna_num from qna where "+sel+" like '%"+find+"%')b ";
					       sql+= " where a.qna_ref = b.qna_num and qna_activate='Y'";
					
				    pstmt = con.prepareStatement(sql);
				    rs = pstmt.executeQuery();
				    System.out.println(sql);
                    
				    if(rs.next()) {
				    	result = rs.getInt(1);
				    }
				    
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(rs != null) try { rs.close(); } catch(Exception e) {}
					if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
					if(con != null) try { con.close(); } catch(Exception e) {}
				}
				
				return result;
			}
			
			
			// 검색 목록
			public List<QnaDTO> selectFqna(int startRow, int endRow, String sel,String find){
				List<QnaDTO> fqnalist = new ArrayList<QnaDTO>();
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = getConnection();
					
//					String sql = "select * from (select rownum rnum, a.* from qna a, ";
//	                	   sql+= " (select qna_num from qna where "+sel+" like '%"+find+"%' )b "; 
//	                       sql+= " where a.qna_ref = b.qna_num and qna_activate='Y' order by qna_ref desc, qna_seq asc) ";
//	                       sql+= " where rnum>=? and rnum<=?";
					
					String sql = "select * from (select rownum rnum, a.* from qna a, ";
						   sql+= " (select qna_num from qna where "+sel+" like '%"+find+"%') b "; 
						   sql+= " where a.qna_ref = b.qna_num  and qna_activate='Y' order by qna_ref desc, qna_seq asc) "; 
						   sql+= " where rnum >= ? and rnum <= ? ";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						QnaDTO qna = new QnaDTO();
						qna.setQna_num(rs.getInt("qna_num"));
						qna.setId(rs.getString("id"));
						qna.setQna_subject(rs.getString("qna_subject"));
						qna.setQna_content(rs.getString("qna_content"));
						qna.setQna_file(rs.getString("qna_file"));
						qna.setQna_ref(rs.getInt("qna_ref"));
						qna.setQna_lev(rs.getInt("qna_lev"));
						qna.setQna_seq(rs.getInt("qna_seq"));
						qna.setQna_writedate(rs.getTimestamp("qna_writedate"));
						qna.setQna_activate(rs.getString("qna_activate"));
					
						fqnalist.add(qna);
					}
					       
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(rs != null) try { rs.close(); } catch(Exception e) {}
					if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
					if(con != null) try { con.close(); } catch(Exception e) {}	
				}
				
				return fqnalist;
			}
			
	
	// 상세 정보 구하기
	public QnaDTO getDetail(int qna_num) {
		QnaDTO qna = new QnaDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select * from qna where qna_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qna.setQna_num(rs.getInt("qna_num"));
				qna.setId(rs.getString("id"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_file(rs.getString("qna_file"));
				qna.setQna_ref(rs.getInt("qna_ref"));
				qna.setQna_lev(rs.getInt("qna_lev"));
				qna.setQna_seq(rs.getInt("qna_seq"));
				qna.setQna_writedate(rs.getTimestamp("qna_writedate"));
				qna.setQna_activate(rs.getString("qna_activate"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch(Exception e){}
			if(pstmt != null)try {pstmt.close();}catch(Exception e){}
			if(con != null)try {con.close();}catch(Exception e){}
		}
		
		
		return qna;
	}
	
	// 답글 작성
	public int qnaReply(QnaDTO qna) {
		int result = 0,  max_qna_seq = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		// 부모글에 대한 정보
		int qna_ref = qna.getQna_ref();
		int qna_lev = qna.getQna_lev();
		int qna_seq = qna.getQna_seq();
	 
	    try {
	    	con = getConnection();
	    	
	    	String sql = "select max(qna_seq) from qna where qna_ref=?";
	    	
	    	pstmt = con.prepareStatement(sql);
	    	pstmt.setInt(1, qna_ref);
	    	rs = pstmt.executeQuery();
	    	
	    	if(rs.next()) {
	    		max_qna_seq = rs.getInt(1);
	    	}
	    	
	    	sql = "insert into qna values(qna_seq.nextval,?,?,?,?,?,?,?,sysdate,?)";
	    	
	    	pstmt = con.prepareStatement(sql);
	    	pstmt.setString( 1, qna.getId() );
	    	pstmt.setString( 2, qna.getQna_subject() );
	    	pstmt.setString( 3, qna.getQna_content() );
	    	pstmt.setString( 4, qna.getQna_file() );
            pstmt.setInt(5, qna_ref);	    			
            pstmt.setInt(6, qna_lev + 1);	    			
            pstmt.setInt(7, max_qna_seq + 1);	    			
            pstmt.setString(8, "Y");
            
            result = pstmt.executeUpdate();
	    
            System.out.println("id:"+qna.getId());
	    
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	if(rs != null)try {rs.close();}catch(Exception e){}
	    	if(pstmt != null)try {pstmt.close();}catch(Exception e){}
			if(con != null)try {con.close();}catch(Exception e){}
	    }
		
		return result;
	}
	
	
	// 문의글 수정
	public int update(QnaDTO qna) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "update qna set qna_subject=?,qna_content=?,qna_file=? "; 
			       sql+= " where qna_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1 , qna.getQna_subject());
			pstmt.setString(2 , qna.getQna_content());
			pstmt.setString(3 , qna.getQna_file());
			pstmt.setInt(4 , qna.getQna_num());
			result = pstmt.executeUpdate();
			       
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)try {pstmt.close();}catch(Exception e){}
			if(con != null)try {con.close();}catch(Exception e){}
		}
		
		return result;
	}
	
	
	// 문의글 숨김
	public int delete(int qna_num){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = getConnection();
			
			String sql = "update qna set qna_activate='N' where qna_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1 , qna_num);
			pstmt.executeUpdate();
			
			sql = "update qna set qna_activate='N' where qna_ref=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1 , qna_num);
			
			result = pstmt.executeUpdate();
			
		
			System.out.println("result:"+result);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)try {pstmt.close();}catch(Exception e){}
			if(con != null)try {con.close();}catch(Exception e){}
		}
		
		return result;
	}
}
