package lmp.admin.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.admin.db.vo.AdminVO;

public class AdminDao extends MenuDao{

	/**
	 * 관리자 dao
	 */
	
	/**
	 * 관리자 목록 추가
	 * 
	 * 등록날짜 default 현재날짜
	 * 
	 * @param adminVO
	 * @throws SQLException
	 */
	public void add(AdminVO adminVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO admins("
										+ "admin_num,"
										+ "admin_name,"
										+ "admin_pw,"
										+ "admin_phone,"
										+ "admin_email,"
										+ "admin_address,"
										+ "admin_note) "
										+ "VALUES(admin_num_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1, adminVO.getName());
		pstmt.setString(2, adminVO.getPw());
		pstmt.setString(3, adminVO.getPhone());
		pstmt.setString(4, adminVO.getEmail());
		pstmt.setString(5, adminVO.getAddress());
		pstmt.setString(6, adminVO.getNote());
			
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
		
	}

	
	/**
	 * 관리자 정보 업데이트
	 * 
	 * 이름, 비밀번호, 연락처, 이메일, 주소, 비고
	 * 
	 * @param adminVO
	 * @throws SQLException
	 */
	public void update(AdminVO adminVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " admins "
					+ "SET"
					+ " admin_name = ?,"
					+ " admin_pw = ?,"
					+ " admin_phone = ?,"
					+ " admin_email = ?,"
					+ " admin_address = ?,"
					+ " admin_note = ? "
					+ "WHERE"
					+ " admin_num = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,adminVO.getName());
		pstmt.setString(2,adminVO.getPw());
		pstmt.setString(3,adminVO.getPhone());
		pstmt.setString(4,adminVO.getEmail());
		pstmt.setString(5,adminVO.getAddress());
		pstmt.setString(6,adminVO.getNote());
		pstmt.setInt(7,adminVO.getNum());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 전체 직원 목록 가져오기
	 * 
	 * @return ArrayList<AdminVO> adminList
	 */
	public ArrayList<AdminVO> get() throws SQLException {
		String sql = "SELECT * FROM Admins";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<AdminVO> adminList = new ArrayList<>();
		while (rs.next()) {
			adminList.add(new AdminVO(
								rs.getInt("admin_num"),
								rs.getString("admin_name"),
								rs.getString("admin_pw"),
								rs.getString("admin_phone"),
								rs.getString("admin_email"),
								rs.getString("admin_address"),
								rs.getString("admin_registrationdate"),
								rs.getString("admin_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return adminList;
	}
	
	/**
	 * 관리자 로그인시
	 * 
	 * @param admin_num
	 * @return AdminVO
	 * @throws SQLException
	 */
	public AdminVO getAdminInfo(int admin_num) throws SQLException {
		String sql = "SELECT * FROM admins WHERE admin_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, admin_num);
		ResultSet rs = pstmt.executeQuery();
		AdminVO adminVO = null;
		while (rs.next()) {
			adminVO = new AdminVO(
								rs.getInt("admin_num"),
								rs.getString("admin_pw")
								);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return adminVO;
	}
	
	/**
	 * 관리자 검색시
	 * 
	 * header
	 * 1 - 사원번호
	 * 2 - 사원이름
	 * 3 - 사원연락처
	 * 4 - 사원 등록일
	 * 
	 * @param header
	 * @param searchStr
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<AdminVO> get(int header, String searchStr) throws SQLException {
		
		StringBuilder sql = new StringBuilder(selectSql(header));

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, "%"+searchStr+"%");
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<AdminVO> adminList = new ArrayList<>();
		while (rs.next()) {
			adminList.add(new AdminVO(
								rs.getInt("admin_num"),
								rs.getString("admin_name"),
								rs.getString("admin_pw"),
								rs.getString("admin_phone"),
								rs.getString("admin_email"),
								rs.getString("admin_address"),
								rs.getString("admin_registrationdate"),
								rs.getString("admin_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return adminList;
	}
	

	/**
	 * 직원 삭제
	 * 
	 * @param adminVO
	 * @throws SQLException
	 */
	public void delete(String adminNum) throws SQLException {
		
		String sql = "DELETE FROM admins WHERE admin_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(adminNum));
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	public StringBuilder selectSql(int header) {
		StringBuilder sql = new StringBuilder();
		String num = "SELECT * FROM admins WHERE admin_num LIKE ?";
		String name = "SELECT * FROM admins WHERE admin_name LIKE ?";
		String phone = "SELECT * FROM admins WHERE admin_phone LIKE ?";
		String registrationdate = "SELECT * FROM admins WHERE admin_registrationdate LIKE ?";
		if (header == 1) {
			sql.append(num);
		} else if (header == 2) {
			sql.append(name);
		} else if (header == 3) {
			sql.append(phone);
		} else if (header == 4) {
			sql.append(registrationdate);
		}
		return sql;
	}


	public ArrayList<AdminVO> getByPhone(String phone, String admin_id) throws SQLException {

		String sql = "SELECT * FROM admins WHERE admin_phone LIKE ? AND admin_id <> ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%" + phone + "%");
		pstmt.setString(2, admin_id);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<AdminVO> adminList = new ArrayList<>();
		while (rs.next()) {
			adminList.add(new AdminVO(
								rs.getInt("admin_num"),
								rs.getString("admin_name"),
								rs.getString("admin_pw"),
								rs.getString("admin_phone"),
								rs.getString("admin_email"),
								rs.getString("admin_address"),
								rs.getString("admin_registrationdate"),
								rs.getString("admin_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return adminList;
	}
	
	public ArrayList<AdminVO> getByEmail(String email, String admin_num) throws SQLException {

		String sql = "SELECT * FROM admins WHERE admin_email LIKE ? AND admin_num <> ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%" + email + "%");
		pstmt.setString(2, admin_num);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<AdminVO> adminList = new ArrayList<>();
		while (rs.next()) {
			adminList.add(new AdminVO(
								rs.getInt("admin_num"),
								rs.getString("admin_name"),
								rs.getString("admin_pw"),
								rs.getString("admin_phone"),
								rs.getString("admin_email"),
								rs.getString("admin_address"),
								rs.getString("admin_registrationdate"),
								rs.getString("admin_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return adminList;
	}

}
