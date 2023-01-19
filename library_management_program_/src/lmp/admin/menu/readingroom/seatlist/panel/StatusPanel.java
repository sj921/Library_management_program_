package lmp.admin.menu.readingroom.seatlist.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import lmp.admin.db.dao.ReadingRoomDao;
import lmp.admin.db.vo.ReadingRoomVO;
import lmp.admin.db.vo.SeatUseDetailVO;
import lmp.admin.menu.readingroom.seatlist.label.StatusLabel;

public class StatusPanel extends JPanel{

	ReadingRoomDao readingRoomDao = new ReadingRoomDao();
	ArrayList<ReadingRoomVO> readingRoomVo = new ArrayList<ReadingRoomVO>();
	int totalSeat;
	
	GridLayout gridLayout = new GridLayout(6, 1);
	private static JLabel[] LABELS = new JLabel[6]; 
	
	// 좌석수 라벨 덩어리 패널
	public StatusPanel(ArrayList<SeatUseDetailVO> sudVO) {
		
		setLayout(gridLayout);
		setBorder(new LineBorder(Color.BLACK, 2));
		setBackground(new Color(0, 0, 0, 0));
		
		try {
			readingRoomVo.addAll(readingRoomDao.get());
			totalSeat = readingRoomVo.size();
		} catch (SQLException e) {}
		
		for (int i = 0; i < LABELS.length; i++) {
			LABELS[i] = new StatusLabel();
		}
		
		LABELS[0].setText("총 자리");
		LABELS[1].setText(Integer.toString(totalSeat));
		LABELS[2].setText("이용중인 자리");
		LABELS[3].setText("" + sudVO.size());
		LABELS[4].setText("남은 자리");
		LABELS[5].setText("" + (totalSeat - sudVO.size()));
		for (JLabel label : LABELS) {
			label.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
			label.setBackground(Color.WHITE);
			add(label);
		}
	}
	
	public void refresh(ArrayList<SeatUseDetailVO> sudVO) {
		
		LABELS[3].setText("" + sudVO.size());
		LABELS[5].setText("" + (totalSeat - sudVO.size()));
		this.validate();
	}
	
}
