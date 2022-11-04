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

	@OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)	// si inserisce il campo-nome della class-entity in cui vi è l'oggetto corrente. L'attributo fetch di default è LAZY
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

	@Override
	public String toString() {
		return "Autore [id=" + id + ", nome=" + nome + ", paese=" + paese + "]";
	}
	
	
}
