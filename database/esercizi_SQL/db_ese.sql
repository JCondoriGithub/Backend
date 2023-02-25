-- si crea il database db_ese
CREATE DATABASE db_ese;

-- si crea la tabella Clienti
CREATE TABLE Clienti(
id_cliente int auto_increment not null,
nome varchar(30) not null,
cognome varchar(30) not null,
data_nascita date not null,
regione_residenza varchar(20) not null,
PRIMARY KEY(id_cliente)
);

-- si crea la tabella Fornitori
CREATE TABLE Fornitori(
id_fornitore int auto_increment not null,
denominazione varchar(30) not null,
regione_residenza varchar(20) not null,
PRIMARY KEY(id_fornitore)
);

-- si crea la tabella Fatture
CREATE TABLE Fatture(
id_fattura int auto_increment not null,
tipologia varchar(20) not null,
importo decimal not null,
iva varchar(10) not null,
data_fattura date not null,
id_fornitore int not null,
id_cliente int not null,
PRIMARY KEY(id_fattura),
FOREIGN KEY(id_fornitore) REFERENCES Fornitori(id_fornitore),
FOREIGN KEY(id_cliente) REFERENCES Clienti(id_cliente)
);

-- si crea la tabella Prodotti
CREATE TABLE Prodotti(
id_prodotto int auto_increment not null,
descrizione varchar(100) not null,
in_produzione varchar(5) not null,
in_commercio varchar(5) not null,
data_attivazione date not null,
data_disattivazione date not null,
PRIMARY KEY(id_prodotto)
);

-- 1) Estrarre il nome e il cognome dei clienti nati nel 1982
SELECT nome, cognome
FROM db_ese.Clienti
WHERE Clienti.data_nascita BETWEEN '1982-01-01' AND '1982-12-31';

-- 2) Estrarre una colonna di nome “Denominazione” contenente il nome, seguito da un carattere “-“, seguito dal cognome, per i soli clienti residenti nella regione Lombardia
SELECT concat(nome, '-', cognome) AS Denominazione
FROM db_ese.Clienti
WHERE Clienti.regione_residenza = 'Lombardia';

-- 3) Qual è il numero di fatture con iva al 20%?
SELECT count(id_fattura) AS tot_fatture_20
FROM db_ese.Fatture
WHERE Fatture.iva = '20%';

-- 4) Riportare il numero di fatture e la somma dei relativi importi divisi per anno di fatturazione.
SELECT data_fattura, count(id_fattura) AS tot_fatture, sum(importo) AS tot_importo
FROM db_ese.Fatture
GROUP BY Fatture.data_fattura;

-- 5) Estrarre i prodotti attivati nel 2017 e che sono in produzione oppure in commercio
SELECT *
FROM db_ese.Prodotti
WHERE (Prodotti.data_attivazione BETWEEN '2017-01-01' AND '2017-12-31') AND (Prodotti.in_produzione = '1' OR Prodotti.in_commercio = '1');

-- 6) Considerando soltanto le fatture con iva al 20 per cento, qual è il numero di fatture per ogni anno?
SELECT data_fattura, count(*) AS tot_fatture
FROM db_ese.Fatture
WHERE Fatture.iva = '20%'
GROUP BY Fatture.data_fattura;

-- 7) In quali anni sono state registrate più di 2 fatture con tipologia ‘A’?
SELECT Fatture.data_fattura, count(*) AS tot_fatture
FROM db_ese.Fatture
WHERE Fatture.tipologia = 'A'
GROUP BY Fatture.data_fattura
HAVING tot_fatture > 2;

-- 8) Riportare l’elenco delle fatture (numero, importo, iva e data) con in aggiunta il nome del fornitore
SELECT id_fattura, importo, iva, data_fattura, denominazione
FROM db_ese.Fatture
LEFT JOIN db_ese.Fornitori
ON Fatture.id_fornitore = Fornitori.id_fornitore;

-- 9) Estrarre il totale degli importi delle fatture divisi per residenza dei clienti
SELECT Clienti.regione_residenza, sum(Fatture.importo) AS tot_importo
FROM db_ese.Fatture
INNER JOIN db_ese.Clienti
ON Fatture.id_cliente = Clienti.id_cliente
GROUP BY Clienti.regione_residenza;

-- 10) Estrarre il numero dei clienti nati nel 1980 che hanno almeno una fattura superiore a 50 euro
SELECT count(DISTINCT c.id_cliente) AS tot_clienti
FROM db_ese.Clienti AS c
INNER JOIN db_ese.Fatture AS f
ON c.id_cliente = f.id_cliente
WHERE (c.data_nascita BETWEEN '1980-01-01' AND '1980-12-31') AND f.importo > 50;

