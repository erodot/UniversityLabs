#include <iostream>
#include <ctime>
#include <mpi.h>
#include "CImg.h"

#define NUM 2000

using namespace std;
using namespace cimg_library;

int main(int argc, char* argv[]){
    double start_time, end_time;
    int id, p;

    CImg<unsigned char> images[NUM];
    for(int i=0; i<NUM; i++){
        CImg<unsigned char> image("lena.bmp");
        images[i] = image;
    }

    MPI_Init(&argc,&argv);
	MPI_Comm_rank(MPI_COMM_WORLD,&id);
	MPI_Comm_size(MPI_COMM_WORLD,&p);

    if(id == 0)
        start_time  = clock();

    for(int i=id*NUM/p; i<(id+1)*NUM/p; i++){
        images[i].blur(2.5);
    }

    if(id == 0){
        end_time = clock();
        cout<<"Time: "<< (end_time - start_time) / CLOCKS_PER_SEC << " seconds.\n";
    }
    MPI_Finalize();

    return 0;
}