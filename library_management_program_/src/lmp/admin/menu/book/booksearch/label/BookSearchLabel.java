package lmp.admin.menu.book.booksearch.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class BookSearchLabel extends JLabel{
	
	public BookSearchLabel() {
		
		this.setText("도서검색");
		this.setBounds(540, 20, 200, 50);
		this.setFont(new Font(null, Font.BOLD, 22));
		this.setForeground(Color.WHITE);
		
	}

}
