#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include "process.h"
#define SIZE 225
#define N 11
#define TEMPO_ENTRADA_GLOBAL 100
#define QUANTUM_FCFS 1000
#define QUANTUM_RR 3


struct process
{
    int PID;
    int t_inicio;
    int* seq_RUN;
    int* seq_BLOCKED;
    int incrementa_run;
    int incrementa_blocked;
    int nr_numero_seq_run;
    int nr_numero_seq_blocked;
    int size_run;
    int size_blocked;
    int count_quantum;
};


Process* new_process(int size)
{
    Process* processo =(Process*)malloc(sizeof(Process));
    processo->seq_RUN=malloc(size*sizeof(int));
    processo->seq_BLOCKED=malloc(size*sizeof(int));
    return processo;
}


Process* organizar_processos(int array[], int inicio, int fim)
{
    Process* nr_processo = new_process(SIZE);
    int posicaoRUN = 0;
    int posicaoBLOCKED = 0;
    int count = 0;
    for(int i=inicio; i<=fim; i++)
    {
        if(array[i]>=100)
        {
            count = 1;
            nr_processo->PID = array[i];
        }
        else if(count == 1)
        {
            nr_processo->t_inicio = array[i];                 //isto serve tudo pra organizar os processos em structs
            count++;
        }
        else if(count % 2 == 0)
        {
            nr_processo->seq_RUN[posicaoRUN] = array[i];
            count++;
            posicaoRUN++;
        }
        else if(count % 2 != 0)
        {
            nr_processo->seq_BLOCKED[posicaoBLOCKED] = array[i];
            count++;
            posicaoBLOCKED++;
        }
    }
    return nr_processo;
}


int find_PID(int PID, Process* array_processos[], int k)
{
	for (int i = 0; i < k; ++i)
	{
		if (array_processos[i]->PID == PID)                            //esta função ao receber um PID, retorna a posição
		{                                                              //do array dos processos onde está o processo com
			return i;                                                  //aquele PID.
		}
	}
	return -1;
}