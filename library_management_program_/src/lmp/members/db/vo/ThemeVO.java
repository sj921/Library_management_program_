package lmp.members.db.vo;

public class ThemeVO {

	Integer num;
	String  name;
	String  activation;
	
	public ThemeVO(Integer num, String name, String activation) {
		this.num = num;
		this.name = name;
		this.activation = activation;
	}

	public Integer getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public String getActivation() {
		return activation;
	}
	
	
	
}
