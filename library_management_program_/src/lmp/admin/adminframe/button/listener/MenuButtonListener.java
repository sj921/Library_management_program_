package lmp.admin.adminframe.button.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import lmp.admin.adminframe.button.MenuButton;
import lmp.admin.adminframe.frame.AdminFrame;
import lmp.admin.db.dao.AdminLogHistoryDao;
import lmp.admin.login.AdminLoginFrame;

public class MenuButtonListener implements ActionListener {

	AdminFrame adminFrame;
	AdminLogHistoryDao adminLogDao = new AdminLogHistoryDao();

	public MenuButtonListener(AdminFrame adminFrame) {
		this.adminFrame = adminFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		MenuButton menuButton = (MenuButton) e.getSource();
		this.adminFrame.getMenuCardPanel().getCard().show(this.adminFrame.getMenuCardPanel(),menuButton.getText());
		
	}
}
