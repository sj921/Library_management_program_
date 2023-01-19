package lmp.admin.db.vo;

import java.util.Date;

public class BookVO {
	
	/**
	 * 도서 정보
	 */
	
	private String	   id;
	private String	   title;
	private String	   author;
	private String	   publisher;
	private String	   isbn;
	private Integer	   bias;
	private Integer	   duplicates;
	private String	   regDate;
	private Integer	   price;
	private LocationVO location;
	private String	   note;
	private Object[]	list;
	
	
	public BookVO() {}
	/**
	 * 도서 등록 / 검색 생성자.
	 * 
	 * @param book_id
	 * @param book_title
	 * @param book_author
	 * @param book_publisher
	 * @param book_isbn
	 * @param book_bias
	 * @param book_duplicates
	 * @param book_registrationdate
	 * @param book_price
	 * @param location
	 * @param book_note
	 */
	public BookVO(
				  String  book_id,
				  String  book_title,
				  String  book_author,
				  String  book_publisher,
				  String  book_isbn,
				  Integer book_bias,
				  Integer book_duplicates,
				  String  book_registrationdate,
				  Integer book_price,
				  LocationVO location,
				  String  book_note
				  ) {
		
		this.id			=	book_id;
		this.title		=	book_title;
		this.author		=	book_author;
		this.publisher	=	book_publisher;
		this.isbn		=	book_isbn;
		this.bias		=	book_bias;
		this.duplicates	=	book_duplicates;
		this.regDate	=	book_registrationdate;
		this.price		=	book_price;
		this.location	=	new LocationVO(location);
		this.note		=	book_note;
		this.list		=	new Object[] {
										  book_id,
										  book_title,
										  book_author,
										  book_publisher,
										  book_isbn,
										  book_bias,
										  book_duplicates,
										  book_registrationdate,
										  book_price,
										  location,
										  book_note
		};
		
	}
	
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public Integer getBias() {
		return bias;
	}

	public Integer getDuplicates() {
		return duplicates;
	}

	public String getRegDate() {
		return regDate;
	}

	public Integer getPrice() {
		return price;
	}

	public LocationVO getLocation() {
		return location;
	}

	public String getNote() {
		return note;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setBias(Integer bias) {
		this.bias = bias;
	}

	public void setDuplicates(Integer duplicates) {
		this.duplicates = duplicates;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setLocation(LocationVO location) {
		this.location = location;
	}

	public void setNote(String note) {
		this.note = note;
	}

	// 테이블에 데이터를 쉽게 넣기 위해 getList 메서드 생성
	public Object[] getList() {
		return list;
	}

	@Override
	public String toString() {
		
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", this.id,this.title,this.author,this.publisher,this.isbn,this.bias,this.duplicates,this.price,this.location.getLocID(),this.regDate,this.note);
	}
}
