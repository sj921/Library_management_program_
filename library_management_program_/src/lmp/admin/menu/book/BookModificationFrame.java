package lmp.admin.menu.book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import lmp.admin.db.vo.BookVO;
import lmp.admin.db.vo.LocationVO;
import lmp.util.ImageConvert;

public class BookModificationFrame extends JFrame implements MouseListener, KeyListener {

	private final static String[] TABLE_COLUMN = {"제목", "저자", "출판사", "ISBN", "편권수", "복권수", "등록일", "가격", "위치", "비고" };
	private JTextField[] fields_Modify = new JTextField[10];
	private final static String[] BOOK_LOCATIONS = {"A.철학", "B.종교", "C.사회과학", "D.자연과학", "E.기술과학", "F.예술", "G.언어", "H.문학", "I.역사"};
	private JScrollPane scrolledTable_Modify;
	public JTable table_Modify;
	public DefaultTableModel model_Modify = new DefaultTableModel(TABLE_COLUMN, 1); // column추가, 행은 1개 지정
	private JButton overwriteBtn, comebackBtn, saveBtn_Modify;
	JComboBox cb_Modify = new JComboBox(BOOK_LOCATIONS);

	ImageConvert img = new ImageConvert();
	
	JFrame f = this;
	
	BookDao bookDao = new BookDao();
	
	public BookModificationFrame(String title) {

		this.setTitle(title);
		this.setLayout(new BorderLayout(10, 10));
		
		// 중앙 스크롤테이블(도서검색 후 그 정보를 가져와 보여주는 영역)
		table_Modify = AdminFrame.getTable(model_Modify);
		// table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrolledTable_Modify = new JScrollPane(table_Modify); // 스크롤 될 수 있도록 JScrollPane 적용
		scrolledTable_Modify.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 너무 붙어있어서 가장자리 띄움(padding)
		this.add("Center", scrolledTable_Modify); // 가운데에 JTable 추가
				

		cb_Modify.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 12));
		
		// 상단 패널(정보수정할 내용을 입력하는 텍스트필드 영역)
		JPanel topPanel = new JPanel(new GridLayout(6, 2, 100, 5));
		for (int i = 0; i < TABLE_COLUMN.length; i++) {
			JLabel label = new JLabel(TABLE_COLUMN[i]);
			label.setForeground(Color.WHITE);
			topPanel.add(label);
			fields_Modify[i] = new JTextField(100);
			if (TABLE_COLUMN[i].equals("위치")) {
				// 콤보박스 초기값을 수정 도서의 위치로 설정
				cb_Modify.setSelectedIndex(
						BookMgmt.model_BookMgmt.getValueAt(
								BookMgmt.table_BookMgmt.getSelectedRow(), 9).toString().charAt(0) - 'A');
				topPanel.add(cb_Modify);
			} else {
				if (BookMgmt.model_BookMgmt.getValueAt(BookMgmt.table_BookMgmt.getSelectedRow(), i + 1) == null){
					fields_Modify[i].setText("");
				} else {
					fields_Modify[i].setText(
						String.valueOf(BookMgmt.model_BookMgmt.getValueAt(
								BookMgmt.table_BookMgmt.getSelectedRow(), i + 1)));
				}
				topPanel.add(fields_Modify[i]);
			}
		}
		topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.add("North", topPanel); // 가장 위쪽 Panel 설정
		topPanel.setBackground(Color.DARK_GRAY);

		fields_Modify[5].setEditable(false);
		fields_Modify[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "수정이 불가능한 항목입니다.");
			}
		});
		fields_Modify[6].setEditable(false);
		fields_Modify[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "수정이 불가능한 항목입니다.");
			}
		});

		// 위치정보
//		cb_Modify.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					fields_Modify[8].setText((String) e.getItem());
//				}
//			}
//		});
		
		

		// 하단 패널(덮어쓰기/원래대로/저장하기 버튼이 위치하는 영역)
		JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 10));

		overwriteBtn = new JButton("추가");
		comebackBtn = new JButton("제외");
		saveBtn_Modify = new JButton("저장");

		// 덮어쓰기,원래대로,저장 각 버튼 이미지 삽입
		// 덮어쓰기 버튼 이미지
		overwriteBtn = BookMgmt.getButton(" 덮어쓰기");
		overwriteBtn.setIcon(img.scaledMgmtImage("overwrite"));
		overwriteBtn.setBounds(1020, 110, 120, 40);
		overwriteBtn.setForeground(Color.BLACK);

		// 원래대로 버튼 이미지
		comebackBtn = BookMgmt.getButton(" 원래대로");
		comebackBtn.setIcon(img.scaledMgmtImage("comeback"));
		comebackBtn.setBounds(1020, 110, 120, 40);
		comebackBtn.setForeground(Color.BLACK);

		// 저장버튼 이미지
		saveBtn_Modify = BookMgmt.getButton(" 저장하기");
		saveBtn_Modify.setIcon(img.scaledMgmtImage("savemodify"));
		saveBtn_Modify.setBounds(1020, 110, 120, 40);
		saveBtn_Modify.setForeground(Color.BLACK);
		saveBtn_Modify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateRecord();
				f.dispose();
			}
		});

		bottomPanel.add(overwriteBtn);
		bottomPanel.add(comebackBtn);
		bottomPanel.add(saveBtn_Modify);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add("South", bottomPanel); // 오른쪽에 버튼들 추가

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null); // 창 가운데 위치
		this.setVisible(true);

		// 이벤트 추가
		overwriteBtn.addMouseListener(this); // 추가 처리
		comebackBtn.addMouseListener(this); // 삭제 처리
		for (int i = 0; i < TABLE_COLUMN.length; i++)
			fields_Modify[i].addKeyListener(this); // 엔터 처리
		table_Modify.addMouseListener(this); // 셀 읽기 처리
		
	}

	private boolean isInvalidInput(String input) {
		return input == null || input.length() == 0;
	}

	public void comebackRecord(int index) {
		DefaultTableModel model = (DefaultTableModel) table_Modify.getModel();
		if (index < 0) {
			if (table_Modify.getRowCount() == 0)// 비어있는 테이블이면
				return;
			index = 0;
		}
		model.removeRow(index);
	}

	public void overwriteRecord() {
		for (int i = 0; i < TABLE_COLUMN.length; i++) {
			
			// 편권수/가격에 음수 혹은 문자 입력하면 안내 문구 출력
			if (TABLE_COLUMN[i].equals("편권수")) {
				try {
					if (Integer.parseInt(fields_Modify[i].getText()) <= 0) {
						JOptionPane.showMessageDialog(null, "편권수에 1이상의 수를 입력해주세요.");
						return;
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "편권수를 숫자로 입력해주세요.");
					return;
				}
			} else if (TABLE_COLUMN[i].equals("가격")) {
				try {
					if (Integer.parseInt(fields_Modify[i].getText()) <= 0) {
						JOptionPane.showMessageDialog(null, "가격에 1이상의 수를 입력해주세요.");
						return;
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "가격을 숫자로 입력해주세요.");
					return;
				}
			}
			
			// 위치 정보는 textField가 아니기 때문에 반복문에서 제외
			if (TABLE_COLUMN[i].equals("위치")) {
				model_Modify.setValueAt(cb_Modify.getSelectedItem(), 0, i);
				// 아무것도 없으면 아래 코드 패스
			} else if (fields_Modify[i].getText().trim().equals("")) {
				continue;
			// fields에 무언가 있을 때 테이블에 있는 정보와 비교해서 다르면 정보 수정
			} else {
				model_Modify.setValueAt(fields_Modify[i].getText(), 0, i);
			}
		}
	}
	
	public void updateRecord() {
		int row = BookMgmt.table_BookMgmt.getSelectedRow();
		BookVO book = new BookVO();
		ArrayList<BookVO> duplicateList = new ArrayList<BookVO>();
		
		try {
			book = bookDao.get(1, BookMgmt.model_BookMgmt.getValueAt(row, 0).toString()).get(0);
		} catch (SQLException e1) {}
		
		// 편권수가 수정될 경우 기존 도서들은 복권수 감소, 해당 도서는 복권수 1로 변경
		if (book.getBias() != model_Modify.getValueAt(0, 4)) {
			try {
				bookDao.decreaseDuplicate(book.getTitle(), book.getAuthor(), book.getBias());
			} catch (SQLException e) {}
			
			try {
				
				String bookTitle = model_Modify.getValueAt(0, 0).toString();
				String bookAuthor = model_Modify.getValueAt(0, 1).toString();
				int bookBias = Integer.parseInt(model_Modify.getValueAt(0, 4).toString());
				
				// 수정 도서의 제목, 저자, 편권수 정보와 같은 정보를 가진 도서들이 있다면 복권수 증가
				duplicateList.addAll(bookDao.getDuplicates(bookTitle, bookAuthor, bookBias));
				
				for (BookVO duplicate : duplicateList) {
					bookDao.increaseDuplicate(bookTitle, bookAuthor, bookBias);
				}
				BookMgmt.bookVO.get(row).setDuplicates(duplicateList.get(0).getDuplicates());
				
			} catch (NumberFormatException | SQLException e1) {
				// 같은 정보의 도서가 없다면 복권수 1
				BookMgmt.bookVO.get(row).setDuplicates(1);
			}			
		}
		
		// BookVO에서 수정 항목 데이터 변경
		BookMgmt.bookVO.get(row).setTitle(model_Modify.getValueAt(0, 0).toString());
		BookMgmt.bookVO.get(row).setAuthor(model_Modify.getValueAt(0, 1).toString());
		BookMgmt.bookVO.get(row).setPublisher(model_Modify.getValueAt(0, 2).toString());
		BookMgmt.bookVO.get(row).setIsbn(model_Modify.getValueAt(0, 3).toString());
		BookMgmt.bookVO.get(row).setBias(Integer.parseInt(model_Modify.getValueAt(0, 4).toString()));
		BookMgmt.bookVO.get(row).setPrice(Integer.parseInt(model_Modify.getValueAt(0, 7).toString()));
		BookMgmt.bookVO.get(row).setLocation(new LocationVO(model_Modify.getValueAt(0, 8).toString().substring(0, 1), model_Modify.getValueAt(0, 8).toString().substring(2)));
		if (model_Modify.getValueAt(0, 9) != null) {
			BookMgmt.bookVO.get(row).setNote(model_Modify.getValueAt(0, 9).toString());
		}
		try {
			bookDao.update(BookMgmt.bookVO.get(row));
		} catch (SQLException e) {}
		
		JOptionPane.showMessageDialog(null, "수정 사항이 저장되었습니다.");
		
		// 데이터 수정후 검색 테이블 새로고침
		BookMgmt.tableValidate();
		
//				(
//				model_Modify.getValueAt(0, 0).toString(),
//				model_Modify.getValueAt(0, 1).toString(),
//				model_Modify.getValueAt(0, 2).toString(),
//				model_Modify.getValueAt(0, 3).toString(),
//				model_Modify.getValueAt(0, 4).toString(),
//				Integer.parseInt(model_Modify.getValueAt(0, 5).toString()),
//				Integer.parseInt(model_Modify.getValueAt(0, 6).toString()),
//				model_Modify.getValueAt(0, 7).toString(),
//				Integer.parseInt(model_Modify.getValueAt(0, 8).toString()),
//				(LocationVO)model_Modify.getValueAt(0, 9),
//				model_Modify.getValueAt(0, 10)).toString();
//		bookDao.up
	}

	public void printCell(int row, int col) {
		DefaultTableModel model = (DefaultTableModel) table_Modify.getModel();
	}

	// MouseListener Overrides
	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		if (src == overwriteBtn)
			overwriteRecord();

		if (src == comebackBtn) {
			for (int i = 1; i < BookMgmt.bookColumn.length; i++) {
				model_Modify.setValueAt(BookMgmt.model_BookMgmt.getValueAt(BookMgmt.table_BookMgmt.getSelectedRow(), i),
						0, i - 1);
			}
			table_Modify.setModel(model_Modify);
		}

		// 저장버튼 클릭 시 작동기능 명령(203-223)
//		if (src == saveBtn_Modify) {
//			int num = JOptionPane.showConfirmDialog(table, "정말로 저장합니까?", "DB에 저장하기", JOptionPane.YES_NO_OPTION);
//			switch (num) {
//			case 0:
//				BookDao bookDao = new BookDao();
//				ArrayList<BookVO> bookVO = new ArrayList<>();
//				try {
//					bookVO.clear();
//					bookVO.addAll(bookDao.get(1, String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
//					bookDao.update(bookVO.get(0));
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				table.validate();
//				JOptionPane.showMessageDialog(table, "변경된 내용이 DB에 저장되었습니다.");
//				break;
//			case 1:
//				JOptionPane.showMessageDialog(table, "취소합니다.");
//				break;
//			}
//		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// KeyListener Overrides
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
	}

}
