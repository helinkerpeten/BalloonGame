package BalloonGame;
import java.awt.Image;
import javax.swing.ImageIcon;

public class FileReader {
	private ImageIcon imageicon ;

	public Shape getShape(String ref) {
	imageicon = new ImageIcon(this.getClass().getClassLoader().getResource(ref));
		Image image= imageicon.getImage();
		Shape shape = new Shape(image);
		return shape;
	}

}

/*
 * Instead of using java.io.File class to handle a disk file, 
 * it is better to use java.net.URL. URL is more flexible and 
 * can handle files from more sources, such as disk file 
 * and JAR file (used for distributing your program).
 *  It works on application as well as applet. 
 *  Example:-3.1-LOAD�NG IMAGES
 *  http://www3.ntu.edu.sg/home/ehchua/programming/java/J8b_Game_2DGraphics.html
 *  
 */	