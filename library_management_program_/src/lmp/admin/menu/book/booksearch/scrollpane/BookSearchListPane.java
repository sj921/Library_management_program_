package lmp.admin.menu.book.booksearch.scrollpane;

import java.awt.Color;

import javax.swing.JScrollPane;

import lmp.admin.menu.book.booksearch.scrollpane.table.BookSearchListTable;

public class BookSearchListPane extends JScrollPane{
	
	BookSearchListTable bookSearchListTable;
	
	public BookSearchListPane() {
	
		this.bookSearchListTable = new BookSearchListTable();
		this.setViewportView(this.bookSearchListTable);
        this.setBounds(0, 156, 1152, 395);
        this.setBackground(new Color(87, 119, 119));
        
	}

	public BookSearchListTable getBookSearchListTable() {
		return bookSearchListTable;
	}
	
}
