package lmp.members.db.vo;

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
	

	public MemberVO(Integer mem_num,String mem_name) {
		
		this.num = mem_num;
		this.name = mem_name;
	}

	/**
	 * 회원 로그인시 생성자
	 * 
	 * @param mem_id
	 * @param mem_pw
	 */
	public MemberVO(Integer mem_num, String mem_id, String mem_pw) {
		
		this.num = mem_num;
		this.id = mem_id;
		this.pw = mem_pw;
		
	}
	
	public MemberVO(Integer mem_num, String mem_name, String mem_pw, String mem_phone, String mem_email,String mem_address) {
		this.num = mem_num;
		this.name = mem_name;
		this.pw = mem_pw;
		this.phone = mem_phone;
		this.email = mem_email;
		this.address = mem_address;
	}
	
	public MemberVO(String mem_name,
					String mem_id,
					String mem_pw, 
					String mem_phone, 
					String mem_birthday, 
					String mem_sex, 
					String mem_email,
					String mem_address) {
		this.name = mem_name;
		this.id = mem_id;
		this.pw = mem_pw;
		this.birthDay = mem_birthday;
		this.sex = mem_sex;
		this.phone = mem_phone;
		this.email = mem_email;
		this.address = mem_address;
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
					Integer	mem_num,
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
	
	@Override
	public String toString() {
		
		return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s", this.num,this.name,this.id,this.pw,this.birthDay,this.phone,this.email,this.address,this.regDate,this.note);
	}

}
