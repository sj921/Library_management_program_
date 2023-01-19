package lmp.admin.menu.book.booksearch.combobox;

import java.awt.Font;

import javax.swing.JComboBox;

public class SearchKeywordComboBox extends JComboBox{
	
	private static String[] category = { "제목", "저자", "출판사", "ISBN", "편권수", "복권수", "등록일", "가격", "위치", "비고" };
	
	public SearchKeywordComboBox() {
		
		super(category);
		this.setBounds(230, 80, 110, 30);
		this.setFont(new Font(null, Font.BOLD, 15));
	}

}
