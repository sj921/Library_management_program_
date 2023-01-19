package lmp.members.menu.readingroom.usagelist;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import lmp.members.menu.readingroom.usagelist.panel.UsageListTitlePanel;

public class UsageListPanel extends JPanel{

	static BorderLayout borderLayout = new BorderLayout();
	
	UsageListTitlePanel usagListTitlePanel;
	
	public UsageListPanel() {
		
		setBounds(80, 50, 1300, 80);
		this.setLayout(borderLayout);
		
	}
		
}