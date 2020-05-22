import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class JavaCV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//nu.pattern.OpenCV.loadShared();
		nu.pattern.OpenCV.loadLocally();
		
		Mat mat = Mat.eye(4,  4, CvType.CV_8UC1);
		System.out.println("mat=" +  mat.dump());

	}

}
