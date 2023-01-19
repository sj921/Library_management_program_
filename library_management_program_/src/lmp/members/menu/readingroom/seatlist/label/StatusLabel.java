package lmp.members.menu.readingroom.seatlist.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class StatusLabel extends JLabel{

	public StatusLabel() {

		this.setOpaque(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setForeground(Color.BLACK);
		this.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 18));
	}

}