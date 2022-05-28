package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;
import dto.QnaDTO;;

public class MemberDAO {

	// 싱글톤 : 객체 생성을 한번만 수행하는 것.
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() { // 정적 메소드
		return instance;
	}

	// 컨넥션풀에서 컨넥션을 구해오는 메소드
	private Connection getConnection() throws Exception {
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	// 회원가입
	public int insert(MemberDTO member) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			
			String sql = "insert into member values (?,?,?,?,?,?,?,?,sysdate,'Y')";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone1());
			pstmt.setString(5, member.getPhone2());
			pstmt.setString(6, member.getPhone3());
			pstmt.setString(7, member.getMailid());
			pstmt.setString(8, member.getDomain());

			result = pstmt.executeUpdate();			
			
			System.out.println("12");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return result;
	}

	// ID중복 검사(ajax)
	public int idcheck(String id) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select * from  member where id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // SQL문 실행

			if (rs.next()) { // 중복 ID
				result = 1;
				System.out.println("중복아이디임");
			} else { // 사용 가능한 ID
				result = -1;
				System.out.println("중복아이디가 아님");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return result;
	}

	// 로그인(회원인증)
	public int memberAuth(String id, String passwd) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select * from (select * from member where member_activate='Y') where id=? and passwd=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery(); // SQL문 실행

			if (rs.next()) { // 회원 인증 성공
				result = 1;
			} else { // 회원 인증 실패
				result = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return result;
	}

	// 회원 1명의 상세정보 구하기 : 수정품폼, 수정, 삭제
	public MemberDTO getDetail(String id) {
		MemberDTO member = new MemberDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select * from member where id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // SQL문 실행

			if (rs.next()) {
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setMailid(rs.getString("mailid"));
				member.setDomain(rs.getString("domain"));
				member.setRegdate(rs.getTimestamp("regdate"));
				member.setMember_activate(rs.getString("member_activate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return member;
	}

	// 회원정보 수정
	public int update(MemberDTO member) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "update member set name=?,phone1=?,phone2=?,phone3,mailid=?, ";
			sql += " domain=?, member_activate=? where id=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone1());
			pstmt.setString(5, member.getPhone2());
			pstmt.setString(6, member.getPhone3());
			pstmt.setString(7, member.getMailid());
			pstmt.setString(8, member.getDomain());
			pstmt.setTimestamp(9, member.getRegdate());
			pstmt.setString(10, member.getMember_activate());

			result = pstmt.executeUpdate(); // SQL문 실행

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return result;
	}

	// 아이디 찾기
	public MemberDTO idsearch(String name, String mailid, String domain) {
		MemberDTO member = new MemberDTO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select * from member where name=? and mailid=? and domain=? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, mailid);
			pstmt.setString(3, domain);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setMailid(rs.getString("mailid"));
				member.setRegdate(rs.getTimestamp("regdate"));
				member.setMember_activate(rs.getString("member_activate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

		return member;
	}

	// 비밀번호 찾기
	public MemberDTO passwdsearch(String id, String name, String mailid, String domain) {
		MemberDTO member = new MemberDTO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select * from member where id=? and name=? and mailid=? and domain=? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, mailid);
			pstmt.setString(4, domain);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setMailid(rs.getString("mailid"));
				member.setRegdate(rs.getTimestamp("regdate"));
				member.setMember_activate(rs.getString("member_activate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

		return member;
	}

	// 회원정지
	public int delete(String id) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "update member set member_activate ='N' where id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate(); // SQL문 실행

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return result;
	}
	
	// 회원 수 구하기
	public int getCount() {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select count(*) from member where member_activate = 'Y'";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (Exception e) {}
			if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
			if (con != null) try {con.close();} catch (Exception e) {}
		}
		
		return result;
	}
	
	// 회원 목록 구해오기
	public List<MemberDTO> getList(int start, int end){
		List<MemberDTO> memberlist = new ArrayList<MemberDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select * from (select rownum rnum, member.* from ";
				   sql += " (select * from member where member_activate='Y' order by regdate desc) member ) ";
				   sql += " where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setMailid(rs.getString("mailid"));
				member.setDomain(rs.getString("domain"));
				member.setRegdate(rs.getTimestamp("regdate"));
				member.setMember_activate(rs.getString("member_activate"));
				
				memberlist.add(member);
			}
				   
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return memberlist;
	}
	
	
	// 검색된 데이터 갯수
				public int getFount(String sel, String find) {
					int result = 0;
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					try {
						con = getConnection();
						
						String sql = "select count(*) from member where member_activate='Y' ";
							   sql+= " and "+sel+" like '%"+find+"%'";
						
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
				public List<MemberDTO> selectFqna(int startRow, int endRow, String sel,String find){
					List<MemberDTO> fmemberlist = new ArrayList<MemberDTO>();
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					try {
						con = getConnection();
						
						String sql = "select * from ( select rownum rnum, m.* from ";
						       sql += " (select * from member where member_activate='Y' ";
						       sql += " and "+sel+" like '%"+find+"%' order by regdate desc) m ) ";
						       sql += " where rnum >= ? and rnum <= ?";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, startRow);
						pstmt.setInt(2, endRow);
						rs = pstmt.executeQuery();
						
						while(rs.next()) {
							MemberDTO member = new MemberDTO();
							member.setId(rs.getString("id"));
							member.setPasswd(rs.getString("passwd"));
							member.setName(rs.getString("name"));
							member.setPhone1(rs.getString("phone1"));
							member.setPhone2(rs.getString("phone2"));
							member.setPhone3(rs.getString("phone3"));
							member.setMailid(rs.getString("mailid"));
							member.setDomain(rs.getString("domain"));
							member.setRegdate(rs.getTimestamp("regdate"));
							member.setMember_activate(rs.getString("member_activate"));
						
							fmemberlist.add(member);
						}
						       
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						if(rs != null) try { rs.close(); } catch(Exception e) {}
						if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
						if(con != null) try { con.close(); } catch(Exception e) {}	
					}
					
					return fmemberlist;
				}

}
