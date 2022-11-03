package jpa.hibernate.modello;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_Impiegato")
public class Impiegato {

	@Id
	@Column(name = "code_impiegato")
	private int codice;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "data_nascita")
	private String dataNascita;
	
	@OneToOne(cascade = {CascadeType.ALL})	// l'attributo cascade indica che a tutte le operazioni effettuate su questa entity, anche le entity associate ad essa verrano influenzate(remove, persist, ecc...)
	@JoinColumn(name = "code_indirizzo")	// si indica che un questa colonna si inserisce la PK dell'oggetto "Indirizzo"
	private Indirizzo indirizzo;
	
	public Impiegato() {
		
	}

	public Impiegato(int codice, String nome, String cognome, String dataNascita) {
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return "Impiegato [codice=" + codice + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
				+ dataNascita + ", indirizzo=" + indirizzo + "]";
	}
	
}
