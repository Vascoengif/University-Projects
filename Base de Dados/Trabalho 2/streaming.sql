--TABELAS ENTIDADES

drop table clientes cascade;
create table clientes(
	login varchar(20) primary key,
	nomeCliente varchar(20),
	morada text,
	password varchar(30),
	dataAdesao text,
	cartaoCredito integer
);

drop table elenco cascade;
create table elenco(
	nomeElenco varchar(35) primary key,
	Nacionalidade text,
	dataNasc text
);

drop table diretores cascade;
create table diretores(
	nomeElenco varchar(35) primary key
);

drop table atores cascade;
create table atores(
	nomeElenco varchar(35) primary key
);

drop table idiomas cascade;
create table idiomas(
	idioma varchar(20) primary key
);

drop table generos cascade;
create table generos(
	genero varchar(20) primary key
);

drop table titulo cascade;
create table titulo(
	lingua varchar(20) primary key
);

drop table premios cascade;
create table premios(
	tipo varchar(40) primary key,
	anoPremio text
);

drop table filmes cascade;
create table filmes(
	url text primary key,
	resumo text,
	pathPasta text,
	localFilmado text,
	dataEstreia text
);

drop table pagamentos cascade;
create table pagamentos(
	primary key (login, nrPagamento),
	login varchar(20),
	nrPagamento integer,
	valor integer, 
	dataPagamento text
);


--TABELAS RELAÇÕES

drop table R2 cascade;
create table R2(
	primary key (login, url),
	login varchar(20),
	url text,
	dataVisto text,
	foreign key (login) references clientes on delete restrict,
	foreign key (url) references filmes on delete restrict
);

drop table R3 cascade;
create table R3(
	primary key (url, idioma),
	url text,
	idioma varchar(20),
	foreign key (url) references filmes on delete restrict,
	foreign key (idioma) references idiomas on delete restrict
);

drop table R4 cascade;
create table R4(
	primary key (url, genero),
	url text,
	genero varchar(20),
	foreign key (url) references filmes on delete restrict,
	foreign key (genero) references generos on delete restrict
);

drop table R5 cascade;
create table R5(
	primary key (url, lingua),
	url text,
	lingua varchar(20),
	foreign key (url) references filmes on delete restrict,
	foreign key (lingua) references titulo on delete restrict
);

drop table R6 cascade;
create table R6(
	primary key (nomeElenco, tipo),
	nomeElenco varchar(35),
	tipo varchar(40),
	foreign key (nomeElenco) references elenco on delete restrict,
	foreign key (tipo) references premios on delete restrict
);

drop table R7 cascade;
create table R7(
	primary key (tipo, nomeElenco),
	tipo varchar(40),
	nomeElenco varchar(35),
	foreign key (tipo) references premios on delete restrict,
	foreign key (nomeElenco) references elenco on delete restrict
);

drop table R8 cascade;
create table R8(
	primary key (tipo, url),
	tipo varchar(40),
	url text,
	foreign key (tipo) references premios on delete restrict,
	foreign key (url) references filmes on delete restrict
);

drop table R9 cascade;
create table R9(
	primary key (tipo, url),
	tipo varchar(40),
	url text,
	foreign key (tipo) references premios on delete restrict,
	foreign key (url) references filmes on delete restrict
);

drop table R10 cascade;
create table R10(
	primary key (nomeElenco, url),
	nomeElenco varchar(35),
	url text,
	foreign key (nomeElenco) references elenco on delete restrict,
	foreign key (url) references filmes on delete restrict
);

drop table R11 cascade;
create table R11(
	primary key (nomeElenco, url),
	nomeElenco varchar(35),
	url text,
	foreign key (nomeElenco) references elenco on delete restrict,
	foreign key (url) references filmes on delete restrict
);





----INSERT FILMES

--TheShawshankRedemption
insert into filmes values ('https://streamingfilmes.pt/TheShawshankRedemption', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', 'C:\Users\Administrator\StreamingFilmes\TheShawshankRedemption', 'Ohio, United States of America', to_date('31031995','DDMMYYYY'));

--insert into premios values ('Best Picture', unnest(array['1973','1995']));
--insert into premios values ('Best Actor', unnest(array['1973', '1995']));
--insert into premios values ('Best Writing', unnest(array['1973', '1995']));
insert into premios values ('Best Cinematography', '1995');
insert into premios values ('Best Sound', '1995');
insert into premios values ('Best Film Editing', '1995');
--insert into premios values ('Best Music', unnest(array['1975','1995');
--insert into R9 values ('Best Picture', 'https://streamingfilmes.pt/TheShawshankRedemption');
--insert into R9 values ('Best Actor', 'https://streamingfilmes.pt/TheShawshankRedemption');
--insert into R9 values ('Best Writing', 'https://streamingfilmes.pt/TheShawshankRedemption');
insert into R9 values ('Best Cinematography', 'https://streamingfilmes.pt/TheShawshankRedemption');
insert into R9 values ('Best Sound', 'https://streamingfilmes.pt/TheShawshankRedemption');
insert into R9 values ('Best Film Editing', 'https://streamingfilmes.pt/TheShawshankRedemption');
--insert into R9 values ('Best Music', 'https://streamingfilmes.pt/TheShawshankRedemption');

insert into titulo values ('Inglês');
insert into titulo values ('Português');
insert into R5 values ('https://streamingfilmes.pt/TheShawshankRedemption', 'Inglês');

insert into generos values ('Drama');
insert into R4 values ('https://streamingfilmes.pt/TheShawshankRedemption', 'Drama');

insert into idiomas values ('Inglês');
insert into R3 values ('https://streamingfilmes.pt/TheShawshankRedemption', 'Inglês');

insert into elenco values ('Tim Robbins', 'Americana', to_date('16101958','DDMMYYYY'));
insert into elenco values ('Morgan Freeman', 'Americana', to_date('01061937','DDMMYYYY'));
insert into elenco values ('Bob Gunton', 'Americana', to_date('16101958','DDMMYYYY'));
insert into elenco values ('William Sadler', 'Americana', to_date('13041950','DDMMYYYY'));
insert into elenco values ('Frank Darabont', 'Francesa', to_date('28011959','DDMMYYYY'));
insert into atores values ('Tim Robbins');
insert into atores values ('Morgan Freeman');
insert into atores values ('Bob Gunton');
insert into atores values ('William Sadler');
insert into R11 values ('Tim Robbins', 'https://streamingfilmes.pt/TheShawshankRedemption');
insert into R11 values ('Morgan Freeman', 'https://streamingfilmes.pt/TheShawshankRedemption');
insert into R11 values ('Bob Gunton', 'https://streamingfilmes.pt/TheShawshankRedemption');
insert into R11 values ('William Sadler', 'https://streamingfilmes.pt/TheShawshankRedemption');

insert into diretores values ('Frank Darabont');
insert into R10 values ('Frank Darabont', 'https://streamingfilmes.pt/TheShawshankRedemption');





--TheGodfather

insert into filmes values ('https://streamingfilmes.pt/TheGodfather', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', 'C:\Users\Administrator\StreamingFilmes\TheGodfatherTrilogy\TheGodfather', 'California, United States of America', to_date('24101972','DDMMYYYY'));

--insert into R9 values ('Best Picture', 'https://streamingfilmes.pt/TheGodfather');
--insert into R9 values ('Best Actor', 'https://streamingfilmes.pt/TheGodfather');
--insert into R9 values ('Best Writing', 'https://streamingfilmes.pt/TheGodfather');

insert into R5 values ('https://streamingfilmes.pt/TheGodfather', 'Inglês');

insert into generos values ('Crime');
insert into R4 values ('https://streamingfilmes.pt/TheGodfather', unnest(array['Crime', 'Drama']));

insert into idiomas values ('Italiano');
insert into idiomas values ('Latim');
insert into R3 values ('https://streamingfilmes.pt/TheGodfather', unnest(array['Inglês', 'Italiano', 'Latim']));

insert into elenco values ('Marlon Brando', 'Americana', to_date('03041924','DDMMYYYY'));
insert into elenco values ('Al Pacino', 'Americana', to_date('25041940','DDMMYYYY'));
insert into elenco values ('James Caan', 'Americana', to_date('26031940','DDMMYYYY'));
insert into elenco values ('Richard S. Castellano', 'Americana', to_date('04091933','DDMMYYYY'));
insert into elenco values ('Francis Ford Coppola', 'Americana', to_date('07041939','DDMMYYYY'));
insert into atores values ('Marlon Brando');
insert into atores values ('Al Pacino');
insert into atores values ('James Caan');
insert into atores values ('Richard S. Castellano');
insert into R11 values ('Marlon Brando', 'https://streamingfilmes.pt/TheGodfather');
insert into R11 values ('Al Pacino', 'https://streamingfilmes.pt/TheGodfather');
insert into R11 values ('James Caan', 'https://streamingfilmes.pt/TheGodfather');
insert into R11 values ('Richard S. Castellano', 'https://streamingfilmes.pt/TheGodfather');

insert into diretores values ('Francis Ford Coppola');
insert into R10 values ('Francis Ford Coppola', 'https://streamingfilmes.pt/TheGodfather');





--TheGodfatherII

insert into filmes values ('https://streamingfilmes.pt/TheGodfatherII', 'The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.', 'C:\Users\Administrator\StreamingFilmes\TheGodfatherTrilogy\TheGodfatherII', 'California, United States of America', to_date('14101977','DDMMYYYY'));


insert into premios values ('Best Art Direction', '1975');
--insert into R9 values ('Best Picture', 'https://streamingfilmes.pt/TheGodfatherII');
--insert into R9 values ('Best Actor', 'https://streamingfilmes.pt/TheGodfatherII');
--insert into R9 values ('Best Director', 'https://streamingfilmes.pt/TheGodfatherII');
--insert into R9 values ('Best Writing', 'https://streamingfilmes.pt/TheGodfatherII');
--insert into R9 values ('Best Art Direction', 'https://streamingfilmes.pt/TheGodfatherII');
--insert into R9 values ('Best Music', 'https://streamingfilmes.pt/TheGodfatherII');

insert into R5 values ('https://streamingfilmes.pt/TheGodfatherII', 'Inglês');

insert into R4 values ('https://streamingfilmes.pt/TheGodfatherII', unnest(array['Crime', 'Drama']));

insert into idiomas values ('Espanhol');
insert into idiomas values ('Siciliano');
insert into R3 values ('https://streamingfilmes.pt/TheGodfatherII', unnest(array['Inglês', 'Italiano', 'Latim', 'Espanhol', 'Siciliano']));

insert into elenco values ('Robert Duvall', 'Americana', to_date('05011931','DDMMYYYY'));
insert into elenco values ('Diane Keaton', 'Americana', to_date('05011946','DDMMYYYY'));
insert into elenco values ('Robert De Niro', 'Americana', to_date('17081943','DDMMYYYY'));
insert into atores values ('Robert Duvall');
insert into atores values ('Diane Keaton');
insert into atores values ('Robert De Niro');
insert into R11 values ('Al Pacino', 'https://streamingfilmes.pt/TheGodfatherII');
insert into R11 values ('Robert Duvall', 'https://streamingfilmes.pt/TheGodfatherII');
insert into R11 values ('Diane Keaton', 'https://streamingfilmes.pt/TheGodfatherII');
insert into R11 values ('Robert De Niro', 'https://streamingfilmes.pt/TheGodfatherII');

insert into R10 values ('Francis Ford Coppola', 'https://streamingfilmes.pt/TheGodfatherII');





--BatmanCavaleiroDasTrevas

insert into filmes values ('https://streamingfilmes.pt/BatmanCavaleiroDasTrevas', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', 'C:\Users\Administrator\StreamingFilmes\Batman\BatmanCavaleiroDasTrevas', 'United States of America. United Kingdom', to_date('24072008','DDMMYYYY'));


insert into premios values ('Best Actor Performance', '2009');
insert into premios values ('Best Achievement in Sound Editing', '2009');
insert into R9 values ('Best Actor Performance', 'https://streamingfilmes.pt/BatmanCavaleiroDasTrevas');
insert into R9 values ('Best Achievement in Sound Editing', 'https://streamingfilmes.pt/BatmanCavaleiroDasTrevas');


insert into R5 values ('https://streamingfilmes.pt/BatmanCavaleiroDasTrevas', 'Português');

insert into generos values ('Ação');
insert into R4 values ('https://streamingfilmes.pt/BatmanCavaleiroDasTrevas', unnest(array['Ação', 'Crime', 'Drama']));

insert into idiomas values ('Mandarim');
insert into R3 values ('https://streamingfilmes.pt/BatmanCavaleiroDasTrevas', unnest(array['Inglês', 'Mandarim']));

insert into elenco values ('Christian Bale', 'Britânica', to_date('30011974','DDMMYYYY'));
insert into elenco values ('Heath Ledger', 'Australiana', to_date('04041979','DDMMYYYY'));
insert into elenco values ('Aaron Eckhart', 'Americana', to_date('12031968','DDMMYYYY'));
insert into elenco values ('Michael Caine', 'Britânica', to_date('14031933','DDMMYYYY'));
insert into elenco values ('Christopher Nolan', 'Britânica', to_date('30071970','DDMMYYYY'));
insert into atores values ('Christian Bale');
insert into atores values ('Heath Ledger');
insert into atores values ('Aaron Eckhart');
insert into atores values ('Michael Caine');
insert into R11 values ('Christian Bale', 'https://streamingfilmes.pt/BatmanCavaleiroDasTrevas');
insert into R11 values ('Heath Ledger', 'https://streamingfilmes.pt/BatmanCavaleiroDasTrevas');
insert into R11 values ('Aaron Eckhart', 'https://streamingfilmes.pt/BatmanCavaleiroDasTrevas');
insert into R11 values ('Michael Caine', 'https://streamingfilmes.pt/BatmanCavaleiroDasTrevas');

insert into diretores values ('Christopher Nolan');
insert into R10 values ('Christopher Nolan', 'https://streamingfilmes.pt/BatmanCavaleiroDasTrevas');





--12AngryMen

insert into filmes values ('https://streamingfilmes.pt/12AngryMen', 'A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.', 'C:\Users\Administrator\StreamingFilmes\12AngryMen', 'United States of America', to_date('28101959','DDMMYYYY'));

insert into R5 values ('https://streamingfilmes.pt/12AngryMen', 'Inglês');

insert into R4 values ('https://streamingfilmes.pt/12AngryMen', 'Drama');

insert into R3 values ('https://streamingfilmes.pt/12AngryMen', 'Inglês');

insert into elenco values ('Martin Balsam', 'Americana', to_date('04111919','DDMMYYYY'));
insert into elenco values ('John Fiedler', 'Americana', to_date('03021925','DDMMYYYY'));
insert into elenco values ('Lee J. Cobb', 'Americana', to_date('08121911','DDMMYYYY'));
insert into elenco values ('E. G. Marshall', 'Americana', to_date('18061914','DDMMYYYY'));
insert into elenco values ('Sidney Lumet', 'Americana', to_date('25061924','DDMMYYYY'));
insert into atores values ('Martin Balsam');
insert into atores values ('John Fiedler');
insert into atores values ('Lee J. Cobb');
insert into atores values ('E. G. Marshall');
insert into R11 values ('Martin Balsam', 'https://streamingfilmes.pt/12AngryMen');
insert into R11 values ('John Fiedler', 'https://streamingfilmes.pt/12AngryMen');
insert into R11 values ('Lee J. Cobb', 'https://streamingfilmes.pt/12AngryMen');
insert into R11 values ('E. G. Marshall', 'https://streamingfilmes.pt/12AngryMen');

insert into diretores values ('Sidney Lumet');
insert into R10 values ('Sidney Lumet', 'https://streamingfilmes.pt/12AngryMen');





--SchindlersList

insert into filmes values ('https://streamingfilmes.pt/SchindlersList', 'In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.', 'C:\Users\Administrator\StreamingFilmes\SchindlersList', 'Poland', to_date('04031994','DDMMYYYY'));

insert into R5 values ('https://streamingfilmes.pt/SchindlersList', 'Inglês');

insert into generos values ('Biografia');
insert into generos values ('História');
insert into R4 values ('https://streamingfilmes.pt/SchindlersList', unnest(array['Biografia', 'Drama', 'História']));

insert into idiomas values ('Hebraico');
insert into idiomas values ('Alemão');
insert into idiomas values ('Polaco');
insert into R3 values ('https://streamingfilmes.pt/SchindlersList', unnest(array['Inglês', 'Hebraico', 'Alemão', 'Polaco']));

insert into elenco values ('Liam Neeson', 'Britânica', to_date('04111919','DDMMYYYY'));
insert into elenco values ('Ben Kingsley', 'Britânica', to_date('31121943','DDMMYYYY'));
insert into elenco values ('Ralph Fiennes', 'Britânica', to_date('22121962','DDMMYYYY'));
insert into elenco values ('Caroline Goodall', 'Britânica', to_date('13111959','DDMMYYYY'));
insert into elenco values ('Steven Spielberg', 'Americana', to_date('18121946','DDMMYYYY'));
insert into atores values ('Liam Neeson');
insert into atores values ('Ben Kingsley');
insert into atores values ('Ralph Fiennes');
insert into atores values ('Caroline Goodall');
insert into R11 values ('Liam Neeson', 'https://streamingfilmes.pt/SchindlersList');
insert into R11 values ('Ben Kingsley', 'https://streamingfilmes.pt/SchindlersList');
insert into R11 values ('Ralph Fiennes', 'https://streamingfilmes.pt/SchindlersList');
insert into R11 values ('Caroline Goodall', 'https://streamingfilmes.pt/SchindlersList');

insert into diretores values ('Steven Spielberg');
insert into R10 values ('Steven Spielberg', 'https://streamingfilmes.pt/SchindlersList');





--PulpFiction

insert into filmes values ('https://streamingfilmes.pt/PulpFiction', 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', 'C:\Users\Administrator\StreamingFilmes\PulpFiction', 'United States of America', to_date('04021997','DDMMYYYY'));

insert into R5 values ('https://streamingfilmes.pt/PulpFiction', 'Inglês');

insert into R4 values ('https://streamingfilmes.pt/PulpFiction', unnest(array['Crime', 'Drama']));

insert into idiomas values ('Francês');
insert into R3 values ('https://streamingfilmes.pt/PulpFiction', unnest(array['Inglês', 'Espanhol', 'Francês']));

insert into elenco values ('Tim Roth', 'Britânica', to_date('14051961','DDMMYYYY'));
insert into elenco values ('Amanda Plummer', 'Americana', to_date('23031957','DDMMYYYY'));
insert into elenco values ('John Travolta', 'Americana', to_date('18021954','DDMMYYYY'));
insert into elenco values ('Samuel L. Jackson', 'Americana', to_date('21121948','DDMMYYYY'));
insert into elenco values ('Quentin Tarantino', 'Americana', to_date('27031963','DDMMYYYY'));
insert into atores values ('Tim Roth');
insert into atores values ('Amanda Plummer');
insert into atores values ('John Travolta');
insert into atores values ('Samuel L. Jackson');
insert into R11 values ('Tim Roth', 'https://streamingfilmes.pt/PulpFiction');
insert into R11 values ('Amanda Plummer', 'https://streamingfilmes.pt/PulpFiction');
insert into R11 values ('John Travolta', 'https://streamingfilmes.pt/PulpFiction');
insert into R11 values ('Samuel L. Jackson', 'https://streamingfilmes.pt/PulpFiction');

insert into diretores values ('Quentin Tarantino');
insert into R10 values ('Quentin Tarantino', 'https://streamingfilmes.pt/PulpFiction');





--FightClub

insert into filmes values ('https://streamingfilmes.pt/FightClub', 'An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.', 'C:\Users\Administrator\StreamingFilmes\FightClub', 'United States of America. Germany', to_date('12111999','DDMMYYYY'));

insert into R5 values ('https://streamingfilmes.pt/FightClub', 'Inglês');

insert into R4 values ('https://streamingfilmes.pt/FightClub', 'Drama');

insert into R3 values ('https://streamingfilmes.pt/FightClub', 'Inglês');

insert into elenco values ('Edward Norton', 'Americana', to_date('18081969','DDMMYYYY'));
insert into elenco values ('Brad Pitt', 'Americana', to_date('18121963','DDMMYYYY'));
insert into elenco values ('Meat Loaf', 'Americana', to_date('27091947','DDMMYYYY'));
insert into elenco values ('Zach Grenier', 'Americana', to_date('12021954','DDMMYYYY'));
insert into elenco values ('David Fincher', 'Americana', to_date('28081962','DDMMYYYY'));
insert into atores values ('Edward Norton');
insert into atores values ('Brad Pitt');
insert into atores values ('Meat Loaf');
insert into atores values ('Zach Grenier');
insert into R11 values ('Edward Norton', 'https://streamingfilmes.pt/FightClub');
insert into R11 values ('Brad Pitt', 'https://streamingfilmes.pt/FightClub');
insert into R11 values ('Meat Loaf', 'https://streamingfilmes.pt/FightClub');
insert into R11 values ('Zach Grenier', 'https://streamingfilmes.pt/FightClub');

insert into diretores values ('David Fincher');
insert into R10 values ('David Fincher', 'https://streamingfilmes.pt/FightClub');





--AOrigem

insert into filmes values ('https://streamingfilmes.pt/AOrigem', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O..', 'C:\Users\Administrator\StreamingFilmes\AOrigem', 'United States of America. United Kingdom', to_date('22072010','DDMMYYYY'));

insert into R5 values ('https://streamingfilmes.pt/AOrigem', 'Português');

insert into generos values ('Aventura');
insert into generos values ('Ficção Científica');
insert into R4 values ('https://streamingfilmes.pt/AOrigem', unnest(array['Ação', 'Aventura', 'Ficção Científica']));

insert into idiomas values ('Japonês');
insert into R3 values ('https://streamingfilmes.pt/AOrigem', unnest(array['Inglês', 'Francês', 'Japonês']));

insert into elenco values ('Leonardo DiCaprio', 'Americana', to_date('11111974','DDMMYYYY'));
insert into elenco values ('Joseph Gordon-Levitt', 'Americana', to_date('17021981','DDMMYYYY'));
insert into elenco values ('Ellen Page', 'Canadense', to_date('21021987','DDMMYYYY'));
insert into elenco values ('Tom Hardy', 'Britânica', to_date('15091977','DDMMYYYY'));
insert into atores values ('Leonardo DiCaprio');
insert into atores values ('Joseph Gordon-Levitt');
insert into atores values ('Ellen Page');
insert into atores values ('Tom Hardy');
insert into R11 values ('Leonardo DiCaprio', 'https://streamingfilmes.pt/AOrigem');
insert into R11 values ('Joseph Gordon-Levitt', 'https://streamingfilmes.pt/AOrigem');
insert into R11 values ('Ellen Page', 'https://streamingfilmes.pt/AOrigem');
insert into R11 values ('Tom Hardy', 'https://streamingfilmes.pt/AOrigem');

insert into R10 values ('Christopher Nolan', 'https://streamingfilmes.pt/AOrigem');





--TheFastAndTheFuriousTokyoDrift

insert into filmes values ('https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift', 'A teenager becomes a major competitor in the world of drift racing after moving in with his father in Tokyo to avoid a jail sentence in America.', 'C:\Users\Administrator\StreamingFilmes\FastAndFurious\TheFastAndTheFuriousTokyoDrift', 'Japan', to_date('22062006','DDMMYYYY'));

insert into R5 values ('https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift', 'Inglês');

insert into R4 values ('https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift', unnest(array['Ação', 'Crime']));

insert into idiomas values ('Português');
insert into R3 values ('https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift', unnest(array['Inglês', 'Japonês', 'Português']));

insert into elenco values ('Lucas Black', 'Americana', to_date('29111982','DDMMYYYY'));
insert into elenco values ('Nikki Griffin', 'Americana', to_date('16041978','DDMMYYYY'));
insert into elenco values ('Vin Diesel', 'Americana', to_date('18071967','DDMMYYYY'));
insert into elenco values ('Brian Tee', 'Japonesa', to_date('15031977','DDMMYYYY'));
insert into elenco values ('Nathalie Kelley', 'Peruana', to_date('03031985','DDMMYYYY'));
insert into elenco values ('Brandon Brendel', 'Americana', to_date('05041981','DDMMYYYY'));
insert into elenco values ('Justin Lin', 'Taiwan-Americana', to_date('27031963','DDMMYYYY'));
insert into atores values ('Lucas Black');
insert into atores values ('Nikki Griffin');
insert into atores values ('Vin Diesel');
insert into atores values ('Brian Tee');
insert into atores values ('Nathalie Kelley');
insert into atores values ('Brandon Brendel');
insert into R11 values ('Lucas Black', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift');
insert into R11 values ('Nikki Griffin', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift');
insert into R11 values ('Vin Diesel', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift');
insert into R11 values ('Brian Tee', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift');
insert into R11 values ('Nathalie Kelley', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift');
insert into R11 values ('Brandon Brendel', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift');

insert into diretores values ('Justin Lin');
insert into R10 values ('Justin Lin', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift');









--INSERT CLIENTES

insert into clientes values ('ricardoliveira0', 'Ricardo Oliveira', 'Rua General Humberto Delgado', 'gostodefilmes123', to_date('080817','DDMMYY'), '123456');
insert into pagamentos values ('ricardoliveira0', '001', '3', to_date('120817', 'DDMMYY'));
insert into pagamentos values ('ricardoliveira0', '002', '3', to_date('120917', 'DDMMYY'));
insert into pagamentos values ('ricardoliveira0', '003', '3', to_date('121017', 'DDMMYY'));
insert into pagamentos values ('ricardoliveira0', '004', '3', to_date('141119', 'DDMMYY'));
insert into R2 values ('ricardoliveira0', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift', to_date('130817', 'DDMMYY'));
insert into R2 values ('ricardoliveira0', 'https://streamingfilmes.pt/PulpFiction', to_date('160817', 'DDMMYY'));
insert into R2 values ('ricardoliveira0', 'https://streamingfilmes.pt/AOrigem', to_date('140917', 'DDMMYY'));
insert into R2 values ('ricardoliveira0', 'https://streamingfilmes.pt/TheGodfather', to_date('191017', 'DDMMYY'));
insert into R2 values ('ricardoliveira0', 'https://streamingfilmes.pt/TheGodfatherII', to_date('021117', 'DDMMYY'));
insert into R2 values ('ricardoliveira0', 'https://streamingfilmes.pt/FightClub', to_date('231217', 'DDMMYY'));


insert into clientes values ('vasco_b', 'Vasco Barnabé', 'Rua de Mora', 'rapeiocabelonaqueima18', to_date('101018','DDMMYY'), '123457');
insert into pagamentos values ('vasco_b', '010', '3', to_date('101018', 'DDMMYY'));
insert into pagamentos values ('vasco_b', '011', '3', to_date('121118', 'DDMMYY'));
insert into pagamentos values ('vasco_b', '012', '3', to_date('131218', 'DDMMYY'));
insert into pagamentos values ('vasco_b', '013', '3', to_date('280119', 'DDMMYY'));
insert into pagamentos values ('vasco_b', '014', '3', to_date('051119', 'DDMMYY'));
insert into R2 values ('vasco_b', 'https://streamingfilmes.pt/TheFastAndTheFuriousTokyoDrift', to_date('101018', 'DDMMYY'));
insert into R2 values ('vasco_b', 'https://streamingfilmes.pt/SchindlersList', to_date('081018', 'DDMMYY'));
insert into R2 values ('vasco_b', 'https://streamingfilmes.pt/12AngryMen', to_date('151218', 'DDMMYY'));
insert into R2 values ('vasco_b', 'https://streamingfilmes.pt/BatmanCavaleiroDasTrevas', to_date('021219', 'DDMMY'));



----10

--a)
select url
from R4
where genero like 'Thriler'
intersect
select url
from R4
where genero like 'Mistério'


--b)



--c)
select distinct R3.url
from R3 natural inner join R11 natural inner join elenco
where R3.idioma like 'Inglês' and elenco.Nacionalidade like 'Francesa'


--d)
select Sum(valor) as totalCobrado 
from pagamentos
where dataPagamento like '2019-11-%'
group by valor


--e)
select url
from R2
where dataVisto like '2019-12-02'


--f)
select url
from R2
where login like 'ricardoliveira0'


--g)
select nomeElenco
from R10
except
select nomeElenco
from R10 natural inner join R4
where genero like 'Fantasia'