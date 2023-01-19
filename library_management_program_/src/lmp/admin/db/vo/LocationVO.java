package lmp.admin.db.vo;

public class LocationVO {
	/**
	 * 도서 위치 정보
	 */
	private String	locID;
	private String	locName;
	
	/**
	 * join을 위한 생성자
	 * 
	 * @param location
	 */
	public LocationVO(LocationVO location) {
		this.locID	 = location.getLocID();
		this.locName = location.getLocName();
	}
	
	/**
	 * 열람실 정보 생성자
	 * 
	 * @param location_id
	 * @param location_name
	 */
	public LocationVO(String location_id, String location_name) {
		this.locID	 = location_id;
		this.locName = location_name;
	}
	
	public String getLocID() {
		return locID;
	}

	public String getLocName() {
		return locName;
	}
	
	
	@Override
	public String toString() {
		
		return String.format("%s,%s", this.locID, this.locName);
	}

}
