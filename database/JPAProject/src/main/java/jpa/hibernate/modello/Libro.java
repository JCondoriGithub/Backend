package jpa.hibernate.modello;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "tbl_Libro")
public class Libro {

	@Id
	@Column(name = "id_libro")
	private Integer id;

	@Column(name = "titolo")
	private String titolo;
	
	@ManyToOne(fetch = FetchType.LAZY)	// si deve impostare l'attributo fetch come LAZY
	@JoinColumn(name = "code_autore")	// si indica che un questa colonna si inserisce la PK dell'oggetto "Autore"
	private Autore autore;
	
	public Libro() {

	}

	public Libro(Integer id, String titolo, Autore autore) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.autore = autore;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getTitolo() {
		return titolo;
	}



	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}



	public Autore getAutore() {
		return autore;
	}



	public void setAutore(Autore autore) {
		this.autore = autore;
	}



	@Override
	public String toString() {
		return "Libro [id=" + id + ", titolo=" + titolo + ", autore=" + autore + "]";
	}
	
}
