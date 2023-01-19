package lmp.members.menu.readingroom.usagelist.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lmp.members.db.dao.FontDao;
import lmp.members.db.dao.ThemeDao;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.readingroom.seatlist.label.GenderPanel;
import lmp.members.menu.readingroom.usagelist.label.UsageListCheckOutLabel;
import lmp.members.menu.readingroom.usagelist.label.UsageListTitleLabel;
import lmp.util.font.MyFont;
import lmp.util.theme.Theme;

public class UsageListTitlePanel extends JPanel{

	BorderLayout borderLayout = new BorderLayout();
	UsageListTitleLabel usageListTitleLabel;
	UsageListCheckOutLabel usageListCheckOutLabel;
	GenderPanel genderPanel;

	Theme theme = new Theme();
	
	public UsageListTitlePanel(ReadingRoomPanel readingRoomPanel) throws SQLException {
		theme = readingRoomPanel.getTheme();
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0, 0), 20)));
		setBackground(new Color(0, 0, 0, 0));
		
		usageListCheckOutLabel = new UsageListCheckOutLabel(readingRoomPanel);
		usageListTitleLabel = new UsageListTitleLabel();
		genderPanel = new GenderPanel();
		
		this.setLayout(borderLayout);
		this.add(usageListTitleLabel, "Center");
		this.add(usageListCheckOutLabel, "East");
		this.add(genderPanel, "West");

	}

}