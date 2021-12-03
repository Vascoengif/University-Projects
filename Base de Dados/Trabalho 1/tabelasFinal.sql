drop table motorista cascade; -- Apaga a tabela, caso esta já esteja criada no pgAdmin4
create table motorista (
    Nome varchar(20),
    NCartaCond varchar(20),
    DataNasc text,
    Nbi char(4) primary key
);

insert into motorista values ('Manuel Duarte','L-123',to_date('140176','DDMMYY'),'1234');
insert into motorista values ('Fernando Nobre','L-124',to_date('140177','DDMMYY'),'1235');
insert into motorista values ('Anibal Silva','L-125',to_date('140178','DDMMYY'),'1236');
insert into motorista values ('Francisco Lopes','L-126',to_date('140179','DDMMYY'),'1237');





drop table telefone cascade; -- Apaga a tabela, caso esta já esteja criada no pgAdmin4
create table telefone (
    Nbi char(4),
    Telefone char(10),
    primary key (Nbi, Telefone),
    foreign key (Nbi) references motorista on delete restrict
);

--Manuel Duarte
insert into telefone values ('1234', unnest(array['266 262626', '939393939']));  -- 'unnest(array[1,...,n])' coloca vários valores de 'Telefone', distribuídos por linhas, para um mesmo valor em 'Nbi'

--Fernando Nobre
insert into telefone values ('1235', unnest(array['266 262627', '939393940']));

--Anibal Silva
insert into telefone values ('1236', unnest(array['266 262628', '939393941']));

--Francisco Lopes
insert into telefone values ('1237','266 262629');





drop table modelo cascade; -- Apaga a tabela, caso esta já esteja criada no pgAdmin4
create table modelo (
    Marca varchar(10),
    Modelo varchar(22) primary key,
    Nlugares Integer,
    Consumo varchar(4)
);

insert into modelo values ('Renault','Espace','7','7l');
insert into modelo values ('Mercedes','CLK','7','9l');
insert into modelo values ('Honda','Civic','5','5l');
insert into modelo values ('Mercedes','classe S','5','6.5l');





drop table taxi cascade; -- Apaga a tabela, caso esta já esteja criada no pgAdmin4
create table taxi (
    Modelo varchar(22),
    Ano char(4),
    Kms Integer,
    Matricula char(8) primary key,
    foreign key (Modelo) references modelo on delete restrict
);

insert into taxi values ('Espace', '2015', '123098', '22-AA-22');
insert into taxi values ('CLK', '2014', '234554', '21-AA-22');
insert into taxi values ('Civic', '2012', '89764', '20-AA-22');
insert into taxi values ('classe S', '2015', '79744', '19-AA-22');





drop table servico cascade; -- Apaga a tabela, caso esta já esteja criada no pgAdmin4
create table servico ( 
    DataInicio text,
    DataFim text,
    Kms Integer,
    Valor decimal,
    Matricula char(8),
    CoordGPSInic real,
    CoordGPSﬁn real,
    primary key (Matricula, DataInicio),
    foreign key (Matricula) references taxi on delete restrict
);

insert into servico values (to_timestamp('02-01-2016 08:12:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('02-01-2016 08:32:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'12','5.25','19-AA-22','0.75','0.76');
insert into servico values (to_timestamp('02-01-2016 08:43:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('02-01-2016 08:52:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'7','3.25','19-AA-22','0.77','0.78');
insert into servico values (to_timestamp('02-01-2016 08:53:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('02-01-2016 09:59:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'98','53.25','19-AA-22','0.78','0.84');
insert into servico values (to_timestamp('02-01-2016 10:13:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('02-01-2016 10:29:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'18','19.25','19-AA-22','0.84','0.85');
insert into servico values (to_timestamp('02-01-2016 11:10:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('02-01-2016 11:39:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'23','22.25','19-AA-22','0.86','0.88');
insert into servico values (to_timestamp('02-01-2016 12:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('02-01-2016 13:39:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'21','42.25','19-AA-22','0.88','0.90');
insert into servico values (to_timestamp('02-01-2016 15:20:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('02-01-2016 15:39:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'9','12.25','19-AA-22','0.93','0.94');
--
insert into servico values (to_timestamp('02-01-2016 08:20:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('02-01-2016 15:33:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'202','127.95','20-AA-22','0.76','0.98');
----------------------------------------------------
insert into servico values (to_timestamp('03-01-2016 08:02:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('03-01-2016 8:33:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'13','5.10','21-AA-22','0.76','0.77');
insert into servico values (to_timestamp('03-01-2016 08:35:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('03-01-2016 8:43:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'5','3.00','21-AA-22','0.77','0.78');
insert into servico values (to_timestamp('03-01-2016 08:59:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('03-01-2016 9:18:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'12','5.15','21-AA-22','0.78','0.79');
insert into servico values (to_timestamp('03-01-2016 09:50:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('03-01-2016 10:31:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'28','29.10','21-AA-22','0.79','0.82');
--
insert into servico values (to_timestamp('04-01-2016 08:22:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('04-01-2016 08:43:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'14','6.35','20-AA-22','0.74','0.76');
insert into servico values (to_timestamp('04-01-2016 08:58:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('04-01-2016 09:42:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'35','18.05','20-AA-22','0.77','0.80');
insert into servico values (to_timestamp('04-01-2016 10:30:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('04-01-2016 11:39:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'102','54.00','20-AA-22','0.80','0.88');
insert into servico values (to_timestamp('04-01-2016 12:45:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('04-01-2016 13:04:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'12','6.00','20-AA-22','0.88','0.90');
insert into servico values (to_timestamp('04-01-2016 15:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('04-01-2016 15:39:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'20','12.25','20-AA-22','0.90','0.92');
insert into servico values (to_timestamp('04-01-2016 15:50:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('04-01-2016 16:12:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'24','13.85','20-AA-22','0.92','0.94');
--
insert into servico values (to_timestamp('05-01-2016 08:20:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('05-01-2016 08:43:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'16','7.35','21-AA-22','0.75','0.76');
insert into servico values (to_timestamp('05-01-2016 08:58:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('05-01-2016 09:47:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'33','15.05','21-AA-22','0.76','0.80');
insert into servico values (to_timestamp('05-01-2016 10:33:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('05-01-2016 11:30:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'90','49.50','21-AA-22','0.80','0.86');
insert into servico values (to_timestamp('05-01-2016 12:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('05-01-2016 12:18:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'10','6.00','21-AA-22','0.86','0.87');
insert into servico values (to_timestamp('05-01-2016 16:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('05-01-2016 16:41:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'21','12.95','21-AA-22','0.89','0.90');
insert into servico values (to_timestamp('05-01-2016 16:42:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('05-01-2016 16:59:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'28','14.05','21-AA-22','0.91','0.92');
--
insert into servico values (to_timestamp('06-01-2016 08:21:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('06-01-2016 08:45:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'15','6.55','22-AA-22','0.74','0.76');
insert into servico values (to_timestamp('06-01-2016 08:59:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('06-01-2016 09:43:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'33','17.75','22-AA-22','0.77','0.80');
insert into servico values (to_timestamp('06-01-2016 10:32:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('06-01-2016 11:38:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'99','53.70','22-AA-22','0.80','0.88');
insert into servico values (to_timestamp('06-01-2016 12:44:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('06-01-2016 13:02:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'11','6.10','22-AA-22','0.88','0.90');
insert into servico values (to_timestamp('06-01-2016 15:03:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,to_timestamp('06-01-2016 15:44:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone,'23','10.05','22-AA-22','0.90','0.92');





drop table turno cascade; -- Apaga a tabela, caso esta já esteja criada no pgAdmin4
create table turno (
    DataInicio text,
    DataFim text,
    KmInicio Integer,
    KmFim Integer,
    Matricula char(8),
    Nbi char(4),
    primary key (Matricula, Nbi),
    foreign key (Matricula) references taxi on delete restrict,
    foreign key (Nbi) references motorista on delete restrict
);

insert into turno values (to_timestamp('02-01-2016 08:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, to_timestamp('02-01-2016 17:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, '79744', '79944', '19-AA-22', '1234');
insert into turno values (to_timestamp('02-01-2016 08:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, to_timestamp('02-01-2016 17:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, '89764', '89964', '20-AA-22', '1235');
insert into turno values (to_timestamp('03-01-2016 08:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, to_timestamp('03-01-2016 17:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, '234554', '234954', '21-AA-22', '1236');
insert into turno values (to_timestamp('03-01-2016 08:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, to_timestamp('03-01-2016 17:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, '123098', '123498', '22-AA-22', '1237');
--
insert into turno values (to_timestamp('04-01-2016 08:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, to_timestamp('04-01-2016 17:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, '89964','90212', '20-AA-22', '1234');
insert into turno values (to_timestamp('05-01-2016 08:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, to_timestamp('05-01-2016 17:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, '234954', '235177', '21-AA-22', '1234');
insert into turno values (to_timestamp('06-01-2016 08:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, to_timestamp('06-01-2016 17:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, '123498', '123703', '22-AA-22', '1234');




drop table cliente cascade; -- Apaga a tabela, caso esta já esteja criada no pgAdmin4
create table cliente (
    Nome varchar(20),
    Morada text,
    CodigoPostal text,
    Nif char(12),
    primary key (Nif)
);


insert into cliente values ('José Silva','Rua Antonio Silva 23','7100-434 Évora','600700800900');
insert into cliente values ('Francisco Passos', 'Rua Manuel Passos 12','7000-131 Évora','600700800901');
insert into cliente values ('Pedro Sousa','Rua Joaquim Sousa 21','7500-313 Évora','600700800902');





drop table pedido cascade; -- Apaga a tabela, caso esta já esteja criada no pgAdmin4
create table pedido (
    Nif char(12),
    MoradaInicio varchar(20),
    CodigoPostalInicio text,
    DataPedido text,
    Matricula char(8),
    DataInicio text,
    primary key (Nif, Matricula),
    foreign key (Nif) references cliente on delete restrict,
    foreign key (Matricula) references taxi on delete restrict
);

insert into pedido values ('600700800900', 'Rua Silva Pais 33', '7120-212 Évora', to_timestamp('02-01-2016 09:00:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone, '19-AA-22', to_timestamp('02-01-2016 08:43:00','DD-MM-YYYY HH24:MI:SS')::timestamp without time zone);
