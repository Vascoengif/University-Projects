#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "cliente.h"
#define SIZE_USER_PASS 10
#define SIZE_SALAS 3
#define N_CLIENTES 100

struct cliente
{
	char *userName;
	char *password;
	char *operador;
	char *room;
};

cliente_t *new_cliente()
{
	cliente_t *cliente_v = malloc(sizeof(cliente_t));
	cliente_v->userName = malloc(SIZE_USER_PASS * sizeof(char));
	cliente_v->password = malloc(SIZE_USER_PASS * sizeof(char));
	cliente_v->operador = malloc(1 *sizeof(char));
	cliente_v->room = malloc(1 *sizeof(char));
	strcpy(cliente_v->operador, "0");
	strcpy(cliente_v->room, "0");
	strcpy(cliente_v->password, " ");
	strcpy(cliente_v->userName, " ");

	return cliente_v;
}

void registar(char userName[SIZE_USER_PASS], char password[SIZE_USER_PASS])
{
	if(UserName_registado(userName))
	{
		printf("Nome já estava registado!\n");
	}
                                                                                  //operador, nao testado
	else
	{
		FILE *fp_registados = fopen("registados.txt", "a");
		fprintf(fp_registados, "User:%s\nPassword:%s\n", userName, password);
		fclose (fp_registados);
	}
}

cliente_t *find_cliente(cliente_t *cliente_v[], char userName[SIZE_USER_PASS])
{
	for (int i = 0; i < N_CLIENTES; ++i)
	{
		/////////////////////////////////////////////////////////
		puts(userName);                                                           //operador, nao utilizado
		puts(cliente_v[i]->userName);
		/////////////////////////////////////////////////////////

		if (!strcmp(cliente_v[i]->userName, userName))
			return cliente_v[i];
	}
}

void erase_cliente(cliente_t *cliente_v[], char userName[SIZE_USER_PASS])
{
	bool aux = false;

	for (int i = 0; i < sizeof(cliente_t); ++i)
	{
		if (aux == true)
			cliente_v[i] = cliente_v[i+1];

		else if (strcmp(cliente_v[i]->userName, userName))                     //nao utilizado
			cliente_v[i] == cliente_v[i];

		else
		{
			cliente_v[i] = cliente_v[i+1];
			aux = true;
			i++;
		}
	}
}

void oper(cliente_t *cliente_v)
{
	strcpy(cliente_v->operador, "1");

	FILE *fp_operador = fopen("operador.txt", "a");
	fprintf(fp_operador, "User:%s\nPassword:%s\n", cliente_v->userName, cliente_v->password);   //tornar um registado num operador, nao foi testado
	fclose (fp_operador);
}


bool UserName_online(char userName[SIZE_USER_PASS])
{
	char names[SIZE_USER_PASS];
	FILE *fp = fopen("usuarios.txt", "r");
	
	if(fp == NULL)
		return false;

	fgets(names, 10, fp);

	do
	{
		names[strlen(names)-1] = 0; //tira o /n


		if (!strcmp(userName, names))
		{
			fclose(fp);
			return true;
		}

	}while(fgets(names, 10, fp) != NULL);
	
	fclose(fp);
	return false;
}

bool UserName_registado(char userName[SIZE_USER_PASS])
{
	char names[SIZE_USER_PASS];
	FILE *fp = fopen("registados.txt", "r");

	if(fp == NULL)
		return false;

	fgets(names, 6, fp);  //obtem o 'User: '
	fgets(names, 10, fp);  //obtem o Nome do user

	do
	{
		names[strlen(names)-1] = 0;

		if(!strcmp(names, userName))
		{
			fclose(fp);
			return true;
		}

		else
		{
			fgets(names, 10, fp); //obtem 'Password:'
			fgets(names, 10, fp); //obtem a palavra pass
			fgets(names, 6, fp);  //obtem 'User:'
		}
		
	}while(fgets(names, 10, fp) != NULL);
	
	fclose(fp);
	return false;
}


bool UserName_operador(char userName[SIZE_USER_PASS])
{
	char names[SIZE_USER_PASS], nomes[SIZE_USER_PASS];
	int aux;
	FILE *fp = fopen("operador.txt", "r");

	if(fp == NULL)
		return false;

	fgets(names, 6, fp);  //obtem o 'User: '
	fgets(names, 10, fp);  //obtem o Nome do user

	do
	{
		names[strlen(names)-1] = 0;

		//fgets(msg, BUFFER_SIZE, fp);                          //erro

		for (int c = 0; c < SIZE_USER_PASS; ++c)
		{
			if (c == 0)
				strcpy(nomes, &names[c]);

			else if (strcmp(&names[c], "\0"))
				strcat(nomes, &names[c]);

			else
			{
				aux = c;
				strcat(nomes, &names[c]);
				break;
			}

			printf("nomes = %s\n", nomes);
			printf("names = %s\n", names);
		}

		printf("\n%s\n", nomes);

		char nomes_aux[aux];

		for (int v = 0; v <= aux; ++v)
		{
			if (strcmp(&nomes[v], "\0"))
			{
				nomes_aux[v] = &nomes[v];
			}

			else
				break;

			printf("\n\nnomes_aux = %s\n", nomes_aux);
			printf("nomes = %s\n\n", nomes_aux);
		}

		if(!strcmp(nomes_aux, userName))
		{
			printf("retorna true\n");
			fclose(fp);
			return true;
		}

		else
		{
			fgets(names, 10, fp); //obtem 'Password:'
			fgets(names, 10, fp); //obtem a palavra pass
			fgets(names, 6, fp);  //obtem 'User:'
		}
		
	}while(fgets(names, 10, fp) != NULL);
	
	fclose(fp);
	return false;
}

bool verifica_login(char userName[SIZE_USER_PASS], char password[SIZE_USER_PASS])
{
	char names[SIZE_USER_PASS], pass[SIZE_USER_PASS];
	FILE *fp_registados = fopen("registados.txt", "r");

	if(fp_registados == NULL)
		return false;

	fgets(names, 6, fp_registados);  //obtem o 'User: '
	fgets(names, 10, fp_registados);  //obtem o Nome do user

	do
	{
		names[strlen(names)-1] = 0;

		if(!strcmp(names, userName))
		{                                                                     //usado na PASS
			fgets(pass, 10, fp_registados);
			fgets(pass, 10, fp_registados);
			pass[strlen(pass)-1] = 0;

			if (!strcmp(pass, password))
				return true;
		
			return false;
		}

		else
		{
			fgets(names, 10, fp_registados); //obtem 'Password:'
			fgets(names, 10, fp_registados); //obtem a palavra pass
			fgets(names, 6, fp_registados);  //obtem 'User:'
		}
		
	}while(fgets(names, 10, fp_registados) != NULL);

	return false;
}

void erase_OPER_User(char userName[SIZE_USER_PASS])
{
	char names[SIZE_USER_PASS];
	FILE *fp = fopen("operador.txt", "r");
	FILE *aux = fopen("operador_aux.txt", "a");

	fgets(names, 6, fp);  //obtem o 'User: '
	fgets(names, 10, fp);  //obtem o Nome do user

	do
	{
		names[strlen(names)-1] = 0;

		if (strcmp(names, userName))
		{
			fprintf(aux, "User:%s\n", names);

			fgets(names, 10, fp); //obtem 'Password:'              //nao testado (QUIT)
			fgets(names, 10, fp); //obtem a palavra pass

			names[strlen(names)-1] = 0;

			fprintf(aux, "Password:%s\n", names);

			fgets(names, 6, fp);  //obtem 'User:'
		}

		else
		{
			fgets(names, 10, fp); //obtem 'Password:'
			fgets(names, 10, fp); //obtem a palavra pass
			fgets(names, 6, fp);  //obtem 'User:'
		}

	}while(fgets(names, 10, fp) != NULL);

	fclose(fp);
	fclose(aux);

	remove("operador.txt");
	rename("operador_aux.txt", "operador.txt");
}

void erase_User(cliente_t *cliente_v[], char userName[SIZE_USER_PASS])
{
	char names[SIZE_USER_PASS];
	FILE *fp = fopen("registados.txt", "r");
	FILE *aux = fopen("registados_aux.txt", "a");

	fgets(names, 6, fp);  //obtem o 'User: '
	fgets(names, 10, fp);  //obtem o Nome do user

	do
	{
		names[strlen(names)-1] = 0;

		if (strcmp(names, userName))
		{
			fprintf(aux, "User:%s\n", names);

			fgets(names, 10, fp); //obtem 'Password:'
			fgets(names, 10, fp); //obtem a palavra pass

			names[strlen(names)-1] = 0;

			fprintf(aux, "Password:%s\n", names);                //nao testado (KICK)

			fgets(names, 6, fp);  //obtem 'User:'
		}

		else
		{
			erase_cliente(cliente_v, userName);
			fgets(names, 10, fp); //obtem 'Password:'
			fgets(names, 10, fp); //obtem a palavra pass
			fgets(names, 6, fp);  //obtem 'User:'
		}

	}while(fgets(names, 10, fp) != NULL);

	fclose(fp);
	fclose(aux);

	remove("registados.txt");
	rename("registados_aux.txt", "registados.txt");
}

void erase_Online(cliente_t *cliente_v[], char userName[SIZE_USER_PASS])
{
	char names[SIZE_USER_PASS];
	FILE *fp = fopen("usuarios.txt", "r");
	FILE *aux = fopen("usuarios_aux.txt", "a");

	fgets(names, 10, fp);  //obtem o Nome do user

	do
	{
		names[strlen(names)-1] = 0;

		if (strcmp(names, userName))
			fprintf(aux, "%s\n", names);              //nao usado, saio do programa sempre por crl C

	}while(fgets(names, 10, fp) != NULL);

	fclose(fp);
	fclose(aux);

	remove("usuarios.txt");
	rename("usuarios_aux.txt", "usuarios.txt");
}
