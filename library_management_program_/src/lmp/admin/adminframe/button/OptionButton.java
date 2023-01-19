package lmp.admin.adminframe.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import lmp.util.ImageConvert;

public class OptionButton extends JButton {
	
	ImageConvert img = new ImageConvert();
	
	public OptionButton(String text) {
		if (text.equals("home")) {			
			setBounds(20, 30, 50, 80);
			setToolTipText("홈 화면");
			
		} else if (text.equals("setup")){
			setBounds(90, 30, 50, 80);
			setToolTipText("설정");
		}
		setFont(new Font("한컴 말랑말랑 Regular",Font.BOLD, 15));
		setIcon(img.scaledOptionImage(text));
		initialize();
	}
	
	public void initialize() {
		setHorizontalTextPosition(CENTER);
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
