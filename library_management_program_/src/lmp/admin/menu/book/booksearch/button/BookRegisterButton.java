package lmp.admin.menu.book.booksearch.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import lmp.admin.menu.book.bookregisterframe.BookRegisterFrame;
import lmp.admin.menu.book.booksearch.BookSearchPanel;

public class BookRegisterButton extends BookButton {
	
	public BookRegisterButton() {
		
		this.setText("등록");
		this.setBounds(1020, 10, 120, 40);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookRegisterFrame();
				
			}
		});
	}
}
