package lmp.members.menu.book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import lmp.admin.adminframe.frame.AdminFrame;
import lmp.members.db.dao.BookDao;
import lmp.members.db.dao.CheckOutDao;
import lmp.members.db.dao.FontDao;
import lmp.members.db.dao.ThemeDao;
import lmp.members.db.vo.BookVO;
import lmp.members.db.vo.CheckOutVO;
import lmp.util.ImageConvert;
import lmp.util.font.MyFont;
import lmp.util.theme.Theme;

public class BookSearchPanel extends JPanel {

	// 패널의 열 수는 검색된 정보 수에 따라 다르게 설정 가능
	static String[] category = { "등록번호", "제목", "저자", "출판사", "ISBN", "위치" };
	public static String[] bookColumn = {"제목", "저자", "출판사", "ISBN", "편권수", "가격", "위치", "대출 가능 여부"};

	static JComboBox cb = new JComboBox(category);

	static JTextField textF = new JTextField();
	
	JPanel panel = this;

	public DefaultTableModel model_BookMgmt = new DefaultTableModel(bookColumn, 30) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};

	public DefaultTableModel getModel_BookMgmt() {
		return model_BookMgmt;
	}

	public BookDao bookDao = new BookDao();
	public ArrayList<BookVO> bookVO;
	// 도서현황 검색 창 초기화면 테이블
	public JTable table_BookMgmt = AdminFrame.getTable(model_BookMgmt);
	
	ImageConvert img = new ImageConvert();
	
	FontDao fontDao = new FontDao();
	MyFont myFont = new MyFont();
	ThemeDao themeDao = new ThemeDao();
	Theme theme = new Theme();
	
	
	public BookSearchPanel() throws SQLException {
		
		theme.setTheme(themeDao.getTheme().getName());
		setBackground(theme.getSub1Color());
		setLayout(null);
		
		JScrollPane scroll = new JScrollPane(table_BookMgmt);
		scroll.setBounds(0, 250, 1500, 500);
		add(scroll);
		
		myFont.setFont(fontDao.getFont());
		// 도서검색 라벨 선언
		JLabel label = new JLabel("도서 검색");
		label.setBounds(600, 30, 300, 50);
		label.setFont(myFont.getTitle());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);

		// 도서검색 콤보박스 선언
		cb.setBounds(320, 130, 150, 35);
		cb.setFont(myFont.getText());

		// 도서검색 텍스트필드 선언
		textF.setBounds(530, 130, 450, 35);

		// 텍스트필드 옆 검색 버튼 선언
		JButton button = AdminFrame.getButton("검색");
		button.setBounds(1010, 100, 120, 100);
		button.setIcon(img.scaledMgmtImage("search"));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 검색 키워드로 검새된 결과 받아오기
				try {
					bookVO = new ArrayList<>();
					
					bookVO.clear();
					bookVO.addAll(bookDao.get(cb.getSelectedIndex() + 1, textF.getText()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				model_BookMgmt.setRowCount(bookVO.size());
				
				CheckOutDao cDao = new CheckOutDao();
				ArrayList<CheckOutVO> cVo = new ArrayList<>();
				
				int resetRow = 0;
				// 한 권씩 정보 모델에 넣기
				for (BookVO book : bookVO) {
					for (int i = 0; i < bookColumn.length; ++i) {
						if (bookColumn[i].equals("대출 가능 여부")) {
							try {
								// 해당 도서의 대출 여부를 파악하기 위한 코드
								cVo.clear();
								cVo.addAll(cDao.checkBook(book.getId()));
								
								// 대출 내역이 존재하는 도서 중
								if (cVo.size() != 0) {
									// 반납일이 없는 도서 대출 불가 표시
									if (cVo.get(0).getCheckInDate() == null) {
										model_BookMgmt.setValueAt("대출 불가", resetRow, i);
									} else {
										model_BookMgmt.setValueAt("대출 가능", resetRow, i);
									}
								} else {
									model_BookMgmt.setValueAt("대출 가능", resetRow, i);
								}
								// 비고가 null이 아닌 도서 중
								if (book.getNote() != null) {
									// 훼손 / 분실 관련 도서 대출 불가 표시
									if (book.getNote().contains("훼손") ||
										book.getNote().contains("분실")) {
										model_BookMgmt.setValueAt("대출 불가", resetRow, i);
									} else {
										model_BookMgmt.setValueAt("대출 가능", resetRow, i);
									}
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						} else {
							model_BookMgmt.setValueAt(book.getList()[i], resetRow, i);
						}
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
		
		add(label);
		add(cb);
		add(textF);
		add(button);
		
	}
	
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


	// 메인 검색 테이블 새로고침 메서드
	public void tableValidate() {
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
