package lmp.admin.db.vo;

import java.util.Date;

import lmp.admin.db.dao.CheckOutDao;

public class MemberVO {
	
	/**
	 * 	회원정보
	*/
	
	private	Integer	num;
	private	String	name;
	private	String	id;
	private	String	pw;
	private	String	birthDay;
	private	String	sex;
	private	String	phone;
	private	String	email;
	private	String	address;
	private	String	regDate;
	private	String	note;
	private Object[] list;
	
	
	public MemberVO(Integer mem_num,String mem_name) {
		
		this.num = mem_num;
		this.name = mem_name;
	}
	
	/**
	 * 회원 정보 생성자
	 * 
	 * @param mem_num
	 * @param mem_name
	 * @param mem_id
	 * @param mem_pw
	 * @param mem_birthDay
	 * @param mem_sex
	 * @param mem_phone
	 * @param mem_email
	 * @param mem_address
	 * @param mem_registrationDate
	 * @param mem_note
	 */
	public MemberVO(
					int		mem_num,
					String	mem_name,
					String	mem_id,
					String	mem_pw,
					String	mem_birthDay,
					String	mem_sex,
					String	mem_phone,
					String	mem_email,
					String	mem_address,
					String	mem_registrationDate,
					String	mem_note
					) {
		
		this.num		=	mem_num;
		this.name		=	mem_name;
		this.id			= 	mem_id;
		this.pw			=	mem_pw;
		this.birthDay	=	mem_birthDay;
		this.sex		=	mem_sex;
		this.phone		=	mem_phone;
		this.email		=	mem_email;
		this.address	=	mem_address;
		this.regDate	=	mem_registrationDate;
		this.note		=	mem_note;
		this.list		=	new Object[] {
										  mem_num,
										  mem_name,
										  mem_id,
										  mem_birthDay,
										  mem_sex,
										  mem_phone,
										  mem_email,
										  mem_address,
										  mem_registrationDate,
										  mem_note
										  };
	}
	
	public MemberVO(int mem_num, String mem_name, String mem_phone, String mem_email, String mem_address, String mem_note) {
		this.num = mem_num;
		this.name = mem_name;
		this.phone = mem_phone;
		this.email = mem_email;
		this.address = mem_address;
		this.note = mem_note;
	}

	public Integer getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public String getSex() {
		return sex;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getRegDate() {
		return regDate;
	}

	public String getNote() {
		return note;
	}
	
	// 테이블에 데이터 넣기 쉽도록 getList 메서드 생성
	public Object[] getList() {		
		return list;
	}
	
	@Override
	public String toString() {
		
		return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s", this.num,this.name,this.id,this.pw,this.birthDay,this.phone,this.email,this.address,this.regDate,this.note);
	}
	
}
