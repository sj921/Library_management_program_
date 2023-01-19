package lmp.members.menu.setting.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;

import lmp.members.db.dao.ThemeDao;

public class ThemeListener extends MouseAdapter {

	ThemeDao themeDao = new ThemeDao();
	JButton[] btns;

	public ThemeListener(JButton[] btns) {
		this.btns = btns;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		JButton btn = (JButton) e.getSource();

		for (int i = 0; i < btns.length; i++) {
			if (btns[i].getText() == btn.getText()) {
				btns[i].setBackground(new Color(153, 204, 255));
				try {
					themeDao.setTheme(btns[i].getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				btns[i].setBackground(Color.WHITE);
			}
		}

	}

}
