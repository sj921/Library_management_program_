package lmp.members.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Collections;

import lmp.members.db.vo.MemberVO;
import lmp.members.db.vo.ReadingRoomVO;
import lmp.members.db.vo.SeatUseDetailVO;



public class SeatUseDetailDao extends MenuDao{
	
	/**
	 * 열람실 이용내역 dao
	 */

	/**
	 * 열람실 이용내역 추가
	 * use_id, mem_num, seat_num 등록
	 * 
	 * start_time default 현재시간으로 등록
	 * 
	 * @param sudVO
	 * @throws SQLException
	 */
	public void add(Integer mem_num, Integer seat_num) throws SQLException{
		Connection conn = getConnection();
		
		String sql = "INSERT INTO seat_use_details (use_id, mem_num, seat_num) VALUES(user_detail_id_seq.nextval, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, mem_num);
		pstmt.setInt(2, seat_num);
			
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
	}

	/**
	 * 열람실 이용내역 업데이트
	 * 
	 * 퇴실 , 강제퇴실 할경우
	 * end_time 현재시간으로 수정.
	 * 
	 * @param sudVO
	 * @throws SQLException
	 */
	public void update(SeatUseDetailVO sudVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " seat_use_details "
					+ "SET"
					+ " end_time = to_char(sysdate, 'yyyy.mm.dd hh24:mi') "
					+ "WHERE"
					+ " use_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,sudVO.getUse_id());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	
	/**
	 * 열람실 좌석 정보
	 * 
	 * @return ArrayList<ReadingRoomVO>
	 * @throws SQLException
	 */
	public ArrayList<ReadingRoomVO> getRoomInfo() throws SQLException {
		
		String sql = "SELECT * FROM readingroom";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<ReadingRoomVO> seatList = new ArrayList<>();
		while (rs.next()) {
			
			seatList.add(new ReadingRoomVO(rs.getInt("seat_num"),rs.getString("table_divider")));
			
		}
		
		Collections.sort(seatList);
		
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return seatList;
		
	}
	
	/**
	 * 열람실 이용중인 좌석 가져오기
	 * 
	 * @return ArrayList<SeatUseDetailVO> sudList
	 */
	public ArrayList<SeatUseDetailVO> getUse() {
		String sql = "SELECT * FROM seat_use_details JOIN members USING(mem_num) JOIN readingroom USING(seat_num) WHERE end_time is null";
		ArrayList<SeatUseDetailVO> sudList = new ArrayList<>();
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		){
			while (rs.next()) {
				sudList.add(new SeatUseDetailVO(rs.getInt("use_id"),
						new MemberVO(rs.getInt("mem_num"), rs.getString("mem_name")),
						new ReadingRoomVO(rs.getInt("seat_num"), rs.getString("table_divider")),
						rs.getString("start_time"), rs.getString("end_time")));
			}
		} catch (SQLException e) {

		}
		return sudList;
		
	}
	
	public SeatUseDetailVO searchSeat(int seat_num) throws SQLException {
		String sql = "SELECT * FROM seat_use_details JOIN members USING(mem_num) JOIN readingroom USING(seat_num) WHERE seat_num = ? AND end_time is null";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, seat_num);
		ResultSet rs = pstmt.executeQuery();
		SeatUseDetailVO sudVO = null;
		while (rs.next()) {
				sudVO =	new SeatUseDetailVO(
							rs.getInt("use_id"),
							new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_name")
								),
							new ReadingRoomVO(
								rs.getInt("seat_num"),
								rs.getString("table_divider")
								),
							rs.getString("start_time"),
							rs.getString("end_time")
							);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return sudVO;
	}
	
	public SeatUseDetailVO getUsingInfo(Integer mem_num) throws SQLException {
		String sql = "SELECT * FROM seat_use_details JOIN members USING(mem_num) JOIN readingroom USING(seat_num) WHERE mem_num = ? AND end_time is null";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mem_num);
		ResultSet rs = pstmt.executeQuery();
		SeatUseDetailVO sudVO = null;
		while (rs.next()) {
				sudVO = new SeatUseDetailVO(
							rs.getInt("use_id"),
							new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_name")
								),
							new ReadingRoomVO(
								rs.getInt("seat_num"),
								rs.getString("table_divider")
								),
							rs.getString("start_time"),
							rs.getString("end_time")
							);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return sudVO;
	}
	
	public SeatUseDetailVO getCheckOutInfo(Integer use_id) throws SQLException {
		String sql = "SELECT * FROM seat_use_details JOIN members USING(mem_num) JOIN readingroom USING(seat_num) WHERE use_id = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, use_id);
		ResultSet rs = pstmt.executeQuery();
		SeatUseDetailVO sudVO = null;
		while (rs.next()) {
				sudVO = new SeatUseDetailVO(
							rs.getInt("use_id"),
							new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_name")
								),
							new ReadingRoomVO(
								rs.getInt("seat_num"),
								rs.getString("table_divider")
								),
							rs.getString("start_time"),
							rs.getString("end_time")
							);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return sudVO;
	}

}
