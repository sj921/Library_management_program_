package lmp.members.menu.setting;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import lmp.members.db.dao.FontDao;
import lmp.members.db.dao.ThemeDao;
import lmp.members.db.vo.ThemeVO;
import lmp.members.memberframe.frame.MemberFrame;
import lmp.members.menu.setting.listener.FontListener;
import lmp.members.menu.setting.listener.ThemeListener;
import lmp.util.theme.Theme;

public class SettingMenu extends JFrame {

	private Font font = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 14);

	GridLayout gridLayout = new GridLayout(2, 3,5,5);

	JPanel themePanel;
	JPanel fontPanel;
	JButton[] themeBtns;
	JButton[] fontBtns;
	Theme theme = new Theme();
	ThemeDao themeDao = new ThemeDao();;
	FontDao fontDao = new FontDao();
	ArrayList<ThemeVO> themes = new ArrayList<>();
	ArrayList<String> fonts = new ArrayList<>();
	MemberFrame memberFrame;
	
	public SettingMenu() throws SQLException {
		getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("설정");
		titleLabel.setFont(new Font("굴림", Font.BOLD, 30));
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(140, 10, 95, 29);
		getContentPane().add(titleLabel);

		JLabel themeTitleLabel = new JLabel("테마");
		themeTitleLabel.setBounds(12, 54, 57, 15);
		themeTitleLabel.setForeground(Color.BLACK);
		getContentPane().add(themeTitleLabel);

		themePanel = new JPanel();
		themePanel.setBounds(12, 79, 360, 46);
		getContentPane().add(themePanel);
		themePanel.setLayout(gridLayout);
		

		JLabel fontTitleLabel = new JLabel("폰트크기");
		fontTitleLabel.setBounds(12, 135, 57, 15);
		getContentPane().add(fontTitleLabel);

		fontPanel = new JPanel();
		fontPanel.setBounds(12, 160, 360, 46);
		getContentPane().add(fontPanel);
		fontPanel.setLayout(new GridLayout(1, 3, 5, 5));
		
		SettingMenu setMenu = this;
		JButton btnNewButton = new JButton("닫기");
		btnNewButton.setBounds(275, 228, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					memberFrame.refresh();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				memberFrame.validate();
				setMenu.dispose();
				
			}
		});
		getContentPane().setBackground(theme.getSub2Color());
		getContentPane().add(btnNewButton);
		setBounds(100,100,400,300);
		
		initialize();
		
	}

	public void initialize() throws SQLException {
		
		themes.addAll(themeDao.getThemes());
		themeBtns = new JButton[themes.size()];
		for (int i = 0; i < themes.size(); i++) {
			themeBtns[i] = getThemeButton(themes.get(i));
		}	
		
		fonts.addAll(fontDao.getFonts());
		fontBtns = new JButton[fonts.size()];
		
		for (int i = 0; i < fonts.size(); i++) {
			fontBtns[i] = getFontButton(fonts.get(i));
		}
		
		ThemeListener themeListener = new ThemeListener(themeBtns);
		for (JButton themeBtn : themeBtns) {
			themeBtn.addMouseListener(themeListener);
			themePanel.add(themeBtn);
		}
		
		FontListener fontListener = new FontListener(fontBtns);
		for (JButton fontBtn : fontBtns) {
			fontBtn.addMouseListener(fontListener);
			fontPanel.add(fontBtn);
		}
	}
	
	public JButton getThemeButton(ThemeVO themeVO) throws SQLException {
		
		return new JButton() {
			{
				setText(themeVO.getName());
				if (themeVO.getActivation().equals("1")) {
					setBackground(new Color(153, 204, 255));
				} else {
					setBackground(Color.WHITE);
				}
				setHorizontalTextPosition(CENTER);
				setForeground(Color.BLACK);
				setOpaque(true);
			}
			
		};
	}

	public JButton getFontButton(String text) throws SQLException {

		return new JButton() {
			{
				setText(text);
				if (fontDao.getFont().equals(text)) {
					setBackground(new Color(153,204,255));
				} else {
					setBackground(Color.WHITE);
				}
				setHorizontalTextPosition(CENTER);
				setForeground(Color.BLACK);
				setOpaque(true);
			}
		};
	}

	public void setMemberFrame(MemberFrame memberFrame) {
		this.memberFrame = memberFrame;
	}
	
	

}
