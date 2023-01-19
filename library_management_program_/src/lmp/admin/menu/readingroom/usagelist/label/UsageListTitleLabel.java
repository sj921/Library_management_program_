package lmp.admin.menu.readingroom.usagelist.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class UsageListTitleLabel extends JLabel{
	
	// 메뉴 타이틀 라벨
	public UsageListTitleLabel() {
		
		this.setText("열람실 이용 내역");
		this.setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 40));
		setForeground(Color.WHITE);
	}

}
