package jpa.hibernate.tests;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jpa.hibernate.modello.Autore;
import jpa.hibernate.modello.Libro;

public class TestAutori {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistenza");
	private static EntityManager manager;

	public static void main(String[] args) {

		manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		
		Libro libro = new Libro();
		libro.setId(1);
		libro.setTitolo("JPA e Hibernate");
		manager.persist(libro);
		
		Libro libro2 = new Libro();
		libro2.setId(2);
		libro2.setTitolo("Cucina");
		manager.persist(libro2);
		
		Autore autor = new Autore(1, "Riccado Benzone", "Italia");
		autor.addLibro(libro);	// invece di assegnare un'autore al libro si assegna un libro all'autore, quindi nell'entity di partenza non è "registrata l'assegnazione" -> andare nella classe "Autore"
		autor.addLibro(libro2);
		
		Autore autor2 = new Autore(2, "Milo Cotogno", "Italia");
		autor2.addLibro(libro);
		autor2.addLibro(libro2);
		
		System.out.println("libri scritti (pre-persist): " + autor.getLibri().size());
		System.out.println("2 - libri scritti (pre-persist): " + autor2.getLibri().size());

		manager.persist(autor);
		manager.persist(autor2);
		
		manager.getTransaction().commit();
		
		manager.close();
		
		stampaDati();
	}

	static void stampaDati() {
		
		manager = emf.createEntityManager();

		Autore autore = manager.find(Autore.class, 1);
		Autore autore2 = manager.find(Autore.class, 2);

		System.out.println("libri scritti (post-persist): " + autore.getLibri().size());	// si prelevano i libri dal database
		System.out.println("2 - libri scritti (post-persist): " + autore2.getLibri().size());
		
		System.out.println(autore);
		System.out.println(autore2);

		List<Libro> libri = autore.getLibri();
		for(Libro libro: libri)
			System.out.println(libro);
		
		libri = autore2.getLibri();
		for(Libro libro: libri)
			System.out.println(libro);
		
		manager.close();
	}
}
