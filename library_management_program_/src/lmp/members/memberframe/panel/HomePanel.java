package lmp.members.memberframe.panel;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.members.db.dao.ThemeDao;
import lmp.util.ImageConvert;
import lmp.util.theme.Theme;

public class HomePanel extends JPanel{

	ImageConvert img = new ImageConvert();
	JLabel label = new JLabel();
	
	public HomePanel() throws SQLException {
		ThemeDao themeDao = new ThemeDao();
		Theme theme = new Theme();
		theme.setTheme(themeDao.getTheme().getName());
		label.setSize(1500, 750);
		label.setIcon(theme.getHomeImage());
		setLayout(null);
		add(label);
	}
	
	public void setLabel(ImageIcon image) {
		label.setIcon(image);
	}
}
