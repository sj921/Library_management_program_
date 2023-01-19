package lmp.members.memberframe.button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class LogoutButton extends JButton {
	
	public LogoutButton() {
		initialize();
	}
	
	public void initialize() {
		
		setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 20));
		setBackground(Color.WHITE);
		setForeground(Color.RED);
		setFocusable(false);
		setBounds(1770, 10, 120, 50);
		
	}

}
