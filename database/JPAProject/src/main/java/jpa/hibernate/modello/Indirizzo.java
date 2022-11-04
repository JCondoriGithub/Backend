package jpa.hibernate.modello;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_indirizzo")
public class Indirizzo {

	@Id
	@Column(name = "id_indirizzo")
	private int id;
	
	@Column(name = "via")
	private String via;
	
	@Column(name = "comune")
	private String comune;
	
	@Column(name = "regione")
	private String regione;
	
	@Column(name = "paese")
	private String paese;
	
	@OneToOne(mappedBy = "indirizzo", fetch = FetchType.LAZY)	// si annota una relazione già esistente in cui l'entity d'arrivo è "Indirizzo" e viene rappresentato dal campo "indirizzo" dell'entity "Impiegato"
	private Impiegato impiegato;								// FetchType.LAZY indica che quest'oggetto di relazione verrà chiamato solo all'invocazione del suo metodo specifico di get

	public Indirizzo() {
		
	}

	public Indirizzo(int id, String via, String comune, String regione, String paese) {
		this.id = id;
		this.via = via;
		this.comune = comune;
		this.regione = regione;
		this.paese = paese;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public Impiegato getImpiegato() {
		return impiegato;
	}

	public void setImpiegato(Impiegato impiegato) {
		this.impiegato = impiegato;
	}	
	
	@Override
	public String toString() {
		return "Indirizzo [id=" + id + ", via=" + via + ", comune=" + comune + ", regione=" + regione + ", paese="
				+ paese + ", impiegato=" + impiegato.getCodice() + "]";
	}
	
}
