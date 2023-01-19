package lmp.admin.db.vo;

import java.util.Date;

public class SeatUseDetailVO {

	/**
	 * 열람실 이용 내역 VO
	 * 
	 * MEMBERS JOIN READINGROOM
	 */
	
	private Integer use_id;
	private MemberVO member;
	private ReadingRoomVO readingroom;
	private String startTime;
	private String endTime;
	private Object[] list;
	
	/**
	 * 열람실 이용내역 정보 생성자
	 * 
	 * @param member
	 * @param readingroom
	 * @param startTime
	 * @param endTime
	 */
	public SeatUseDetailVO(
							Integer use_id,
							MemberVO member,
							ReadingRoomVO readingroom,
							String startTime,
							String endTime
							) {
		this.use_id		 = use_id;
		this.member		 = member;
		this.readingroom = readingroom;
		this.startTime	 = startTime;
		this.endTime	 = endTime;
		this.list		 = new Object[]{
										use_id,
										readingroom.getSeatNum(),
										member.getNum(),
										member.getName(),
										member.getPhone(),
										member.getSex(),
									    startTime,
										};
		
	}

	public Integer getUse_id() {
		return use_id;
	}

	public MemberVO getMember() {
		return member;
	}

	public ReadingRoomVO getReadingroom() {
		return readingroom;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
	
	public Object[] getSudList() {
		return list;
	}
	
	@Override
	public String toString() {
		return String.format("%d,%s,%s,%s,%s", this.use_id, this.member, this.readingroom, this.startTime, this.endTime);
	}
	
}
