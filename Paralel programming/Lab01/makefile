# export MPICC=/usr/local/bin/mpicc && export MPIRUN=/usr/local/bin/mpirun && export TMPDIR=/tmp

EXECS=sort sort-mpi sort-openmp
MPICC?=mpicc

all: ${EXECS}

sort: sort.cpp
	clang++ ./sort.cpp -o sort && ./sort && rm -rf sort

sort-mpi: sort-mpi.c
	${MPICC} -o sort-mpi sort-mpi.c && mpirun -n 4 sort-mpi && rm -rf sort-mpi

sort-openmp: sort-openmp.c
	gcc -openmp sort-openmp.c -o sort-openmp && ./sort-openmp && rm -rf sort-openmp
