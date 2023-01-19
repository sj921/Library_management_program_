package lmp.admin.menu.readingroom.seatlist.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import lmp.admin.db.dao.ReadingRoomDao;
import lmp.admin.db.vo.ReadingRoomVO;
import lmp.admin.db.vo.SeatUseDetailVO;
import lmp.admin.menu.readingroom.seatlist.label.SeatLabel;

public class SeatPanel extends JPanel{

	ReadingRoomDao rDao = new ReadingRoomDao();
	ArrayList<ReadingRoomVO> rVo = new ArrayList<>();
	
	GridLayout gridLayout = new GridLayout(5, 2, 3, 3);
	SeatLabel[] seatLabels = new SeatLabel[gridLayout.getRows() * gridLayout.getColumns()];
	// 좌석 수 gridLayout으로 10개씩 묶음. 한 묶음 끝나고 좌석 수 이어가기 위한 변수
	int tens;
	
	// 각 좌석 덩어리 패널
	public SeatPanel(ArrayList<SeatUseDetailVO> sudVOs, int tensDigit) {
		
		this.setLayout(gridLayout);
		this.setBackground(new Color(0, 0, 0, 0));
		this.tens = tensDigit * 10;
		
		
		try {
			rVo.addAll(rDao.get());
		} catch (SQLException e) {}
		
		for (int i = 0 + tens; i < gridLayout.getRows() * gridLayout.getColumns() + tens; i++) {
			
			if (rVo.get(i).getTableDivider().equals("0")) {
				seatLabels[i - tens] = new SeatLabel(Integer.toString(i + 1));
			} else {
				seatLabels[i - tens] = new SeatLabel("| " + (i + 1) + " |");
			}
			seatLabels[i - tens].setBorder(new LineBorder(Color.BLACK, 3));
			add(seatLabels[i - tens]);
		}
		
		for (SeatUseDetailVO sudVO : sudVOs) {
			int usageSeatNum = sudVO.getReadingroom().getSeatNum();
			String sex = sudVO.getMember().getSex();
			if (usageSeatNum >= tens + 1 && usageSeatNum <= tens + 10) {
				if (sex.equals("0")) {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(138, 228, 255));
				} else {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(255, 183, 210));
				}
			}
		}
		
	}
	
	public void refresh(ArrayList<SeatUseDetailVO> sudVOs) {
		
		for (int i = 0; i < seatLabels.length; i++) {
			seatLabels[i].setBackground(Color.WHITE);
		}
		
		for (SeatUseDetailVO sudVO : sudVOs) {
			int usageSeatNum = sudVO.getReadingroom().getSeatNum();
			String sex = sudVO.getMember().getSex();
			if (usageSeatNum >= tens + 1 && usageSeatNum <= tens + 10) {
				if (sex.equals("0")) {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(138, 228, 255));
				} else {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(255, 183, 210));
				}
			}
		}
		
		this.validate();
		
	}
}
