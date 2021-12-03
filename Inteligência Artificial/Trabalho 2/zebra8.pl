
%exercicio 1.a)

%estado inicial, onde há 8 lugares
estado_inicial(e([
	v(c(1),D,_),
	v(c(2),D,_),
	v(c(3),D,_),
	v(c(4),D,_),
	v(c(5),D,_),
	v(c(6),D,_),
	v(c(7),D,_),
	v(c(8),D,_)], [])):- pessoas(D).
	
%dominio	
pessoas(['Maria', 'Manuel', 'Madalena', 'Joaquim', 'Ana', 'Julio', 'Matilde', 'Gabriel']).

%ve_restricoes
ve_restricoes(e(_Nafec,Afect)):- \+ (member(v(c(I),_Di,Vi),Afect),
                                     member(v(c(J),_Dj,Vj),Afect),
                                     I\= J,
                                     restrict(I,Vi,J,Vj)).
                                     
%restricoes
%sucede se alguma restrição falha!!
restrict(I,X,J,Y):-restricoes(L), member(frente(X,Y),L), \+ ((I=1, J=5) ;( I=2, J=8);(I=3,J=7);(I=4,J=6)).
restrict(I,X,J,Y):-restricoes(L), member(esq(X,Y),L), \+ (I is J+1; (I=1, J=8)).
restrict(I,X,J,Y):-restricoes(L), member(dir(X,Y),L), \+ (I is J-1; (I=8, J=1)).
restrict(I,X,J,Y):-restricoes(L), member(lado(X,Y),L), \+ ((I is J-1; (I=8, J=1));(I is J+1; (I=1,J=8)) ).
restrict(I,X,J,Y):-restricoes(L), member(cabeceira(X),L), \+ (I=1 ; I=5).
restrict(I,X,J,Y):-restricoes(L), member(cabeceira_esq(X),L), \+ I=1.
restrict(I,X,J,Y):-restricoes(L), member(cabeceira_dir(X),L), \+ I=5.
restrict(I,X,J,Y):- I\=J, X = Y.

restricoes([esq('Manuel','Maria'), frente('Joaquim','Maria'), lado('Joaquim','Matilde'), cabeceira('Gabriel') ]).

%--------------------------------------------------------------------------------


%escreve resultados
esc(L):- sort(L,L1), write(L1), nl, esc1(L1).
esc1([]).
esc1([v(c(X),_,E)|R]) :- write(E) ,write('-'),write(X), nl, esc1(R).

