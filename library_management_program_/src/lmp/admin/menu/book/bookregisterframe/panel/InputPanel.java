package lmp.admin.menu.book.bookregisterframe.panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import lmp.admin.menu.book.bookregisterframe.label.IndexLabel;
import lmp.admin.menu.book.bookregisterframe.textfield.InputTextField;

public class InputPanel extends JPanel {
	
	GridLayout gridLayout = new GridLayout(5, 2, 100, 5);
	private final String[] INDEX = {"제목", 
			"저자", 
			"출판사", 
			"ISBN", 
			"편권수", 
			"복권수", 
			"등록일", 
			"가격", 
			"위치", 
			"비고"};
	
	public InputPanel() {
		
		this.setLayout(gridLayout);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < 10; i++) {
			this.add(INDEX[i],new IndexLabel(INDEX[i]));
			this.add(INDEX[i],new InputTextField());
		};
		
	}

}
