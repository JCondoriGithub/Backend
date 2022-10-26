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

-- Trovare cognome dei direttori di dipartimento e dei responsabili di progetto
SELECT impiegato.Cognome
FROM esercizi.impiegato, esercizi.dipartimento
WHERE impiegato.Matricola=dipartimento.Direttore
UNION
SELECT impiegato.Cognome
FROM esercizi.impiegato, esercizi.progetto
WHERE impiegato.Matricola=progetto.Responsabile;

-- Trovare nomi dei dipartimenti in cui lavorano impiegati che guadagnano piu di 60
SELECT DISTINCT dipartimento.Nome
FROM esercizi.impiegato, esercizi.dipartimento
WHERE impiegato.Stipendio>60 AND impiegato.Dipartimento=dipartimento.Codice;

-- Trovare nomi dei dipartimenti in cui tutti gli impiegati guadagnano piu di 60
SELECT dipartimento.Nome
FROM esercizi.dipartimento
WHERE dipartimento.Nome NOT IN
(SELECT DISTINCT dipartimento.Nome
FROM esercizi.impiegato, esercizi.dipartimento
WHERE impiegato.Stipendio<=60 AND impiegato.Dipartimento=dipartimento.Codice);

-- Trovare cognome degli impiegati di stipendio massimo
SELECT impiegato.Cognome
FROM esercizi.impiegato
WHERE impiegato.Stipendio=
(SELECT max(impiegato.stipendio)
 FROM esercizi.impiegato);
 
-- Trovare matricola e cognome degli impiegati che non lavorano a nessun progetto
SELECT impiegato.Matricola, impiegato.Cognome
FROM esercizi.impiegato
WHERE impiegato.Matricola NOT IN
(SELECT partecipazione.Impiegato
FROM esercizi.partecipazione);

-- Trovare matricola e cognome degli impiegati che lavorano a piu di un progetto ***
SELECT impiegato.Matricola, impiegato.Cognome
FROM esercizi.impiegato
WHERE 1<(SELECT count(*)
FROM esercizi.partecipazione
WHERE partecipazione.Impiegato = impiegato.Matricola);

-- Trovare matricola e cognome degli impiegati che lavorano a un solo progetto
SELECT impiegato.Matricola, impiegato.Cognome
FROM esercizi.impiegato
WHERE 1=(SELECT count(*)
FROM esercizi.partecipazione
WHERE impiegato.Matricola=partecipazione.Impiegato);

-- Trovare per ciascun dipartimento lo stipendio medio degli impiegati che vi lavorano
SELECT dipartimento.Nome, AVG(impiegato.Stipendio)
FROM esercizi.impiegato, esercizi.dipartimento
WHERE impiegato.Dipartimento=dipartimento.Codice
GROUP BY impiegato.dipartimento;