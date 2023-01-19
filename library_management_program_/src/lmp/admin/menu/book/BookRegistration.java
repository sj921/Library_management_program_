package lmp.admin.menu.book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BookRegistration extends JFrame implements MouseListener, KeyListener {

	private final String[] labels = { "제목", "저자", "출판사", "ISBN", "편권수", "복권수", "등록일", "가격", "위치", "비고" };
	private JTextField[] fields = new JTextField[10];

	private JScrollPane scrolledTable;
	private JTable table;

	private JButton addBtn;
	private JButton delBtn;
	private JButton saveBtn;

	public BookRegistration(String title) {

		// this.setTitle(title);
		this.setLayout(new BorderLayout(10, 10));

		JPanel topPanel = new JPanel(new GridLayout(5, 2, 100, 5));
		for (int i = 0; i < 10; i++) {
			JLabel label = new JLabel(labels[i]);
			label.setForeground(Color.WHITE);
			topPanel.add(label);
			fields[i] = new JTextField(100);
			topPanel.add(fields[i]);
		}
		topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.add("North", topPanel); // 가장 위쪽 Panel 설정
		topPanel.setBackground(Color.DARK_GRAY);

		String column[] = { "제목", "저자", "출판사", "ISBN", "편권수", "복권수", "등록일", "가격", "위치", "비고" };
		DefaultTableModel model = new DefaultTableModel(column, 0); // column추가, 행은 1개 지정

		table = new JTable(model);
		// table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrolledTable = new JScrollPane(table); // 스크롤 될 수 있도록 JScrollPane 적용
		scrolledTable.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 너무 붙어있어서 가장자리 띄움(padding)
		this.add("Center", scrolledTable); // 가운데에 JTable 추가

		JPanel rightPanel = new JPanel(new GridLayout(3, 1, 10, 10));

		addBtn = new JButton("추가");
		delBtn = new JButton("제외");
		saveBtn = new JButton("저장");

//	      saveBtn.addActionListener(new ActionListener() {
//	         JOptionPane op = new JOptionPane();
//
//	         @Override
//	         public void actionPerformed(ActionEvent e) {
//	            try {
//	               saveBook();
//	            } catch (SQLException e1) {
//	               // TODO Auto-generated catch block
//	               e1.printStackTrace();
//	            }
//
//	            // JOptionPane.showMessageDialog(table, "DB update completed");
//	         }
//
//	         private void saveBook() throws SQLException {
//	            // TODO Auto-generated method stub
//	            BookDao dao = new BookDao();
//
//	            DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//	            //특정행 선택 찾기 = getSelectedRow
//	            for (int i = 0; i < model.getRowCount(); i++) {
//
//	                  BookVO book = new BookVO(null, 
//	                        model.getValueAt(i, 0).toString(), 
//	                        model.getValueAt(i, 1).toString(), 
//	                        model.getValueAt(i, 2).toString(), 
//	                        model.getValueAt(i, 3).toString(), 
//	                        Integer.parseInt(model.getValueAt(i, 4).toString()),
//	                        Integer.parseInt(model.getValueAt(i, 5).toString()),
//	                        model.getValueAt(i, 6).toString(),
//	                        Integer.parseInt(model.getValueAt(i, 7).toString()),
//	                        null, //로케이션 확인
//	                        model.getValueAt(i, 9).toString()
//	                        );
//	                  
//	                  //book.setTitle(model.getValueAt(i, 0).toString());   //setter 있는 경우 
//
//	                  dao.add(book);
//	                  //dao.update(book);
//	                  //dao.delete(book);
//	               
//	               }
//
//	         }
//
//	      });

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null); // 창 가운데 위치
		this.setVisible(true);

		// 이벤트 추가
		addBtn.addMouseListener(this); // 추가 처리
		delBtn.addMouseListener(this); // 삭제 처리
		for (int i = 0; i < column.length; i++)
			fields[i].addKeyListener(this); // 엔터 처리
		table.addMouseListener(this); // 셀 읽기 처리

		// 추가,제외,저장 각 버튼 이미지 삽입
		// 추가버튼 이미지
		BufferedImage bfi_add = null;
		try {
			bfi_add = ImageIO.read(new File("src\\lmp\\admin\\menu\\book\\images\\plusBtnIcon.png"));
		} catch (IOException e) {}
		Image image_add = bfi_add.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);

		addBtn = BookMgmt.getButton(" 추가");
		addBtn.setIcon(new ImageIcon(image_add));
		addBtn.setBounds(1020, 110, 120, 40);
		addBtn.setForeground(Color.BLACK);
		// 제외버튼 이미지
		BufferedImage bfi_del = null;
		try {
			bfi_del = ImageIO.read(new File("src\\lmp\\admin\\menu\\book\\images\\minusBtnIcon.png"));
		} catch (IOException e) {}
		Image del = bfi_del.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);

		delBtn = BookMgmt.getButton(" 제외");
		delBtn.setIcon(new ImageIcon(del));
		delBtn.setBounds(1020, 110, 120, 40);
		delBtn.setForeground(Color.BLACK);
		// 저장버튼 이미지
		BufferedImage bfi_save = null;
		try {
			bfi_save = ImageIO.read(new File("src\\lmp\\admin\\menu\\book\\images\\saveIconImage.png"));
		} catch (IOException e) {}
		Image save = bfi_save.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);

		saveBtn = BookMgmt.getButton(" 저장");
		saveBtn.setIcon(new ImageIcon(save));
		saveBtn.setBounds(1020, 110, 120, 40);
		saveBtn.setForeground(Color.BLACK);
		rightPanel.add(addBtn);
		rightPanel.add(delBtn);
		rightPanel.add(saveBtn);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add("East", rightPanel); // 오른쪽에 버튼들 추가
	}

	private boolean isInvalidInput(String input) {
		return input == null || input.length() == 0;
	}

	public void removeRecord(int index) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (index < 0) {
			if (table.getRowCount() == 0)// 비어있는 테이블이면
				return;
			index = 0;
		}
		model.removeRow(index);
	}

	public void addRecord() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String[] record = new String[10];
		for (int i = 0; i < 9; i++) {
			if (isInvalidInput(fields[i].getText())) {
				return;
			}
			record[i] = fields[i].getText();
		}
		model.addRow(record);

		// 모든 TextField 비우기
		for (int i = 0; i < 10; i++)
			fields[i].setText("");
		fields[0].requestFocus();
	}

	public void printCell(int row, int col) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	}

	// MouseListener Overrides
	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		if (src == addBtn)
			addRecord();

		if (src == delBtn) {
			int selected = table.getSelectedRow();
			removeRecord(selected);
		}

		if (src == table) {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			printCell(row, col);
		}

	}

	//
	// private int saveBook(List<BookVO> bookList) {
//	      // TODO Auto-generated method stub
//	      
//	      BookVO book = new BookVO();
//	      
//	      
	// }

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
		if (keyCode == KeyEvent.VK_ENTER) {
			addRecord();
		}
	}

}