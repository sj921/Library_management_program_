package lmp.members.db.vo;


public class CheckOutVO {

	/**
	 * 대여 정보 VO
	 * 
	 * CHECKOUT JOIN BOOKS, MEMBERS
	 * 
	 */
	
	private Integer	 checkOutID;
	private BookVO	 book;
	private MemberVO member;
	private String	 checkOutDate;
	private String	 expectReturnDate;
	private String	 checkInDate;
	
	/**
	 * 도서 정보, 대여상태 생성자
	 * 
	 * @param book
	 * @param checkOutDate
	 * @param expectRetrunDate
	 * @param checkInDate
	 */
	public CheckOutVO(
					   Integer checkOutID,
					   BookVO book,
					   String checkOutDate,
					   String expectRetrunDate,
					   String checkInDate
					   ) {
		this.checkOutID = checkOutID;
		this.book = book;
		this.checkOutDate = checkOutDate;
		this.expectReturnDate = expectRetrunDate;
		this.checkInDate = checkInDate;
	}
	
	/**
	 * 대여 내역 생성자
	 * 
	 * 회원정보, 도서정보, 대여/반납 날짜
	 * 
	 * @param checkOutID
	 * @param book
	 * @param member
	 * @param checkOutDate
	 * @param expectReturnDate
	 * @param checkInDate
	 */
	public CheckOutVO(
					   Integer	checkOutID,
					   BookVO   book,
					   MemberVO member,
					   String	checkOutDate,
					   String	expectReturnDate,
					   String	checkInDate
					   ) {
		this.checkOutID = checkOutID;
		this.book = book;
		this.member = member;
		this.expectReturnDate = expectReturnDate;
		this.checkOutDate = checkOutDate;
	}


	public Integer getCheckOutID() {
		return checkOutID;
	}

	public BookVO getBook() {
		return book;
	}

	public MemberVO getMember() {
		return member;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public String getExpectReturnDate() {
		return expectReturnDate;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	
	@Override
	public String toString() {
		
		return String.format("%d%s%s%s", this.checkOutID, this.book, this.member, this.checkOutID, this.expectReturnDate, this.checkInDate);
	}
	
}
