package lmp.admin.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.admin.db.vo.AdminLogHistoryVO;
import lmp.admin.db.vo.AdminVO;
import lmp.admin.db.vo.BookVO;
import lmp.admin.db.vo.CheckOutVO;
import lmp.admin.db.vo.MemberVO;
import lmp.admin.db.vo.SeatUseDetailVO;


public abstract class MenuDao {

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
