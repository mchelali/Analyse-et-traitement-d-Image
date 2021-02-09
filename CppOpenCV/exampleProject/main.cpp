#include <stdio.h>
#include <opencv2/opencv.hpp>
using namespace cv;
using namespace std;


void getHistogram(Mat image, int * hist, int ng){

    for (int i=0; i < ng; i++){
        hist[i] = 0;
    }
     
    for(int i=0; i < image.rows; i++){
        for(int j=0; j < image.cols; j++){
            int pix = (int) image.at<uchar>(i, j);
            hist[pix]++;
        }
    }

}

void shiwHist(int* hist, int ng){

    int max = 0;
    for (int i=0; i < ng;i++){
        if (hist[i] > max)
            max = hist[i];
    }


    Mat imgHist = Mat::zeros(ng, ng, CV_8UC1);

    for (int i=0; i < ng;i++){
        int max_high = (int) (((float) hist[i] / (float) max) * ng);
        for (int j=ng-1; j > ng - max_high; j--){
            imgHist.at<uchar>(j, i) = 255;
        }
    }

    namedWindow("Historam", WINDOW_AUTOSIZE );
    imshow("Historam", imgHist);
    waitKey(0);
}

int main()
{

    // ----------- Lecture d'une Image ----------------
    
    Mat image = imread("/home/ramoya/Documents/GitHub/Analyse-et-traitement-d-Image/Test_Images/landscape.png");
    
    if ( image.empty() )
    {
        printf("No image data \n");
        return -1;
    }

    // ----------- Avoir des information sur le format de l'image ----------------
    
    cout << "Nombre de canals : " << image.channels() << endl;
    cout << "Nombre de lignes : " << image.rows << endl;
    cout << "Nombre de colonnes : " << image.cols << endl;
    
    // ----------- Accees aux valeurs des pixels ----------------

    // 1 - Si l'image est une image couleur
    cout << "image[0, 0] = " << image.at<Vec3b>(0,0) << endl;  

    Mat gray;

    cvtColor(image, gray, cv::COLOR_BGR2GRAY);// on transforme img vers le gris
    // 1 - Si l'image est une image grise avec un seul cannal
    cout << "gray[0, 0] = " << ((int) gray.at<uchar>(0,0)) << endl;  

    int *hist = new int[256];
    int ng = 256;
    cout << "Calcul de l'hist" <<  endl;  
    getHistogram(gray, hist, ng);
    cout << "Affichage de l'hist" <<  endl;  
    shiwHist(hist, ng);

    // ----------- Pour creer une image noir (tous les pixels vallent 0) ----------------
    Mat image2 = Mat::zeros(512, 512, CV_8UC1);
    
    namedWindow("Display Image", WINDOW_AUTOSIZE );
    imshow("Display Image", image);
    waitKey(0);
    return 0;
}
