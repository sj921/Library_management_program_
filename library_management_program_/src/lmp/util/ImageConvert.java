package lmp.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.ImageIcon;

import lmp.members.db.dao.ImageDao;
import lmp.members.db.vo.ImageVO;

public class ImageConvert {
	
	/*
	 *  DB 이미지 경로 저장 불러오기 
	 */
	
	
	BufferedImage bufferedImage;
	Image image;
	ImageIcon icon;
	static ImageDao imageDao = new ImageDao();
	ImageVO imageVO;
	
	
	/**
	 * scaledImage 100*100
	 * 
	 * @param image_name
	 * @return icon
	 */
	public ImageIcon scaledMenuImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
		} finally {
			return icon;
		}
	}
	
	/**
	 * scaledImage 30*30
	 * 
	 * @param image_name
	 * @return icon
	 */
	public ImageIcon scaledSmallImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
		} finally {
			return icon;
		}
	}
	
	
	/**
	 * scaledImage 60*60
	 * 
	 * @param image_name
	 * @return
	 */
	public ImageIcon scaledMgmtImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
		} finally {
			return icon;
		}
	}
	
	/**
	 * cardPanel 사이즈로 scaled
	 * 1500 * 750
	 * 
	 * @param image_name
	 * @return icon
	 */
	public ImageIcon scaledPanelImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(1500, 750, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
		} finally {
			return icon;
		}
	}
	
	/**
	 * scaledImage 50*50
	 * 
	 * @param image_name
	 * @return icon
	 */
	
	
	public ImageIcon scaledOptionImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
		} finally {			
			return icon;
		}
	}
	
	/**
	 * scaledImage 120*50
	 * 
	 * @param image_name
	 * @return icon
	 */
	public ImageIcon scaledLogImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(120, 50, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
		} finally {			
			return icon;
		}
	}
	
	public ImageIcon scaledLoginButtonImage(String image_name) {
		try {
			imageVO = imageDao.getImage(image_name);
			bufferedImage = ImageIO.read(new File(imageVO.getImage_path()));
			image = bufferedImage.getScaledInstance(302, 40, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException | SQLException e) {
		} finally {			
			return icon;
		}
	}
	
	/**
	 * image insert DB
	 * 
	 * image_id
	 * image_name
	 * image_path
	 * 
	 * @param file
	 */
	public void inputImage(File file) {
		try {
			imageDao.addImage(file);
		} catch (SQLException e) {}		
	}

}
