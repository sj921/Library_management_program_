package lmp.admin.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.admin.db.vo.MemberVO;
import lmp.util.ShaPasswordEncoder;

public class MemberDao extends MenuDao{

	/*
	 * 회원 정보 dao
	 */
	
	ShaPasswordEncoder pwEncoder = new ShaPasswordEncoder();
	/**
	 * 회원 등록
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void add(MemberVO memberVO) throws SQLException{
		
		Connection conn = getConnection();
		
		String sql = "INSERT INTO members("
										+ "mem_num,"
										+ "mem_name,"
										+ "mem_id,"
										+ "mem_pw"
										+ "mem_birthday,"
										+ "mem_sex,"
										+ "mem_phone,"
										+ "mem_email,"
										+ "mem_address"
										+ "mem_note"
										+ ") VALUES(member_num_seq.nextval,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, memberVO.getName());
		pstmt.setString(2, memberVO.getId());
		pstmt.setString(3, memberVO.getPw());
		pstmt.setString(4, memberVO.getBirthDay());
		pstmt.setString(5, memberVO.getSex());
		pstmt.setString(6, memberVO.getPhone());
		pstmt.setString(7, memberVO.getEmail());
		pstmt.setString(8, memberVO.getAddress());
		pstmt.setString(9, memberVO.getNote());
		
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void update(MemberVO memberVO) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " members"
					+ " SET"
					+ " mem_name = ?,"
					+ " mem_phone = ?,"
					+ " mem_email = ?,"
					+ " mem_address = ?,"
					+ " mem_note = ?"
					+ " WHERE"
					+ " mem_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, memberVO.getName());
		pstmt.setString(2, memberVO.getPhone());
		pstmt.setString(3, memberVO.getEmail());
		pstmt.setString(4, memberVO.getAddress());
		pstmt.setString(5, memberVO.getNote());
		pstmt.setInt(6, memberVO.getNum());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
	public void resetPassword(String mem_num) throws Exception {
		
		Connection conn = getConnection();
 		String sql =  "UPDATE"
					+ " members"
					+ " SET"
					+ " mem_pw = ?"
					+ " WHERE"
					+ " mem_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, pwEncoder.encrypt("a123456789!"));
		pstmt.setString(2, mem_num);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 전체 회원 목록 가져오기
	 * 
	 * @return ArrayList<MemberVO> memberList
	 */
	public ArrayList get() throws SQLException {
		String sql = "SELECT * FROM members";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemberVO> memberList = new ArrayList<>();
		while (rs.next()) {
			memberList.add(new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_name"),
								rs.getString("mem_id"),
								rs.getString("mem_pw"),
								rs.getString("mem_birthday"),
								rs.getString("mem_sex"),
								rs.getString("mem_phone"),
								rs.getString("mem_email"),
								rs.getString("mem_address"),
								rs.getString("mem_registrationdate"),
								rs.getString("mem_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberList;
	}
	
	
	/**
	 * 회원 조건 검색
	 * header
	 * 1 : num - 회원번호
	 * 2 : name - 회원이름
	 * 3 : id - 회원 아이디
	 * 4 : phone - 회원 연락처
	 * 
	 * searchStr
	 * header에 해당하는 값
	 * 
	 * @param header
	 * @param searchStr
	 * @return ArrayList<MemberVO> memberList
	 */
	public ArrayList get(int header, String searchStr) throws SQLException {
		
		StringBuilder sql = new StringBuilder(selectSql(header));

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, "%"+searchStr+"%");
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemberVO> memberList = new ArrayList<>();
		while (rs.next()) {
			memberList.add(new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_name"),
								rs.getString("mem_id"),
								rs.getString("mem_pw"),
								rs.getString("mem_birthday"),
								rs.getString("mem_sex"),
								rs.getString("mem_phone"),
								rs.getString("mem_email"),
								rs.getString("mem_address"),
								rs.getString("mem_registrationdate"),
								rs.getString("mem_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberList;
	}
	
	
	public ArrayList<MemberVO> getByPhone(String phone, String mem_id) throws SQLException {
		
		String sql = "SELECT * FROM members WHERE mem_phone LIKE ? AND mem_id <> ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%" + phone + "%");
		pstmt.setString(2, mem_id);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemberVO> memberList = new ArrayList<>();
		while (rs.next()) {
			memberList.add(new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_name"),
								rs.getString("mem_id"),
								rs.getString("mem_pw"),
								rs.getString("mem_birthday"),
								rs.getString("mem_sex"),
								rs.getString("mem_phone"),
								rs.getString("mem_email"),
								rs.getString("mem_address"),
								rs.getString("mem_registrationdate"),
								rs.getString("mem_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberList;
	}
	
	
	public ArrayList<MemberVO> getByEmail(String email, String mem_id) throws SQLException {
		
		String sql = "SELECT * FROM members WHERE mem_email LIKE ? AND mem_id <> ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%" + email + "%");
		pstmt.setString(2, mem_id);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemberVO> memberList = new ArrayList<>();
		while (rs.next()) {
			memberList.add(new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_name"),
								rs.getString("mem_id"),
								rs.getString("mem_pw"),
								rs.getString("mem_birthday"),
								rs.getString("mem_sex"),
								rs.getString("mem_phone"),
								rs.getString("mem_email"),
								rs.getString("mem_address"),
								rs.getString("mem_registrationdate"),
								rs.getString("mem_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberList;
	}
	
	/**
	 * 해당하는 조건의 sql문 가져오기
	 * header
	 * 회원번호 - 1
	 * 회원이름 - 2
	 * 회원아이디 - 3
	 * 회원연락처 - 4
	 * 
	 * @param header
	 * @return StringBuilder sql
	 */
	public StringBuilder selectSql(int header) {
		StringBuilder sql = new StringBuilder();
		String num = "SELECT * FROM members WHERE mem_num LIKE ?";
		String name = "SELECT * FROM members WHERE mem_name LIKE ?";
		String id = "SELECT * FROM members WHERE mem_id LIKE ?";
		String phone = "SELECT * FROM members WHERE mem_phone LIKE ?";
		if (header == 1) {
			sql.append(num);
		} else if (header == 2) {
			sql.append(name);
		} else if (header == 3) {
			sql.append(id);
		} else if (header == 4) {
			sql.append(phone);
		}
		return sql;
	}

	/**
	 * 회원 삭제
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void delete(int mem_num) throws SQLException {
		String sql = "DELETE FROM members WHERE mem_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mem_num);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}

	
}
