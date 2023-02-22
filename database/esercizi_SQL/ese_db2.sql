-- si crea il database db2
CREATE DATABASE db2;

-- si crea la tabella Artisti
CREATE TABLE db2.Artisti(
id_artista int not null auto_increment,
nome varchar(30) not null,
genere varchar(20) not null,
nazionalità varchar(20) not null,
note varchar(30),
PRIMARY KEY(id_artista)
);

-- si crea la tabella Registrazioni
CREATE TABLE db2.Registrazioni(
id_registrazione int not null auto_increment,
titolo varchar(30) not null,
etichetta varchar(10) not null,
data_registrazione date not null,
numero_brani int not null,
durata_totale int not null,
PRIMARY KEY(id_registrazione)
);

-- si crea la tabella Brani
CREATE TABLE db2.Brani(
id_brano int not null auto_increment,
titolo varchar(30) not null,
durata decimal not null,
posizione int not null,
id_artista int not null,
id_registrazione int not null,
PRIMARY KEY(id_brano),
FOREIGN KEY(id_artista) REFERENCES db2.Artisti(id_artista),
FOREIGN KEY(id_registrazione) REFERENCES db2.Registrazioni(id_registrazione)
);

-- nomi degli artisti spagnoli
SELECT nome
FROM db2.Artisti
WHERE Artisti.nazionalità = 'spagna';

-- titoli e durata delle canzoni di Mina
SELECT titolo, durata
FROM db2.Artisti, db2.Brani
WHERE Artisti.nome = 'Mina' AND Artisti.id_artista = Brani.id_artista;

-- titolo, durata e posizione dei brani dell’album ‘The dark side of the moon'
SELECT Brani.titolo, durata, posizione
FROM db2.Registrazioni, db2.Brani
WHERE Registrazioni.titolo = 'The dark side of the moon' AND Registrazioni.id_registrazione = Brani.id_registrazione;

-- la durata totale degli album di Eminem
SELECT durata_totale
FROM db2.Registrazioni, db2.Brani, db2.Artisti
WHERE Registrazioni.id_registrazione = Brani.id_registrazione AND Brani.id_artista = Artisti.id_artista AND Artisti.nome = 'Eminem';

-- titolo del terzo brano dell’album Animals dei Pink Floyd
SELECT Brani.titolo
FROM db2.Registrazioni, db2.Brani
WHERE Registrazioni.titolo = 'Animals' AND Registrazioni.id_registrazione = Brani.id_registrazione LIMIT 2, 1;

-- nome degli artisti che lavorano con Virgin
SELECT DISTINCT Artisti.nome
FROM db2.Artisti, db2.Brani, db2.Registrazioni
WHERE Artisti.id_artista = Brani.id_artista AND Brani.id_registrazione = Registrazioni.id_registrazione AND Registrazioni.etichetta = 'Virgin';

-- numero dei brani di ogni album di David Bowie
SELECT numero_brani
FROM db2.Artisti, db2.Registrazioni, db2.Brani
WHERE Artisti.nome = 'David Bowie' AND Artisti.id_artista = Brani.id_artista AND Brani.id_registrazione = Registrazioni.id_registrazione;

-- genere musicale di Beyoncé e titoli dei suoi album
SELECT genere, Registrazioni.titolo
FROM db2.Artisti, db2.Brani, db2.Registrazioni
WHERE Artisti.nome = 'Beyoncé' AND Artisti.id_artista = Brani.id_artista AND Brani.id_registrazione = Registrazioni.id_registrazione;

