package lmp.admin.adminframe.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import lmp.util.ImageConvert;

public class MenuButton extends JButton{
	
	static ImageConvert img = new ImageConvert();
	
	public MenuButton(String text) {
		
		if (text.equals("book")) {
			setText("도서관리");
		} else if (text.equals("barcode")) {
			setText("대여/반납 관리");
		} else if (text.equals("readingroom")) {
			setText("열람실 관리");
		} else if (text.equals("member")) {
			setText("회원관리");
		} else if (text.equals("employee")) {
			setText("직원관리");
		}
		
		setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
		setIcon(img.scaledMenuImage(text));
		initialize();
	}

	public void initialize() {
		setHorizontalTextPosition(CENTER);
		setVerticalTextPosition(BOTTOM);
		setForeground(Color.WHITE);
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
}
