package lmp.admin.menu.readingroom.usagelist;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import lmp.admin.menu.readingroom.ReadingRoomPanel;
import lmp.admin.menu.readingroom.seatlist.SeatListPanel;
import lmp.admin.menu.readingroom.usagelist.panel.UsageListTitlePanel;
import lmp.admin.menu.readingroom.usagelist.scrollpane.UsageListScrollPane;

public class UsageListPanel extends JPanel{

	static BorderLayout borderLayout = new BorderLayout();
	
	UsageListScrollPane usageListScrollPane;
	UsageListTitlePanel usagListTitlePanel;
	
	// 타이틀, 버튼, 이용 회원 테이블 부모 패널
	public UsageListPanel() {
		
		this.setLayout(borderLayout);
	}
	
	public UsageListScrollPane getUsageListScrollPane() {
		return usageListScrollPane;
	}
	
	
		
}
