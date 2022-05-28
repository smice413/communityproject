package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardListDTO;

public class BoardListDAO {
	
	private static BoardListDAO instance = new BoardListDAO();
	
	public static BoardListDAO getInstance() {
		return instance;
	}
	
	// 컨넥션 연결
	private Connection getConnection() throws Exception {
		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
  		return ds.getConnection();
	}
	//개설요청신청글 입력값
	public int insert(BoardListDTO bl) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String sql="insert into boardlist values(boardlist_seq.nextval,?,?,?,?,sysdate,?,?,?)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, bl.getBl_name());
			pstmt.setString(2, bl.getBl_exp());
			pstmt.setString(3, bl.getBl_private());
			pstmt.setString(4, bl.getBl_passwd());
			pstmt.setString(5, "N");
			pstmt.setString(6, bl.getBl_category());
			pstmt.setString(7, bl.getId()); 
			
			result = pstmt.executeUpdate(); //SQL문 실행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		return result;
	}
	
	// 총 데이터 갯수(개설승인된 것만 출력됨)
	public int getCount() {
		int blcount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql ="select count(*) from boardlist where bl_activate='Y'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //SQL문 실행
			
			if(rs.next()) {
				blcount= rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(Exception e) {}
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		return blcount;
	}
	// 관리자용 게시판 목록 출력을 위한 데이터 검색(승인된 것만 리스트 출력)
	public List<BoardListDTO> getList(int startRow, int endRow){
		List<BoardListDTO> bllist = new ArrayList<BoardListDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql ="select * from (select rownum rnum, board.* from ";
				   sql+=" (select * from boardlist where bl_activate='Y' order by bl_code desc) board)";
				   sql+=" where rnum >= ? and rnum <= ?";
				   
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery(); // SQL문 실행
			
			
		while(rs.next()) {
			BoardListDTO bl = new BoardListDTO();
			
			bl.setBl_code(rs.getInt("bl_code"));
			bl.setBl_name(rs.getString("bl_name"));
			bl.setBl_exp(rs.getString("bl_exp"));
			bl.setBl_private(rs.getString("bl_private"));
			bl.setBl_passwd(rs.getString("bl_passwd"));
			bl.setBl_regdate(rs.getTimestamp("bl_regdate"));
			bl.setBl_activate(rs.getString("bl_activate"));
			bl.setBl_category(rs.getString("bl_category"));
			bl.setId(rs.getString("id"));
			
			bllist.add(bl);
		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(Exception e) {}
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		return bllist;
	}
	
	// 총 데이터 갯수(개설승인 안된 것만 출력됨)
	public int getCountN() {
		int blcountN = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql ="select count(*) from boardlist where bl_activate='N'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //SQL문 실행
			
			if(rs.next()) {
				blcountN= rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(Exception e) {}
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		return blcountN;
	}
	// 관리자용 개설승인 처리을 위한 데이터 검색(개설승인 안된 것만 리스트 출력)
	public List<BoardListDTO> getListN(int startRow, int endRow){
		List<BoardListDTO> bllistN = new ArrayList<BoardListDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql ="select * from (select rownum rnum, board.* from ";
			sql+=" (select * from boardlist where bl_activate='N' order by bl_code desc) board)";
			sql+=" where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery(); // SQL문 실행
			
			
			while(rs.next()) {
				BoardListDTO bl = new BoardListDTO();
				
				bl.setBl_code(rs.getInt("bl_code"));
				bl.setBl_name(rs.getString("bl_name"));
				bl.setBl_exp(rs.getString("bl_exp"));
				bl.setBl_private(rs.getString("bl_private"));
				bl.setBl_passwd(rs.getString("bl_passwd"));
				bl.setBl_regdate(rs.getTimestamp("bl_regdate"));
				bl.setBl_activate(rs.getString("bl_activate"));
				bl.setBl_category(rs.getString("bl_category"));
				bl.setId(rs.getString("id"));
				
				bllistN.add(bl);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(Exception e) {}
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		return bllistN;
	}
	// 개설 요청 처리(관리자 전용)
	public int Request(int bl_code) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con=getConnection();
			String sql="update boardlist set bl_activate = 'Y' where bl_code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bl_code);
			result= pstmt.executeUpdate();	//SQL문 실행
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		return result;
	}
	
	
	// 게시판 삭제(관리자 전용)
	public int delete(int bl_code) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con=getConnection();
			String sql="update boardlist set bl_activate = 'N' where bl_code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bl_code);
			result= pstmt.executeUpdate();	//SQL문 실행
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		return result;
	}
	
	// 게시판 수정폼으로 상세정보 구해오기
	public BoardListDTO getDetail(int bl_code) {
		BoardListDTO bl = new BoardListDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql = "select * from boardlist where bl_code = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, bl_code);
			rs = pstmt.executeQuery(); // SQL문 실행
			
			if(rs.next()) {
				
				bl.setBl_code(rs.getInt("bl_code"));
				bl.setBl_name(rs.getString("bl_name"));
				bl.setBl_exp(rs.getString("bl_exp"));
				bl.setBl_private(rs.getString("bl_private"));
				bl.setBl_passwd(rs.getString("bl_passwd"));
				bl.setBl_regdate(rs.getTimestamp("bl_regdate"));
				bl.setBl_activate(rs.getString("bl_activate"));
				bl.setBl_category(rs.getString("bl_category"));
				bl.setId(rs.getString("id"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(Exception e) {}
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		
		
		return bl;
	}
	// 게시판 수정 처리
	public int update(BoardListDTO bl) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String sql ="update boardlist set bl_name=?,bl_private=?,bl_exp=?,bl_category=? where bl_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bl.getBl_name());
			pstmt.setString(2, bl.getBl_private());
			pstmt.setString(3, bl.getBl_exp());
			pstmt.setString(4, bl.getBl_category());
			pstmt.setInt(5, bl.getBl_code());
			
			result=pstmt.executeUpdate(); //SQL문 실행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null) try {con.close();}catch(Exception e) {}
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
	// 게시판 이름 중복 검사
		public int bl_namecheck(String bl_name) {
			int result = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = getConnection();

				String sql = "select * from boardlist where bl_name=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bl_name);
				rs = pstmt.executeQuery(); // SQL문 실행

				if (rs.next()) { // 중복 제목
					result = 1;
				} else { 		// 사용 가능한 제목
					result = -1;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)	try {rs.close();} catch (Exception e) {}
				if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
				if (con != null)try {con.close();} catch (Exception e) {}
			}
			
			return result;
		}
}
