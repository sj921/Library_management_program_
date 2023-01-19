package lmp.members.memberframe.button.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import lmp.members.db.dao.MemberLogHistoryDao;
import lmp.members.login.MemberLoginFrame;
import lmp.members.memberframe.button.LogButton;
import lmp.members.memberframe.frame.MemberFrame;

public class LogButtonListener implements ActionListener {
	
	MemberLogHistoryDao memLogDao = new MemberLogHistoryDao(); 
	MemberFrame memberFrame;
	
	public LogButtonListener(MemberFrame memberFrame) {
		this.memberFrame = memberFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		LogButton btn = (LogButton) e.getSource();
		
		if (btn.getToolTipText().equals("로그인")) {
			try {
				if (memLogDao.getLog() != null) {
					JOptionPane.showMessageDialog(memberFrame, "로그인 상태입니다.");
					return;
				} else {
					MemberLoginFrame memLogFrame = new MemberLoginFrame();
					memLogFrame.setVisible(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				if (memLogDao.getLog() == null) {
					JOptionPane.showMessageDialog(memberFrame, "로그인 정보가 없습니다.");
					return;
				} else {
					int var = JOptionPane.showConfirmDialog
							(null, "로그아웃 하시겠습니까?", "로그아웃",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.INFORMATION_MESSAGE, null);
					if (var == JOptionPane.YES_OPTION) {
						memLogDao.update(memLogDao.getLog());
						this.memberFrame.getLoginButton().setVisible(true);
						this.memberFrame.getMenuCardPanel().getCard().show(this.memberFrame.getMenuCardPanel(), "홈 화면");
						
					}
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

}
