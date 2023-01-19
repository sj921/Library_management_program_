package lmp.admin.menu.book.booksearch.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import lmp.admin.menu.book.booksearch.BookSearchPanel;
import lmp.admin.menu.book.booksearch.scrollpane.table.BookSearchListTable;

public class BookDeleteButton extends BookButton {
	
	BookSearchPanel bookSearchPanel;
	BookSearchListTable bookSearchListTable;
	DefaultTableModel model;
	
	public BookDeleteButton(BookSearchPanel bookSearchPanel) {
		this.setText("삭제");
		this.bookSearchPanel = bookSearchPanel;
		this.bookSearchListTable = this.bookSearchPanel.getBookSearchListTable();
		this.model = this.bookSearchListTable.getModel();
		this.setBounds(1020, 60, 120, 40);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = JOptionPane.showConfirmDialog(bookSearchPanel, "delete? really? please, think again.", "내용삭제",
						JOptionPane.YES_NO_OPTION);
				// Yes -> 0 No -> 1 을 반환함
				switch (num) {
				case 0:
					model.removeRow(bookSearchListTable.getSelectedRow());
					bookSearchPanel.validate();
					JOptionPane.showMessageDialog(bookSearchPanel, "선택한 도서의 정보를 삭제합니다. 다시 되돌릴 수 없습니다.");
					// 데이터 삭제 메서드/클래스 추가
					break;
				case 1:
					JOptionPane.showMessageDialog(bookSearchPanel, "취소합니다.");
					break;
				}
			}
		});
	}	
}
