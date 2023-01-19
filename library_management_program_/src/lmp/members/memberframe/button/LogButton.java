package lmp.members.memberframe.button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import lmp.util.ImageConvert;

public class LogButton extends JButton {
	
	ImageConvert img = new ImageConvert();
	
	public LogButton(String text) {
		setToolTipText(text);
		
		if (text.equals("로그인")) {
			setBounds(1620, 10, 120, 50);
			setOpaque(true);
			setIcon(img.scaledLogImage("login"));
		} else {
			setBounds(1770, 10, 120, 50);
			setOpaque(true);
			setIcon(img.scaledLogImage("logout"));
		}
		initialize();
	}
	
	public void initialize() {
		
		setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 20));
		setBackground(Color.WHITE);
		setForeground(Color.RED);
		setFocusable(false);
	}
}
