package lmp.members.memberframe.frame.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import lmp.members.db.dao.MemberDao;
import lmp.members.db.dao.MemberLogHistoryDao;

public class MemberFrameWindowListener extends WindowAdapter {

	MemberLogHistoryDao memLogDao = new MemberLogHistoryDao();
	MemberDao memberDao = new MemberDao();
	
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			if(memLogDao.getLog() != null) {
			memLogDao.update(memLogDao.getLog());
			}
		} catch (SQLException e1) {
			return;
		}
	}
	
}
