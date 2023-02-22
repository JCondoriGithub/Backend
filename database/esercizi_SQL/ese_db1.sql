-- si crea il database db1
CREATE DATABASE db1;

-- si crea la tabella Tirocinante
CREATE TABLE db1.Tirocinante(
matricola int not null,
nome varchar(30) not null,
cognome varchar(30) not null,
classe varchar(5) not null,
id_azienda int not null,
id_tutor int not null,
PRIMARY KEY(matricola),
FOREIGN KEY(id_azienda) REFERENCES db1.Azienda(id_azienda),
FOREIGN KEY(id_tutor) REFERENCES db1.Tutor(id_tutor)
);

-- si crea la tabella Azienda
CREATE TABLE db1.Azienda(
id_azienda int not null,
nome varchar(30) not null,
luogo varchar(50) not null,
settore varchar(30) not null,
tipologia varchar(30) not null,
PRIMARY KEY(id_azienda)
-- FOREIGN KEY(id_azienda) REFERENCES db1.Tirocinante(id_azienda)
);

-- si crea la tabella Tutor
CREATE TABLE db1.Tutor(
id_tutor int not null,
nome varchar(30) not null,
cognome varchar(30) not null,
materia varchar(20) not null,
PRIMARY KEY(id_tutor)
);

-- nome e cognome degli studenti che svolgono il tirocinio al Leclerc
SELECT Tirocinante.nome, Tirocinante.cognome
FROM db1.Tirocinante, db1.Azienda
WHERE Tirocinante.id_azienda = Azienda.id_azienda AND Azienda.nome = 'Leclerc';

-- nome e cognome dei tirocinanti della quarta D
SELECT nome, cognome
FROM db1.Tirocinante
WHERE classe = '4D';

-- settore e tipologia delle aziende in cui ha svolto il tirocinio Pippo Boi
SELECT settore, tipologia
FROM db1.Tirocinante, db1.Azienda
WHERE Tirocinante.id_azienda = Azienda.id_azienda AND Tirocinante.nome = 'Pippo' AND Tirocinante.cognome = 'Boi';

-- nome, cognome e materie dei tutor dei tirocinanti del settore informatica
SELECT Tutor.nome, Tutor.cognome, Tutor.materia
FROM db1.Tirocinante, db1.Azienda, db1.Tutor
WHERE Tirocinante.id_azienda = Azienda.id_azienda AND Azienda.settore = 'informatica' AND Tirocinante.id_tutor = Tutor.id_tutor;

-- i luoghi dove svolgono i tirocini gli studenti seguiti dal prof. Ciaschetti
SELECT Azienda.luogo
FROM db1.Tirocinante, db1.Tutor, db1.Azienda
WHERE Tirocinante.id_tutor = Tutor.id_tutor AND Tutor.cognome = 'Ciaschetti' AND Tirocinante.id_azienda = Azienda.id_azienda;

-- nome e cognome dei tirocinanti seguiti dal prof. Salvatorico Ledda
SELECT Tirocinante.nome, Tirocinante.cognome
FROM db1.Tutor, db1.Tirocinante
WHERE Tutor.nome = 'Salvatorico' AND Tutor.cognome = 'Ledda' AND Tutor.id_tutor = Tirocinante.id_tutor;

-- materie dei tutor dei tirocinanti di Sulcigraf
SELECT Tutor.materia
FROM db1.Azienda, db1.Tirocinante, db1.Tutor
WHERE Azienda.nome = 'Sulcigraf' AND Azienda.id_azienda = Tirocinante.id_azienda AND Tirocinante.id_tutor = Tutor.id_tutor;

-- classi che fanno il tirocinio
SELECT DISTINCT classe
FROM db1.Tirocinante, db1.Azienda
WHERE Tirocinante.id_azienda = Azienda.id_azienda;


