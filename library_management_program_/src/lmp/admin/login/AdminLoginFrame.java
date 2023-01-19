package lmp.admin.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lmp.admin.adminframe.frame.AdminFrame;
import lmp.admin.db.dao.AdminDao;
import lmp.admin.db.dao.AdminLogHistoryDao;
import lmp.admin.db.vo.AdminVO;
import lmp.admin.login.join.EmployeeRegistrationFrame;
import lmp.main.SelectModeFrame;
import lmp.members.db.dao.ThemeDao;
import lmp.util.ImageConvert;
import lmp.util.ShaPasswordEncoder;
import lmp.util.theme.Theme;

public class AdminLoginFrame extends JFrame{
	/**
	 * 관리자 로그인 프레임
	 */

	SelectModeFrame selectModeFrame;
	AdminLoginFrame adminLoginFrame;
	AdminFrame adminFrame;						
	
	AdminLogHistoryDao adminLogHistoryDao = new AdminLogHistoryDao();
	AdminDao adminDao = new AdminDao();

	private JTextField idField;
	private JPasswordField pwField;

	ImageConvert img = new ImageConvert();
	ShaPasswordEncoder pwEncoder = new ShaPasswordEncoder();
	
	ThemeDao themeDao = new ThemeDao();
	Theme theme = new Theme();
	
	
	public AdminLoginFrame(SelectModeFrame selectModeFrame) {
		this.selectModeFrame = selectModeFrame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		adminLoginFrame = this;
		try {
			theme.setTheme(themeDao.getTheme().getName());
		} catch (SQLException e2) {
		}
		setAutoRequestFocus(false);
		setBounds(100, 100, 400, 350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		
		JPanel loginPanel = new JPanel();
		getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		loginPanel.setBackground(theme.getSub2Color());
		loginPanel.setFocusCycleRoot(true);
		
		
		
		
		idField = new JTextField("사원번호");
		idField.setBounds(50, 105, 300, 35);
		loginPanel.add(idField);
		idField.setColumns(30);
		
		JTextField pwTField = new JTextField("비밀번호");
		
		pwTField.setBounds(50, 160, 300, 35);
		loginPanel.add(pwTField);
		
		
		pwField = new JPasswordField("");
		pwField.setBounds(50, 160, 300, 35);
		loginPanel.add(pwField);
		pwField.setColumns(30);

		JButton loginBtn = new JButton("로그인");
		loginBtn.setBounds(50, 210, 302, 40);
		loginBtn.setFocusPainted(false);
		loginPanel.add(loginBtn);

		JLabel loginImageLabel = new JLabel();
		loginImageLabel.setIcon(img.scaledMenuImage("dhlibrary"));
		loginImageLabel.setBounds(140, 10, 100,100);
		
		loginPanel.add(loginImageLabel);
		
		JLabel joinLabel = new JLabel("회원가입");
		joinLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
		joinLabel.setBounds(290, 270, 70, 15);
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		joinLabel.addMouseListener(getMouseListener());
		loginPanel.add(joinLabel);
		
		
		idField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if (idField.getText().equals("사원번호")) {
					idField.setText("");
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				
				if (idField.getText().equals("")) {
					idField.setText("사원번호");
				}
				
			}
		});
		
		pwTField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				pwTField.setVisible(false);
				pwField.setFocusable(true);
				pwField.requestFocusInWindow();
				
			}
		});
		
		pwField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (new String(pwField.getPassword()).equals("")) {					
					pwTField.setVisible(true);
				}
			}
		});
		
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkLogin(idField.getText(), new String(pwField.getPassword()))) {
						adminLoginFrame.dispose();
						selectModeFrame.dispose();
						adminFrame = new AdminFrame();

					} else {
						JOptionPane.showMessageDialog(adminLoginFrame, "사원번호/비밀번호를 확인하세요");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		joinLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new EmployeeRegistrationFrame();
			}
		});
	}
	
	public MouseListener getMouseListener() {
		MouseListener mouse = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				setCursor(null);
			}
		};
		return mouse;
	}
	
	
	

	public boolean checkLogin(String admin_num, String admin_pw) {
		AdminVO adminVO = null;
		try {
			
			adminVO = adminDao.getAdminInfo(Integer.parseInt(admin_num));
			if (adminVO == null) {
				return false;
			} else {
				if (pwEncoder.matches(admin_pw,adminVO.getPw())) {
					adminLogHistoryDao.add(adminVO);
					return true;
				} else {
					return false;
				}
			}
			
		}catch (NumberFormatException nfe) {
			return false;
		}catch (SQLException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
