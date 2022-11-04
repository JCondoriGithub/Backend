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
		
		Autore autore1 = new Autore(1, "Luca Carminati", "Italia");
		Autore autore2 = new Autore(2, "David lugansk", "Usa");
		Autore autore3 = new Autore(3, "Abdel Mahmed", "Egitto");
		
		manager.persist(autore1);
		manager.persist(autore2);
		manager.persist(autore3);
		
		manager.persist(new Libro(1, "Il fucile m-16", autore2));
		manager.persist(new Libro(2, "Come fare il parkourista", autore1));
		manager.persist(new Libro(3, "Come fare la pizza", autore1));
		manager.persist(new Libro(4, "Cosa vedere in Egitto", autore3));
		manager.persist(new Libro(5, "Come fare la lasagna", autore1));
		
		manager.getTransaction().commit();
		
		manager.close();
		
		stampaDati();
	}

	static void stampaDati() {
		
		manager = emf.createEntityManager();

		Autore autore = manager.find(Autore.class, 1);
		System.out.println(autore);
		
		List<Libro> libri = autore.getLibri();
		for(Libro libro: libri)
			System.out.println(libro);
		
		manager.close();
	}
}
