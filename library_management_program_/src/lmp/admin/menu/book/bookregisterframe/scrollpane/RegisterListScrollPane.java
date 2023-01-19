package lmp.admin.menu.book.bookregisterframe.scrollpane;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import lmp.admin.menu.book.bookregisterframe.scrollpane.table.RegisterListTable;

public class RegisterListScrollPane extends JScrollPane{
	
	RegisterListTable registerListTable;
	
	public RegisterListScrollPane() {
		
		this.registerListTable = new RegisterListTable();
		this.setViewportView(registerListTable);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
	}

}
