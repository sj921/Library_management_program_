package lmp.members.db.vo;

public class ImageVO {

	Integer image_id;
	String image_name;
	String image_path;
	
	public ImageVO(Integer image_id, String image_name, String image_path) {
		
		this.image_id = image_id;
		this.image_name = image_name;
		this.image_path = image_path;
		
	}

	public Integer getImage_id() {
		return image_id;
	}

	public String getImage_name() {
		return image_name;
	}

	public String getImage_path() {
		return image_path;
	}
}
