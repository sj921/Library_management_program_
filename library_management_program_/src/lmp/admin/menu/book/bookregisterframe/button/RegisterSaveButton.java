package lmp.admin.menu.book.bookregisterframe.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RegisterSaveButton extends JButton implements ActionListener{
	
	public RegisterSaveButton() {
		
		this.setText("저장");
		this.setBounds(1020, 110, 120, 40);
		this.setForeground(Color.BLACK);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
