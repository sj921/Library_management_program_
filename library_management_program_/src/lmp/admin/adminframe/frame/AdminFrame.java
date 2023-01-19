package lmp.admin.adminframe.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import lmp.admin.adminframe.button.MenuButton;
import lmp.admin.adminframe.button.OptionButton;
import lmp.admin.adminframe.button.listener.MenuButtonListener;
import lmp.admin.adminframe.button.listener.OptionButtonListener;
import lmp.admin.adminframe.frame.listener.AdminFrameWindowListener;
import lmp.admin.adminframe.panel.HomePanel;
import lmp.admin.adminframe.panel.MenuButtonPanel;
import lmp.admin.adminframe.panel.MenuCardPanel;
import lmp.admin.db.dao.SeatUseDetailDao;
import lmp.admin.db.vo.SeatUseDetailVO;
import lmp.admin.menu.book.BookMgmt;
import lmp.admin.menu.checkin_out.Member_Searching_Panel;
import lmp.admin.menu.employees.EmployeesMgmt;
import lmp.admin.menu.member.MemberMgmt;
import lmp.admin.menu.readingroom.ReadingRoomPanel;
import lmp.members.db.dao.ThemeDao;
import lmp.members.db.vo.ThemeVO;
import lmp.members.memberframe.label.ClockLabel;
import lmp.util.theme.Theme;

public class AdminFrame extends JFrame {

	JPanel panel = new JPanel();
	JScrollPane sp = new JScrollPane();
	
	private static MenuButtonPanel menuButtonPanel;
	private static MenuCardPanel menuCardPanel;
	
	private static MenuButton bookMgmtBtn;
	private static MenuButton checkInOutBtn;
	private static MenuButton readingroomBtn;
	private static MenuButton memberMgmtBTn;
	private static MenuButton employeeMgmtBtn;
	
	private static HomePanel homePanel;
	private static BookMgmt bookPanel;
	private static Member_Searching_Panel memberSearchPanel;
	private static ReadingRoomPanel readingroomPanel;
	private static MemberMgmt memberPanel;
	private static EmployeesMgmt employeePanel;
	
	private static ClockLabel clockLabel;
	
	private static OptionButton homeButton;
	private static OptionButton setupButton;
	
	private static ThemeDao themeDao;
	private static Theme theme = new Theme();
	
	public AdminFrame() throws SQLException {
		
		setLayout(null);
		setBounds(300, 100, 1200, 800);
		setTitle("관리자 모드");
		AdminFrame adminFrame = this;
		themeDao = new ThemeDao();
		
		// 자정 되면 전좌석 강제 퇴실
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				SeatUseDetailDao sDao = new SeatUseDetailDao();
				ArrayList<SeatUseDetailVO> sVo = new ArrayList<>();
				try {
					sVo.addAll(sDao.get());
					
					for (SeatUseDetailVO seat : sVo) {
						sDao.update(seat.getUse_id());
					}
				} catch (SQLException e) {}
			}
		};
		timer.schedule(task, date.getTime());
		
		
		menuButtonPanel = new MenuButtonPanel();
		menuCardPanel = new MenuCardPanel();
		
		homePanel = new HomePanel();
		bookPanel = new BookMgmt();
		memberSearchPanel = new Member_Searching_Panel();
		readingroomPanel = new ReadingRoomPanel();
		memberPanel = new MemberMgmt();
		employeePanel = new EmployeesMgmt();
		
		bookMgmtBtn = new MenuButton("book");
		checkInOutBtn = new MenuButton("barcode");
		readingroomBtn = new MenuButton("readingroom");
		memberMgmtBTn = new MenuButton("member");
		employeeMgmtBtn = new MenuButton("employee");
		
		homeButton = new OptionButton("home");
		setupButton = new OptionButton("setup");
		
		clockLabel = new ClockLabel();
		
		bookMgmtBtn.addActionListener(new MenuButtonListener(this));
		checkInOutBtn.addActionListener(new MenuButtonListener(this));
		readingroomBtn.addActionListener(new MenuButtonListener(this));
		memberMgmtBTn.addActionListener(new MenuButtonListener(this));
		employeeMgmtBtn.addActionListener(new MenuButtonListener(this));
		
		homeButton.addActionListener(new OptionButtonListener(this));
		setupButton.addActionListener(new OptionButtonListener(this));
		
		menuButtonPanel.add(bookMgmtBtn);
		menuButtonPanel.add(checkInOutBtn);
		menuButtonPanel.add(readingroomBtn);
		menuButtonPanel.add(memberMgmtBTn);
		menuButtonPanel.add(employeeMgmtBtn);
		
		menuCardPanel.add("홈 화면", homePanel);
		menuCardPanel.add("도서관리", bookPanel);
		menuCardPanel.add("대여/반납 관리", memberSearchPanel);
		menuCardPanel.add("열람실 관리", readingroomPanel);
		menuCardPanel.add("회원관리", memberPanel);
		menuCardPanel.add("직원관리", employeePanel);
		
		panel.add(clockLabel);
		panel.add(homeButton);
		panel.add(setupButton);
		panel.add(menuButtonPanel);
		panel.add(menuCardPanel);
		initialize();
		
		addWindowListener(new AdminFrameWindowListener());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(sp);
		setContentPane(sp);
		setVisible(true);
		
	}
	
	// 버튼 누르기 전 초기 화면 이미지 설정
	public void initialize() throws SQLException {
		ThemeVO getTheme = themeDao.getTheme();
		theme.setTheme(getTheme.getName());
		
		Color sub1Color = theme.getSub1Color();
		
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1900, 1000));
		panel.setBackground(theme.getMainColor());
		bookPanel.setBackground(sub1Color);
		memberSearchPanel.setBackground(sub1Color);
		readingroomPanel.setBackground(sub1Color);
//		readingroomPanel.getSeatListPanel().setBackground(sub1Color);
//		readingroomPanel.getSeatListPanel().setBorder(new LineBorder(sub1Color, 10));
//		readingroomPanel.getUsageListPanel().getUsageListScrollPane().setBackground(sub1Color);
		memberPanel.setBackground(sub1Color);
		employeePanel.setBackground(sub1Color);
		homePanel.setLabel(theme.getHomeImage());
		
		sp.setViewportView(panel);
		sp.getVerticalScrollBar().setUnitIncrement(16);
	}
	
	// 버튼 생성 및 설정 메서드
	public static JButton getButton(String text) {
		 return new JButton() {
			 {
				setHorizontalTextPosition(CENTER);
				setVerticalTextPosition(BOTTOM);
				setForeground(Color.WHITE);
				setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
				setText(text);
				if (!getText().equals("")) {
					setToolTipText(text);
				}
				setBorderPainted(false);
				setFocusPainted(false);
				setContentAreaFilled(false);
				addMouseListener(new MouseAdapter() {
					// 버튼에 마우스 올리면 테두리 생성
					@Override
					public void mouseEntered(MouseEvent e) {
						Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
						setCursor(cursor);
					}
					// 버튼에서 마우스 떼면 테두리 삭제
					@Override
					public void mouseExited(MouseEvent e) {
						Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
						setCursor(cursor);
					}
				});
			 }
		 };
	}
	
	public static JTable getTable(DefaultTableModel model) {
		JTable table = new JTable(model);
		
		table.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
		table.setRowHeight(25);
		// 테이블 컬럼 이동 안되게 설정
		table.getTableHeader().setReorderingAllowed(false);
		// 테이블에서 하나의 행만 선택되게 설정
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		return table;
	}
	
	public MenuCardPanel getMenuCardPanel() {
		return menuCardPanel;
	}
	
}
