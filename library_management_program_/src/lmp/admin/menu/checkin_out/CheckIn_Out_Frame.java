package lmp.admin.menu.checkin_out;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import lmp.admin.adminframe.frame.AdminFrame;
import lmp.admin.db.dao.BookDao;
import lmp.admin.db.dao.CheckOutDao;
import lmp.admin.db.vo.BookVO;
import lmp.admin.db.vo.CheckOutVO;
import lmp.admin.menu.book.BookMgmt;
import lmp.util.ImageConvert;

public class CheckIn_Out_Frame extends JFrame{

	JFrame frame = this;
	
	JPanel checkPanel;
	JLabel checkedOutLabel;
	JTable checkedOutTable, checkOutTable;
	JScrollPane checkedOutPane, checkOutPane;
	JButton checkInButton, searchbutton, checkOutButton;
	JLabel checkInLabel;
	JComboBox keyword;
	JTextField searchField;
	
	
	//	table.getValueAt(table.getSelectedRow(), 0)
	// 위 코드로 회원번호 뽑아서 DB에서 해당 회원번호에 맞는 정보 가져와서 출력 아래 배열에 저장
	String[] checkedOutCategory = {"대출번호", "도서 등록번호", "도서명", "저자", "대출 일자", "반납 예정일"};
	
	String[] category = {"등록번호", "도서명", "저자", "출판사", "ISBN", "편권수", "복권수", "도서 등록일", "가격", "위치", "비고"};
	
	String[] keywordList = {"등록번호", "도서명", "저자", "출판사", "ISBN", "위치"};
	
	DefaultTableModel checkOutModel = new DefaultTableModel(category, 7) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	
	DefaultTableModel checkedOutModel = new DefaultTableModel(checkedOutCategory, 3) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	CheckOutDao checkOutDao = new CheckOutDao();
	BookDao bookDao = new BookDao();
	
	// 해당 회원의 대여 내역
	ArrayList<CheckOutVO> checkedOutList = new ArrayList<>();
	// 전체 회원의 대여 내역
	ArrayList<CheckOutVO> checkedOutRecord = new ArrayList<>();
	ArrayList<BookVO> bookList = new ArrayList<>();
	
	ImageConvert img = new ImageConvert();
	
	public CheckIn_Out_Frame(String memberNum) {
		setTitle("회원 대출 관리");
		
		checkOutTable = AdminFrame.getTable(checkOutModel);
		checkOutPane = new JScrollPane(checkOutTable);
		checkOutPane.setBounds(0, 300, 986, 184);
		

		checkPanel = new JPanel();
		checkPanel.setLayout(null);
		
		checkedOutLabel = new JLabel("대출 목록");
		checkedOutLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 20));
		checkedOutLabel.setHorizontalAlignment(JLabel.CENTER);
		checkedOutLabel.setBounds(440, 10, 100, 50);
		checkedOutLabel.setForeground(Color.WHITE);
		
		
		getCheckedOutList(memberNum);
		
		
		checkedOutTable = AdminFrame.getTable(checkedOutModel);
		checkedOutPane = new JScrollPane(checkedOutTable);
		checkedOutPane.setBounds(0, 70, 986, 98);
		
		
		checkInButton = AdminFrame.getButton("반납");
		checkInButton.setHorizontalTextPosition(JButton.RIGHT);
		checkInButton.setVerticalTextPosition(JButton.CENTER);
		checkInButton.setIcon(img.scaledMgmtImage("checkIn"));
		checkInButton.setBounds(820, 172, 150, 70);
		
		// 반납 버튼을 누르면 DB에 존재하는 해당 등록번호의 도서 정보 업데이트
		checkInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택한 항목이 없거나 선택한 항목이 빈 행이면 안내문구 출력
				if (checkedOutTable.getSelectedRow() != -1 &&
					checkedOutTable.getValueAt(checkedOutTable.getSelectedRow(), 0) != null) {
					
					// 반납 버튼 누른 후 한 번 확인
					int num = JOptionPane.showConfirmDialog(frame,
															"반납하시겠습니까?",
															"반납 확인",
															JOptionPane.YES_NO_OPTION);
					switch (num) {
						case 0 :
							try {
								checkOutDao.update(checkedOutList.get(checkedOutTable.getSelectedRow()));
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(
									frame, String.format(
											"대출번호 : %s\n\t반납 완료",
											checkedOutTable.getValueAt(checkedOutTable.getSelectedRow(), 0)));
							BookMgmt.tableValidate();
							break;
						case 1 :
							JOptionPane.showMessageDialog(frame, "취소되었습니다.");
							return;
					}
					
					getCheckedOutList(memberNum);
					// 대여 목록 테이블 초기화 (초기화 과정 없이 위쪽의 도서를 반납처리 하면 리스트의 길이가 줄어들어 아래쪽 도서가 남아있는 오류 발생)
					checkedOutModel.setRowCount(0);
					checkedOutModel.setRowCount(3);
					
					int rowCount = 0;
					for (CheckOutVO checkedList : checkedOutList) {
						for (int i = 0; i < checkedOutCategory.length; ++i) {
							checkedOutModel.setValueAt(checkedList.getList()[i], rowCount, i);
						}
						++rowCount;
					}
					checkedOutTable.setModel(checkedOutModel);
					checkedOutPane.validate();
					
				} else {
					JOptionPane.showMessageDialog(frame, "반납할 도서를 선택해주세요.");
				}
			}
		});
		
		
		checkInLabel = new JLabel("대출 희망 도서 검색");
		checkInLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 20));
		checkInLabel.setHorizontalAlignment(JLabel.CENTER);
		checkInLabel.setBounds(390, 200, 200, 50);
		checkInLabel.setForeground(Color.WHITE);
		
		
		keyword = new JComboBox(keywordList);
		keyword.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		keyword.setBounds(130, 250, 120, 30);
		
		searchbutton = AdminFrame.getButton("검색");
		searchbutton.setIcon(img.scaledSmallImage("search"));
		searchbutton.setBounds(690, 230, 70, 70);
		
		// 텍스트 필드에서 엔터 누르면 버튼 클릭되도록 액션 추가 (검색 버튼 눌러도 되고 텍스트 필드에서 엔터 눌러도 검색됨)
		searchField = new JTextField();
		searchField.setBounds(310, 250, 350, 30);
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchbutton.doClick();
			}
		});		
		
		checkOutButton = AdminFrame.getButton("대출");
		checkOutButton.setHorizontalTextPosition(JButton.RIGHT);
		checkOutButton.setVerticalTextPosition(JButton.CENTER);
		checkOutButton.setIcon(img.scaledMgmtImage("checkout"));
		// 버튼을 누르면 VO에 들어있는 현재 회원의 정보, 해당 도서의 정보 업데이트
		checkOutButton.setBounds(820, 490, 150, 70);
		
		searchbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				int index = 0;
//				for (int i = 0; i < category.length; ++i) {
//					if (category[i].equals(keyword.getSelectedItem())) {
//						index = i;
//					}
//				}
				
				// DB에서 도서 정보 테이블 컬럼, 도서 정보 가져와서 category, contents에 넣어야 함
//				String[][] valid = new String[50][category.length];
//				int index2 = 0;
//				for (int i = 0; i < contents.length; ++i) {
//					if (contents[i][index].contains(searchField.getText())) {
//						valid[index2++] = contents[i];
//					}
//				}
//				model.setRowCount(valid.length);
//				
//				for (int i = 0; i < valid.length; ++i) {
//					for (int j = 0; j < category.length; ++j) {
//						model.setValueAt(valid[i][j], i, j);
//					}
//				}
				
				
				try {
					bookList.clear();
					bookList.addAll(bookDao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				checkOutModel.setColumnIdentifiers(category);
				checkOutModel.setRowCount(bookList.size());
				
				int num = 0;
				for (BookVO list : bookList) {
					for (int i = 0; i < list.getList().length; ++i) {
						checkOutModel.setValueAt(list.getList()[i], num, i);
					}
					++num;
				}
				checkOutTable.setModel(checkOutModel);
				checkPanel.validate();
				
				checkOutButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						// 선택된 행이 없거나 정보가 없는 행 선택 후 대출버튼 누르면 안내문구 출력
						if (checkOutTable.getSelectedRow() == -1 || checkOutModel.getValueAt(checkOutTable.getSelectedRow(), 0) == null) {
							JOptionPane.showMessageDialog(null, "대출할 도서를 선택해주세요.");
							return;
						}
						// 연체 중인 도서 있으면 안내문구 띄우고 대출 안되게 설정
						// 회원의 대여 목록을 확인하며 반납 예정일이 지났다면 안내문구 출력
						for (CheckOutVO list : checkedOutList) {
							// 반납 예정일이 오늘보다 먼저인가?
							if (LocalDate.parse((list.getExpectReturnDate()),
									DateTimeFormatter.ofPattern("yy/MM/dd")).compareTo(LocalDate.now()) < 0) {
								JOptionPane.showMessageDialog(frame, "연체 중인 도서가 존재합니다.");
								return;
							}
						}
						
						Object book_note = checkOutModel.getValueAt(checkOutTable.getSelectedRow(), 10);
						if (book_note != null) {
							if (book_note.toString().contains("훼손") || book_note.toString().contains("분실")) {
								JOptionPane.showMessageDialog(frame, "대출 불가능 도서입니다.");
								return;
							}
						}
						
						// 이미 대출된 도서 대출 안되게 거르기
						try {
							// 선택된 도서의 대여 기록 뽑아오기
							checkedOutRecord = checkOutDao.get(bookList.get(checkOutTable.getSelectedRow()).getId());
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						// 선택된 도서의 대여 기록을 확인하며 반납일이 없는 (미반납 상태인) 대여 기록이 있다면 안내문구 출력
						for (CheckOutVO list : checkedOutRecord) {
							if (list.getCheckInDate() == null) {
								JOptionPane.showMessageDialog(frame, "대여 중인 도서입니다.");
								return;
							}
						}
						
						// 대출내역 정보가 3건 이상이면 풀대출 안내문구 출력
						if (checkedOutList.size() == 3) {
							JOptionPane.showMessageDialog(frame, "대출 불가능 (대출 권수 확인)");
							return;
						}
						
						if (checkOutTable.getValueAt(checkOutTable.getSelectedRow(), 0) != null) {
							try {
								checkOutDao.add(bookList.get(checkOutTable.getSelectedRow()),
										memberNum);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							getCheckedOutList(memberNum);
							JOptionPane.showMessageDialog(frame, String.format("등록번호 : %s\n\t대출 완료",
									checkOutTable.getValueAt(checkOutTable.getSelectedRow(), 0)));
							BookMgmt.tableValidate();
						} else {
							JOptionPane.showMessageDialog(frame, "대출할 도서를 선택해주세요.");							
						}
						// 대출 버튼 클릭시 해당 도서가 대출 목록의 빈 행에 들어가도록 빈 행 찾는 코드
						int emptyRow = 0;
						for (int i = 0; i < checkedOutTable.getRowCount(); ++i) {
							if (checkedOutTable.getValueAt(i, 0) == null) {
								emptyRow = i;
								break;
							}
						}
//							for (int i = 0; i < category.length; ++i) {
//								checkedOutTable.setValueAt(checkOutTable.getValueAt(checkOutTable.getSelectedRow(), i), emptyRow, i);
//								checkPanel.validate();
//							}
						
					}
				});
			}
		});
		
		checkPanel.add(checkedOutLabel);
		checkPanel.add(checkedOutPane);
		checkPanel.add(checkInButton);
		checkPanel.add(checkInLabel);
		checkPanel.add(keyword);
		checkPanel.add(searchField);
		checkPanel.add(searchbutton);
		checkPanel.add(checkOutButton);
		checkPanel.add(checkOutPane);
		checkPanel.setBackground(new Color(49, 82, 91));
		

		add(checkPanel);
		setBounds(500, 200, 1000, 600);
		setResizable(false);
		setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void getCheckedOutList(String memberNum) {
		try {
			checkedOutList.clear();
			checkedOutList.addAll(checkOutDao.get(3, memberNum));
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		int num = 0;
		if (checkedOutList.size() > 0) {
			for (CheckOutVO list : checkedOutList) {
				for (int i = 0; i < list.getList().length; ++i) {
					checkedOutModel.setValueAt(list.getList()[i], num, i);
				}
				++num;
			}
		}
	}
	
	public void bookSearchTableValidate() {
		try {
			bookList.clear();
			bookList.addAll(bookDao.get(keyword.getSelectedIndex() + 1, searchField.getText()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		checkOutModel.setColumnIdentifiers(category);
		checkOutModel.setRowCount(bookList.size());
		
		int num = 0;
		for (BookVO list : bookList) {
			for (int i = 0; i < list.getList().length; ++i) {
				checkOutModel.setValueAt(list.getList()[i], num, i);
			}
			++num;
		}
		checkOutTable.setModel(checkOutModel);
		checkPanel.validate();
	}
	
}
