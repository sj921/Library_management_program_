package lmp.members.menu.readingroom.seatlist.label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.members.menu.readingroom.ReadingRoomPanel;

public class GenderPanel extends JPanel {
	
	JLabel maleLabel;
	JLabel femaleLabel;
	
	public GenderPanel() {
	
		this.setLayout(new GridLayout(1, 2));
		this.setBackground(new Color(126, 151, 148));
		this.setPreferredSize(new Dimension(100, 30));
		
		maleLabel = getLabel("남", new Color(153, 204, 255));
		femaleLabel = getLabel("여", new Color(255, 153, 204));
		
		this.add(maleLabel);
		this.add(femaleLabel);
	}
	
	public JLabel getLabel(String text, Color backGroundColor) {
		JLabel label = new JLabel(text);
		label.setOpaque(true);
		label.setBackground(backGroundColor);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
		
		return label;
	}
}