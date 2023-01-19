package lmp.members.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.members.db.vo.BookVO;
import lmp.members.db.vo.CheckOutVO;
import lmp.members.db.vo.LocationVO;
import lmp.members.db.vo.MemberVO;

public class CheckOutDao extends MenuDao{
	
	/**
	 * 회원 대출정보
	 * 
	 * @param mem_num
	 * @return ArrayList<CheckOutVO>
	 * @throws SQLException
	 */
	public ArrayList<CheckOutVO> get(int mem_num) throws SQLException {
		
		String sql = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE mem_num = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setInt(1, mem_num);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<CheckOutVO> checkOutList = new ArrayList<>();
		while (rs.next()) {
			checkOutList.add(new CheckOutVO(
								rs.getInt("check_out_id"),
								new BookVO(rs.getInt("book_id"),
										rs.getString("book_title"),
										rs.getString("book_author"),
										rs.getString("book_publisher"),
										rs.getString("book_isbn"),
										rs.getInt("book_bias"),
										rs.getInt("book_duplicates"),
										rs.getString("book_registrationdate"),
										rs.getInt("book_price"),
										new LocationVO(rs.getString("location_id"), rs.getString("location_name")),
										rs.getString("book_note")),
								new MemberVO(rs.getInt("mem_num"),
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
								rs.getString("check_out_date"),
								rs.getString("expect_return_date"),
								rs.getString("check_in_date")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return checkOutList;
	}
	
	
	/**
	 * 도서 대출 정보
	 * 
	 * @param book_id
	 * @return ArrayList<CheckOutVO>
	 * @throws SQLException
	 */	
	public ArrayList checkBook(int book_id) throws SQLException {
		
		String sql = "SELECT * FROM check_out_info JOIN books USING(book_id) JOIN locations USING(location_id) WHERE book_id = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setInt(1, book_id);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<CheckOutVO> checkOutList = new ArrayList<>();
		while (rs.next()) {
			checkOutList.add(new CheckOutVO(
								rs.getInt("check_out_id"),
								new BookVO(rs.getInt("book_id"),
										rs.getString("book_title"),
										rs.getString("book_author"),
										rs.getString("book_publisher"),
										rs.getString("book_isbn"),
										rs.getInt("book_bias"),
										rs.getInt("book_duplicates"),
										rs.getString("book_registrationdate"),
										rs.getInt("book_price"),
										new LocationVO(rs.getString("location_id"), rs.getString("location_name")),
										rs.getString("book_note")),
								rs.getString("check_out_date"),
								rs.getString("expect_return_date"),
								rs.getString("check_in_date")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return checkOutList;
	}

}
