# export MPICC=/usr/local/bin/mpicc && export MPIRUN=/usr/local/bin/mpirun && export TMPDIR=/tmp
# make && mpirun -n 4 eratosthenes && mpirun -n 4 singleprime
# make && mpirun eratosthenes && mpirun singleprime

EXECS=eratosthenes singleprime
MPICC?=mpicc

all: ${EXECS}

eratosthenes: eratosthenes.c
	${MPICC} -o eratosthenes eratosthenes.c

singleprime: singleprime.c 
	${MPICC} -o singleprime singleprime.c
