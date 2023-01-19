package lmp.admin.menu.book.booksearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.admin.menu.book.booksearch.button.BookButton;
import lmp.admin.menu.book.booksearch.button.BookDeleteButton;
import lmp.admin.menu.book.booksearch.button.BookRegisterButton;
import lmp.admin.menu.book.booksearch.button.BookSearchButton;
import lmp.admin.menu.book.booksearch.button.BookUpdateButton;
import lmp.admin.menu.book.booksearch.combobox.SearchKeywordComboBox;
import lmp.admin.menu.book.booksearch.label.BookSearchLabel;
import lmp.admin.menu.book.booksearch.scrollpane.BookSearchListPane;
import lmp.admin.menu.book.booksearch.scrollpane.table.BookSearchListTable;
import lmp.admin.menu.book.booksearch.textfield.BookSearchTextField;

public class BookSearchPanel extends JPanel {
	
	BookSearchListPane bookSearchListPane;
	BookSearchListTable bookSearchListTable;
	BookSearchLabel bookSearchLabel;
	BookButton bookRegisterButton;
	BookButton bookDeleteButton;
	BookButton bookUpdatedButton;
	BookSearchButton bookSearchButton;
	BookSearchTextField bookSearchTextField;
	SearchKeywordComboBox searchKeywordComboBox;
	
	GridLayout grid;
	
	public BookSearchPanel() {
		
		this.bookSearchListPane = new BookSearchListPane();
		this.bookSearchListTable = new BookSearchListTable();
		this.bookSearchLabel = new BookSearchLabel();
		this.bookRegisterButton = new BookRegisterButton();
		this.bookDeleteButton = new BookDeleteButton(this);
		this.bookSearchButton = new BookSearchButton(this.getBookSearchListTable());
		this.bookUpdatedButton = new BookUpdateButton(this);
		this.bookSearchTextField = new BookSearchTextField(this.getBookSearchButton());
		this.searchKeywordComboBox = new SearchKeywordComboBox();
		
		this.add(bookSearchLabel);
		this.add(searchKeywordComboBox);
		this.add(bookSearchTextField);
		this.add(bookSearchButton);
		this.add(bookRegisterButton);
		this.add(bookDeleteButton);
		this.add(bookUpdatedButton);
		this.add(bookSearchListPane);
		
		this.setBounds(17, 200, 1150, 550);
		this.setBackground(new Color(87, 119, 119));
		this.setLayout(null);
			
	}

	public BookSearchListPane getBookSearchListPane() {
		return bookSearchListPane;
	}

	public BookSearchListTable getBookSearchListTable() {
		return bookSearchListTable;
	}

	public BookButton getBookRegisterButton() {
		return bookRegisterButton;
	}

	public BookButton getBookDeleteButton() {
		return bookDeleteButton;
	}

	public BookButton getBookUpdatedButton() {
		return bookUpdatedButton;
	}

	public BookSearchButton getBookSearchButton() {
		return bookSearchButton;
	}

	public BookSearchTextField getBookSearchTextField() {
		return bookSearchTextField;
	}
}
