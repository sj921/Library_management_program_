package lmp.admin.menu.book.bookregisterframe.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RegisterListAddButton extends JButton{
	
	public RegisterListAddButton() {
		
		this.setText("추가");
		this.setBounds(1020, 110, 120, 40);
		this.setForeground(Color.BLACK);		
	}
}
