package lmp.members.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FontDao extends MenuDao{

	/**
	 * 활성화된 폰트 정보
	 * 
	 * @return font_name
	 * @throws SQLException
	 */
	public String getFont() throws SQLException {

		String sql = "SELECT * FROM fonts WHERE font_activation = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "1");
		ResultSet rs = pstmt.executeQuery();

		String getFont = "";
		while (rs.next()) {
			getFont = rs.getString("font_size");
		}

		rs.close();
		pstmt.close();
		conn.close();

		return getFont;
	}

	/**
	 * 모든 폰트 정보
	 * 
	 * @return ArrayList<String>
	 * @throws SQLException
	 */
	public ArrayList<String> getFonts() throws SQLException {

		String sql = "SELECT * FROM fonts";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		ArrayList<String> getFonts = new ArrayList<>();
		while (rs.next()) {
			getFonts.add(rs.getString("font_size"));
		}

		rs.close();
		pstmt.close();
		conn.close();

		return getFonts;
	}

	/**
	 * 폰트 추가
	 * 
	 * @param font_size
	 * @throws SQLException
	 */
	public void addTheme(String font_size) throws SQLException {

		String sql = "INSERT INTO fonts VALUES(font_id_seq.nextval, ?,'0')";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, font_size);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	/**
	 * 활성화 폰트 설정
	 * 
	 * @param font_size
	 * @throws SQLException
	 */
	public void setFont(String font_size) throws SQLException {

		String sql1 = "UPDATE fonts SET font_activation = '0'";
		String sql2 = "UPDATE fonts SET font_activation = '1' WHERE font_size = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql1);
		pstmt.executeUpdate();

		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, font_size);
		pstmt.executeUpdate();

		conn.commit();

		pstmt.close();
		conn.close();
	}

}
