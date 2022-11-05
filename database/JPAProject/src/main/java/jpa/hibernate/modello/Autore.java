package jpa.hibernate.modello;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_Autore")
public class Autore {

	@Id
	@Column(name = "id_autore")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "paese")
	private String paese;

	@OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
	private List<Libro> libri = new ArrayList<>();
	
	public Autore() {
		
	}
	
	public Autore(Integer id, String nome, String paese) {
		this.id = id;
		this.nome = nome;
		this.paese = paese;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}
	
	public List<Libro> getLibri() {
		return libri;
	}

	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
	
	public void addLibro(Libro libro) {
		
		if(!libri.contains(libro)) {
			libri.add(libro);
			libro.setAutore(this);	// si DEVE indicare esplicitamente la relazione dal punto di vista del libro/entity di partenza
		}
	}
	
	public void removeLibro(Libro libro) {
		
		if(libri.contains(libro)) {
			libri.remove(libro);
			libro.setAutore(null);	// si indica anche che il libro non ha più un'autore 
		}
	}

	@Override
	public String toString() {
		return "Autore [id=" + id + ", nome=" + nome + ", paese=" + paese + "]";
	}
	
	
}
