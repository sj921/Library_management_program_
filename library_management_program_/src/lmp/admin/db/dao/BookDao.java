package lmp.admin.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.admin.db.vo.BookVO;
import lmp.admin.db.vo.LocationVO;

public class BookDao extends MenuDao{

	/**
	 * 도서 dao
	 */
	
	
	/**
	 * 도서 등록
	 * 
	 * book_id 시퀀스 증가
	 * 
	 * 등록날짜 default 현재날짜
	 * 
	 * @param bookVO
	 * @throws SQLException
	 */
	public void add(BookVO bookVO) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql = "INSERT INTO books("
									  + "book_id,"
									  + " book_title,"
									  + " book_author,"
									  + " book_publisher,"
									  + " book_isbn,"
									  + " book_bias,"
									  + " book_duplicates,"
									  + " book_price,"
									  + " location_id,"
									  + " book_note) VALUES(book_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1, bookVO.getTitle());
		pstmt.setString(2, bookVO.getAuthor());
		pstmt.setString(3, bookVO.getPublisher());
		pstmt.setString(4, bookVO.getIsbn());
		pstmt.setInt(5, bookVO.getBias());
		// 복권수 확인 절차
		// 등록하는 도서와 같다고 판단되는 도서가 존재하면
		if (getDuplicates(bookVO.getTitle(), bookVO.getAuthor(), bookVO.getBias()).size() > 0) {
			
			ArrayList<BookVO> duplicateList = new ArrayList<BookVO>();
			duplicateList.addAll(getDuplicates(bookVO.getTitle(), bookVO.getAuthor(), bookVO.getBias()));
			
			// 존재하는 도서들의 복권수 + 1
			pstmt.setInt(6, duplicateList.get(0).getDuplicates() + 1);
			increaseDuplicate(duplicateList.get(0).getTitle(), duplicateList.get(0).getAuthor(), duplicateList.get(0).getBias());
		} else {
			// 존재하지 않는다면 복권수 1로 저장
			pstmt.setInt(6, 1);
		}
		pstmt.setInt(7, bookVO.getPrice());
		pstmt.setString(8, bookVO.getLocation().getLocID());
		pstmt.setString(9, bookVO.getNote());
			
		pstmt.executeUpdate();
		
		conn.commit();
		pstmt.close();
		
		conn.close();
		
	}
	

	/**
	 * 도서 정보 업데이트
	 * 
	 * 제목, 저자, 출판사, isbn번호, 편권차, 복권수, 가격, 위치, 비고
	 * 
	 * @param bookVO
	 * @throws SQLException
	 */
	public void update(BookVO bookVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " books"
					+ " SET"
					+ " book_title = ?,"
					+ " book_author = ?,"
					+ " book_publisher = ?,"
					+ " book_isbn = ?,"
					+ " book_bias = ?,"
					+ " book_duplicates = ?,"
					+ " book_price = ?,"
					+ " location_id = ?,"
					+ " book_note = ?"
					+ " WHERE"
					+ " book_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bookVO.getTitle());
		pstmt.setString(2, bookVO.getAuthor());
		pstmt.setString(3, bookVO.getPublisher());
		pstmt.setString(4, bookVO.getIsbn());
		pstmt.setInt(5, bookVO.getBias());
		pstmt.setInt(6, bookVO.getDuplicates());
		pstmt.setInt(7, bookVO.getPrice());
		pstmt.setString(8, bookVO.getLocation().getLocID());
		pstmt.setString(9, bookVO.getNote());
		pstmt.setString(10, bookVO.getId());
		
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 같은 도서 등록시 복권수 증가
	 * 
	 * @param book_title
	 * @param book_author
	 * @param book_bias
	 * @throws SQLException
	 */
	public void increaseDuplicate(String book_title, String book_author, int book_bias) throws SQLException {
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " books"
					+ " SET"
					+ " book_duplicates = book_duplicates + 1"
					+ " WHERE"
					+ " book_title = ?"
					+ " AND"
					+ " book_author = ?"
					+ " AND"
					+ " book_bias = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, book_title);
		pstmt.setString(2, book_author);
		pstmt.setInt(3, book_bias);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
	/**
	 * 도서 삭제시 복권수 감소
	 * 
	 * @param book_title
	 * @param book_author
	 * @param book_bias
	 * @throws SQLException
	 */
		public void decreaseDuplicate(String book_title, String book_author, int book_bias) throws SQLException {
			Connection conn = getConnection();
			
			String sql =  "UPDATE"
						+ " books"
						+ " SET"
						+ " book_duplicates = book_duplicates - 1"
						+ " WHERE"
						+ " book_title = ?"
						+ " AND"
						+ " book_author = ?"
						+ " AND"
						+ " book_bias = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book_title);
			pstmt.setString(2, book_author);
			pstmt.setInt(3, book_bias);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}
	
	
	/**
	 * 도서 조건 검색
	 * 
	 * header
	 * 1 : id - 등록번호
	 * 2 : title - 제목
	 * 3 : author - 저자
	 * 4 : publisher - 출판사
	 * 5 : isbn - isbn 번호
	 * 6 : location - 위치
	 * 
	 * @param header
	 * @param searchStr
	 * @return ArrayList<BookVO> bookList
	 * @throws SQLException
	 */
	public ArrayList<BookVO> get(int header, String searchStr) throws SQLException {
		
		StringBuilder sql = selectSql(header);

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		if (header != 6) {
			pstmt.setString(1, "%" + searchStr + "%");
		} else {
			pstmt.setString(1, "%" + searchStr + "%");
			pstmt.setString(2, "%" + searchStr + "%");
		}
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<BookVO> bookList = new ArrayList<>();
		while (rs.next()) {
			bookList.add(new BookVO(
								rs.getString("book_id"),
								rs.getString("book_title"),
								rs.getString("book_author"),
								rs.getString("book_publisher"),
								rs.getString("book_isbn"),
								rs.getInt("book_bias"),
								rs.getInt("book_duplicates"),
								rs.getString("book_registrationdate"),
								rs.getInt("book_price"),
								new LocationVO(rs.getString("location_id"), rs.getString("location_name")),
								rs.getString("book_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return bookList;
	}
	
	
	/**
	 * 도서 복권수 확인용
	 * 
	 * @param book_title
	 * @param book_author
	 * @param bias
	 * @return
	 * @throws SQLException
	 */
	public ArrayList getDuplicates(String book_title, String book_author, int bias) throws SQLException {
		
		String sql = "SELECT * FROM books JOIN locations USING(location_id) WHERE book_title = ? AND book_author = ? AND book_bias = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book_title);
		pstmt.setString(2, book_author);
		pstmt.setInt(3, bias);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<BookVO> bookList = new ArrayList<>();
		while (rs.next()) {
			bookList.add(new BookVO(
								rs.getString("book_id"),
								rs.getString("book_title"),
								rs.getString("book_author"),
								rs.getString("book_publisher"),
								rs.getString("book_isbn"),
								rs.getInt("book_bias"),
								rs.getInt("book_duplicates"),
								rs.getString("book_registrationdate"),
								rs.getInt("book_price"),
								new LocationVO(rs.getString("location_id"), rs.getString("location_name")),
								rs.getString("book_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return bookList;
	}
	
	
	public StringBuilder selectSql(int header) {
		StringBuilder sql = new StringBuilder();
		String id = "SELECT * FROM books JOIN locations USING(location_id) WHERE book_id LIKE ?";
		String title = "SELECT * FROM books JOIN locations USING(location_id) WHERE book_title LIKE ?";
		String author = "SELECT * FROM books JOIN locations USING(location_id) WHERE book_author LIKE ?";
		String publisher = "SELECT * FROM books JOIN locations USING(location_id) WHERE book_publisher LIKE ?";
		String isbn = "SELECT * FROM books JOIN locations USING(location_id) WHERE book_isbn LIKE ?";
		String location = "SELECT * FROM books JOIN locations USING(location_id) WHERE location_id LIKE ? OR location_name LIKE ?";
		if (header == 1) {
			sql.append(id);
		} else if (header == 2) {
			sql.append(title);
		} else if (header == 3) {
			sql.append(author);
		} else if (header == 4) {
			sql.append(publisher);
		} else if (header == 5) {
			sql.append(isbn);
		} else if(header == 6) {
			sql.append(location);
		}
		return sql;
	}
	
	
	
	/**
	 * 도서 삭제
	 * 
	 * @param bookVO
	 * @throws SQLException
	 */
	public void delete(int bookNum) throws SQLException {
		String sql = "DELETE FROM books WHERE book_id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Integer.toString(bookNum));
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	

}
