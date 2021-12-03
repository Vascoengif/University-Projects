estado_inicial(e([
	v(c(1),D,_),
	v(c(2),D,_),
	v(c(3),D,_),
	v(c(4),D,_),
	v(c(5),D,_),
	v(c(6),D,_),
	v(c(7),D,_),
	v(c(8),D,_),
	v(c(9),D,_),
	v(c(10),D,_),
	v(c(11),D,_),
	v(c(12),D,_)], [])):- pessoas(D).
	
	
pessoas(['Maria', 'Manuel', 'Madalena', 'Joaquim', 'Ana', 'Julio', 'Matilde', 'Gabriel','Miguel','Vasco','Ricardo','Irene']).


ve_restricoes(e(_Nafec,Afect)):- \+ (member(v(c(I),_Di,Vi),Afect),
                                     member(v(c(J),_Dj,Vj),Afect),
                                     I\= J,
                                     restrict(I,Vi,J,Vj)).
                                     

%sucede se alguma restrição falha!!

restrict(I,X,J,Y):-restricoes(L), member(frente(X,Y),L), \+ ((I=1, J=7) ;( I=2, J=12);(I=3,J=11);(I=4,J=10);(I=5,J=9);(I=6,J=8)).
restrict(I,X,J,Y):-restricoes(L), member(esq(X,Y),L), \+ (I is J+1; (I=1, J=12)).
restrict(I,X,J,Y):-restricoes(L), member(dir(X,Y),L), \+ (I is J-1; (I=12, J=1)).
restrict(I,X,J,Y):-restricoes(L), member(lado(X,Y),L), \+ ((I is J-1; (I=12, J=1));(I is J+1; (I=1,J=12)) ).
restrict(I,X,J,Y):-restricoes(L), member(cabeceira(X),L), \+ (I=1 ; I=7).
restrict(I,X,J,Y):-restricoes(L), member(cabeceira_esq(X),L), \+ I=1.
restrict(I,X,J,Y):-restricoes(L), member(cabeceira_dir(X),L), \+ I=7.
restrict(I,X,J,Y):- I\=J, X = Y.

restricoes([esq('Manuel','Maria'), frente('Joaquim','Maria'), lado('Joaquim','Matilde'), cabeceira('Gabriel') ]).

forCheck(e(Lni,[v(N,D,V)|Li]), e(Lnii,[v(N,D,V)|Li])):-  corta(V,Lni,Lnii).

corta(_,[],[]).
corta(V,[v(N,D,_)|Li], [v(N,D1,_)|Lii]):- delete(D,V,D1), corta(V,Li,Lii).

                
%sucessor(e([v(N,D,V)|R],E),e(R,[v(N,D,V)|E])):- member(V,D).

esc(L):- sort(L,L1), write(L1), nl, esc1(L1).
esc1([]).
esc1([v(c(X),_,E)|R]) :- write(E) ,write('-'),write(X), nl, esc1(R).

