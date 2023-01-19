package lmp.members.menu.readingroom.seatlist;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lmp.admin.db.dao.ReadingRoomDao;
import lmp.admin.db.dao.SeatUseDetailDao;
import lmp.admin.db.vo.ReadingRoomVO;
import lmp.admin.db.vo.SeatUseDetailVO;
import lmp.members.db.dao.FontDao;
import lmp.members.db.dao.ThemeDao;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.readingroom.seatlist.panel.SeatPanel;
import lmp.util.font.MyFont;
import lmp.util.theme.Theme;


public class SeatListPanel extends JPanel{
	
	public GridLayout gridLayout = new GridLayout(1,6,30,0);

//	StatusPanel statusPanel;
	SeatPanel[]	seatPanels = new SeatPanel[gridLayout.getColumns()];
	
	static SeatUseDetailDao sudDao = new SeatUseDetailDao();
	static ReadingRoomDao roomDao  = new ReadingRoomDao();
	ArrayList<SeatUseDetailVO> sudVOs;
	ArrayList<ReadingRoomVO> seatList;
	
	ReadingRoomPanel readingRoomPanel;
	
	Theme theme = new Theme();
		
	public SeatListPanel() {
	}
	
	public SeatListPanel(ReadingRoomPanel readingRoomPanel) throws SQLException {
		this.readingRoomPanel = readingRoomPanel;
		theme = readingRoomPanel.getTheme();
		setLayout(gridLayout);
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0, 0), 20)));
		setBackground(new Color(0, 0, 0, 0));
		setBounds(20, 280, 1460, 400);
		
		seatList = roomDao.get();
		sudVOs = sudDao.get();
		
		for (int i = 0; i < gridLayout.getColumns(); i++) {
			seatPanels[i] = new SeatPanel(readingRoomPanel, seatList,sudVOs,i);
			add(seatPanels[i]);
		}

	}

	public void refresh() throws SQLException {
		sudVOs = sudDao.get();
		for (SeatPanel seatPanel : seatPanels) {
			seatPanel.refresh(sudVOs);
		}
		this.validate();
	}
	
	

}