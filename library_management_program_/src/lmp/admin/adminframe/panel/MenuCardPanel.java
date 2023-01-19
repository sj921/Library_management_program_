package lmp.admin.adminframe.panel;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MenuCardPanel extends JPanel{
	
	private CardLayout card = new CardLayout();
	
	public MenuCardPanel() {
		
		setBounds(200, 220, 1500, 750);
		setLayout(card);
	}
	
	public CardLayout getCard() {
		return card;
	}
	
	
}
