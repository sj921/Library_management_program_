package lmp.admin.menu.book.bookregisterframe;

import javax.swing.JFrame;

import lmp.admin.menu.book.bookregisterframe.panel.ButtonPanel;
import lmp.admin.menu.book.bookregisterframe.panel.InputPanel;
import lmp.admin.menu.book.bookregisterframe.scrollpane.RegisterListScrollPane;

public class BookRegisterFrame extends JFrame{
	
	InputPanel inputPanel;
	RegisterListScrollPane registerListScrollPane;
	ButtonPanel buttonPanel;
	
	public BookRegisterFrame() {
		this.inputPanel = new InputPanel();
		this.registerListScrollPane = new RegisterListScrollPane();
		this.buttonPanel = new ButtonPanel();
		this.add("North", inputPanel);
		this.add("Center", registerListScrollPane);
		this.add("East",buttonPanel);
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null); // 창 가운데 위치
		this.setVisible(true);
	
	}
	
	

}
