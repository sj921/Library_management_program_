package lmp.admin.menu.readingroom.usagelist.scrollpane;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import lmp.admin.db.dao.SeatUseDetailDao;
import lmp.admin.db.vo.SeatUseDetailVO;
import lmp.admin.menu.readingroom.usagelist.scrollpane.table.UsageListTable;

public class UsageListScrollPane extends JScrollPane{
	
	private static String colNames[] = {"이용번호", "좌석번호", "회원번호", "이름", "전화번호", "성별", "시작시간"};
	DefaultTableModel model;
	
	UsageListTable usageListTable;

	// 열람실 이용 회원 테이블 부모 스크롤팬
	public UsageListScrollPane() throws SQLException {
		
		SeatUseDetailDao sudDao = new SeatUseDetailDao();
		ArrayList<SeatUseDetailVO> sudList = new ArrayList<>();
		
		sudList.addAll(sudDao.get());
		int resetRow = 0;
		model = new DefaultTableModel(colNames, 60) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (SeatUseDetailVO sud : sudList) {
			for (int i = 0; i < sud.getSudList().length; i++) {
				// DB에서 가져온 성별 데이터에 따라 남/여(문자)로 표시
				if (i == 5) {
					if (sud.getMember().getSex().equals("0")) {
						model.setValueAt("남", resetRow, i);
					} else {
						model.setValueAt("여", resetRow, i);
					}
				} else {
					model.setValueAt(sud.getSudList()[i], resetRow, i);
				}
			}
			resetRow++;
		}
		
		usageListTable = new UsageListTable(model);
		this.setViewportView(usageListTable);
//		this.setBackground(new Color(0, 0, 0, 0));
		this.setOpaque(true);
	}

	public UsageListTable getUsageListTable() {
		return usageListTable;
	}

	public DefaultTableModel getModel() {
		return model;
	}
	
	
	
	

}
