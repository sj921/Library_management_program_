package lmp.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.admin.login.AdminLoginFrame;
import lmp.members.db.dao.FontDao;
import lmp.members.db.dao.ThemeDao;
import lmp.members.db.vo.ThemeVO;
import lmp.members.memberframe.frame.MemberFrame;
import lmp.util.ImageConvert;
import lmp.util.font.MyFont;
import lmp.util.theme.Theme;

public class SelectModeFrame extends JFrame {

	AdminLoginFrame adminLogFrame;

	ThemeDao themeDao = new ThemeDao();
	Theme theme = new Theme();
	FontDao fontDao = new FontDao();
	MyFont myFont = new MyFont();
	ImageConvert img = new ImageConvert();
	JLabel imageLabel = new JLabel();
	
	MemberFrame memberFrame;
	
	public SelectModeFrame() throws SQLException {
		
		SelectModeFrame selectModeFrame = this;
		ThemeVO getTheme = themeDao.getTheme();
		theme.setTheme(getTheme.getName());
		myFont.setFont(fontDao.getFont());
		JButton memberBtn = getButton("회원용");
		memberBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectModeFrame.dispose();
				try {
					MemberFrame memberFrame = new MemberFrame();
					memberFrame.initialize();
					memberFrame.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		memberBtn.setBounds(70, 70, 120, 130);
		memberBtn.setIcon(img.scaledMenuImage("membersEntrance"));

		JButton managerBtn = getButton("관리자용");

		managerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminLoginFrame adminLoginFrame = new AdminLoginFrame(selectModeFrame);
				adminLoginFrame.setVisible(true);
			}
		});
		managerBtn.setBounds(210, 70, 120, 130);
		managerBtn.setIcon(img.scaledMenuImage("employeeEntrance"));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(theme.getMainColor());
		panel.add(memberBtn);
		panel.add(managerBtn);

		getContentPane().add(panel, BorderLayout.CENTER);
		setTitle("도서관 관리 프로그램");
		setVisible(true);
		setResizable(false);
		setSize(400, 300);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 중앙에 띄우기
	}

	public JButton getButton(String text) {
		return new JButton() {
			{
				setHorizontalTextPosition(CENTER);
				setVerticalTextPosition(BOTTOM);
				setFont(myFont.getText());
				setText(text);
				if (!getText().equals("")) {
					setToolTipText(text);
				}
				setBorderPainted(false);
				setFocusPainted(false);
				setContentAreaFilled(false);
				addMouseListener(new MouseAdapter() {
					// 버튼에 마우스 올리면 테두리 생성
					@Override
					public void mouseEntered(MouseEvent e) {
						Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
						setCursor(cursor);
					}

					// 버튼에서 마우스 떼면 테두리 삭제
					@Override
					public void mouseExited(MouseEvent e) {
						Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
						setCursor(cursor);
					}
				});
			}
		};
	}

}
