package lmp.members.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.members.db.vo.ReadingRoomVO;

public class ReadingRoomDao extends MenuDao{

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
		
		ArrayList<ReadingRoomVO> readingrooms = new ArrayList<>();
		ReadingRoomVO readingRoomVO = null; 
		while (rs.next()) {
			readingRoomVO = new ReadingRoomVO(rs.getInt("seat_num"), rs.getString("table_divider"));
			readingrooms.add(readingRoomVO);
		}
		
		
		rs.close();
		pstmt.close();
		conn.close();

		return readingrooms;
	}
	
}
