#include <stdio.h>
#include <opencv2/opencv.hpp>
using namespace cv;
int main()
{

    Mat image;
    image = Mat::zeros(512, 512, CV_8UC1);
    if ( image.empty() )
    {
        printf("No image data \n");
        return -1;
    }
    namedWindow("Display Image", WINDOW_AUTOSIZE );
    imshow("Display Image", image);
    waitKey(0);
    return 0;
}
