package lmp.admin.menu.book.bookregisterframe.scrollpane.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RegisterListTable extends JTable {

	String column[] = { "제목", "저자", "출판사", "ISBN", "편권수", "복권수", "등록일", "가격", "위치", "비고" };
	DefaultTableModel model = new DefaultTableModel(column, 0); // column추가, 행은 1개 지정
	
	public RegisterListTable() {
		
		this.setModel(model);
		
	}
	
}
