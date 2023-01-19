package lmp.admin.menu.readingroom.seatlist.label;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class StatusLabel extends JLabel{
	
	// 좌석수 표시 라벨
	public StatusLabel() {
		
		this.setOpaque(true);
		this.setForeground(Color.BLACK);
		this.setBackground(Color.GRAY);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
}
