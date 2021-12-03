%a)
%estado_inicial(e(1,3,5,7)).
%estado_inicial(e(2,0,0,0)).
%estado_inicial(e(0,0,0,0)).
estado_inicial(e(2,0,3,0)).

%b)
terminal(e(0,0,0,0)).  

%c) 

valor(E,-10,P):- terminal(E),  R is P mod 2, R=1.
valor(E,10,P):- terminal(E),  R is P mod 2, R=0.

  
%op1(E,Jogada,Es)

  
op1(e(N1,N2,N3,N4),ret(1,N),e(N11,N2,N3,N4)):- numero(1,N),
  N11 is N1 - N, N11>= 0.

op1(e(N2,N1,N3,N4),ret(2,N),e(N2,N11,N3,N4)):- numero(1,N),
  N11 is N1 - N, N11>= 0.

op1(e(N1,N2,N3,N4),ret(3,N),e(N1,N2,N33,N4)):- numero(1,N),
  N33 is N3 - N, N33>= 0.

op1(e(N1,N2,N3,N4),ret(4,N),e(N1,N2,N3,N44)):- numero(1,N),
  N44 is N4 - N, N44>= 0.

maximo(7).

numero(N,N).
numero(L, N1):-maximo(M), L<M, L1 is L+1, numero(L1,N1).

%f)

f_avalia(Estado,Val,P):- nim_sum(Estado,Val1),bom_mau2(Val1,Val,P).
	
	nim_sum([V],V).   %% calcula o xor entre todos os elementos
	nim_sum([A,B|R],V):- C is A^B,
		                nim_sum([C|R],V),!.
	
  bom_mau2(0,1,P):-	Y is P mod 2,
		            Y =0,!.
	
  bom_mau2(0,-1,P):-	Y is P mod 2,
		              Y \=0,!.
	
  bom_mau2(X,0,_):-X\=0,!.


%g)
joga :- estado_inicial(E), agente(E).

agente(E) :- terminal(E),valor(E,-10,_) ,write(perdeu),nl.

agente(Ei) :- minimax_decidir(Ei,Op), write(jogo(Op)),
              op1(Ei,Op,Es), write(Es),nl.

  
