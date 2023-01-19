package lmp.admin.menu.readingroom.seatlist;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import lmp.admin.db.dao.SeatUseDetailDao;
import lmp.admin.db.vo.SeatUseDetailVO;
import lmp.admin.menu.readingroom.seatlist.panel.SeatPanel;
import lmp.admin.menu.readingroom.seatlist.panel.StatusPanel;


public class SeatListPanel extends JPanel{
	
	GridLayout gridLayout = new GridLayout(1, 7, 30, 0);

	StatusPanel statusPanel;
	SeatPanel[]	seatPanels = new SeatPanel[gridLayout.getColumns() - 1];
	
	
	SeatUseDetailDao sudDao = new SeatUseDetailDao();
	ArrayList<SeatUseDetailVO> sudVO;
	
	// 좌석 이미지로 표시되는 패널
	public SeatListPanel() {
		
		setLayout(gridLayout);
//		setBorder(new TitledBorder(new LineBorder(new Color(49, 82, 91), 30)));
		setBackground(new Color(0, 0, 0, 0));
//		setOpaque(true);
	
		try {
			sudVO = sudDao.get();
		} catch (SQLException e) {}
		
		statusPanel = new StatusPanel(sudVO);
		add(statusPanel);
		
		for (int i = 0; i < gridLayout.getColumns() - 1; i++) {
			seatPanels[i] = new SeatPanel(sudVO, i);
			add(seatPanels[i]);
		}
		
	}
	
	public void refresh(ArrayList<SeatUseDetailVO> sudVO) {
		statusPanel.refresh(sudVO);
		for (SeatPanel seatPanel : seatPanels) {
			seatPanel.refresh(sudVO);
		}
		this.validate();
	}

}
