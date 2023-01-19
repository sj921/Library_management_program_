package lmp.admin.login.join;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import lmp.admin.db.dao.AdminDao;
import lmp.admin.db.vo.AdminVO;
import lmp.util.ShaPasswordEncoder;
import lmp.util.Validator;

public class EmployeeRegistrationFrame extends JFrame {

	AdminDao adminDao = new AdminDao();
	Validator vd = new Validator();
	ShaPasswordEncoder pwEncoder = new ShaPasswordEncoder();
	
	public EmployeeRegistrationFrame() {
		JLabel join = new JLabel("회원가입");
		JLabel name = new JLabel("이름");
		JLabel pw = new JLabel("비밀번호");
		JLabel chkpw = new JLabel("비밀번호확인");
		JLabel phone = new JLabel("전화번호");
		JLabel email = new JLabel("이메일");
		JLabel address = new JLabel("주소");

		JTextField pwinfoField = new JTextField("숫자, 문자, 특수문자포함 8자리 이상");

		JTextField nameField = new JTextField();
		JPasswordField pwField = new JPasswordField();
		JPasswordField chkpwField = new JPasswordField();
		JTextField phoneField = new JTextField(" ex) 010-1234-5678");
		JTextField emailField = new JTextField();
		JTextField addressField = new JTextField();

		JButton phonecheckBtn = new JButton("중복확인");
		JButton emailcheckBtn = new JButton("중복확인");
		JButton joinBtn = new JButton("가입하기");
		
		setlabel(join, 40, 55, 13);
		
		setlabel(name, 18, 40, 90);
		setField(nameField, 113);

		setlabel(pw, 18, 40, 140);
		setField(pwField, 163);
		pwField.setVisible(false);

		setField(pwinfoField, 163);
		pwinfoField.setForeground(Color.RED);
		pwinfoField.setFont(new Font(null, Font.PLAIN, 11));
		pwinfoField.setVisible(true);

		pwField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (new String(pwField.getPassword()).trim().equals("")) {
					pwinfoField.setText("숫자, 문자, 특수문자포함 8자리 이상");
					pwinfoField.setForeground(Color.RED);
					pwField.setVisible(false);
					pwinfoField.setVisible(true);
				}
			}
		});
		pwinfoField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pwField.setText("");
				pwField.setForeground(Color.BLACK);
				pwinfoField.setVisible(false);
				pwField.setVisible(true);
				pwField.requestFocus();				
			}
		});


		setlabel(chkpw, 18, 40, 190);
		setField(chkpwField, 213);		
		
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, 18);
		
		setBtn(phonecheckBtn, 13, 400, 263);
		setlabel(phone, 18, 40, 240);
		setField(phoneField, 263);
		phoneField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (new String(phoneField.getText()).trim().equals("")) {
					phoneField.setText(" ex) 010-1234-5678");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (phoneField.getText().equals(" ex) 010-1234-5678")) {
					phoneField.setText("");
				}
			}
		});
		
		setlabel(email, 18, 40, 290);
		setField(emailField, 313);
		setBtn(emailcheckBtn, 13, 400, 313);

		setlabel(address, 18, 40, 340);
		setField(addressField, 363);

		setBtn(joinBtn, 15, 400, 400);

		// 전화번호 중복체크 및 유효성검사
		phonecheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vd.isValidatePhone(phoneField.getText()))  {
					AdminVO adminVo = null;
					try {
						adminVo = adminDao.getByPhone(phoneField.getText(), nameField.getText()).get(0);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");

					} catch (IndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");
						joinBtn.setEnabled(true);
					}

					if (adminVo != null) {
						JOptionPane.showMessageDialog(null, "중복되는 전화번호입니다.",
								"경고", JOptionPane.ERROR_MESSAGE);
						joinBtn.setEnabled(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "사용 불가능한 전화번호입니다",
							"경고", JOptionPane.ERROR_MESSAGE);
					joinBtn.setEnabled(false);
				}

			}
		});

		// 이메일 중복체크 및 유효성검사
		emailcheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vd.isValidateEmail(emailField.getText()))  {
					AdminVO adminVo = null;
					try {
						adminVo = adminDao.getByEmail(emailField.getText(), nameField.getText()).get(0);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");

					} catch (IndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");

						joinBtn.setEnabled(true);
					}

					if (adminVo != null) {
						JOptionPane.showMessageDialog(null, "중복되는 이메일입니다.",
								"경고", JOptionPane.ERROR_MESSAGE);
						joinBtn.setEnabled(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "사용 불가능한 이메일입니다",
							"경고", JOptionPane.ERROR_MESSAGE);
					joinBtn.setEnabled(false);
				}
			}
		});



		joinBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Validator vd = new Validator();
				AdminDao dao = new AdminDao();
				AdminVO vo = null;
				try {
					vo = new AdminVO(null, nameField.getText(),
							pwEncoder.encrypt(new String(pwField.getPassword())),
							phoneField.getText(), emailField.getText(), addressField.getText(),
							null, null);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}



				if (vd.isValidateName(nameField.getText()) &&
						vd.isValidatePW(new String(pwField.getPassword())) &&
						new String(pwField.getPassword()).equals(new String(chkpwField.getPassword())) &&
						vd.isValidatePhone(phoneField.getText()) &&
						vd.isValidateEmail(emailField.getText()) &&
						!(addressField.getText().equals(""))
						) {
					int var = JOptionPane.showConfirmDialog(null, "가입하시겠습니까?", "가입안내",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null);
					if (var == JOptionPane.YES_OPTION) {
						try {
							dao.add(vo);
						} catch (SQLException e1) {
						}
						try {
							JOptionPane.showMessageDialog(null, String.format("가입완료되었습니다\n사원번호 : %d",dao.get(2, nameField.getText()).get(0).getNum()));
						} catch (HeadlessException e1) {
						} catch (SQLException e1) {
						}
						dispose();
					}


				} else if (vd.isValidatePW(new String(pwField.getPassword())) == false) {
					JOptionPane.showMessageDialog(null, "사용 불가능한 비밀번호입니다", "경고",
							JOptionPane.ERROR_MESSAGE);

				} else if (new String(pwField.getPassword()).equals(new String(chkpwField.getPassword())) == false) {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다", "경고",
							JOptionPane.ERROR_MESSAGE);

				} else if (vd.isValidateName(nameField.getText()) == false) {
					JOptionPane.showMessageDialog(null, "적절하지 않은 이름입니다", "경고",
							JOptionPane.ERROR_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null, "입력되지 않은 정보가 존재합니다.", "경고",
							JOptionPane.ERROR_MESSAGE);

				}

			} 

		});


		setLayout(null);
		setBounds(700, 200, 550, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void setlabel(JLabel label , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);
		label.setFont(font);
		label.setForeground(new Color(49, 82, 91));
		label.setBounds(x, y, 200, 70);
		add(label);
	}

	public void setField(JTextField field, int y) {
		Font font = new Font(null, Font.PLAIN, 13);
		field.setFont(font);
		field.setBounds(180, y, 200, 30);
		field.setBorder(new LineBorder(new Color(49, 82, 91), 2, false));
		add(field);
	}

	public void setBtn(JButton button , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);

		button.setFont(font);
		button.setBackground(new Color(87, 119, 119));
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.setBounds(x, y, 90, 30);
		add(button);
	}


	public static void main(String[] args) {
		new EmployeeRegistrationFrame();

	}


}


