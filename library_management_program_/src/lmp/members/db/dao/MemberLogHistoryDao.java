package lmp.members.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lmp.members.db.vo.MemberLogHistoryVO;
import lmp.members.db.vo.MemberVO;

public class MemberLogHistoryDao extends MenuDao{

	/**
	 * 회원 로그인시 로그기록 추가
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void add(MemberVO memberVO) throws SQLException{
		
		Connection conn = getConnection();
		
		String sql = "INSERT INTO member_log_history(mem_log_id, mem_num) VALUES(mem_log_id_seq.nextval, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, memberVO.getNum());
		
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 회원 로그아웃시 로그정보 수정
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void update(MemberLogHistoryVO memLogVO) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql =  "Update member_log_history SET logout_time = to_char(sysdate, 'yyyy.mm.dd hh24:mi') WHERE mem_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setInt(1,memLogVO.getMemberVO().getNum());
			
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
	/**
	 * 현재 로그인 정보 가져오기
	 * 
	 * logout_time is null 정보
	 * 
	 * @return MemberLogHistoryVO
	 * @throws SQLException
	 */
	public MemberLogHistoryVO getLog() throws SQLException {
		String sql = "SELECT * FROM member_log_history JOIN members USING(mem_num) WHERE logout_time IS NULL";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MemberLogHistoryVO memLogVO = null;
		while (rs.next()) {
			memLogVO = new MemberLogHistoryVO(rs.getInt("mem_log_id"), new MemberVO(rs.getInt("mem_num"),
																				rs.getString("mem_name"),
																				rs.getString("mem_id"),
																				rs.getString("mem_pw"),
																				rs.getString("mem_birthday"),
																				rs.getString("mem_sex"),
																				rs.getString("mem_phone"),
																				rs.getString("mem_email"),
																				rs.getString("mem_address"),
																				rs.getString("mem_registrationdate"),
																				rs.getString("mem_note")),
																		rs.getString("login_time"), 
																		rs.getString("logout_time"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memLogVO;
	}
	
	

	/**
	 * 해당 회원아이디 로그 기록 삭제
	 * 
	 * @param mem_id
	 * @throws SQLException
	 */
	public void delete(Integer mem_id) throws SQLException {
		String sql = "DELETE FROM member_log_history WHERE mem_id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mem_id);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
	
}
