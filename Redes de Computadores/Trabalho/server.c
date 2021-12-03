#include <stdio.h>
#include <stdint.h>
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <unistd.h> 
#include <string.h>
#include <netdb.h> 
#include <time.h>
#include <sys/select.h>
#include "cliente.c"
#include "t1.c"
#define PORT 5555
#define BUFFER_SIZE 512
#define N_INSTRUCOES 5
#define SIZE 10
#define N_CLIENTES 100

int main(int argc, char const *argv[])
{	
	//array de pointers
	cliente_t **cliente = malloc(N_CLIENTES *sizeof(cliente_t));

	char chat_client[BUFFER_SIZE], chat_client_bytedestuff[BUFFER_SIZE*2], chat_client_hdecode[BUFFER_SIZE*2], msg[BUFFER_SIZE+SIZE+3], msg_aux[BUFFER_SIZE], msg_hcode[(BUFFER_SIZE+3)*2], msg_bytestuff[(BUFFER_SIZE+3)*2], msg_encriptada[(BUFFER_SIZE+3)*2], instrucoes[N_INSTRUCOES], espaco[2], sala_anterior[2], nomes[SIZE];
	struct sockaddr_in address;
	int servsock_default, addrlen = sizeof(address), opt=1, maxfd, num_bits, aux;
	fd_set master, read_fds;

	//AF_INET diz ser IPv4
	//SOCK_STREAM diz ser TCP
	//criada a socket
	servsock_default = socket(AF_INET, SOCK_STREAM, 0);

	setsockopt(servsock_default, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt));

	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons(PORT); //reservar a porta

	bind(servsock_default, (struct sockaddr *)&address, sizeof(address));

	listen(servsock_default, 3); //lida com 3 pedidos ao mesmo tempo

	FD_ZERO(&master); //coloca a struct a zero
	FD_SET(servsock_default, &master); //coloco o socket na struct master

	maxfd = servsock_default;

	while(1)
	{
		read_fds = master; //guardar o master porque o master vai sempre ser alterado

		select(maxfd + 1, &read_fds, NULL, NULL, NULL);
                                                                    //verificação e aceitação de conexão de uma pessoa
																	//pra baixo
		for (int i = 0; i < (maxfd + 1); ++i)
		{

			if (FD_ISSET(i, &read_fds))             //se recebeu alguma coisa entra
			{
				if (i == servsock_default)
				{
					int new_socket = accept(servsock_default, (struct sockaddr *)&address, (socklen_t*)&addrlen);  //connect do exp_1

					if (new_socket > maxfd)
						maxfd = new_socket;                            

					FD_SET(new_socket, &master);

					cliente[new_socket] = new_cliente();           //novo cliente na nova posi

					for (int j = 4; j < maxfd + 1; ++j)
					{
						if(new_socket != j && !strcmp(cliente[new_socket]->room, cliente[j]->room))
						{
							strcpy(msg_aux, cliente[new_socket]->userName);
							strcat(msg_aux, " entrou neste canal");                      //envia pra todos que a pessoa entrou

							hcode(msg_aux, msg_hcode);                                   //encriptação
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(j, msg_encriptada, strlen(msg_encriptada), 0);
						}
					}
				}

				else
				{
					num_bits = recv(i, chat_client_bytedestuff, BUFFER_SIZE*2, 0);
					
					bytedestuff(chat_client_bytedestuff, chat_client_hdecode);
					hdecode(chat_client_hdecode, chat_client);

					chat_client[num_bits/2] = 0;       //a encriptação tem o dobro do tamanho e assim recebo o tamanho da msg certa

					FILE *fp = fopen("MSG.txt", "w");         //file criado e apagado no fim de cada iteração
					fprintf(fp, "%s", chat_client);
					fclose(fp);

					fp = fopen("MSG.txt", "r");
					fgets(instrucoes, N_INSTRUCOES, fp);
					fgets(espaco, 2, fp);

					if(!strcmp(instrucoes, "NICK"))
					{



						fgets(msg, BUFFER_SIZE, fp);

						for (int c = 0; c < SIZE+1; ++c)
						{
							if (c == 0)
								strcpy(nomes, &msg[c]);

							else if (strcmp(&msg[c], "\0"))
								strcat(nomes, &msg[c]);

							else
							{
								aux = c;
								strcat(nomes, &msg[c]);
								break;
							}
						}

						char nomes_aux[aux];

						for (int v = 0; v < aux; ++v)
						{
							if (strcmp(&nomes[v], "\0"))
							{
								nomes_aux[v] = nomes[v];
							}

							else
								break;	
						}





						if(!strcmp(nomes_aux, ""))
						{
							strcpy(msg_aux, "RPLY 002 - Erro: Falta introducao do nome");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}

						else if(strlen(nomes_aux) > 10)  //nao funciona
						{
							strcpy(msg_aux, "RPLY 003 - Erro: Nome pedido nao valido");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}

						else if(UserName_online(nomes_aux))
						{
							strcpy(msg_aux, "RPLY 004 - Erro: nome ja em uso");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
						
						else
						{
							if(!strcmp(cliente[i]->userName, " "))
								printf("novo utilizador ");

							else
								printf("%s mudou o seu nome para ", cliente[i]->userName);
							
							FILE *fp_aux = fopen("usuarios.txt", "a");
							fprintf(fp_aux, "%s\n", nomes_aux);
							fclose(fp_aux);
							strcpy(cliente[i]->userName, nomes_aux);

							if (!strcmp(nomes_aux, "Admin"))
								strcpy(cliente[i]->operador, "1");
							
							printf("%s\n", cliente[i]->userName);
							
							strcpy(msg_aux, "RPLY 001 - Nome atribuido com sucesso");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
						
					}

					else if(!strcmp(instrucoes, "MSSG"))
					{
						fgets(chat_client, BUFFER_SIZE, fp);

						if (!strcmp(chat_client, "MSSG"))
						{
							strcpy(msg_aux, "RPLY 102 - Erro. Nao ha texto na mensagem");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}

						else if(sizeof(chat_client) > 512)
						{
							strcpy(msg_aux, "RPLY 103 - Erro. Mensagem demasiado longa");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
						
						else
						{
							for (int j = 4; j < maxfd + 1; ++j)
							{
								if(i != j && !strcmp(cliente[i]->room, cliente[j]->room))
								{
									strcpy(msg, "[");
									strcat(msg, cliente[i]->userName);
									strcat(msg, "] ");
									strcat(msg, chat_client);

									hcode(msg, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(j, msg_encriptada, strlen(msg_encriptada), 0);
								}

								else if(i == j)
								{
									strcpy(msg_aux, "RPLY 101 - mensagem enviada com sucesso");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
								}
							}
						}
					}

					else if(!strcmp(instrucoes, "PASS"))
					{
						fgets(msg, SIZE, fp);

						if (!strcmp(cliente[i]->userName, " "))
						{
							strcpy(msg_aux, "RPLY 202 - Erro. Nome nao está definido");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}

						else if (verifica_login(cliente[i]->userName, msg))
						{
							strcpy(cliente[i]->password, msg);

							strcpy(msg_aux, "RPLY 201 - Autenticacao com sucesso");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
						else
						{
							strcpy(msg_aux, "RPLY 203 - Erro. Password incorreta");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
					}

					else if(!strcmp(instrucoes, "JOIN"))
					{
						fgets(espaco, 2, fp);

						if(UserName_registado(cliente[i]->userName) && strcmp(cliente[i]->password, " "))
						{
							if (!strcmp(espaco, "0") || !strcmp(espaco, "1") || !strcmp(espaco, "2"))
							{
								strcpy(sala_anterior, cliente[i]->room);
								strcpy(cliente[i]->room, espaco);

								strcpy(msg_aux, "RPLY 301 - Mudanca de canal com sucesso");

								hcode(msg_aux, msg_hcode);
								bytestuff(msg_hcode, msg_bytestuff);
								canal(msg_bytestuff, msg_encriptada);

								send(i, msg_encriptada, strlen(msg_encriptada), 0);

								for (int j = 4; j < maxfd + 1; ++j)
								{
									if(i != j && !strcmp(cliente[i]->room, cliente[j]->room))
									{
										strcpy(msg_aux, cliente[i]->userName);
										strcat(msg_aux, " entrou neste canal");

										hcode(msg_aux, msg_hcode);
										bytestuff(msg_hcode, msg_bytestuff);
										canal(msg_bytestuff, msg_encriptada);

										send(j, msg_encriptada, strlen(msg_encriptada), 0);
									}

									else if(i != j && !strcmp(cliente[j]->room, sala_anterior))
									{
										strcpy(msg_aux, cliente[i]->userName);
										strcat(msg_aux, " deixou este canal");

										hcode(msg_aux, msg_hcode);
										bytestuff(msg_hcode, msg_bytestuff);
										canal(msg_bytestuff, msg_encriptada);

										send(j, msg_encriptada, strlen(msg_encriptada), 0);
									}

								}
							}
							else
							{
								strcpy(msg_aux, "RPLY 302 – Erro. canal nao existente");

								hcode(msg_aux, msg_hcode);
								bytestuff(msg_hcode, msg_bytestuff);
								canal(msg_bytestuff, msg_encriptada);

								send(i, msg_encriptada, strlen(msg_encriptada), 0);
							}
						}

						else
						{
							strcpy(msg_aux, "RPLY 303 - Erro. Nao pode mudar para o canal");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
					}

					else if(!strcmp(instrucoes, "LIST"))
					{
						if(UserName_registado(cliente[i]->userName) && strcmp(cliente[i]->password, " "))
						{
							strcpy(msg_aux, "Canais disponiveis:\n0 - Default;\n1 - CN (Computer Network);\n2 - OSS (Open Source Software)");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}

					}

					else if(!strcmp(instrucoes, "WHOS"))
					{
						if(UserName_registado(cliente[i]->userName) && strcmp(cliente[i]->password, " "))
						{
							for (int j = 4; j < maxfd + 1; ++j)
							{
								if(!strcmp(cliente[i]->room, cliente[j]->room))
								{
									strcpy(msg_aux, "\nNick name: ");
									strcat(msg_aux, cliente[j]->userName);

									if (UserName_registado(cliente[j]->userName))
										strcat(msg_aux, "\nRegistado\n");
									else
										strcat(msg_aux, "\nNao Registado\n");

									if(!strcmp(cliente[i]->operador, "1"))
										strcat(msg_aux, "Operador\n");
									
									else
										strcat(msg_aux, "Nao operador\n");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
								}
							}
						}
					}

					else if(!strcmp(instrucoes, "KICK"))
					{
						fgets(msg, BUFFER_SIZE, fp);

						strcpy(nomes, msg);

						if (UserName_operador(cliente[i]->userName))
						{
							if(UserName_registado(cliente[i]->userName))
							{
								if (strcmp(cliente[i]->password, " "))
								{					
									erase_User(cliente, nomes);

									printf("%s foi expulso\n", nomes);

									strcpy(msg_aux, "RPLY 601 – Utilizador expulso");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
								}

								else
								{
									strcpy(msg_aux, "RPLY 604 – Erro. Acao nao autorizada, utilizador cliente nao esta autenticado");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
								}
							}

							else
							{
								strcpy(msg_aux, "RPLY 603 – Erro. Acao nao autorizada, utilizador ");
								strcat(msg_aux, cliente[i]->userName);
								strcat(msg_aux, " nao e um utilizador registado");

								hcode(msg_aux, msg_hcode);
								bytestuff(msg_hcode, msg_bytestuff);
								canal(msg_bytestuff, msg_encriptada);

								send(i, msg_encriptada, strlen(msg_encriptada), 0);
							}
						}

						else
						{
							strcpy(msg_aux, "RPLY 602 – Erro. Acao nao autorizada, utilizador cliente nao e um operperador");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}


						
					}

					else if(!strcmp(instrucoes, "REGS"))
					{
						fgets(msg, BUFFER_SIZE, fp);

						strcpy(nomes, msg);
						
						if (UserName_operador(cliente[i]->userName))
						{
							if(UserName_registado(cliente[i]->userName))
							{
								if (strcmp(cliente[i]->password, " "))
								{	
									//vou receber uma string (nome com comprimento de 1-9 | pass com comprimento de 1-9)
									//pegar no nome e colocar numa variavel
									//pegar na pass e meter noutra variavel

									printf("%s foi registado\n", nomes);

									strcpy(msg_aux, "RPLY 701 – Utilizador foi registado com sucesso");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);

								}

								else
								{
									strcpy(msg_aux, "RPLY 704 – Erro. Acao nao autorizada, utilizador cliente nao esta autenticado");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
								}
							}

							else
							{
								strcpy(msg_aux, "RPLY 703 – Erro. Acao nao autorizada, utilizador ");
								strcat(msg_aux, cliente[i]->userName);
								strcat(msg_aux, " nao e um utilizador registado");

								hcode(msg_aux, msg_hcode);
								bytestuff(msg_hcode, msg_bytestuff);
								canal(msg_bytestuff, msg_encriptada);

								send(i, msg_encriptada, strlen(msg_encriptada), 0);
							}
						}

						else
						{
							strcpy(msg_aux, "RPLY 702 – Erro. Acao nao autorizada, utilizador cliente nao e um operperador");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
					}

					else if(!strcmp(instrucoes, "OPER"))
					{
						fgets(msg, BUFFER_SIZE, fp);

						strcpy(nomes, msg);

						if(UserName_operador(cliente[i]->userName))
						{
							if (UserName_registado(cliente[i]->userName))
							{
								if (strcmp(cliente[i]->password, " "))
								{
									cliente_t *pessoa = find_cliente(cliente, nomes);
									oper(pessoa);

									printf("%s foi promovido a operador\n", nomes);

									strcpy(msg_aux, "RPLY 801 – Foi promovido a operador");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
											
								}

								else
								{
									strcpy(msg_aux, "RPLY 804 – Erro. Acao nao autorizada, utilizador cliente nao esta autenticado");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
								}
								
							}
							
							else
							{
								strcpy(msg_aux, "RPLY 803 – Erro. Acao nao autorizada, utilizador ");
								strcat(msg_aux, cliente[i]->userName);
								strcat(msg_aux, " nao e um utilizador registado");

								hcode(msg_aux, msg_hcode);
								bytestuff(msg_hcode, msg_bytestuff);
								canal(msg_bytestuff, msg_encriptada);

								send(i, msg_encriptada, strlen(msg_encriptada), 0);
							}
						}

						else
						{
							strcpy(msg_aux, "RPLY 802 – Erro. Acao nao autorizada, utilizador cliente nao e um operador");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
					}

					else if (!strcmp(instrucoes, "QUIT"))
					{
						fgets(msg, SIZE, fp);

						strcpy(nomes, msg);

						if(UserName_operador(cliente[i]->userName))
						{
							if (UserName_registado(cliente[i]->userName))
							{
								if (strcmp(cliente[i]->password, " "))
								{
									erase_OPER_User(nomes);

									strcpy(cliente[i]->operador, "0");

									printf("%s deixou de ser operador\n", nomes);

									strcpy(msg_aux, "RPLY 901 – Deixou de ser operador");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
								}

								else
								{
									strcpy(msg_aux, "RPLY 904 – Erro. Acao nao autorizada, utilizador cliente nao esta autenticado");

									hcode(msg_aux, msg_hcode);
									bytestuff(msg_hcode, msg_bytestuff);
									canal(msg_bytestuff, msg_encriptada);

									send(i, msg_encriptada, strlen(msg_encriptada), 0);
								}
							}

							else
							{
								strcpy(msg_aux, "RPLY 903 – Erro. Acao nao autorizada, utilizador ");
								strcat(msg_aux, cliente[i]->userName);
								strcat(msg_aux, " nao e um utilizador registado");

								hcode(msg_aux, msg_hcode);
								bytestuff(msg_hcode, msg_bytestuff);
								canal(msg_bytestuff, msg_encriptada);

								send(i, msg_encriptada, strlen(msg_encriptada), 0);
							}
						}

						else
						{
							strcpy(msg_aux, "RPLY 902 – Erro. Acao nao autorizada, utilizador cliente nao e um operador");

							hcode(msg_aux, msg_hcode);
							bytestuff(msg_hcode, msg_bytestuff);
							canal(msg_bytestuff, msg_encriptada);

							send(i, msg_encriptada, strlen(msg_encriptada), 0);
						}
					}
					fclose(fp);
				}
				remove("MSG.txt");

				memset(chat_client, 0, BUFFER_SIZE);                          //limpa os arrays utilizados a cada iteração.
				memset(chat_client_bytedestuff, 0, BUFFER_SIZE*2);
				memset(chat_client_hdecode, 0, BUFFER_SIZE*2);
				memset(msg, 0, BUFFER_SIZE+SIZE+3);
				memset(msg_hcode, 0, (BUFFER_SIZE+3)*2);
				memset(msg_bytestuff, 0, (BUFFER_SIZE+3)*2);
				memset(msg_encriptada, 0, (BUFFER_SIZE+3)*2);
				memset(nomes, 0, SIZE);
				memset(instrucoes, 0, N_INSTRUCOES);
				memset(espaco, 0, 2);
			}
		}
	}

	/*close(i);
	FD_CLR(i, &read_fds);
	erase_Online(cliente, cliente[i]->userName);*/
	return 0;
}