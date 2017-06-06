#include <stdlib.h>
#include <ctime>
#include <iostream>
using namespace std;

#define N 50000
double start_time;
double end_time;

void shellSort (int numbers[], int length)
{
	int i, j, increment, temp;
	increment = 3;
	while (increment > 0)
	{
		for (i=0; i < length; i++)
		{
			j = i;
			temp = numbers[i];
			while ((j >= increment) && (numbers[j-increment] > temp))
			{
				numbers[j] = numbers[j - increment];
				j = j - increment;
			}
			numbers[j] = temp;
		}
		increment = increment/2;
	}
}
int main(int argc, char **argv)
{
    int vector[N],i;

	srand((unsigned int)(NULL));
    for(i=0;i<N;i++){
        vector[i] = rand() % N;
    }
    start_time = clock();
    shellSort(vector,N);
    end_time = clock();
    cout << "Serial sort time: " << (end_time - start_time)/CLOCKS_PER_SEC<<" seconds."<<endl;
    return 0;
}