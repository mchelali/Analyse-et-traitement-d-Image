cmake_minimum_required(VERSION 3.0)
project( DisplayImage )

if(WIN32)
    SET("OpenCV_DIR" "C:/opencv451/opencv/build")
    #SET("OpenCV_INCLUDE_DIRS" "C:/opencv451/opencv/build")
    #SET("OpenCV_LIBS" "C:/opencv451/opencv/build/x64/vc15")
    #SET("OpenCV_DLLS" "C:/opencv451/opencv/build/x64/vc15/bin/opencv_world451d.dll")
endif()

find_package( OpenCV REQUIRED  COMPONENTS  core imgproc highgui )
include_directories( ${OpenCV_INCLUDE_DIRS} )

add_executable( DisplayImagea main.cpp )
target_link_libraries( DisplayImagea ${OpenCV_LIBS} )
