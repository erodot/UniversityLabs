#include "mpi.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define LIMIT     5000000
#define FIRST     0

int isprime(int n) {
    int i,squareroot;
    if (n > 10) {
        squareroot = (int) sqrt(n);
        for (i=3; i<=squareroot; i+=2)
            if ((n%i)==0)
                return 0;
        return 1;
    }
    else
        return 0;
}


int main (int argc, char *argv[])
{
int   ntasks,               /* total number of tasks in partitiion */
      rank,                 /* task identifier */
      n,                    /* loop variable */
      pc,                   /* prime counter */
      pcsum,                /* number of primes found by all tasks */
      foundone,             /* most recent prime found */
      maxprime,             /* largest prime found */
      mystart,              /* where to start calculating */
      step;               /* calculate every nth number */

double start_time, end_time;
	
MPI_Init(&argc,&argv);
MPI_Comm_rank(MPI_COMM_WORLD,&rank);
MPI_Comm_size(MPI_COMM_WORLD,&ntasks);

start_time = MPI_Wtime();
mystart = (rank*2)+1;       
step = ntasks*2;          
pc=0;                      
foundone = 0;

if (rank == FIRST) {
   pc = 4;
   for (n=mystart; n<=LIMIT; n=n+step) {
      if (isprime(n)) {
         pc++;
         foundone = n;
         //printf("%d\n",foundone);
         }
      }
   MPI_Reduce(&pc,&pcsum,1,MPI_INT,MPI_SUM,FIRST,MPI_COMM_WORLD);
   MPI_Reduce(&foundone,&maxprime,1,MPI_INT,MPI_MAX,FIRST,MPI_COMM_WORLD);
   end_time=MPI_Wtime();
   printf("Done. Largest prime is %d Total primes %d\n",maxprime,pcsum);
   printf("Time elapsed: %.2lf seconds\n",end_time-start_time);
   }

if (rank > FIRST) {
   for (n=mystart; n<=LIMIT; n=n+step) {
      if (isprime(n)) {
         pc++;
         foundone = n;
         //printf("%d\n",foundone);
         }
      }
   MPI_Reduce(&pc,&pcsum,1,MPI_INT,MPI_SUM,FIRST,MPI_COMM_WORLD);
   MPI_Reduce(&foundone,&maxprime,1,MPI_INT,MPI_MAX,FIRST,MPI_COMM_WORLD);
   }

MPI_Finalize();
}