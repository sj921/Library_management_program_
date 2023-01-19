package lmp.members.memberframe.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.Timer;

import lmp.members.db.dao.FontDao;
import lmp.util.font.MyFont;

public class ClockLabel extends JLabel {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a HH : mm");

	Timer timer;
	
	public ClockLabel() throws SQLException {
		setFont(new Font("한컴 말랑말랑 Regular",Font.BOLD, 15));
		setForeground(Color.WHITE);
		setBounds(5,5,300,20);
		initialize();
	}

	public void initialize() {
		
		setText(dateFormat.format(new GregorianCalendar().getTime()));
		ActionListener updateClockAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setText(dateFormat.format(new GregorianCalendar().getTime()));
			}
		};
		timer = new Timer(1000, updateClockAction);
		timer.start();
	}
	
}
