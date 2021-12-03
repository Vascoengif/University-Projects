esta_Comboio(Obj,Comboio).
esta_Cidade(Obj,Cidade).
esta_CC(Comboio, Cidade).

carrega(Obj, C).
descarrega(Obj, C).
c1_transporta(Cidade1, Cidade2, [ ]).
c2_transporta(Cidade1, Cidade2, [ ]).


%acao(Nome,Precondições, ADDList, DELList)

acao(carrega(Obj,C),[esta_Cidade(Obj,Cidade), esta_CC(C,Cidade)],[esta_Comboio(Obj,C)],[esta_Cidade(Obj,Cidade)]):- member(Obj,[1,2,3,4,5]), member(C,[Porto, Lisboa, Evora]). 

acao(descarrega(Obj,C),[esta_Comboio(Obj,Comboio), esta_CC(Comboio,Cidade)],[esta_Cidade(Obj,Cidade)],[esta_Comboio(Obj,Comboio)]):- member(Obj,[1,2,3,4,5]), member(C,[Porto, Lisboa, Evora]). 

acao(c1_transporta(Cidade1, Cidade2, [ListaDeObjetos]), [esta_CC(c1,Cidade1), esta_Comboio(ListaDeObjetos, c1)],[esta_CC(c1, Cidade2)],[esta_CC(c1,Cidade1)]):- Cidade1 \= Cidade2, member(Cidade1,[Lisboa,Porto]), member(Cidade2,[Lisboa, Porto]), member(ListadeObjetos, [1,2,3,4,5]).

acao(c2_transporta(Cidade1, Cidade2, [ListaDeObjetos]), [esta_CC(c2,Cidade1), esta_Comboio(ListaDeObjetos, c2)],[esta_CC(c2,Cidade2)],[esta_CC(c2,Cidade1)]):- Cidade1 \= Cidade2, member(Cidade1,[Lisboa,Evora]), member(Cidade2,[Lisboa, Evora]), member(ListadeObjetos, [1,2,3,4,5]).

%ex2
estado_inicial([esta_Cidade(Obj1, Porto), esta_Cidade(Obj2, Lisboa), esta_Cidade(Obj3, Lisboa), esta_Cidade(Obj4, Evora), esta_Cidade(Obj5, Evora), esta_CC(C1, Lisboa), esta_CC(C2, Lisboa)]).

estado_final([esta_Cidade(Obj2, Porto), esta_Cidade(Obj4, Porto), esta_Cidade(Obj5, Lisboa), esta_Cidade(Obj1, Evora), esta_Cidade(Obj3, Evora)]).

%ex3
estado1([esta_Cidade(Obj1,Lisboa), esta_Cidade(Obj2,Lisboa), esta_Cidade(Obj3,Lisboa), esta_Cidade(Obj4,Evora), esta_Cidade(Obj5,Evora),_,_]).
