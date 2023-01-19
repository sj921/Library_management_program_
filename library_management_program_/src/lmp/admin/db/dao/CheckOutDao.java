package lmp.admin.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.admin.db.vo.BookVO;
import lmp.admin.db.vo.CheckOutVO;
import lmp.admin.db.vo.LocationVO;
import lmp.admin.db.vo.MemberVO;

public class CheckOutDao extends MenuDao{
	
	/**
	 * 대여/반납 dao
	 */

	/**
	 * 대여 목록 추가
	 * 
	 * check_out_id 자동 증가
	 * 
	 * book_id, mem_num 등록
	 * 
	 * 대여 날짜 default 현재시간
	 * 반납 예정 날짜 default 현재 날짜 + 7
	 * 
	 * @param checkOutVO
	 * @throws SQLException
	 */
	// 모든 도서 중 하나의 도서를 대여 목록에 넣어야 하는데 기존 매개변수(CheckOutVO)로 받으면 모든 도서 중 선택된 도서의 데이터를 찾을 수 없음.
	// (이미 대여된 도서 중에서만 찾을 수 있음)
	public void add(BookVO bookVO, String memberNum) throws SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO check_out_info(check_out_id, book_id, mem_num) VALUES(check_out_id_seq.nextval,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bookVO.getId());
		pstmt.setString(2, memberNum);
		
		pstmt.executeUpdate();
		
		conn.commit();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 반납 목록 업데이트
	 * 
	 * 반납날짜 check_in_date 현재 날짜로 수정
	 * 
	 * @param checkOutVO
	 * @throws SQLException
	 */
	public void update(CheckOutVO checkOutVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " check_out_info"
					+ " SET"
					+ " check_in_date = sysdate"
					+ " WHERE"
					+ " check_out_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,checkOutVO.getCheckOutID());
		
		pstmt.executeUpdate();
		
		conn.commit();
		
		pstmt.close();
		conn.close();
	}
	
	
	/**
	 * header 
	 * id - 등록번호
	 * title - 제목
	 * num - 회원번호
	 * name - 회원이름
	 * 
	 * searchStr
	 * header에 해당하는 값
	 * 
	 * @param header
	 * @param searchStr
	 * @return ArrayList<CheckOutVO> checkOutList
	 * @throws SQLException
	 */
	public ArrayList get(int header, String searchStr) throws SQLException {
		
		StringBuilder sql = new StringBuilder(selectSql(header));

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, "%"+searchStr+"%");
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<CheckOutVO> checkOutList = new ArrayList<>();
		while (rs.next()) {
			checkOutList.add(new CheckOutVO(
								rs.getInt("check_out_id"),
								new BookVO(rs.getString("book_id"),
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
	
	public ArrayList get(String book_id) throws SQLException {
		
		String sql = "SELECT * FROM check_out_info JOIN books USING(book_id) JOIN locations USING(location_id) WHERE book_id LIKE ? AND check_in_date IS NULL";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book_id);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<CheckOutVO> checkedOutList = new ArrayList<>();
		while (rs.next()) {
			checkedOutList.add(new CheckOutVO(
								rs.getInt("check_out_id"),
								new BookVO(rs.getString("book_id"),
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
		
		return checkedOutList;
	}
	
	/**
	 * 해당하는 조건의 sql문 가져오기
	 * header
	 * 도서 등록번호 - 1
	 * 도서 제목 - 2
	 * 회원번호 - 3
	 * 회원이름 - 4
	 * 
	 * @param header
	 * @return StringBuilder sql
	 */
	public StringBuilder selectSql(int header) {
		StringBuilder sql = new StringBuilder();
		String id = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE book_id LIKE ? AND check_in_date IS NULL";
		String title = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE book_title LIKE ? AND check_in_date IS NULL";
		String num = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE mem_num LIKE ? AND check_in_date IS NULL";
		String name = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE mem_name LIKE ? AND check_in_date IS NULL";
		if (header == 1) {
			sql.append(id);
		} else if (header == 2) {
			sql.append(title);
		} else if (header == 3) {
			sql.append(num);
		} else if (header == 4) {
			sql.append(name);
		}
		return sql;
	}

	
	
	
	
	/**
	 * 대여 내역 삭제 (이용중인 좌석 제외) 
	 * 
	 */
	public void delete() throws SQLException {
		String sql = "DELETE FROM check_out_info WHERE check_in_date is not null";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}

}
