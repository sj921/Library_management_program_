package lmp.members.menu.book;
/*
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import lmp.admin.AdminFrame;
import lmp.db.dao.BookDao;
import lmp.db.vo.BookVO;

public class BookSearchFrame extends JFrame {

	JLabel label;
	JComboBox keyword;
	JButton button;
	JTextField searchField;
	JScrollPane result;
	JTable table;
	
	String[] keywordList = {"등록번호", "도서명", "저자", "출판사", "ISBN", "위치"};
	
	String[] bookColumn = {"등록번호", "도서명", "저자", "출판사", "ISBN", "편권수", "복권수", "가격", "위치", "비고"};
	
	BookDao bdao = new BookDao();
	
	DefaultTableModel model = new DefaultTableModel(bookColumn, 30) {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	
	
	
	public BookSearchFrame() {
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1185, 761);
		panel.setLayout(null);
		
		table = new JTable(model);
		// 테이블 컬럼 이동 안되게 설정
		table.getTableHeader().setReorderingAllowed(false);
		// 테이블에서 하나의 행만 선택되게 설정
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		result = new JScrollPane(table);
		result.setBounds(0, 300, 1185, 462);
		
		
		BufferedImage buffer = null;
		try {
			buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/bookMgmtImage.png"));
			Image image = buffer.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			label = new JLabel(new ImageIcon(image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		label.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 20));
		label.setBounds(530, 20, 150, 150);
		label.setText("도서 검색");
		label.setForeground(Color.WHITE);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		
		
		keyword = new JComboBox(keywordList);
		keyword.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		keyword.setBounds(220, 200, 150, 30);
		keyword.setBackground(new Color(126, 151, 148));
		
		try {
			buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/searchButtonIcon.png"));
			Image image = buffer.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			button = AdminFrame.getButton("검색하기");
			button.setIcon(new ImageIcon(image));
			button.setBounds(850, 180, 100, 80);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		
		// 텍스트 필드에서 엔터 누르면 버튼 클릭되도록 액션 추가 (검색 버튼 눌러도 되고 텍스트 필드에서 엔터 눌러도 검색됨)
		searchField = new JTextField("검색어를 입력해주세요.") {
			private Shape shape;
			{
				setOpaque(false);
			}
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
				super.paintComponent(g);
			}
			protected void paintBorder(Graphics g) {
				g.setColor(getBackground());
				g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
			}
			public boolean contains(int x, int y) {
				if (shape == null || !shape.getBounds().equals(getBounds())) {
					shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
				}
				return shape.contains(x, y);
			}
		};
		searchField.setBackground(new Color(126, 151, 148));
		searchField.setBounds(420, 200, 400, 30);
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchField.setText("");
				button.doClick();
			}
		});
		searchField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchField.setText("");
			}
		});
		
		panel.add(label);
		panel.add(keyword);
		panel.add(searchField);
		panel.add(button);
		panel.add(result);
		panel.setBackground(new Color(49, 82, 91));
		
		// 검색 버튼 눌렀을 때 해당 키워드에 맞는 정보 있으면 출력
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchField.setText("");
				
				String[][] valid = new String[50][bookColumn.length];
				
				// 테이블 수정 안되게 세팅
				DefaultTableModel model = null;
				try {
					model = new DefaultTableModel(bdao.get(keyword.getItemCount(), searchField.getText()).size(), bookColumn.length) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					model.addRow(bdao.get(keyword.getItemCount(), searchField.getText()).toArray());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(model);
				table.validate();
				
				
				// 컨테이너 사이즈에 따라 자동으로 테이블 크기 조정 안되게 세팅 (가로 스크롤 나오게 설정)
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				result.setViewportView(table);
				validate();
			}
		});
		
		add(panel);
		
		setResizable(false);
		setBounds(400, 150, 1200, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new BookSearchFrame();
	}
}
*/