package lmp.admin.menu.book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import lmp.util.ImageConvert;

public class BookMgmt extends JPanel {

	// 패널의 열 수는 검색된 정보 수에 따라 다르게 설정 가능
	static String[] category = { "등록번호", "제목", "저자", "출판사", "ISBN", "위치" };
	public static String[] bookColumn = { "등록번호", "제목", "저자", "출판사", "ISBN", "편권수", "복권수", "등록일", "가격", "위치", "비고" };

	static JComboBox cb = new JComboBox(category);

	static JTextField textF = new JTextField();
	
	JPanel panel = this;
	
	public static DefaultTableModel model_BookMgmt = new DefaultTableModel(bookColumn, 30) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	
	public DefaultTableModel getModel_BookMgmt() {
		return model_BookMgmt;
	}
	
	public static BookDao bookDao = new BookDao();
	public static ArrayList<BookVO> bookVO;
	// 도서현황 검색 창 초기화면 테이블
	public static JTable table_BookMgmt = AdminFrame.getTable(model_BookMgmt);

	public static JButton getButton(String text) {
		return new JButton() {
			{
				setText(text);
				setBackground(Color.PINK);
				setBorderPainted(false);
				setFocusPainted(false);
				setContentAreaFilled(false);
				setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 18));
				setVerticalTextPosition(JButton.CENTER);
				setHorizontalTextPosition(JButton.RIGHT);
				setForeground(Color.WHITE);
				addMouseListener(new MouseAdapter() {
					// 버튼에 마우스 올리면 배경색 변경
					@Override
					public void mouseEntered(MouseEvent e) {
						Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
						setCursor(cursor);
					}

					// 버튼에서 마우스 떼면 배경색 투명
					@Override
					public void mouseExited(MouseEvent e) {
						Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
						setCursor(cursor);
					}
				});
			}
		};
	}
	
	ImageConvert img = new ImageConvert();

	public BookMgmt() {

		setBackground(new Color(87, 119, 119));
//		setBounds(17, 200, 1150, 550);
		setLayout(null);
		
		// JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table_BookMgmt);
		scroll.setBounds(0, 250, 1500, 500);
		add(scroll);

		// 도서검색 라벨 선언
		JLabel label = new JLabel("도서 검색");
		label.setBounds(600, 30, 300, 50);
		label.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 40));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);

		// 도서검색 콤보박스 선언
		cb.setBounds(330, 130, 150, 35);
		cb.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));

		// 도서검색 텍스트필드 선언
		textF.setBounds(530, 130, 450, 35);

		// 텍스트필드 옆 검색 버튼 선언
		JButton button = AdminFrame.getButton("검색");
		button.setBounds(1010, 100, 120, 100);
		button.setIcon(img.scaledMgmtImage("search"));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * int index = 0; for (int i = 0; i < category.length; ++i) { if
				 * (category[i].equals(cb.getSelectedItem())) { index = i; } }
				 * 
				 * // DB에서 도서 정보 테이블 컬럼, 도서 정보 가져와서 category, contents에 넣어야 함 String[][] valid =
				 * new String[50][category.length];
				 * 
				 * 
				 * int index2 = 0; for (int i = 0; i < contents.length; ++i) { if
				 * (contents[i][index].contains(textF.getText())) { valid[index2++] =
				 * contents[i]; } } model.setRowCount(valid.length);
				 * 
				 * for (int i = 0; i < valid.length; ++i) { for (int j = 0; j < category.length;
				 * ++j) { model.setValueAt(valid[i][j], i, j); } } table.setModel(model);
				 * 
				 * bookMgmt.add(scroll); bookMgmt.validate(); textF.setText("");
				 */

				try {
					bookVO = new ArrayList<>();

					bookVO.clear();
					bookVO.addAll(bookDao.get(cb.getSelectedIndex() + 1, textF.getText()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				model_BookMgmt.setRowCount(bookVO.size());
				model_BookMgmt.setColumnIdentifiers(bookColumn);

				int resetRow = 0;
				for (BookVO book : bookVO) {
					for (int i = 0; i < book.getList().length; ++i) {

						model_BookMgmt.setValueAt(book.getList()[i], resetRow, i);
					}
					++resetRow;
				}
				table_BookMgmt.setModel(model_BookMgmt);
				scroll.validate();
				validate();
			}
		});

		textF.addActionListener(new ActionListener() { // 검색버튼 클릭 시 작동기능
			@Override
			public void actionPerformed(ActionEvent e) {
				button.doClick();
			}
		});

		// 도서등록 버튼 선언
		JButton registration = getButton(" 등록");
		registration.setBounds(1320, 15, 150, 70);
		registration.setIcon(img.scaledMgmtImage("bookregister"));
		registration.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(bookMgmtframe, "도서등록 화면으로 이동합니다.");
				// new BookRegistration("화면등록");
				new BookRegistrationFrame("화면등록");
			}
		});
		add(registration);

		// 내용삭제 버튼 선언
		JButton delete = getButton(" 삭제");
		delete.setBounds(1320, 95, 150, 70);
		delete.setIcon(img.scaledMgmtImage("bookdelete"));
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (table_BookMgmt.getSelectedRow() == -1 ||
					model_BookMgmt.getValueAt(table_BookMgmt.getSelectedRow(), 0) == null) {
					JOptionPane.showMessageDialog(null, "삭제할 도서를 선택하세요.");
				} else {
					int num = JOptionPane.showConfirmDialog(null, "선택한 도서정보\n정말로 삭제하시겠습니까?", "선택도서 삭제",
							JOptionPane.YES_NO_OPTION);
					// Yes -> 0 No -> 1 을 반환함
					switch (num) {
					case 0:
						try {
							// 도서 삭제시 대여 중이면 안내문구 출력 후 버튼 무효
							CheckOutDao checkOutDao = new CheckOutDao();
							ArrayList<CheckOutVO> checkOutVo = new ArrayList<CheckOutVO>();

							checkOutVo.addAll(checkOutDao.get(
									model_BookMgmt.getValueAt(
											table_BookMgmt.getSelectedRow(), 0).toString()));

							if (checkOutVo.size() != 0) {
								JOptionPane.showMessageDialog(null, "대여 중인 도서입니다.\n반납 후 삭제 해주시기 바랍니다.");
								return;
							}

							int book_id = Integer.parseInt(
									(model_BookMgmt.getValueAt(table_BookMgmt.getSelectedRow(), 0).toString()));

							ArrayList<BookVO> updateDuplicateList = new ArrayList<>();

							updateDuplicateList = bookDao.get(1, String.valueOf(book_id));

							// 도서 삭제
							bookDao.delete(book_id);

							// 삭제한 도서와 동일한 이름, 저자, 편권수를 가진 책이 있으면 복권수 감소
							if (updateDuplicateList.size() != 0) {
								bookDao.decreaseDuplicate(updateDuplicateList.get(0).getTitle(),
									updateDuplicateList.get(0).getAuthor(), updateDuplicateList.get(0).getBias());	
							}

							// 삭제된 도서 제외하고 테이블 새로고침
							bookVO = new ArrayList<>();

							bookVO.clear();
							bookVO.addAll(bookDao.get(cb.getSelectedIndex() + 1, textF.getText()));
							model_BookMgmt.setRowCount(bookVO.size());
							model_BookMgmt.setColumnIdentifiers(bookColumn);

							int resetRow = 0;
							for (BookVO book : bookVO) {
								for (int i = 0; i < book.getList().length; ++i) {
									model_BookMgmt.setValueAt(book.getList()[i], resetRow, i);
								}
								++resetRow;
							}
							table_BookMgmt.setModel(model_BookMgmt);
							scroll.validate();
							validate();

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
//	                  BookDao bookDao = new BookDao();
//	                  ArrayList<BookVO> bookVO = new ArrayList<>();
//	                  try {
//	                     bookVO.clear();
//	                     bookVO.addAll(bookDao.get(1, String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
//	                     bookDao.delete(bookVO.get(0));
//	                  } catch (SQLException e1) {
//	                     e1.printStackTrace();
//	                  }
						validate();
						JOptionPane.showMessageDialog(null, "선택한 도서를 삭제했습니다. 다시 되돌릴 수 없습니다.");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "취소합니다.");
						break;
					}
				}
			}
		});
		add(delete);

		// 정보수정 버튼 선언
		JButton modification = getButton(" 수정");
		modification.setBounds(1320, 175, 150, 70);
		modification.setIcon(img.scaledMgmtImage("bookmodify"));
		modification.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table_BookMgmt.getSelectedRow() == -1
						|| model_BookMgmt.getValueAt(table_BookMgmt.getSelectedRow(), 0) == null) {
					JOptionPane.showMessageDialog(null, "수정할 도서를 선택하세요.");
				} else {
					BookModificationFrame bookmodification = new BookModificationFrame("정보수정");
					for (int i = 1; i < model_BookMgmt.getColumnCount(); i++) {
						bookmodification.model_Modify
								.setValueAt(model_BookMgmt.getValueAt(table_BookMgmt.getSelectedRow(), i), 0, i - 1);
					}
					bookmodification.table_Modify.setModel(bookmodification.model_Modify);
				}
			}
		});
		add(modification);

		add(label);
		add(cb);
		add(textF);
		add(button);
		
//		this.setLayout(new GridLayout(2, 1));
//		add(new BookSearchPanel());
//		add(new BookListTablePanel());
	}

	// 메인 검색 테이블 새로고침 메서드
	public static void tableValidate() {
		try {
			bookVO = new ArrayList<>();

			bookVO.clear();
			bookVO.addAll(bookDao.get(cb.getSelectedIndex() + 1, textF.getText()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		model_BookMgmt.setRowCount(bookVO.size());
		model_BookMgmt.setColumnIdentifiers(bookColumn);

		int resetRow = 0;
		for (BookVO book : bookVO) {
			for (int i = 0; i < book.getList().length; ++i) {

				model_BookMgmt.setValueAt(book.getList()[i], resetRow, i);
			}
			++resetRow;
		}
		table_BookMgmt.setModel(model_BookMgmt);
	}
	
}