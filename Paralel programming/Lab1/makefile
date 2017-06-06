# export MPICC=/usr/local/bin/mpicc && export MPIRUN=/usr/local/bin/mpirun && export TMPDIR=/tmp

CIMG_OPTIONS = -O2 -lm -lpthread -I/usr/X11R6/include -L/usr/X11R6/lib -lm -lpthread -lX11

GCC_COMPILER = g++-7
MPI_COMPILER = mpic++

EXECS=serial mpi openmp

default: ${EXECS}

serial: serial.cpp
	 $(GCC_COMPILER) serial.cpp -o exec $(CIMG_OPTIONS) && ./exec && rm -rf exec

openmp: openmp.cpp
	$(GCC_COMPILER) openmp.cpp -fopenmp -o exec $(CIMG_OPTIONS) && ./exec && rm -rf exec

mpi: mpi.cpp
	$(MPI_COMPILER) mpi.cpp -o exec $(CIMG_OPTIONS) && mpirun -n 4 ./exec && rm -rf exec