package lmp.util.font;

import java.awt.Font;

public class MyFont {

	/**
	 * 폰트 설정
	 */
	private Font title;
	private Font text;
	private Font table;
	
	
	public MyFont() {
		
	}
	
	public void setFont(String font_size) {
		
		switch (font_size) {
		
			case "Small" :
				title = new Font("한컴 말랑말랑 Regular", Font.BOLD, 35);
				text  = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 13);
				table = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 10);
				break;
			case "Midium":
				title = new Font("한컴 말랑말랑 Regular", Font.BOLD, 40);
				text  = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 18);
				table = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15);
				break;
			case "Large" :
				title = new Font("한컴 말랑말랑 Regular", Font.BOLD, 45);
				text  = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 23);
				table = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 20);
				break;
		}
	}

	public Font getTitle() {
		return title;
	}

	public Font getText() {
		return text;
	}

	public Font getTable() {
		return table;
	}
	
	
	
}
