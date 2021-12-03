%% tabuleiro 5 x 4

estado_inicial([[l,l,l,l,l],[l,l,x,l,l],[o,x,o,x,o],[x,o,x,o,x]]).
%estado_inicial([[l,l,l,l,l],[l,x,l,l,l],[o,x,o,x,o],[x,o,x,o,x]]).



terminal(G) :- linha(G,_).
terminal(G) :- coluna(G,_).
terminal(G) :- diagonal(G,_).
terminal(G) :- completo(G_).

linha([[X,X,X,_,_],_,_,_],X) :- X \= l.
linha([[_,X,X,X,_],_,_,_],X) :- X \= l.
linha([[_,_,X,X,X],_,_,_],X) :- X \= l.
linha([_,[X,X,X,_,_],_,_],X) :- X \= l.
linha([_,[_,X,X,X,_],_,_],X) :- X \= l.
linha([_,[_,_,X,X,X],_,_],X) :- X \= l.
linha([_,_,[X,X,X,_,_],_],X) :- X \= l.
linha([_,_,[_,X,X,X,_],_],X) :- X \= l.
linha([_,_,[_,_,X,X,X],_],X) :- X \= l.
linha([_,_,_,[X,X,X,_,_]],X) :- X \= l.
linha([_,_,_,[_,X,X,X,_]],X) :- X \= l.
linha([_,_,_,[_,_,X,X,X]],X) :- X \= l.

coluna([[X,_,_,_,_],[X,_,_,_,_],[X,_,_,_,_],_],X) :- X \= l.
coluna([_,[X,_,_,_,_],[X,_,_,_,_],[X,_,_,_,_]],X) :- X \= l.
coluna([[_,X,_,_,_],[_,X,_,_,_],[_,X,_,_,_],_],X) :- X \= l.
coluna([_,[_,X,_,_,_],[_,X,_,_,_],[_,X,_,_,_]],X) :- X \= l.
coluna([[_,_,X,_,_],[_,_,X,_,_],[_,_,X,_,_],_],X) :- X \= l.
coluna([_,[_,_,X,_,_],[_,_,X,_,_],[_,_,X,_,_]],X) :- X \= l.
coluna([[_,_,_,X,_],[_,_,_,X,_],[_,_,_,X,_],_],X) :- X \= l.
coluna([_,[_,_,_,X,_],[_,_,_,X,_],[_,_,_,X,_]],X) :- X \= l.
coluna([[_,_,_,_,X],[_,_,_,_,X],[_,_,_,_,X],_],X) :- X \= l.
coluna([_,[_,_,_,_,X],[_,_,_,_,X],[_,_,_,_,X]],X) :- X \= l.

diagonal([[X,_,_,_,_],[_,X,_,_,_],[_,_,X,_,_],_],X) :- X \= l.
diagonal([[_,X,_,_,_],[_,_,X,_,_],[_,_,_,X,_],_],X) :- X \= l.
diagonal([[_,_,X,_,_],[_,_,_,X,_],[_,_,_,_,X],_],X) :- X \= l.

diagonal([_,[X,_,_,_,_],[_,X,_,_,_],[_,_,X,_,_]],X) :- X \= l.
diagonal([_,[_,X,_,_,_],[_,_,X,_,_],[_,_,_,X,_]],X) :- X \= l.
diagonal([_,[_,_,X,_,_],[_,_,_,X,_],[_,_,_,_,X]],X) :- X \= l.

diagonal([[_,_,_,_,X],[_,_,_,X,_],[_,_,X,_,_],_],X) :- X \= l.
diagonal([[_,_,_,X,_],[_,_,X,_,_],[_,X,_,_,_],_],X) :- X \= l.
diagonal([[_,_,X,_,_],[_,X,_,_,_],[X,_,_,_,_],_],X) :- X \= l.

diagonal([_,[_,_,_,_,X],[_,_,_,X,_],[_,_,X,_,_]],X) :- X \= l.
diagonal([_,[_,_,_,X,_],[_,_,X,_,_],[_,X,_,_,_]],X) :- X \= l.
diagonal([_,[_,_,X,_,_],[_,X,_,_,_],[X,_,_,_,_]],X) :- X \= l.

completo([L1,L2,L3,L4,L5]) :- append(L1,L2, L12),
	            append(L12, L3, L123),
	            append(L123, L4, L1234),
	            append(L1234, L5, L12345),
	            \+ member(l, L12345).

%funçao de utilidade

valor(G, 1) :- linhas(G,x).
valor(G, 1) :- colunas(G,x).
valor(G, 1) :- diagonal(G,x).
valor(G, -1) :- linhas(G,o).
valor(G, -1) :- colunas(G,o).
valor(G, -1) :- diagonal(G,o).
valor(_, 0).


op(E, J,joga(X,Y), En) :-
	joga_vazio(E,J,X, Y, En).

joga_vazio([[l,C2,C3,C4,C5],[X,L21,L22,L23,L24],L3,L4], J, 1, 1, [[J,C2,C3,C4,C5],[X,L21,L22,L23,L24],L3,L4]) :- X \= l.
joga_vazio([[C1,l,C3,C4,C5],[L21,X,L22,L23,L24],L3,L4], J, 1, 2, [[C1,J,C3,C4,C5],[L21,X,L22,L23,L24],L3,L4]) :- X \= l.
joga_vazio([[C1,C3,l,C4,C5],[L21,L22,X,L23,L24],L3,L4], J, 1, 3, [[C1,C3,J,C4,C5],[L21,L22,X,L23,L24],L3,L4]) :- X \= l.
joga_vazio([[C1,C3,C4,l,C5],[L21,L22,L23,X,L24],L3,L4], J, 1, 4, [[C1,C3,C4,J,C5],[L21,L22,L23,X,L24],L3,L4]) :- X \= l.
joga_vazio([[C1,C3,C4,C5,l],[L21,L22,L23,L24,X],L3,L4], J, 1, 5, [[C1,C3,C4,C5,J],[L21,L22,L23,L24,X],L3,L4]) :- X \= l.

joga_vazio([L1,[l,C2,C3,C4,C5],[X,L21,L22,L23,L24],L4], J, 2, 1, [L1,[J,C2,C3,C4,C5],[X,L21,L22,L23,L24],L4]) :- X \= l.
joga_vazio([L1,[C1,l,C3,C4,C5],[L21,X,L22,L23,L24],L4], J, 2, 2, [L1,[C1,J,C3,C4,C5],[L21,X,L22,L23,L24],L4]) :- X \= l.
joga_vazio([L1,[C1,C3,l,C4,C5],[L21,L22,X,L23,L24],L4], J, 2, 3, [L1,[C1,C3,J,C4,C5],[L21,L22,X,L23,L24],L4]) :- X \= l.
joga_vazio([L1,[C1,C3,C4,l,C5],[L21,L22,L23,X,L24],L4], J, 2, 4, [L1,[C1,C3,C4,J,C5],[L21,L22,L23,X,L24],L4]) :- X \= l.
joga_vazio([L1,[C1,C3,C4,C5,l],[L21,L22,L23,L24,X],L4], J, 2, 5, [L1,[C1,C3,C4,C5,J],[L21,L22,L23,L24,X],L4]) :- X \= l.

joga_vazio([L1,L2,[l,C2,C3,C4,C5],[X,L21,L22,L23,L24]], J, 3, 1, [L1,L2,[J,C2,C3,C4,C5],[X,L21,L22,L23,L24]]) :- X \= l.
joga_vazio([L1,L2,[C1,l,C3,C4,C5],[L21,X,L22,L23,L24]], J, 3, 2, [L1,L2,[C1,J,C3,C4,C5],[L21,X,L22,L23,L24]]) :- X \= l.
joga_vazio([L1,L2,[C1,C3,l,C4,C5],[L21,L22,X,L23,L24]], J, 3, 3, [L1,L2,[C1,C3,J,C4,C5],[L21,L22,X,L23,L24]]) :- X \= l.
joga_vazio([L1,L2,[C1,C3,C4,l,C5],[L21,L22,L23,X,L24]], J, 3, 4, [L1,L2,[C1,C3,C4,J,C5],[L21,L22,L23,X,L24]]) :- X \= l.
joga_vazio([L1,L2,[C1,C3,C4,C5,l],[L21,L22,L23,L24,X]], J, 3, 5, [L1,L2,[C1,C3,C4,C5,J],[L21,L22,L23,L24,X]]) :- X \= l.

joga_vazio([L1,L2,L3,[l,C2,C3,C4,C5]], J, 4, 1, [L1,L2,L3,[J,C2,C3,C4,C5]]).
joga_vazio([L1,L2,L3,[C1,l,C3,C4,C5]], J, 4, 2, [L1,L2,L3,[C1,J,C3,C4,C5]]).
joga_vazio([L1,L2,L3,[C1,C2,l,C4,C5]], J, 4, 3, [L1,L2,L3,[C1,C2,J,C4,C5]]).
joga_vazio([L1,L2,L3,[C1,C2,C3,l,C5]], J, 4, 4, [L1,L2,L3,[C1,C2,C3,J,C5]]).
joga_vazio([L1,L2,L3,[C1,C2,C3,C4,l]], J, 4, 5, [L1,L2,L3,[C1,C2,C3,C4,J]]).







joga :-  
	estado_inicial(Ei), 
	minimax_decidir(Ei,Op),
	write(Op),nl.





minimax_decidir(Ei,terminou) :- terminal(Ei).

% Nota: assume que o jogador é o "x"
minimax_decidir(Ei,Opf) :- 
	findall(Vc-Op, (op(Ei,x,Op,Es), minimax_valor(Es,Vc,1)), L),
	escolhe_max(L,Opf).



minimax_valor(Ei,Val,_) :- 
	terminal(Ei), 
	valor(Ei,Val).



minimax_valor(Ei,Val,P) :- 
	P1 is P+1, jogador(P1,J),
	findall(Val1, (op(Ei,J,_,Es), minimax_valor(Es,Val1,P1)), V),
	seleciona_valor(V,P,Val).


jogador(P, o) :- X is P mod 2, X = 0.
jogador(P, x) :- X is P mod 2, X = 1.


seleciona_valor(V,P,Val) :- 
	X is P mod 2, X=0,!, 
	maximo(V,Val).


seleciona_valor(V,_,Val):- minimo(V,Val).



escolhe_max([A|R],Val):- escolhe_max(R,A,Val).

escolhe_max([],_-Op,Op).
escolhe_max([A-_|R],X-Op,Val) :- A < X,!, escolhe_max(R,X-Op,Val).
escolhe_max([A|R],_,Val):- escolhe_max(R,A,Val).


maximo([A|R],Val):- maximo(R,A,Val).

maximo([],A,A).
maximo([A|R],X,Val):- A < X,!, maximo(R,X,Val).
maximo([A|R],_,Val):- maximo(R,A,Val).

minimo([A|R],Val):- minimo(R,A,Val).

minimo([],A,A).
minimo([A|R],X,Val):- A > X,!, minimo(R,X,Val).
minimo([A|R],_,Val):- minimo(R,A,Val).
