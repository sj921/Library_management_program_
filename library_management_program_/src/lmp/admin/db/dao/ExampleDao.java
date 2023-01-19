package lmp.admin.db.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lmp.admin.db.vo.BookVO;

public class ExampleDao extends MenuDao {
	
	/**
	 * example용 dao
	 * 
	 */

	public void add(BookVO bookVO) throws SQLException {

		Connection conn = getConnection();
		String sql = "INSERT INTO books(" + "book_id," + "book_title," + "book_author," + "book_publisher,"
				+ "book_isbn," + "book_price," + "location_id," + "book_registrationdate,"
				+ "book_note) VALUES(book_id_seq.nextval,?,?,?,?,?,?,sysdate,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, bookVO.getTitle());
		pstmt.setString(2, bookVO.getAuthor());
		pstmt.setString(3, bookVO.getPublisher());
		pstmt.setString(4, bookVO.getIsbn());
		pstmt.setInt(5, bookVO.getPrice());
		pstmt.setString(6, bookVO.getLocation().getLocID());
		pstmt.setString(7, bookVO.getNote());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();

	}

	public void add(File file) throws SQLException, FileNotFoundException {
		Connection conn = getConnection();

		String sql = "INSERT INTO image_information VALUES(image_id_seq.nextval,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		FileInputStream fis = new FileInputStream(file);
		pstmt.setString(1, file.getName());
		pstmt.setBinaryStream(2, fis, (long) (file.length()));

		int rowNum = pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void get(String image_name) throws SQLException, FileNotFoundException {
		Connection conn = getConnection();

		String sql = "SELECT image_byte_info FROM image_information WHERE image_name = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, image_name + ".png");

		ResultSet rs = pstmt.executeQuery();
		Blob image = rs.getBlob(2);
		InputStream binstr = image.getBinaryStream();

		rs.close();
		pstmt.close();
		conn.close();

	}
}
