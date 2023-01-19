package lmp.members.memberframe.panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import lmp.util.theme.Theme;

public class MenuButtonPanel extends JPanel{

	
	Theme theme;
	GridLayout grid = new GridLayout(1,3,100,0);
	
	public MenuButtonPanel() {
		theme = new Theme();
		initialize();
		
	}
	
	public void initialize() {
		
		setLayout(grid);
		setBounds(350, 0, 1200, 200);
		setBackground(theme.getMainColor());
		
	}
	
}
