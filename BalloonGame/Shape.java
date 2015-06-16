package BalloonGame;

import java.awt.Graphics;
import java.awt.Image;

public class Shape {
	private Image image; 
	public Shape(Image image) {
		this.image = image;
	}
	
	public int getWidth() {
		return image.getWidth(null);
	}

	
	public int getHeight() {
		return image.getHeight(null);
	}
	
	
	//www3.ntu.edu.sg/home/ehchua/programming/java/J8b_Game_2DGraphics.html
	/*the most commonly-used implementation subclass is java.awt.image.BufferedImage, 
	 * which stores the pixels in memory buffer so that they can be directly accessed.
	 *  A BufferedImage comprises a ColorModel and a Raster of pixels. 
	 *  The ColorModel provides a color interpretation of the image's pixel data.
	 *  An Image object (and subclass BufferedImage) can be rendered onto a JComponent 
     *    (such as JPanel) via Graphics' (or Graphics2D') drawImage() method.
	 */
	//G�r�nt� i�leme operat�rleri Image ile degil, BufferedImage ile �al���r.
	//BufferedImage ,Image yi extend eder
	//Image'yi BufferedImage ye �evirmek i�in  BufferedImage'nin Graphics'ini al�p 
	//Image '� ona �izmen gerekir.
	
	
	
	public void draw(Graphics g,int x,int y) {
		g.drawImage(image,x,y,null);
	}
}