#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include "queues.h"
#define SIZE 225
#define N 11
#define TEMPO_ENTRADA_GLOBAL 100
#define QUANTUM_FCFS 1000
#define QUANTUM_RR 3


struct queue
{
	int size;
	int* array;
	int inicio;
	int fim;
};


Queue* create_queue(int size)
{
	Queue* queue = (Queue*) malloc(sizeof(Queue));
	queue->size = size;
	queue->array = malloc(queue->size * sizeof(int));
	queue->inicio = queue->fim = 0;
	return queue;
}


void enqueue(Queue* fila, int value)
{
	if(fila->fim != fila->inicio || fila->fim == 0)
	{
		fila->array[fila->fim] = value;
		fila->fim = fila->fim + 1;
	}
	else
	{
		printf("A fila está cheia\n");
	}
}


bool empty(Queue* fila)
{
	if(fila->fim == fila->inicio)
	{
		return 1;
	}
	return 0;
}


int dequeue(Queue* fila)
{
	if(!empty(fila))
	{
		int go_away = fila->array[fila->inicio];
		for (int salto=0; fila->inicio + salto < fila->fim; salto++)
		{
			fila->array[fila->inicio + salto] = fila->array[fila->inicio + salto + 1];
		}
		fila->fim = fila->fim - 1;
		return go_away;
	}
	printf("A fila está vazia, não há nada para retornar\n");
	return -1;
}


void free_queue(Queue* fila)
{
	free(fila);
}


int top(Queue* fila)
{
	return fila->array[fila->inicio];
}


void print_queue(Queue* fila)
{
	int aux = fila->inicio;
	while(fila->inicio < fila->fim)
	{
		printf("%d ", fila->array[fila->inicio]);
		fila->inicio = fila->inicio + 1;
	}
	fila->inicio = aux;
}