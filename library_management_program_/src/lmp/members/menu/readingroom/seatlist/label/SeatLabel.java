package lmp.members.menu.readingroom.seatlist.label;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import lmp.admin.db.vo.ReadingRoomVO;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.readingroom.seatlist.listener.SeatMouseAdapter;


public class SeatLabel extends JLabel{
	
	public SeatLabel(ReadingRoomPanel readingRoomPanel,ReadingRoomVO readingRoomVO) throws SQLException {
		
		
		if (readingRoomVO.getTableDivider().equals("0")) {
			setText(Integer.toString(readingRoomVO.getSeatNum()));			
		} else {
			setText("|" + Integer.toString(readingRoomVO.getSeatNum()) + "|");
		}
		setOpaque(true);
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 20));
		
		addMouseListener(new SeatMouseAdapter(readingRoomPanel));
	}
	
}