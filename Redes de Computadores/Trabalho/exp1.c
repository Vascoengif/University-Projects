#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <unistd.h> 
#include <netdb.h>
#include <sys/select.h>
#include "cliente.c"
#include "t1.c"
#define PORT 5555
#define BUFFER_SIZE 512
#define SIZE 10
#define N_CLIENT 20

int chat()
{
	struct hostent *host_entity;
	struct sockaddr_in serv_addr;
	char chat_client[BUFFER_SIZE], chat_client_bytestuff[BUFFER_SIZE*2], chat_client_hcode[BUFFER_SIZE*2], chat_client_encriptada[BUFFER_SIZE*2], chat_serv[BUFFER_SIZE], chat_serv_bytedestuff[BUFFER_SIZE*2], chat_serv_hdecode[BUFFER_SIZE*2];
	int sock_x, maxfd, addrlen = sizeof(serv_addr), num_bits;
	fd_set master, read_fds;

	serv_addr.sin_family = AF_INET; 
    serv_addr.sin_port = htons(PORT);
	host_entity = gethostbyname("localhost");
	bcopy((char*)host_entity -> h_addr, (char*)&serv_addr.sin_addr.s_addr, host_entity ->h_length);
	sock_x = socket(AF_INET, SOCK_STREAM, 0);
	
	connect(sock_x, (struct sockaddr*)&serv_addr, sizeof(serv_addr));  //conectar à porta, accept no server

	FD_ZERO(&master);
	FD_SET(sock_x, &master);
	FD_SET(0, &master); //o 0 é o teclado
	maxfd = sock_x;

	while(1)
	{
		read_fds = master;

		select(maxfd + 1, &read_fds, NULL, NULL, NULL); //verifica se tem alguma coisa para ser enviada

		if(FD_ISSET(0, &read_fds))
		{
			gets(chat_client);

			hcode(chat_client, chat_client_hcode);
			bytestuff(chat_client_hcode, chat_client_bytestuff);
			canal(chat_client_bytestuff, chat_client_encriptada);

			send(sock_x, chat_client_encriptada, strlen(chat_client_encriptada) + 1, 0);
		}

		else
		{
			num_bits = recv(sock_x, chat_serv_bytedestuff, 1000, 0);

			bytedestuff(chat_serv_bytedestuff, chat_serv_hdecode);
			hdecode(chat_serv_hdecode, chat_serv);

			chat_serv[num_bits/2] = 0;
			puts(chat_serv);
		}
	}
}

void WELCOME()
{
	printf("\n");
	printf("\n");

	printf("             ## #  ##    ##     #####  ##########\n");
	printf("            #      ########    ##   ##     ##\n");
	printf("            #      ########   #########    ##\n");
	printf("             ## #  ##    ##  ###     ###   ##\n");
	
	printf("\n");
	printf("\n");
}

int main()
{
	WELCOME();
	chat();

    return 0;
}



//manda e recebe 