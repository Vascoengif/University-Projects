DROP TABLE IF EXISTS events, "Corrida Rosa", "Corrida Azul", "Corrida Verde" CASCADE; 

CREATE TABLE events(
	event VARCHAR(50),
	type VARCHAR(20),
	date VARCHAR(15)
);

CREATE TABLE "Corrida Rosa" (
	dorsal SERIAL,
	nome VARCHAR(30),
	genero VARCHAR(10),
	escalao VARCHAR(15),
	tempodeprova TIME(3)
);

CREATE TABLE "Corrida Azul" (
	dorsal SERIAL,
	nome VARCHAR(30),
	genero VARCHAR(10),
	escalao VARCHAR(15),
	tempodeprova TIME(3)
);

CREATE TABLE "Corrida Verde" (
	dorsal SERIAL,
	nome VARCHAR(30),
	genero VARCHAR(10),
	escalao VARCHAR(15),
	tempodeprova TIME(3)
);


INSERT INTO events (event, type, date) VALUES ('Corrida Rosa', 'Atletismo de Pista', '2020-05-05');
INSERT INTO events (event, type, date) VALUES ('Corrida Azul', 'Corrida de Estrada','2020-05-05');
INSERT INTO events (event, type, date) VALUES ('Corrida Verde', 'Trail','2021-04-07');

INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Vasco', 'Masculino', 'Sénior', '00:50:12.14');
INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Miguel', 'Masculino', 'Júnior', '00:55:18.14');
INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Leonardo', 'Masculino', 'Sénior', '01:10:12.1');
INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Ricardo', 'Masculino', 'Sénior', '00:50:12.254');
INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Margarida', 'Feminino', 'Júnior', '00:40:12.14');
INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Marta', 'Feminino', 'Júnior', '00:44:12.14');
INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Sara', 'Feminino', 'Júnior', '00:51:22.14');
INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Matilde', 'Feminino', 'Júnior', '00:50:12.14');
INSERT INTO "Corrida Rosa" (nome, genero, escalao, tempodeprova) VALUES ('Beatriz', 'Feminino', 'Veterano 50', '00:30:12.14');

INSERT INTO "Corrida Azul" (nome, genero, escalao, tempodeprova) VALUES ('Mariana', 'Feminino', 'Veterano 35', '01:40:12.14');
INSERT INTO "Corrida Azul" (nome, genero, escalao, tempodeprova) VALUES ('Ana', 'Feminino', 'Veterano 35', '02:30:12.14');
INSERT INTO "Corrida Azul" (nome, genero, escalao, tempodeprova) VALUES ('Letícia', 'Feminino', 'Veterano 35', '01:30:12.14');
INSERT INTO "Corrida Azul" (nome, genero, escalao, tempodeprova) VALUES ('Maria', 'Feminino', 'Veterano 35', '03:30:12.14');
INSERT INTO "Corrida Azul" (nome, genero, escalao, tempodeprova) VALUES ('Cláudia', 'Feminino', 'Veterano 35', '10:30:12.14');
INSERT INTO "Corrida Azul" (nome, genero, escalao, tempodeprova) VALUES ('Susana', 'Feminino', 'Veterano 35', '01:10:12.14');

INSERT INTO "Corrida Verde" (nome, genero, escalao, tempodeprova) VALUES ('Gonçalo', 'Masculino', 'Veterano 45', '01:15:17.14');
INSERT INTO "Corrida Verde" (nome, genero, escalao, tempodeprova) VALUES ('Marco', 'Masculino', 'Veterano 35', '07:10:12.14');
INSERT INTO "Corrida Verde" (nome, genero, escalao, tempodeprova) VALUES ('Rui', 'Masculino', 'Veterano 35', '05:10:12.14');
