package lmp.admin.adminframe.panel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.util.ImageConvert;

public class HomePanel extends JPanel{

	ImageConvert img = new ImageConvert();
	JLabel label = new JLabel();
	
	public HomePanel() {
		label.setSize(1500, 750);
		label.setIcon(img.scaledPanelImage("Gold"));
		setLayout(null);
		add(label);
	}
	
	public void setLabel(ImageIcon image) {
		label.setIcon(image);
	}
}
