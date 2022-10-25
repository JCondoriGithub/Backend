-- Trovare matricola e cognome degli impiegati che guadagnano più di 50
SELECT Matricola, Cognome
FROM esercizi.impiegato
WHERE Stipendio>50;

-- Trovare cognome e stipendio degli impiegati che lavorano a Roma
SELECT Cognome, Stipendio
FROM esercizi.impiegato
WHERE Dipartimento='CE';

-- Trovare cognome degli impiegati e nome del dipartimento in cui lavorano
SELECT Cognome, Nome
FROM esercizi.impiegato, esercizi.dipartimento
WHERE Dipartimento=Codice;	-- mostra Cognome e Nome delle istanze, quando hanno gli attributi uguali

-- Trovare cognome degli impiegati che sono direttori di dipartimento
SELECT Cognome
FROM esercizi.impiegato, esercizi.dipartimento
WHERE Matricola=Direttore;

-- Trovare i nomi dei progetti e i cognomi dei responsabili
SELECT Nome, Cognome
FROM esercizi.progetto, esercizi.impiegato
WHERE Responsabile=Matricola;

-- Trovare i nomi dei progetti con bilancio maggiore di 40 e i cognomi degli impiegati che lavorano su di essi
SELECT Nome, Cognome
FROM esercizi.progetto, esercizi.impiegato, esercizi.partecipazione
WHERE Bilancio>40 AND Sigla=Progetto AND Impiegato=Matricola;


