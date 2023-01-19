package lmp.members.menu.readingroom;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.admin.db.dao.ReadingRoomDao;
import lmp.admin.db.dao.SeatUseDetailDao;
import lmp.admin.db.vo.ReadingRoomVO;
import lmp.admin.db.vo.SeatUseDetailVO;
import lmp.members.db.dao.FontDao;
import lmp.members.db.dao.ThemeDao;
import lmp.members.menu.readingroom.seatlist.SeatListPanel;
import lmp.members.menu.readingroom.seatlist.panel.StatusPanel;
import lmp.members.menu.readingroom.usagelist.UsageListPanel;
import lmp.members.menu.readingroom.usagelist.panel.UsageListTitlePanel;
import lmp.util.font.MyFont;
import lmp.util.theme.Theme;

public class ReadingRoomPanel extends JPanel {
	
	SeatListPanel seatListPanel;
	StatusPanel statusPanel;
	UsageListPanel usageListPanel;
	UsageListTitlePanel usageListTitlePanel;
	
	static SeatUseDetailDao sudDao = new SeatUseDetailDao();
	static ReadingRoomDao roomDao  = new ReadingRoomDao();
	ArrayList<SeatUseDetailVO> sudVOs;
	ArrayList<ReadingRoomVO> seatList;
	
	ThemeDao themeDao = new ThemeDao();
	Theme theme = new Theme();
	FontDao fontDao = new FontDao();
	MyFont myFont = new MyFont();
	
	public ReadingRoomPanel() throws SQLException  {
		theme.setTheme(themeDao.getTheme().getName());
		myFont.setFont(fontDao.getFont());
		
		
		statusPanel = new StatusPanel();
		usageListPanel = new UsageListPanel();
		seatListPanel = new SeatListPanel(this);
		usageListTitlePanel = new UsageListTitlePanel(this);
		
		
		setBackground(theme.getSub1Color());  //--> 사이즈 수정 필요
		setLayout(null);
		
		JLabel label = new JLabel("|  |  :  칸막이");
		label.setFont(myFont.getText());
		label.setForeground(Color.WHITE);
		label.setBackground(theme.getSub1Color());
		label.setBounds(100, 100, 200, 100);
		
		add(label);
		usageListPanel.add(usageListTitlePanel);
		add(usageListPanel);
		add(statusPanel);
		add(seatListPanel);
	}
	
	public void refresh() throws SQLException {
		seatListPanel.refresh();
		statusPanel.refresh();
	}
	
	public SeatListPanel getSeatListPanel() {
		return seatListPanel;
	}

	public UsageListPanel getUsageListPanel() {
		return usageListPanel;
	}

	public StatusPanel getStatusPanel() {
		return statusPanel;
	}
	
	public ArrayList<SeatUseDetailVO> getSudVOs() {
		return sudVOs;
	}
	
	public ArrayList<ReadingRoomVO> getSeatList() {
		return seatList;
	}
	
	public Theme getTheme() {
		return theme;
	}	
	
	
}