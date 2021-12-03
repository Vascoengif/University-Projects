%exercicio 1.a)

estado_inicial((7, 2)).

estado_final((1, 5)).

estado_bloqueado(X,Y):- X=1, Y=3;
					X=2, Y=1; 
            		X=2, Y=3;
            		X=2, Y=7;
            		X=4, Y=4;
            		X=5, Y=4;
            		X=6, Y=4.


%op(actual_position, move, new_position, cost)

op((X,Y),(0, 1),(X1,Y1),1):- X1 is X, Y1 is Y+1, lim(X1,Y1).     %vai pra direita

op((X,Y),(0, -1),(X1,Y1),1):- X1 is X, Y1 is Y-1, lim(X1,Y1).     %vai pra esquerda

op((X,Y),(-1, 0),(X1,Y1),1):- X1 is X-1, Y1 is Y, lim(X1,Y1).     %vai pra cima

op((X,Y),(1, 0),(X1,Y1),1):- X1 is X+1, Y1 is Y, lim(X1,Y1).     %vai pra baixo


lim(X,Y):- X < 8, X > 0, Y < 8, Y > 0, \+ estado_bloqueado(X,Y).



%exercicio 1.d) heuristicas


h((X,Y), Val):- estado_final((Xf,Yf)), mod(Vi, Xf, X), mod(Vj, Yf, Y), Val is (Vi+Vj).

%h((X,Y), Val):- estado_final((Xf,Yf)), mod(Vi, Xf, X), mod(Vj, Yf, Y), Val is (Vi+Vj) div 2.

mod(Vj, X, Y) :- X < Y, Vj is Y- X.
mod(Vj, X, Y) :- Vj is X-Y.




%exercicio 1.b)

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


%-----------------------------------------------------------------------------
%exercicio 1.e)

:- dynamic(maxNL/1).
:- dynamic(nos/1).

maxNL(0).
nos(0).

 inc:- retract(nos(N)), N1 is N+1, asserta(nos(N1)).

  actmax(N):- maxNL(N1), N1 >= N,!.
  actmax(N):- retract(maxNL(_N1)), asserta(maxNL(N)).
 

pesquisa_i(Problema):-
  estado_inicial(S0),
  pesquisa_g([no(S0,[],[],0,1,0)],Solucao),
  escreve_seq_solucao_informada(Solucao),
  retract(nos(Ns)),retract(maxNL(NL)),
  asserta(nos(0)),asserta(maxNL(0)),
  write(nos(visitados(Ns),lista(NL))).



%pesquisa_g([],_):- !,fail.
pesquisa_g([no(E,Pai,Op,C,HC,P)|_],no(E,Pai,Op,C,HC,P)):- estado_final(E).

pesquisa_g([E|R],Sol):- inc,  asserta(fechado(E)),  expande_g(E,Lseg), %esc(E),
                              insere_ord(Lseg,R,Resto),length(Resto,N), actmax(N),
                              pesquisa_g(Resto,Sol).


expande_g(no(E,Pai,Op,C,HC,P),L):- findall(no(En,no(E,Pai,Op,C,HC,P),Opn,Cnn,H,P1),
					   (op(E,Opn,En,Cn),
                                            \+ fechado(no(En,_,_,_,_,_)),
					    P1 is P+1, Cnn is Cn+C, h(En,H)), L).

insere_ord([],L,L).
insere_ord([A|L],L1,L2):- insereE_ord(A,L1,L3), insere_ord(L,L3,L2).

insereE_ord(A,[],[A]).
insereE_ord(A,[A1|L],[A,A1|L]):- menor_no(A,A1),!.
insereE_ord(A,[A1|L], [A1|R]):- insereE_ord(A,L,R).

menor_no(no(_,_,_,_,N,_), no(_,_,_,_,N1,_)):- N < N1.

escreve_seq_solucao_informada(no(E,Pai,Op,Custo,_HC,Prof)):- write(custo(Custo)),nl,
                                          write(profundidade(Prof)),nl,
                                          escreve_seq_accoes_informada(no(E,Pai,Op,_,_,_)).


escreve_seq_accoes_informada([]).
escreve_seq_accoes_informada(no(E,Pai,Op,_,_,_)):- escreve_seq_accoes_informada(Pai),
                                              write(e(Op,E)),nl.


esc(A):- write(A), nl.
