package lmp.admin.menu.book.booksearch.textfield;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import lmp.admin.menu.book.booksearch.button.BookSearchButton;

public class BookSearchTextField extends JTextField implements ActionListener{
	
	BookSearchTextField bookSearchTextField = this;
	BookSearchButton booksearchButton;
	
	public BookSearchTextField(BookSearchButton bookSearchButton) {
		
		this.booksearchButton = bookSearchButton;
		this.setText("검색어를 입력하세요");
		this.setBounds(390, 80, 400, 30);
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				bookSearchTextField.setText("");
			}
			
		});
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.booksearchButton.doClick();
		
	}

}
