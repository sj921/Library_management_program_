package lmp.members.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.members.db.vo.BookVO;
import lmp.members.db.vo.MemberLogHistoryVO;
import lmp.members.db.vo.MemberVO;
import lmp.members.db.vo.ReadingRoomVO;
import lmp.members.db.vo.SeatUseDetailVO;


public abstract class MenuDao {
	
	/**
	 * DAO 추상 클래스
	 */
	
	private static String url = "jdbc:oracle:thin:@192.168.0.23:1521:XE";
	private static String user = "library";
	private static String pw = "1234";
	
	/**
	 * Connection 연결 부분 분리 메서드화
	 * 
	 * @return conn
	 */
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, pw);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Class 못찾음");
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
		}			
		return null;
	}
}
