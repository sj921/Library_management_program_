package lmp.admin.menu.member;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import lmp.admin.menu.book.BookMgmt;
import lmp.members.db.dao.MemberLogHistoryDao;
import lmp.admin.adminframe.frame.AdminFrame;
import lmp.admin.db.dao.CheckOutDao;
import lmp.admin.db.dao.MemberDao;
import lmp.admin.db.dao.MenuDao;
import lmp.admin.db.vo.CheckOutVO;
import lmp.admin.db.vo.MemberVO;
import lmp.util.ImageConvert;
import lmp.util.Validator;


public class MemberMgmt extends JPanel {

	// JTable 구성품 
	String[] header = {"회원번호", "이름", "아이디", "생년월일", "성별", "전화번호", "이메일", "주소",
			"등록일", "비고"};
	DefaultTableModel model = new DefaultTableModel(header, 30) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	JTable table;
	JScrollPane scroll;
	Validator vd = new Validator();
	
	ImageConvert img = new ImageConvert();

	public MemberMgmt() {

		JLabel memberInquiry = new JLabel("회원 조회"); // 회원조회글씨
		JTextField searchField = new JTextField(); // 검색창	
		JButton searchBtn = AdminFrame.getButton("검색"); // 검색버튼
		JButton changeBtn = BookMgmt.getButton("수정"); // 수정버튼
		JButton deleteBtn = BookMgmt.getButton("삭제"); // 삭제버튼
		JButton resetPasswordBtn = new JButton("비밀번호 초기화");

		// 회원조회 타이틀 설정
		memberInquiry.setBounds(600, 30, 300, 50);
		memberInquiry.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 40));
		memberInquiry.setForeground(Color.WHITE);
		memberInquiry.setHorizontalAlignment(JLabel.CENTER);
		add(memberInquiry);

		// 텍스트필드 설정
		searchField.setBounds(530, 130, 450, 35);
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchBtn.doClick();
			}
		});
		add(searchField);

		// 검색버튼 설정
		searchBtn.setBounds(1010, 100, 120, 100);
		searchBtn.setIcon(img.scaledMgmtImage("search"));
		add(searchBtn);

		// 수정버튼 설정
		changeBtn.setBounds(1320, 10, 150, 70);
		changeBtn.setIcon(img.scaledMgmtImage("modification"));
		add(changeBtn);
		
		// 삭제버튼 설정
		deleteBtn.setBounds(1320, 90, 150, 70);
		deleteBtn.setIcon(img.scaledMgmtImage("memberdelete"));
		add(deleteBtn);
		
		// 비밀번호 초기화 설정
		resetPasswordBtn.setBounds(1340, 175, 120, 50);
		resetPasswordBtn.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 13));
		resetPasswordBtn.setBackground(new Color(227, 94, 79));
		resetPasswordBtn.setForeground(Color.WHITE);
		resetPasswordBtn.setFocusable(false);
		
		resetPasswordBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mem_num = model.getValueAt(table.getSelectedRow(), 0).toString();
				int num = JOptionPane.showConfirmDialog(
						null,
						"회원 번호 : " + mem_num + "\n비밀번호를 초기화 하시겠습니까?",
						"비밀번호 초기화 확인",
						JOptionPane.YES_NO_OPTION);
				
				switch (num) {
				case 0 :
					MemberDao memDao = new MemberDao();
					
					try {
						memDao.resetPassword(mem_num);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "비밀번호 초기화 완료.");
					
					break;
				case 1 :					
					JOptionPane.showMessageDialog(null, "취소되었습니다.");
					break;
				}
			}
		});
		
		add(resetPasswordBtn);


		// 콤보박스로 검색할내용 선택하기
		String[] keywordList = {"회원번호", "이름", "아이디", "전화번호"};
		JComboBox keyword = new JComboBox<>(keywordList);
		keyword.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		keyword.setBounds(330, 130, 150, 35);
		add(keyword);


		table = AdminFrame.getTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 250, 1500, 500);
		add(scroll);

		// 검색 버튼누르면 결과가 JTable로 생성
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberDao mdao = new MemberDao();
				try {
					ArrayList<MemberVO> mems = new ArrayList<>();

					mems.addAll(mdao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
					int num = 0;
					model.setRowCount(mems.size());
					for (MemberVO mem : mems) {
						for (int i = 0; i < header.length; i++) {
							// DB에서 가져온 성별 데이터에 따라 남/여로 표시
							if (header[i].equals("성별")) {
								if (mem.getSex().equals("0")) {
									model.setValueAt("남", num, i);
								} else {
									model.setValueAt("여", num, i);
								}
							} else {
								model.setValueAt(mem.getList()[i], num, i);
							}
						}
						num++;
					}

					searchField.setText("");
					table.validate();
					scroll.validate();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

			}
		});

		// 수정버튼
		changeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)  {
				
				MemberDao memberDao = new MemberDao();
				if (table.getSelectedRow() == -1 || model.getValueAt(table.getSelectedRow(), 0) == null) {
					JOptionPane.showMessageDialog(null, "수정할 회원을 선택해주세요.");
					return;
				}

				JFrame j = new JFrame();

				JLabel join = new JLabel("회원수정");
				JLabel id = new JLabel("아이디");
				JLabel name = new JLabel("이름");
				JLabel birth = new JLabel("생년월일");
				JLabel sex = new JLabel("성별");
				JLabel phone = new JLabel("전화번호");
				JLabel email = new JLabel("이메일");
				JLabel address = new JLabel("주소");
				JLabel note = new JLabel("비고");
				

				JTextField idField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 2).toString());
				JTextField nameField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 1).toString());
				JTextField birthField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 3).toString());
				JTextField sexField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 4).toString());
				JTextField phoneField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 5).toString());
				JTextField emailField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 6).toString());
				JTextField addressField = new JTextField
						(model.getValueAt(table.getSelectedRow() , 7).toString());
				JTextField noteField = new JTextField("");
				
				if (model.getValueAt(table.getSelectedRow(), 9) == null) {
					noteField.setText("");
				} else {
					noteField.setText(model.getValueAt(table.getSelectedRow(), 9).toString());
				}
				
				  
				JButton phonecheckBtn = new JButton("중복확인");
				JButton emailcheckBtn = new JButton("중복확인");
				JButton joinBtn = new JButton("가입하기");
				JButton changeBtn2 = new JButton("수정");
				JButton cancelBtn = new JButton("취소");


				setlabel2(join, 40, 40, 13);
				j.add(join);

				setlabel2(id, 18, 40, 90);
				setField(idField, 113);
				idField.setEditable(false);
				j.add(id);
				j.add(idField);

				setlabel2(name, 18, 40, 140);
				setField(nameField, 163);
				j.add(name);
				j.add(nameField);

				setlabel2(birth, 18, 40, 190);
				setField(birthField, 213);
				birthField.setEditable(false);
				j.add(birth);
				j.add(birthField);

				setlabel2(sex, 18, 40, 240);
				setField(sexField, 263);
				sexField.setEditable(false);
				j.add(sex);
				j.add(sexField);

				setlabel2(phone, 18, 40, 290);
				setField(phoneField, 313);
				setBtn(phonecheckBtn, 13, 80, 30);
				phonecheckBtn.setLocation(350, 313);
				j.add(phone);
				j.add(phoneField);
				j.add(phonecheckBtn);

				setlabel2(email, 18, 40, 340);
				setField(emailField, 363);
				setBtn(emailcheckBtn, 13, 80, 30);
				emailcheckBtn.setLocation(350, 363);
				j.add(email);
				j.add(emailField);
				j.add(emailcheckBtn);

				setlabel2(address, 18, 40, 390);
				setField(addressField, 413);
				j.add(address);
				j.add(addressField);
				
				setlabel2(note, 18, 40, 440);
				setField(noteField, 463);
				j.add(note);
				j.add(noteField);

				setBtn(changeBtn2, 18, 80, 40);
				changeBtn2.setLocation(350, 500);
				j.add(changeBtn2);
				
				// 전화번호 중복체크 / 유효성검사 / 사용불가능하면 수정버튼 비활성화
				phonecheckBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if (vd.isValidatePhone(phoneField.getText()))  {
							
							MemberVO memberVO = null;
							try {
								memberVO = memberDao.getByPhone(phoneField.getText(), idField.getText()).get(0);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								
							} catch (IndexOutOfBoundsException e2) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								changeBtn2.setEnabled(true);
							}
							
							if (memberVO != null) {
								JOptionPane.showMessageDialog(null, "중복되는 전화번호입니다.",
										"경고", JOptionPane.ERROR_MESSAGE);
								changeBtn2.setEnabled(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "사용 불가능한 전화번호입니다",
									"경고", JOptionPane.ERROR_MESSAGE);
							changeBtn2.setEnabled(false);
						}

					}
				});

				// 이메일 중복체크 / 유효성검사 / 사용불가능하면 수정버튼 비활성화
				emailcheckBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (vd.isValidateEmail(emailField.getText()))  {
							MemberVO memberVO = null;
							try {
								memberVO = memberDao.getByEmail(emailField.getText(), idField.getText()).get(0);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								
							} catch (IndexOutOfBoundsException e2) {
								JOptionPane.showMessageDialog(null, "사용가능합니다");
								changeBtn2.setEnabled(true);
							}
							
							if (memberVO != null) {
								JOptionPane.showMessageDialog(null, "중복되는 이메일입니다.",
										"경고", JOptionPane.ERROR_MESSAGE);
								changeBtn2.setEnabled(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "사용 불가능한 이메일입니다",
									"경고", JOptionPane.ERROR_MESSAGE);
							changeBtn2.setEnabled(false);
						}

					}
				});

				// 수정하기 버튼 >> 누르면 업데이트됨
				changeBtn2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (vd.isValidateName(nameField.getText()) &&
								vd.isValidatePhone(phoneField.getText()) &&
								vd.isValidateEmail(emailField.getText()) &&
								!(addressField.getText().equals("")) 
								) {
							int var = JOptionPane.showConfirmDialog
									(null, "수정하시겠습니까?", "수정 확인",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.INFORMATION_MESSAGE, null);
							if (var == JOptionPane.YES_OPTION) {
								MemberDao mdao = new MemberDao();
								MemberVO vo = new MemberVO((int)model.getValueAt(table.getSelectedRow() , 0),
										nameField.getText(),
										phoneField.getText(),
										emailField.getText(),
										addressField.getText(),
										noteField.getText()
										);
								try {
									mdao.update(vo);
									ArrayList<MemberVO> mems = new ArrayList<>();

									mems.addAll(mdao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
									int num = 0;
									model.setRowCount(mems.size());
									for (MemberVO mem : mems) {						
										for (int i = 0; i < mem.getList().length; i++) {
											if (header[i].equals("성별")) {
												if (mem.getSex().equals("0")) {
													model.setValueAt("남", num, i);
												} else {
													model.setValueAt("여", num, i);
												}
											} else {
												model.setValueAt(mem.getList()[i], num, i);
											}
										}
										num++;
									}
									JOptionPane.showMessageDialog(null, "수정되었습니다");
									j.dispose();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else if (vd.isValidateName(nameField.getText()) == false) {
								JOptionPane.showMessageDialog(null, "적절하지 않은 이름입니다", "경고",
										JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "입력되지 않은 정보가 존재합니다.", "경고",
										JOptionPane.ERROR_MESSAGE);
							}



						}
					}
				});

				j.setLayout(null);
				j.setBounds(330, 130, 480, 600);
				j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				j.setVisible(true);

			}
		});


		// 삭제버튼
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() == -1 || model.getValueAt(table.getSelectedRow(), 0) == null) {
					JOptionPane.showMessageDialog(null, "탈퇴할 회원을 선택해 주세요");
					return;
				}

				 //삭제 버튼 누르면 제일 먼저 미반납 도서 있는지 확인
								CheckOutDao checkOutDao = new CheckOutDao();
								ArrayList<CheckOutVO> checkOutVO = new ArrayList();
								
								try {
									checkOutVO.addAll(checkOutDao.get(3, model.getValueAt(table.getSelectedRow(), 0).toString()));
								} catch (SQLException e2) {
									e2.printStackTrace();
								}
								
								// 미반납 도서가 존재하면 안내문구 출력 후 삭제 버튼 무효
								if (checkOutVO.size() != 0) {
									JOptionPane.showMessageDialog(null, "미반납 도서가 존재합니다.");
									return;
								}


				int var = JOptionPane.showConfirmDialog
						(null, "삭제 하시겠습니까?", "삭제 확인",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null);
				if (var == JOptionPane.YES_OPTION) {
					MemberDao mdao = new MemberDao();
					try {
						mdao.delete((int)table.getValueAt(table.getSelectedRow(), 0));
						// mdao.delete(table.getValueAt(table.getSelectedRow(), 0).toString());

						// 삭제되면 테이블 업데이트
						model.setRowCount(0);
						ArrayList<MemberVO> mems = new ArrayList<>();
						mems.addAll(mdao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
						model.setRowCount(mems.size());
						int num = 0;
						model.setRowCount(mems.size());
						for (MemberVO mem : mems) {
							for (int i = 0; i < mem.getList().length; i++) {
								// DB에서 가져온 성별 데이터 남/여로 표시
								if (header[i].equals("성별")) {
									if (mem.getSex().equals("0")) {
										model.setValueAt("남", num, i);
									} else {
										model.setValueAt("여", num, i);
									}
								} else {
									model.setValueAt(mem.getList()[i], num, i);
								}
							}
							num++;
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		// 판넬기본설정
		setLayout(null);
		//setBorder(new LineBorder(Color.WHITE, 5, false)); // 판넬테두리 
		//		setBounds(0, 100, 1180, 650);
		setBackground(new Color(87, 119, 119));
	}

	// 라벨 생성 및 설정함수
	public static void setlabel(JLabel label , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setBounds(x, y, 300, 30);
	}

	public static void setlabel2(JLabel label , int size, int x, int y) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, size);
		label.setFont(font);
		label.setForeground(new Color(49, 82, 91));
		label.setBounds(x, y, 200, 70);
	}

	// 버튼 생성 및 설정함수
	public static void setBtn(JButton button , int fontSize, int width, int height) {
		Font font = new Font("한컴 말랑말랑 Bold", Font.BOLD, fontSize);

		button.setFont(font);
		button.setBackground(new Color(87, 119, 119));
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.setSize(width, height);
	}

	// 텍스트필드 생성 및 설정함수
	public static void setField(JTextField field, int y) {
		Font font = new Font(null, Font.PLAIN, 13);
		field.setFont(font);
		field.setBounds(140, y, 200, 30);
		field.setBorder(new LineBorder(new Color(49, 82, 91), 2, false));
	}
	
}




