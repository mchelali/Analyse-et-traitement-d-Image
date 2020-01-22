import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.MatOfByte;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Size;

public class ImageProcessing { 
	
	public static void imshow(Mat image) throws IOException {
		 //Encoding the image 
	      MatOfByte matOfByte = new MatOfByte();       
	      Imgcodecs.imencode(".jpg", image, matOfByte); 

	      //Storing the encoded Mat in a byte array 
	      byte[] byteArray = matOfByte.toArray(); 

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
	
	 public static void main(String args[]) throws Exception  { 
	     int width = 963;    //width of the image 
	     int height = 640;   //height of the image 
	
	     System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	     
	     
	     
         Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
         System.out.println("mat = " + mat.dump());
         
       //Instantiating the Imgcodecs class 
         Imgcodecs imageCodecs = new Imgcodecs();
         
         
         // Reading images
         Mat img = Imgcodecs.imread("C:/Users/mchelali/Pictures/landscape.png");
         System.out.println("Image Loaded");
         
         Size sizeA = img.size();
         
         int rows = img.rows(); //Calculates number of rows
         int cols = img.cols(); //Calculates number of columns
         int ch = img.channels(); //Calculates number of channels (Grayscale: 1, RGB: 3, etc.)
         
         System.out.println("The size of the image is " + rows + " " + cols + " " +ch);
         
         //img.convertTo(img, CvType.CV_8UC1);
         for (int i=0; i<rows; i++)
         {
             for (int j=0; j<cols; j++)
             {
            	 // get pixel values 
            	 double[] pix = img.get(i, j);
            	 System.out.println(pix[0] + " " + pix[1] + " " +pix[2]);
            	 
            	 //set pixel after precess
            	 img.put(i, j, pix);
             }
         }
         
         
         //Creating the empty destination matrix
         Mat dst = new Mat();
         //Converting the image to gray sacle and saving it in the dst matrix
         Imgproc.cvtColor(img, dst, Imgproc.COLOR_RGB2GRAY);
         
         // Converting to binary image...
         Imgproc.threshold(img, dst, 200, 500, Imgproc.THRESH_BINARY);
         
         imshow(img);
         
         //Writing the image 
         imageCodecs.imwrite("C:/Users/mchelali/Pictures/landscape-savedwithJAVA.png", img); 
         System.out.println("Image Saved ............"); 
	 }
	 
	 
	 
	 
}
