package lmp.admin.adminframe.button.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import lmp.admin.adminframe.frame.AdminFrame;
import lmp.admin.adminframe.button.OptionButton;
import lmp.admin.adminframe.frame.AdminFrame;
import lmp.admin.menu.setting.SettingMenu;
import lmp.members.db.dao.ThemeDao;
import lmp.util.theme.Theme;

public class OptionButtonListener implements ActionListener{

	AdminFrame adminFrame;
	SettingMenu setMenu;
	ThemeDao themeDao = new ThemeDao();
	Theme theme = new Theme();
	
	public OptionButtonListener(AdminFrame amdinFrame) {
		this.adminFrame = amdinFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		OptionButton btn = (OptionButton) e.getSource();
		if (btn.getToolTipText().equals("홈 화면")) {
			this.adminFrame.getMenuCardPanel().getCard().show(this.adminFrame.getMenuCardPanel(), "홈 화면");
		} else {

			try {
				setMenu = new SettingMenu(this.adminFrame);
				setMenu.setVisible(true);
				theme.setTheme(themeDao.getTheme().getName());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
