package imgProcsJava;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferFloat;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import imgProcsJava.Label8;

public class lancher {
	
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
	
	
	public static BufferedImage normalize_minmax(BufferedImage image) {
		int rows = image.getHeight();
		int cols = image.getWidth();
		
		System.out.println("Size is, w=" + cols + "; h="+rows);
		// search the max and the min values in the image for each chanel
		
		float min = 255;
		float max = 0;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				float pixel = ((image.getRGB(j, i) >> 16) & 0xff);
				System.out.println("x="+i+";y="+j+"; pix="+pixel);
				if (pixel < min) {
					min = pixel;
				}
				if (pixel > max) {
					max = pixel;
				}
			}	
		}
		
		System.out.println("min =" + min + "; max="+max);
		BufferedImage img = new BufferedImage(cols, rows, 10);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int pixel = ((image.getRGB(j, i) >> 16) & 0xff);
				pixel = (int) ( 255 * (pixel - min) / (max - min));
				int[] new_color = {pixel, pixel, pixel};
				img.getRaster().setPixel(j, i, new_color);
			}
		}
		
		return img;
		
	}
	
	public static void cc() throws IOException {
		
		// TODO Auto-generated method stub
		File path = new File("C:\\Users\\mchelali\\Documents\\GitHub\\ImageL3\\Test_Images\\shapesGray.jpg");

		BufferedImage img = null;
		
		try {
			img = ImageIO.read(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		img = threshold(img, 245);
		BufferedImage cc = Label8.getCC(img);
		Label8.getNumberOfCC(cc);
		
		imshow(img);
		imshow(cc);
		
	}
	
	public static BufferedImage threshold(BufferedImage img, int s) {
		int rows = img.getHeight();
		int cols = img.getWidth();
		
		BufferedImage im_thresh = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);
		
		
		int[] noir = {0,0,0,255};
		int[] blanc = {255,255,255,255};
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int pixel = img.getRGB(j, i);
				int red = (pixel >> 16) & 0xff;
				if (red > s) {
					im_thresh.getRaster().setPixel(j, i, noir);
				}else {
					im_thresh.getRaster().setPixel(j, i,blanc );
				}
			}
		}
		
		return im_thresh;
	}
	
	
	public static BufferedImage getFloatBuuffredImage(int w, int h) {
        int bands = 4; // 4 bands for ARGB, 3 for RGB etc
        int[] bandOffsets = {0, 1, 2, 3}; // length == bands, 0 == R, 1 == G, 2 == B and 3 == A

        // Create a TYPE_FLOAT sample model (specifying how the pixels are stored)
        SampleModel sampleModel = new PixelInterleavedSampleModel(DataBuffer.TYPE_FLOAT, w, h, bands, w  * bands, bandOffsets);
        // ...and data buffer (where the pixels are stored)
        DataBuffer buffer = new DataBufferFloat(w * h * bands);

        // Wrap it in a writable raster
        WritableRaster raster = Raster.createWritableRaster(sampleModel, buffer, null);

        // Create a color model compatible with this sample model/raster (TYPE_FLOAT)
        // Note that the number of bands must equal the number of color components in the 
        // color space (3 for RGB) + 1 extra band if the color model contains alpha 
        ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        ColorModel colorModel = new ComponentColorModel(colorSpace, true, false, Transparency.TRANSLUCENT, DataBuffer.TYPE_FLOAT);

        // And finally create an image with this raster
        BufferedImage image = new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);

        //System.out.println("image = " + image);
        
        return image;
	}
	
	public static BufferedImage convolve(BufferedImage img, int[][] mask, int mask_size) {
		
		int rows = img.getHeight();
		int cols = img.getWidth();
		
		BufferedImage output = getFloatBuuffredImage(cols, rows);
		
			
		for(int i=1; i<rows-1 ;i++){
	        for(int j=1; j<cols-1 ;j++){
	        	
	            float conv_pix=0;
	            for(int x=-1; x<2;x++){
	                for(int y=-1;y<2;y++){
	                	float pixel = (float) ((img.getRGB(j+y, i+x) >> 16) & 0xff);
	                	conv_pix = conv_pix + (pixel * mask[x+1][y+1]);
	                	//System.out.println("\tmask " + mask[x+1][y+1] + "; conv pix " +conv_pix);
	                }
	            }
	            //System.out.println("; pixel value = " + conv_pix);
	            float[] new_pixel_value = {conv_pix, conv_pix, conv_pix, 255};
	            output.getRaster().setPixel(j, i, new_pixel_value);
	        }
	    }
		
		return output;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		//exo1();
		//exo2();
		
		//cc();
		
		
		// TODO Auto-generated method stub
		File path = new File("C:\\Users\\mchelali\\Documents\\GitHub\\ImageL3\\Test_Images\\text1.jpg");

		BufferedImage img = null, conv = null, norm = null;
		
		try {
			img = ImageIO.read(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int[][] mask= {{1, 0, -1},
				   {2, 0, -2},
				   {1, 0, -1}};
		
		//BufferedImage im_thresh = threshold(img, 245);
		conv = convolve(img, mask, 3);
		norm = normalize_minmax(conv);
		//System.out.println("size of norm " + norm.getHeight() + "; cols = " + norm.getWidth());
		System.out.println("image = " + img);
		System.out.println("conv = " + conv);
		System.out.println("norm = " + norm);
		
		imshow(img);
		imshow(norm);
		
		System.out.print("end of computing");
		
	}

}
