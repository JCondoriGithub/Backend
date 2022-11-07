package jpa.hibernate.modello;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany(mappedBy = "libri")		// si definisce anche il mappedBy
	private List<Autore> autori = new ArrayList<>();
	
	public Libro() {

	}

	public Libro(Integer id, String titolo, List<Autore> autori) {
		this.id = id;
		this.titolo = titolo;
		this.autori = autori;
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
	
	public List<Autore> getAutori() {
		return autori;
	}

	public void setAutori(List<Autore> autori) {
		this.autori = autori;
	}
	
	public void setAutore(Autore autore) {
		
		if(!autori.contains(autore)) {
			autori.add(autore);
		}
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titolo=" + titolo + ", autori=" + autori + "]";
	}
	
}
