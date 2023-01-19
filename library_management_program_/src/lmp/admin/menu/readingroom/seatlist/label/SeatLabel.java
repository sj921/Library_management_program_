package lmp.admin.menu.readingroom.seatlist.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class SeatLabel extends JLabel{

	// 열람실 각 좌석
	public SeatLabel(String seatNum) {
		
		setText(seatNum);
		setOpaque(true);
		setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.BLACK));
		setHorizontalAlignment(JLabel.CENTER);
		
	}
	
	
	
}
