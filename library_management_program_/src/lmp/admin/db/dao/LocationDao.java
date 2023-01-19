package lmp.admin.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lmp.admin.db.vo.LocationVO;

public class LocationDao extends MenuDao{
	/**
	 * 도서위치 dao
	 */
	
	
	/**
	 * 도서 위치 정보 추가
	 * 
	 * @param locationVO
	 * @throws SQLException
	 */
	public void add(LocationVO locationVO) throws SQLException{
		Connection conn = getConnection(); 
		
		String sql = "INSERT INTO locations VALUES(?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1, locationVO.getLocID());
		pstmt.setString(2, locationVO.getLocName());
		
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
}
