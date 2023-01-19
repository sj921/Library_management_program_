package lmp.admin.menu.book.bookregisterframe.panel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import lmp.admin.menu.book.bookregisterframe.button.RegisterListAddButton;
import lmp.admin.menu.book.bookregisterframe.button.RegisterListDeleteButton;
import lmp.admin.menu.book.bookregisterframe.button.RegisterSaveButton;

public class ButtonPanel extends JPanel{
	
	GridLayout gridLayout = new GridLayout(3, 1, 10, 10);
	
	RegisterListAddButton 	 registerListAddButton;
	RegisterListDeleteButton registerListDeleteButton;
	RegisterSaveButton 		 registerSaveButton;
	
	public ButtonPanel() {
		
		this.registerListAddButton = new RegisterListAddButton();
		this.registerListDeleteButton = new RegisterListDeleteButton();
		this.registerSaveButton = new RegisterSaveButton();
		
		this.add(registerListAddButton);
		this.add(registerListDeleteButton);
		this.add(registerSaveButton);
		
		this.setLayout(gridLayout);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));	
		
	}

}
