
estado_inicial(e([
	v(c(1),D,_),
	v(c(2),D,_),
	v(c(3),D,_),
	v(c(4),D,_)], [])):- pessoas(D).
	
	
pessoas(['Maria', 'Manuel', 'Madalena', 'Joaquim']).


ve_restricoes(e(_Nafec,Afect)):- \+ (member(v(c(I),_Di,Vi),Afect),
                                     member(v(c(J),_Dj,Vj),Afect),
                                     I\= J,
                                     restrict(I,Vi,J,Vj)).
                                     

%sucede se alguma restrição falha!!

restrict(I,X,J,Y):-restricoes(L), member(frente(X,Y),L), \+ ((I=1, J=3) ;( I=2, J=4)).
restrict(I,X,J,Y):-restricoes(L), member(esq(X,Y),L), \+ (I is J+1; (I=1, J=4)).
restrict(I,X,J,Y):-restricoes(L), member(dir(X,Y),L), \+ (I is J-1; (I=4, J=1)).
restrict(I,X,J,Y):-restricoes(L), member(lado(X,Y),L), \+ ((I is J-1; (I=4, J=1));(I is J+1; (I=1,J=4)) ).
restrict(I,X,J,Y):-restricoes(L), member(cabeceira(X),L), \+ (I=1 ; I=3).
restrict(I,X,J,Y):-restricoes(L), member(cabeceira_esq(X),L), \+ I=1.
restrict(I,X,J,Y):-restricoes(L), member(cabeceira_dir(X),L), \+ I=3.
restrict(I,X,J,Y):- I\=J, X = Y.

restricoes([esq('Manuel','Maria'), frente('Joaquim','Maria'), lado('Joaquim','Madalena'), cabeceira('Maria') ]).

forCheck(e(Lni,[v(N,D,V)|Li]), e(Lnii,[v(N,D,V)|Li])):-  corta(V,Lni,Lnii).

corta(_,[],[]).
corta(V,[v(N,D,_)|Li], [v(N,D1,_)|Lii]):- delete(D,V,D1), corta(V,Li,Lii).

                
%sucessor(e([v(N,D,V)|R],E),e(R,[v(N,D,V)|E])):- member(V,D).

esc(L):- sort(L,L1), write(L1), nl, esc1(L1).
esc1([]).
esc1([v(c(X),_,E)|R]) :- write(E) ,write('-'),write(X), nl, esc1(R).

