:- dynamic(nos/1).

nos(0).

inc:- retract(nos(N)), N1 is N+1, asserta(nos(N1)).

  

pesquisa(Problema):-consult(Problema), estado_inicial(E0), back(E0,A), esc(A).


back(e([],A),A).
  back(E,Sol):- sucessor(E,E1), inc, ve_restricoes(E1),
                       forCheck(E1,E2),
                          back(E2,Sol).
                          

forCheck(e(Lni,[v(N,D,V)|Li]), e(Lnii,[v(N,D,V)|Li])):-  corta(V,Lni,Lnii).

corta(_,[],[]).
corta(V,[v(N,D,_)|Li], [v(N,D1,_)|Lii]):- delete(D,V,D1), corta(V,Li,Lii).

sucessor(e([v(N,D,V)|R],E),e(R,[v(N,D,V)|E])):- member(V,D).
