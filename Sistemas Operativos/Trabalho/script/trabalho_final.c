#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include "queues.c"
#include "process.c"
#define SIZE 225
#define N 11
#define TEMPO_ENTRADA_GLOBAL 100
#define QUANTUM_FCFS 1000
#define QUANTUM_RR 3


void solve(Process* array_processos[], int k, int QUANTUM)
{
	Queue* READY = create_queue(SIZE); 
	Queue* BLOCKED = create_queue(SIZE);            //criação das filas READY e BLOCKED e do "estado" RUN
	int RUN = 0;
	       
	for(int processo = 0; processo < k; processo++)
	{
		array_processos[processo]->incrementa_run = 1;                   //este ciclo serve para meter a 0 o valor dos 
		array_processos[processo]->incrementa_blocked = 1;               //incrementadores de cada processo no run e no blocked
		array_processos[processo]->nr_numero_seq_run = 0;
		array_processos[processo]->nr_numero_seq_blocked = 0;
		array_processos[processo]->count_quantum = 1;
	}
	for(int cronometro = 0; cronometro < TEMPO_ENTRADA_GLOBAL; cronometro++)           //vai passando o tempo, 1 ciclo = 1 tempo
	{
		if(!empty(BLOCKED))
		{
			int auxiliar = BLOCKED->inicio;
			while(BLOCKED->inicio < BLOCKED->fim)
			{
				int este_PROCESSO = find_PID(BLOCKED->array[BLOCKED->inicio], array_processos, k);
				if(array_processos[este_PROCESSO]->seq_BLOCKED[array_processos[este_PROCESSO]->nr_numero_seq_blocked] == array_processos[este_PROCESSO]->incrementa_blocked)
				{
					array_processos[este_PROCESSO]->incrementa_blocked = 0;  //esta variável tem de ficar a 0 pois quando sai vai somar como sempre, por isso pra ficar a 1, aqui tem de ficar a 0.
					array_processos[este_PROCESSO]->nr_numero_seq_blocked++;
					enqueue(READY, dequeue(BLOCKED));                        //se o processo tiver cumprido o seu tempo no estado BLOCKED
					BLOCKED->inicio--;                                       //será enviado para o estado READY
				}
				array_processos[este_PROCESSO]->incrementa_blocked++;
				BLOCKED->inicio++;
			}
			BLOCKED->inicio = auxiliar;
		}
		//--------------------------------------fim do estado BLOCKED--------------------------------------------

		//--------------------------------------início do estado RUN---------------------------------------------	
		if(RUN != 0) 
		{
			int este_PROCESSO = find_PID(RUN, array_processos, k);
			if(array_processos[este_PROCESSO]->nr_numero_seq_run == array_processos[este_PROCESSO]->size_run-1 && array_processos[este_PROCESSO]->seq_RUN[array_processos[este_PROCESSO]->size_run-1] == array_processos[este_PROCESSO]->incrementa_run)
			{
				for(int j=este_PROCESSO; j<k-1; j++)
				{
					array_processos[j] = array_processos[j+1];         //vai entrar aqui aquele número que já tenha acabado tudo,
				}                                                      //a não ser que tenha só 1 ciclo no RUN e vá para o else if da linha 72
				k--;
				RUN = 0;
				array_processos[este_PROCESSO]->incrementa_run--;
				array_processos[este_PROCESSO]->count_quantum--;
			}
			else if(array_processos[este_PROCESSO]->seq_RUN[array_processos[este_PROCESSO]->nr_numero_seq_run] == array_processos[este_PROCESSO]->incrementa_run)
			{
				array_processos[este_PROCESSO]->incrementa_run = 0;
				array_processos[este_PROCESSO]->nr_numero_seq_run++;   //vai entrar aqui o número que tenha terminado um valor na sua sequência RUN,
				array_processos[este_PROCESSO]->count_quantum = 0;     //e vai passar para o próximo valor a próxima vez que entrar nesta estação          
				enqueue(BLOCKED, RUN);
				RUN = 0;
			}
			else if(array_processos[este_PROCESSO]->seq_RUN[array_processos[este_PROCESSO]->nr_numero_seq_run] == 1)
			{
				if(array_processos[este_PROCESSO]->incrementa_run-1 == 1)
				{
					array_processos[este_PROCESSO]->incrementa_run = 0;
					array_processos[este_PROCESSO]->count_quantum = 0;
					array_processos[este_PROCESSO]->nr_numero_seq_run++;
					enqueue(BLOCKED, RUN);
					RUN = 0;
				}
			}
			else if(array_processos[este_PROCESSO]->count_quantum == QUANTUM) 
			{
				array_processos[este_PROCESSO]->count_quantum = 0;
				enqueue(READY, RUN);                                //caso o o tempo de QUANTUM tenha sido atingido o processo passará para o estado READY
				RUN = 0;
			}
			array_processos[este_PROCESSO]->incrementa_run++;   //se o processo no RUN não está de saída, incrementa mais um tempo.
			array_processos[este_PROCESSO]->count_quantum++;
		}
		//--------------------------------------fim do estado RUN--------------------------------------------------------------

		
		for(int i=0; i<k; i++)                         
		{
			if(array_processos[i]->t_inicio == cronometro)      //verifica se há processo novo pronto pra entrar no ready
			{
				enqueue(READY, array_processos[i]->PID);
			}	
		}
		
		if(!empty(READY))
		{
			if(RUN == 0)                         //se o houver processos no READY e o RUN estiver vazio, passa o processo no topo do READY para o RUN
			{
				RUN = dequeue(READY);
			}
		}
		if(empty(READY) && empty(BLOCKED) && RUN == 0)
		{
			printf("|||||||||||||||||||||OVER|||||||||||||||||||||\n");
			return;
		}

		printf("%d |READY: ", cronometro); 

		if(empty(READY))
			printf("Empty");
		else
			print_queue(READY);

    	printf("   |RUN: ");

    	if(RUN == 0)
        	printf("Empty");
    	else
        	printf("%d", RUN);

    	printf("   |BLOCKED: ");

    	if(empty(BLOCKED))
    		printf("Empty");
    	else
    		print_queue(BLOCKED);

    	printf("\n\n");
	}
}


int main()
{
	printf("Que tipo de escalonamento pretende simular?\n");
    printf("[1] FCFS\n");
    printf("[2] Round Robin\n");
    int escalonamento;
    scanf("%d", &escalonamento);

    int ficheiro;
    printf("Que ficheiro pretende correr?\n");
    printf("[1] Ficheiro 1;\n");
    printf("[2] Ficheiro 2;\n");
 	printf("[3] Ficheiro 3;\n");
 	scanf("%d", &ficheiro);

 	FILE* f;

 	if(ficheiro == 1)
 	{
 		f = fopen("input1.txt", "r");
    	// Verifica se é possível abrir o ficheiro
    	if(f == NULL)
    	{
        	printf("Não foi possível abrir o ficheiro");
        	return 1;
    	}
 	}
 	else if(ficheiro == 2)
 	{
 		f = fopen("input2.txt", "r");
    	if(f == NULL)
    	{
        	printf("Não foi possível abrir o ficheiro");
        	return 1;
    	}
 	}
 	else if(ficheiro == 3)
 	{
 		f = fopen("input3.txt", "r");
    	if(f == NULL)
    	{
        	printf("Não foi possível abrir o ficheiro");
        	return 1;
    	}
 	}
 	else
 	{
 		printf("Nenhum ficheiro corresponde a esse valor.\n");
 	}

    int valor;
    int array[SIZE];
    int i=0;
    while(fscanf(f, "%d", &valor)!=EOF)
    {
        array[i]=valor;
        i++;
    }

    Process* array_processos[N];
    int inicio=0;
    int fim;
    int k=0;
    for(int j=0; j<i; j++)
    {
        if(array[j+1]>=100 || array[j+1] < 0)
        {
            fim=j;
            Process* processo = organizar_processos(array, inicio, fim);        //isto aqui também é só para organizar o input
            inicio=j+1;
            array_processos[k] = processo;
            k++;
        }
    }

    for(int i=0; i<k; i++)
    {
    	int count=0;
    	int passador=0;
    	while(array_processos[i]->seq_RUN[passador] != '\0')
    	{
    		count++;
    		passador++;
    	}
    	array_processos[i]->size_run = count;
    	count=0;                                                       //guardar os tamanhos de cada sequência de input
    	passador=0;                                                    // dos estados RUN e BLOCKED de cada processo
    	while(array_processos[i]->seq_BLOCKED[passador] != '\0')
    	{
    		count++;
    		passador++;
    	}
    	array_processos[i]->size_blocked = count;
    }

    if(escalonamento == 1)
    {
    	solve(array_processos, k, QUANTUM_FCFS);           //chama a função que resolve tudo
    }
    else if(escalonamento == 2)
    {
    	solve(array_processos, k, QUANTUM_RR);               //chama a função que resolve tudo
    }
    else
    {
    	printf("Nenhum tipo de escalonamento corresponde a esse valor.\n");
    }               

    fclose(f);
    return 0;
}