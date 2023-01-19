package lmp.members.db.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lmp.members.db.vo.ImageVO;

public class ImageDao extends MenuDao {

	
	/**
	 * 이미지 정보
	 * 
	 * @param image_name
	 * @return ImageVO
	 * @throws SQLException
	 */
	public ImageVO getImage(String image_name) throws SQLException {
		
		String sql = "SELECT * FROM images WHERE image_name = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, image_name + ".png");
		ResultSet rs = pstmt.executeQuery();
		
		ImageVO imageVO = null;
		while (rs.next()) {
			imageVO = new ImageVO(rs.getInt("image_id"),rs.getString("image_name"),rs.getString("image_path"));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return imageVO;
	}
	
	/**
	 * 이미지 정보 추가
	 * 
	 * @param file
	 * @throws SQLException
	 */
	public void addImage(File file) throws SQLException {
		
		String sql = "INSERT INTO images VALUES(image_id_seq.nextval, ?, ?)";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, file.getName());
		pstmt.setString(2, "src\\lmp\\util\\images\\imageicon\\" + file.getPath());
		
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
}
