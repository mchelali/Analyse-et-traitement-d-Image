package imgProcsJava;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class lancher {
	
	public static void imshow(BufferedImage image) throws IOException {
		 //Encoding the image 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, "jpg", baos );
		
	      //Storing the encoded Mat in a byte array 
	      byte[] byteArray = baos.toByteArray(); 
	      
	      //Preparing the Buffered Image 
	      InputStream in = new ByteArrayInputStream(byteArray); 
	      BufferedImage bufImage = ImageIO.read(in); 

	      //Instantiate JFrame 
	      JFrame frame = new JFrame(); 

	      //Set Content to the JFrame 
	      frame.getContentPane().add(new JLabel(new ImageIcon(bufImage))); 
	      frame.pack(); 
	      frame.setVisible(true);
	 }
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File path = new File("C:/Users/mchelali/Pictures/landscape.png");

		BufferedImage img = null;
		
		img = ImageIO.read(path);
		
		int width  = img.getWidth();
		int height = img.getHeight();
		
		System.out.println("l = " + height + "\nc = " + width);
		System.out.println("type " + img.getType());
		
		int p = img.getRGB(0,0); 
		int a = (p>>24)&0xff; 
        int r = (p>>16)&0xff; 
        int g = (p>>8)&0xff; 
        int b = p&0xff; 
		System.out.println("a = " +  a + " r = " +  r + " g = " + g + " b = " + b);
		//imshow(img);
	}

}
