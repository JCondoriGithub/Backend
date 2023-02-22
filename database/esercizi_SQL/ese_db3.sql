-- si crea il database db3
CREATE DATABASE db3;

-- si crea la tabella Fornitori
CREATE TABLE db3.Fornitori(
id_fornitore int auto_increment,
nome varchar(30) not null,
indirizzo varchar(50) not null,
città varchar(15) not null,
cap int not null,
PRIMARY KEY(id_fornitore)
);

-- si crea la tabella Prodotti
CREATE TABLE db3.Prodotti(
id_prodotto int auto_increment not null,
quantità int not null,
prezzo decimal not null,
nome varchar(30) not null,
marca varchar(39) not null,
id_fornitore int not null,
PRIMARY KEY(id_prodotto),
FOREIGN KEY(id_fornitore) REFERENCES db3.Fornitori(id_fornitore)
);

-- si crea la tabella Clienti
CREATE TABLE db3.Clienti(
codice_fiscale int not null,
nome varchar(30) not null,
cognome varchar(30) not null,
tipo_carta varchar(20) not null,
numero_carta int not null,
scadenza_carta date not null,
PRIMARY KEY(codice_fiscale)
);

-- si  crea la tabella Acquisti
CREATE TABLE db3.Acquisti(
id_acquisto int auto_increment not null,
id_prodotto int not null,
codfisc_cliente int not null,
PRIMARY KEY(id_acquisto),
FOREIGN KEY(id_prodotto) REFERENCES db3.Prodotti(id_prodotto),
FOREIGN KEY(codfisc_cliente) REFERENCES db3.Clienti(codice_fiscale)
);

-- nome e città dei fornitori della Maionese Kraft
SELECT Fornitori.nome, città
FROM db3.Prodotti, db3.Fornitori
WHERE Prodotti.id_fornitore = Fornitori.id_fornitore AND Prodotti.marca = 'Kraft';

-- nome e cognome dei clienti che hanno acquistato prodotti più costosi di 10 Euro
SELECT Clienti.nome, Clienti.cognome
FROM db3.Clienti, db3.Acquisti, db3.Prodotti
WHERE Clienti.codice_fiscale = Acquisti.codfisc_cliente AND Acquisti.id_prodotto = Prodotti.id_prodotto AND prezzo > 10;

-- nome dei fornitori dei prodotti acquistati da Mario Rossi
SELECT Fornitori.nome
FROM db3.Fornitori, db3.Prodotti, db3.Acquisti, db3.Clienti
WHERE Fornitori.id_fornitore = Prodotti.id_fornitore AND Acquisti.id_prodotto = Prodotti.id_prodotto AND Clienti.codice_fiscale = Acquisti.codfisc_cliente AND Clienti.nome = 'Mario' AND Clienti.cognome = 'Rossi';

-- prezzo dei prodotti forniti da Giacinto acquistati con carta Visa
SELECT Prodotti.prezzo
FROM db3.Fornitori, db3.Prodotti, db3.Acquisti, db3.Clienti
WHERE Fornitori.nome = 'Giacinto' AND Prodotti.id_fornitore = Fornitori.id_fornitore AND Prodotti.id_prodotto = Acquisti.id_prodotto AND Acquisti.codfisc_cliente = Clienti.codice_fiscale AND Clienti.tipo_carta = 'Visa';

-- tipo e numero di carta di credito dei clienti che hanno acquistato i Kinder Pinguì
SELECT tipo_carta, numero_carta
FROM db3.Clienti, db3.Acquisti, db3.Prodotti
WHERE Clienti.codice_fiscale = Acquisti.codfisc_cliente AND Acquisti.id_prodotto = Prodotti.id_prodotto AND Prodotti.nome = 'Kinder Pinguì';

-- nome e cognome dei clienti che hanno acquistato prodotti provenienti da Sassari
SELECT DISTINCT Clienti.nome, Clienti.cognome
FROM db3.Fornitori, db3.Acquisti, db3.Prodotti, db3.Clienti
WHERE Fornitori.città = 'Sassari' AND Fornitori.id_fornitore = Prodotti.id_fornitore AND Prodotti.id_prodotto = Acquisti.id_prodotto AND Clienti.codice_fiscale = Acquisti.codfisc_cliente;

-- nome, indirizzo e città dei fornitori dei prodotti che costano 1 Euro
SELECT Fornitori.nome, indirizzo, città
FROM db3.Fornitori, db3.Prodotti
WHERE Fornitori.id_fornitore = Prodotti.id_fornitore AND Prodotti.prezzo = 1;


