%exercicio 2.a)

% estado_((posiÃ§ao_caixa),(posicao_agente)).

estado_inicial(((6, 2),(7, 2))).

estado_final(((1, 5), _ )).

estado_bloqueado(X,Y):- X=1, Y=3;
            		X=2, Y=1; 
                    X=2, Y=3;
                    X=2, Y=7;
                    X=4, Y=4;
                    X=5, Y=4;
                    X=6, Y=4.



%op(actual_position_caixa,actual_position_agente, move, new_position_caixa,new_position_agente, cost) USADO PARA O A EM PROCESSO NORMAL

op(((X1,Y1),(X,Y)),move(-1,0),((X2,Y2),(X1,Y1)),1):- X2 is X1-1, X1 is X -1, Y2 is Y1, Y1 is Y, lim(X2,Y2).  %vai para cima


op(((X1,Y1),(X,Y)),move(1,0),((X2,Y2),(X1,Y1)),1):- X2 is X1+1, X1 is X + 1, Y2 is Y1, Y1 is Y, lim(X2,Y2). % vai para baixo


op(((X1,Y1),(X,Y)),move(0,-1),((X2,Y2),(X1,Y1)),1):- Y2 is Y1-1, Y1 is Y - 1, X2 is X1, X1 is X, lim(X2,Y2). % vai para a esquerda


op(((X1,Y1),(X,Y)),move(0,1),((X2,Y2),(X1,Y1)),1):- Y2 is Y1+1, Y1 is Y +1, X2 is X1, X1 is X, lim(X2,Y2). % vai para a direitas


op(((X1,Y1),(X,Y)),movea(-1,0),((X1,Y1),(X2,Y2)),1):- X2 is X - 1, Y2 is Y,(X1, Y1) \= (X2,Y2),lim(X2,Y2). % agente vai para cima

op(((X1,Y1),(X,Y)),movea(1,0),((X1,Y1),(X2,Y2)),1):-  X2 is X + 1, Y2 is Y,(X1, Y1) \= (X2,Y2),lim(X2,Y2). % agente vai para baixo

op(((X1,Y1),(X,Y)),movea(0,1),((X1,Y1),(X2,Y2)),1):-  X2 is X , Y2 is Y + 1,(X1, Y1) \= (X2,Y2),lim(X2,Y2). %agente vai para direita

op(((X1,Y1),(X,Y)),movea(0,-1),((X1,Y1),(X2,Y2)),1):-  X2 is X , Y2 is Y - 1,(X1, Y1) \= (X2,Y2),lim(X2,Y2). %agente vai para esquerda

lim(X,Y):- X < 8, X > 0, Y < 8, Y > 0, \+ estado_bloqueado(X,Y).




 %exercicio 2.b)

:- dynamic(fechado/1).
:- dynamic(maxNL/1).
:- dynamic(nos/1).

maxNL(0).
nos(0).

 inc:- retract(nos(N)), N1 is N+1, asserta(nos(N1)).

  actmax(N):- maxNL(N1), N1 >= N,!.
  actmax(N):- retract(maxNL(_N1)), asserta(maxNL(N)).

pesquisa_ni(Problema):-
    estado_inicial(S0),
    pesquisa_largura([no(S0,[],[],0,0)],Solucao),
    escreve_seq_solucao(Solucao),
    retract(nos(Ns)),retract(maxNL(NL)),
    asserta(nos(0)),asserta(maxNL(0)),
    write(nos(visitados(Ns),lista(NL))).

pesquisa_largura([no(E,Pai,Op,C,P)|_],no(E,Pai,Op,C,P)):- estado_final(E),inc.

pesquisa_largura([E|R],Sol):- inc,expande(E,Lseg), %esc(E),
                            E=no(Ei,_Pai,_Op,_C,_P),
                            assertz(fechado(Ei)),
                            insere_fim(Lseg,R,Resto),
                            length(Resto,N), actmax(N),
                            pesquisa_largura(Resto,Sol).


expande(no(E,Pai,Op,C,P),L):- findall(no(En,no(E,Pai,Op,C,P),Opn,Cnn,P1),
                                    (op(E,Opn,En,Cn),P1 is P+1, Cnn is Cn+C, \+ fechado(En)),
                                    L).


insere_fim([],L,L).
insere_fim(L,[],L).
insere_fim(R,[A|S],[A|L]):- insere_fim(R,S,L).


escreve_seq_solucao(no(E,Pai,Op,Custo,Prof)):- write(custo(Custo)),nl,
                                        write(profundidade(Prof)),nl,
                                        escreve_seq_accoes(no(E,Pai,Op,_,_)).


escreve_seq_accoes([]).
escreve_seq_accoes(no(E,Pai,Op,_,_)):- escreve_seq_accoes(Pai),
                                            write(e(Op,E)),nl.

esc(A):- write(A), nl.

