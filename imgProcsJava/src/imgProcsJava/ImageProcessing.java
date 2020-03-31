package imgProcsJava;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageProcessing {
	
	public static void imshow(BufferedImage image) throws IOException {
	      //Instantiate JFrame 
	      JFrame frame = new JFrame(); 

	      //Set Content to the JFrame 
	      frame.getContentPane().add(new JLabel(new ImageIcon(image))); 
	      frame.pack(); 
	      frame.setVisible(true);
	 }
	
	static BufferedImage bresenham(BufferedImage img, int x1, int y1, int x2, int y2, Color c) 
	{ 
		int m_new = 2 * (y2 - y1); 
		int slope_error_new = m_new - (x2 - x1); 

		for (int x = x1, y = y1; x <= x2; x++) 
		{ 
			//System.out.print("(" +x + "," + y + ")\n"); 
			img.setRGB(x, y, c.getRGB());
			// Add slope to increment angle formed 
			slope_error_new += m_new; 

			// Slope error reached limit, time to 
			// increment y and update slope error. 
			if (slope_error_new >= 0) 
			{ 
				y++; 
				slope_error_new -= 2 * (x2 - x1); 
			} 
		} 
		return img;
	}
	
	static BufferedImage drawRect(BufferedImage img, int x1, int y1, int x2, int y2, Color c) 
	{ 
		int width  = img.getWidth();
		int height = img.getHeight();
		
		if (x1 > height && x2 > height) {
			throw new java.lang.Error("x1 ou x2 sont superieur a la hauteur de l'image");
		}
		
		if (y1 > width && y2 > width) {
			throw new java.lang.Error("y1 ou y2 sont superieur a la largeur de l'image");
		}
		
		for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2 ; y++) {
            	img.setRGB(x, y, c.getRGB());
            }
        }
		
		return img;
	}
	
	static BufferedImage zeros(int height, int width) {
		
		int[] noir = {0,0,0,255};
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
        //WritableRaster raster = img.getRaster();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height ; y++) {
            	img.getRaster().setPixel(x, y, noir);
            }
        }
        return img; 
    }
	
	public static void exo1() {
		/**************************
		 * 		     Exo 1		  *
		 **************************/
		// TODO Auto-generated method stub
		File path = new File("C:/Users/mchelali/Pictures/landscape.png");

		BufferedImage img = null;
		
		try {
			img = ImageIO.read(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		int width  = img.getWidth();
		int height = img.getHeight();
		
		System.out.println("l = " + height + ";c = " + width);
		System.out.println("type " + img.getType());
		
		// Affichage de l'image
		try {
			imshow(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int y = (width / 2) - (width / 4);
		int x = (height / 2) + (height / 4);
		
		int p = img.getRGB(x,y); // récupération des couleurs RGB du pixel a la position (x, y)
		
		int a = (p>>24)&0xff; 
        int r = (p>>16)&0xff; 
        int g = (p>>8)&0xff; 
        int b = p&0xff; 
		System.out.println("a = " +  a + " r = " +  r + " g = " + g + " b = " + b);
	}
	
	public static void exo2() {
		/**************************
		 * 		     Exo 2		  *
		 **************************/
		BufferedImage img2 = zeros(128, 128);
		img2 = bresenham(img2, 10, 10, 100, 10, Color.BLUE); 
		img2 = bresenham(img2, 10, 10, 100, 100, Color.CYAN);
		img2 = bresenham(img2, 10, 10, 100, 30, Color.MAGENTA);
		img2 = drawRect(img2, 50, 50, 80, 100, Color.GREEN);
		try {
			imshow(img2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		exo1();
		exo2();
		
	}

}
