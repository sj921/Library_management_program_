package lmp.util.theme;

import java.awt.Color;

import javax.swing.ImageIcon;

import lmp.util.ImageConvert;

public class Theme{

	Color mainColor;
	Color sub1Color;
	Color sub2Color;
	ImageIcon homeImage;
	
	ImageConvert img = new ImageConvert();

	public Theme() {
		
	}
	
	public void setTheme(String theme) {
		switch (theme) {

		case "Gold":
			mainColor = new Color(175,150,105);
			sub1Color = new Color(148,124,83);
			sub2Color = new Color(126,100,56);
			homeImage = img.scaledPanelImage("Gold");
			break;
		case "BlueGreen":
			mainColor = new Color(0, 82, 91);
			sub1Color = new Color(0, 64, 61);
			sub2Color = new Color(0, 46, 31);
			homeImage = img.scaledPanelImage("BlueGreen");
			break;
		case "Green" :
			mainColor = new Color(203, 211, 188);
			sub1Color = new Color(114, 141, 112);
			sub2Color = new Color(69, 92, 63);
			homeImage = img.scaledPanelImage("Green");
			break;
		case "Brown" :
			mainColor = new Color(201, 174, 157);
			sub1Color = new Color(188, 147, 127);
			sub2Color = new Color(145, 85, 74);
			homeImage = img.scaledPanelImage("Brown");
			break;
		case "Purple" :
			mainColor = new Color(230, 212, 210);
			sub1Color = new Color(181, 164, 170);
			sub2Color = new Color(104, 108, 117);
			homeImage = img.scaledPanelImage("Purple");
			break;
		case "Gray" :
			mainColor = new Color(198, 198, 196);
			sub1Color = new Color(178, 175, 167);
			sub2Color = new Color(127, 126, 122);
			homeImage = img.scaledPanelImage("Gray");
			break;
			
		}
	}

	public Color getMainColor() {
		return mainColor;
	}

	public Color getSub1Color() {
		return sub1Color;
	}

	public Color getSub2Color() {
		return sub2Color;
	}
	
	public ImageIcon getHomeImage() {
		return homeImage;
	}
	

	
}
