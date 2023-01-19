package lmp.admin.menu.book.booksearch.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JButton;

public abstract class BookButton extends JButton {
	
	public BookButton() {
			this.setBackground(Color.PINK);
			this.setBorderPainted(false);
			this.setFocusPainted(false);
			this.setContentAreaFilled(false);
			this.setFont(new Font(null, Font.BOLD, 18));
			this.setVerticalTextPosition(JButton.CENTER);
			this.setHorizontalTextPosition(JButton.RIGHT);
			this.setForeground(Color.WHITE);
			this.addMouseListener(new MouseAdapter() {
				// 버튼에 마우스 올리면 배경색 변경
				@Override
				public void mouseEntered(MouseEvent e) {
					// setFocusPainted(true);
					setContentAreaFilled(true);
				}

				// 버튼에서 마우스 떼면 배경색 투명
				@Override
				public void mouseExited(MouseEvent e) {
					// setFocusPainted(false);
					setContentAreaFilled(false);
				}
			});
	}
}
