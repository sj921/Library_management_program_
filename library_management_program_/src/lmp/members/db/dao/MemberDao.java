package lmp.members.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.members.db.vo.MemberVO;



public class MemberDao extends MenuDao{
	
	
	/**
	 * 회원 등록
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void add(MemberVO memberVO) throws SQLException{
		
		Connection conn = getConnection();
		
		String sql = "INSERT INTO members("
										+ "mem_num,mem_name,mem_id,mem_pw,mem_birthday,mem_sex,mem_phone,mem_email,mem_address) "
										+ "VALUES(mem_num_seq.nextval,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberVO.getName());
		pstmt.setString(2, memberVO.getId());
		pstmt.setString(3, memberVO.getPw());
		pstmt.setString(4, memberVO.getBirthDay());
		pstmt.setString(5, memberVO.getSex());
		pstmt.setString(6, memberVO.getPhone());
		pstmt.setString(7, memberVO.getEmail());
		pstmt.setString(8, memberVO.getAddress());
			
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
		
		String sql =  "Update "
					+ "members "
					+ "SET "
					+ "mem_name = ?,"
					+ "mem_id = ?,"
					+ "mem_pw = ?,"
					+ "mem_phone = ?,"
					+ "mem_email = ?,"
					+ "mem_address = ? "
					+ "mem_updatedate = sysdate"
					+ "WHERE "
					+ "mem_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1,memberVO.getName());
		pstmt.setString(2,memberVO.getId());
		pstmt.setString(3,memberVO.getPw());
		pstmt.setString(4,memberVO.getPhone());
		pstmt.setString(5,memberVO.getEmail());
		pstmt.setString(6,memberVO.getAddress());
		pstmt.setInt(7,memberVO.getNum());
			
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 로그인시 회원정보
	 * 
	 * @param mem_id
	 * @return
	 * @throws SQLException
	 */
	public MemberVO getLoginInfo(String mem_id) throws SQLException {
		String sql = "SELECT * FROM members WHERE mem_id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mem_id);
		ResultSet rs = pstmt.executeQuery();

		MemberVO memberVO = null;
		while (rs.next()) {
			memberVO = new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_id"),
								rs.getString("mem_pw")
								);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberVO;
	}
	
	public MemberVO getName(Integer mem_num) throws SQLException {
		String sql = "SELECT * FROM members WHERE mem_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mem_num);
		ResultSet rs = pstmt.executeQuery();

		MemberVO memberVO = null;
		while (rs.next()) {
			memberVO = new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_name")
								);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberVO;
	}
	
	/**
	 * 해당되는 회원정보
	 * 
	 * header 
	 * 1 - id
	 * 2 - 연락처
	 * 3 - 이메일
	 * 
	 * @param header
	 * @param searchStr
	 * @return
	 * @throws SQLException
	 */
	public MemberVO getExist(int header, String searchStr) throws SQLException{
		
		String sql = selectSql(header).toString();
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchStr);
		ResultSet rs = pstmt.executeQuery();

		MemberVO memberVO = null;
		while (rs.next()) {
			memberVO = new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_id"),
								rs.getString("mem_pw")
								);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberVO;
	}
	
	public StringBuilder selectSql(int header) {
		StringBuilder sql = new StringBuilder();

		String id = "SELECT * FROM members WHERE mem_id = ?";
		String phone = "SELECT * FROM members WHERE mem_phone = ?";
		String email = "SELECT * FROM members WHERE mem_email = ?";
		if (header == 1) {
			sql.append(id);
		} else if (header == 2) {
			sql.append(phone);
		} else if (header == 3) {
			sql.append(email);
		}
		return sql;
	}

	/**
	 * 전체 회원 목록 가져오기
	 * 
	 * @return ArrayList<MemberVO> memberList
	 */
	public ArrayList get(int mem_num) throws SQLException {
		String sql = "SELECT * FROM members WHERE mem_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mem_num);
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
	 * 회원 삭제
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void delete(MemberVO memberVO) throws SQLException {
		String sql = "DELETE FROM members WHERE mem_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,memberVO.getNum());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
	
}
