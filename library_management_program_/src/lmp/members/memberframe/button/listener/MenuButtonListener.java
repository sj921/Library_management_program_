package lmp.members.memberframe.button.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import lmp.members.db.dao.MemberLogHistoryDao;
import lmp.members.login.MemberLoginFrame;
import lmp.members.memberframe.button.MenuButton;
import lmp.members.memberframe.frame.MemberFrame;

public class MenuButtonListener implements ActionListener {

	MemberFrame memberFrame;
	MemberLogHistoryDao memLogDao = new MemberLogHistoryDao();

	public MenuButtonListener(MemberFrame memberFrame) {
		this.memberFrame = memberFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		MenuButton menuButton = (MenuButton) e.getSource();
		if (menuButton.getText().equals("회원정보")) {
			try {
				MemberLoginFrame memLogFrame = new MemberLoginFrame();
				if (memLogDao.getLog() != null) {
					this.memberFrame.getMemberMenuPanel().refresh();
					this.memberFrame.getMemberMenuPanel().validate();
					this.memberFrame.getMenuCardPanel().getCard().show(this.memberFrame.getMenuCardPanel(), menuButton.getText());
				} else {
					memLogFrame.setVisible(true);
					memLogFrame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							try {
								MemberLogHistoryDao memLogDao = new MemberLogHistoryDao();
								if (memLogDao.getLog() != null) {
									memberFrame.getMemberMenuPanel().refresh();
									memberFrame.getMemberMenuPanel().validate();
									memberFrame.getMenuCardPanel().getCard().show(memberFrame.getMenuCardPanel(), menuButton.getText());
									
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
				}
			} catch (SQLException sqle) {
				return;
			}
		} else {
			this.memberFrame.getMenuCardPanel().getCard().show(this.memberFrame.getMenuCardPanel(),menuButton.getText());
		}
	}
}
