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
import dto.ReplyDTO;

public class ReplyDAO {

	private static ReplyDAO instance = new ReplyDAO();

	public static ReplyDAO getInstance() {
		return instance;
	}
	
	// 컨넥션풀에서 컨넥션을 구해오는 메소드 
	private Connection getConnection() throws Exception{
		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
  		return ds.getConnection();
	}
	//댓글 입력하기
	public int insert(ReplyDTO reply) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql="insert into reply values(reply_seq.nextval,?,?,";
			       sql+="reply_seq.nextval,0,0,sysdate,?,'Y')";
       
       		pstmt = con.prepareStatement(sql);
       		pstmt.setString(1, reply.getRe_content());
       		pstmt.setString(2, reply.getId());
       		pstmt.setInt(3, reply.getNum());

			result = pstmt.executeUpdate();		// SQL문 실행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
 		}		
		return result;
	}
	
	// 재 댓글 입력하기
	public int insertReply(ReplyDTO reply) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int re_ref = reply.getRe_ref();
		int re_lev = reply.getRe_lev();
		int re_seq = reply.getRe_seq();
		
		try {
			con = getConnection();

			sql="update reply set re_seq = re_seq+1 ";
		    sql+=" where re_ref=? and re_seq > ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			pstmt.executeUpdate();		// SQL문 실행
		
			System.out.println("여기까지는 성공2");
			
			sql="insert into reply values(reply_seq.nextval,?,?,";
			sql+="?,?,?,sysdate,?,'Y')";
       
       		pstmt = con.prepareStatement(sql);
       		pstmt.setString(1, reply.getRe_content());
       		pstmt.setString(2, reply.getId());
       		pstmt.setInt(3, re_ref);
       		pstmt.setInt(4, re_lev + 1);
       		pstmt.setInt(5, re_seq + 1);
       		pstmt.setInt(6, reply.getNum());

			result = pstmt.executeUpdate();		// SQL문 실행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
 		}		
		return result;
	}	
	
	// 댓글 갯수 구하기
	public int getReplyCount(int num) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql="select count(*) from reply where num=?";
			
			pstmt = con.prepareStatement(sql);
	   		pstmt.setInt(1, num);
			rs = pstmt.executeQuery();		// SQL문 실행
			
			if(rs.next()) {
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
	
	// 댓글목록
	public List<ReplyDTO> getReplyList(int start, int end, int num){
		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		System.out.println("DAO"+replysort);
		try {
			con = getConnection();

			String sql="select * from ( select rownum rnum, b.* from ";			
				   sql+=" ( select * from reply where num=? order by re_ref desc, re_seq asc) b ) ";
				   sql+=" where rnum >= ? and rnum <= ?";
	   
	   		pstmt = con.prepareStatement(sql);
	   		pstmt.setInt(1, num);
	   		pstmt.setInt(2, start);
	   		pstmt.setInt(3, end);
	   		rs = pstmt.executeQuery();		// SQL문 실행
	   		
	   		while(rs.next()) {
	   			ReplyDTO reply = new ReplyDTO();
	   			
	   			reply.setRe_num(rs.getInt("re_num"));
	   			reply.setRe_content(rs.getString("re_content"));
	   			reply.setId(rs.getString("id"));
	   			reply.setRe_ref(rs.getInt("re_ref"));
	   			reply.setRe_lev(rs.getInt("re_lev"));
	   			reply.setRe_seq(rs.getInt("re_seq"));
	   			reply.setRe_writedate(rs.getTimestamp("re_writedate"));
	   			reply.setNum(rs.getInt("num"));
	   			reply.setRe_activate(rs.getString("re_activate"));

	   			list.add(reply);
	   		}
	   
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
		}		
		return list;
	}


	// 자식댓글 체크
	public int childReplycheck(int re_num, int re_ref, int re_lev, int re_seq) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql="select * from reply where re_ref=? and re_lev=? and re_seq=?";
			
			pstmt = con.prepareStatement(sql);
	   		pstmt.setInt(1, re_ref);
	   		pstmt.setInt(2, re_lev+1);
	   		pstmt.setInt(3, re_seq+1);
			rs = pstmt.executeQuery();		// SQL문 실행
			
			if(rs.next()) {
				System.out.println("자식 댓글이 있음"+rs.getInt("re_num"));
				result = 1;
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

	//댓글 비활성화
	public int deactivateReply(int re_num) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = getConnection();

			sql="update reply set re_activate = 'N' ";
		    sql+=" where re_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			result = pstmt.executeUpdate();		// SQL문 실행
		
			System.out.println("dao 댓글 비활성화 성공 ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
 		}		
		return result;
	}	
	
	//댓글 삭제
	public int deleteReply(int re_num) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = getConnection();

			sql="delete from reply where re_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			result = pstmt.executeUpdate();		// SQL문 실행
		
			System.out.println("dao 댓글 삭제 성공 ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close();}catch(Exception e) {}
			if(con != null) try { con.close();}catch(Exception e) {}
 		}		
		return result;
	}	
}
