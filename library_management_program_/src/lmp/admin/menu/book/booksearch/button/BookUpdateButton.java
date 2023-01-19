package lmp.admin.menu.book.booksearch.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lmp.admin.menu.book.booksearch.BookSearchPanel;

public class BookUpdateButton extends BookButton {

	BookSearchPanel bookSearchPanel;
	
	public BookUpdateButton(BookSearchPanel bookSearchPanel) {
		this.setText("수정");
		this.bookSearchPanel =  bookSearchPanel;
		this.setBounds(1020, 110, 120, 40);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(bookSearchPanel, "정보수정 완료.");
				
			}
		});
	}	
}
