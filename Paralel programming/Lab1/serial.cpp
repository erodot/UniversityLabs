#include <iostream>
#include <ctime>
#include "CImg.h"

#define NUM 2000

using namespace std;
using namespace cimg_library;

int main(int argc, char* argv[]){
    double start_time, end_time;
    CImg<unsigned char> images[NUM];
    for(int i=0; i<NUM; i++){
        CImg<unsigned char> image("lena.bmp");
        images[i] = image;
    }
    
    start_time  = clock();  
    for(int i=0; i<NUM; i++){
        //cout<<i<<endl;
        images[i].blur(2.5);
    }
    end_time = clock();

    double time_elapsed = (end_time - start_time) / CLOCKS_PER_SEC;

    cout<<"Time: "<< time_elapsed << " seconds.\n";
    return 0;
}