package lmp.members.login.join;

import java.awt.Color;
import java.awt.Font;
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

import lmp.members.db.dao.MemberDao;
import lmp.members.db.vo.MemberVO;
import lmp.util.ShaPasswordEncoder;
import lmp.util.Validator;

public class MemberJoin extends JFrame {

	
	/**
	 * 회원가입 프레임
	 */
	
	MemberDao memberDao = new MemberDao();
	Validator vd = new Validator();
	ShaPasswordEncoder pwEncoder = new ShaPasswordEncoder();
	
	public MemberJoin() {
		JLabel join = new JLabel("회원가입");
		JLabel name = new JLabel("이름");
		JLabel id = new JLabel("아이디");
		JLabel pw = new JLabel("비밀번호");
		JLabel chkpw = new JLabel("비밀번호확인");
		JLabel birth = new JLabel("생년월일");
		JLabel sex = new JLabel("성별");
		JLabel phone = new JLabel("전화번호");
		JLabel email = new JLabel("이메일");
		JLabel address = new JLabel("주소");

		JTextField pwinfoField = new JTextField("특수문자포함 8자리 이상");

		JTextField idField = new JTextField();
		JTextField nameField = new JTextField();
		JPasswordField pwField = new JPasswordField();
		JPasswordField chkpwField = new JPasswordField();
		JTextField phoneField = new JTextField(" ex) 010-1234-5678");
		JTextField emailField = new JTextField();
		JTextField addressField = new JTextField();

		JButton phonecheckBtn = new JButton("중복확인");
		JButton idcheckBtn = new JButton("중복확인");
		JButton emailcheckBtn = new JButton("중복확인");
		JButton joinBtn = new JButton("가입하기");

		JRadioButton maleBtn = new JRadioButton("남");
		JRadioButton femaleBtn = new JRadioButton("여");

		String[] years = {"1950", "1951", "1952", "1953", "1954", "1955",
				"1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963",
				"1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971",
				"1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
				"1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987",
				"1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995",
				"1996", "1997", "1998", "1999", "2000", "2001", "2002"};

		String[] months = {"01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] days = {"01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31"};

		JComboBox year = new JComboBox(years);
		JComboBox month = new JComboBox(months);
		JComboBox day = new JComboBox(days);


		setlabel(join, 40, 55, 13);

		setlabel(id, 18, 40, 90);
		setField(idField, 113);
		setBtn(idcheckBtn, 13, 400, 113);

		setlabel(pw, 18, 40, 140);
		setField(pwField, 163);
		pwField.setVisible(false);

		setField(pwinfoField, 163);
		pwinfoField.setForeground(Color.RED);
		pwinfoField.setVisible(true);

		pwField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (new String(pwField.getPassword()).trim().equals("")) {
					pwField.setText("특수문자포함 8자리 이상");
					pwField.setForeground(Color.RED);
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

		setlabel(name, 18, 40, 240);
		setField(nameField, 263);

		setlabel(birth, 18, 40, 290);
		year.setBounds(180, 315, 70, 30);
		month.setBounds(265, 315, 50, 30);
		day.setBounds(330, 315, 50, 30);
		add(year);
		add(month);
		add(day);

		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, 18);
		setlabel(sex, 18, 40, 340);
		maleBtn.setFont(font);
		maleBtn.setBounds(200, 360, 100, 30);
		add(maleBtn);

		femaleBtn.setFont(font);
		femaleBtn.setBounds(300, 360, 100, 30);
		add(femaleBtn);

		ButtonGroup g = new ButtonGroup();
		g.add(femaleBtn);
		g.add(maleBtn);

		setBtn(phonecheckBtn, 13, 400, 413);
		setlabel(phone, 18, 40, 390);
		setField(phoneField, 413);
		phoneField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				phoneField.setText("");
				phoneField.requestFocus();
			}
		});
		phoneField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (new String(phoneField.getText()).trim().equals(""))
					phoneField.setText(" ex) 010-1234-5678");
			}
		});

		setlabel(email, 18, 40, 440);
		setField(emailField, 463);
		setBtn(emailcheckBtn, 13, 400, 463);
		setBtn(emailcheckBtn, 13, 400, 463);

		setlabel(address, 18, 40, 490);
		setField(addressField, 513);

		setBtn(joinBtn, 15, 400, 580);


		// 아이디 중복체크 및 유효성검사
		idcheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vd.isValidateID(idField.getText()))  {
					MemberVO memberVO = null;
					try {
						memberVO = memberDao.getExist(1, idField.getText());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");
					} catch (IndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");
						joinBtn.setEnabled(true);
					}
					if (memberVO != null) {
						JOptionPane.showMessageDialog(null, "중복되는 아이디입니다.",
								"경고", JOptionPane.ERROR_MESSAGE);
						joinBtn.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "사용가능합니다");
					}
				} else {
					JOptionPane.showMessageDialog(null, "사용 불가한 아이디입니다",
							"경고", JOptionPane.ERROR_MESSAGE);
					joinBtn.setEnabled(false);
				}
			}	
		});

		// 전화번호 중복체크 및 유효성검사
		phonecheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vd.isValidatePhone(phoneField.getText()))  {
					MemberVO memberVO = null;
					try {
						memberVO = memberDao.getExist(2, phoneField.getText());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");

					} catch (IndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");
						joinBtn.setEnabled(true);
					}

					if (memberVO != null) {
						JOptionPane.showMessageDialog(null, "중복되는 전화번호입니다.",
								"경고", JOptionPane.ERROR_MESSAGE);
						joinBtn.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "사용가능합니다");
						joinBtn.setEnabled(true);
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
					MemberVO memberVO = null;
					try {
						memberVO = memberDao.getExist(3, emailField.getText());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");

					} catch (IndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "사용가능합니다");

						joinBtn.setEnabled(true);
					}

					if (memberVO != null) {
						JOptionPane.showMessageDialog(null, "중복되는 이메일입니다.",
								"경고", JOptionPane.ERROR_MESSAGE);
						joinBtn.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "사용가능합니다");

						joinBtn.setEnabled(true);
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
				MemberDao dao = new MemberDao();
				MemberVO vo = null;
				try {
					vo = new MemberVO(
							nameField.getText(), 
							idField.getText(),
							pwEncoder.encrypt(new String(pwField.getPassword())),
							phoneField.getText(), 
							year.getSelectedItem().toString().substring(2)
							+ month.getSelectedItem()
							+ day.getSelectedItem(),
							(maleBtn.isSelected() ? "0" : "1"),
							emailField.getText(), 
							addressField.getText()
							);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}



				if (vd.isValidateName(nameField.getText()) &&
						vd.isValidatePW(new String(pwField.getPassword())) &&
						new String(pwField.getPassword()).equals(new String(chkpwField.getPassword())) &&
						vd.isValidatePhone(phoneField.getText()) &&
						vd.isValidateID(idField.getText()) &&
						vd.isValidateEmail(emailField.getText()) &&
						!(addressField.getText().equals("")) &&
						(maleBtn.isSelected() ? true : true)
						) {
					int var = JOptionPane.showConfirmDialog(null, "가입하시겠습니까?", "가입안내",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null);
					if (var == JOptionPane.YES_OPTION) {
						try {
							dao.add(vo);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "가입완료되었습니다");
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
		setBounds(700, 150, 550, 700);
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
}


