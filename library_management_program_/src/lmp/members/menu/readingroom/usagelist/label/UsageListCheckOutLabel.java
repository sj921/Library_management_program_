package lmp.members.menu.readingroom.usagelist.label;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import lmp.members.menu.readingroom.ReadingRoomPanel;
//import lmp.members.menu.readingroom.sj.usagelist.scrollpane.table.UsageListTable;
import lmp.members.menu.readingroom.usagelist.listener.CheckOutMouseAdapter;

public class UsageListCheckOutLabel extends JLabel implements MouseListener {
	
	public UsageListCheckOutLabel(ReadingRoomPanel readingRoomPanel) {
		
		this.setText("  퇴실하기  ");
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
		this.setBackground(new Color(245, 36, 31));
		this.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
//		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		this.setForeground(Color.WHITE);
		this.addMouseListener(new CheckOutMouseAdapter(readingRoomPanel));
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setCursor(null);
	}
}