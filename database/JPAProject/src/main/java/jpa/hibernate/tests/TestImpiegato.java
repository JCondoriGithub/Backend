package jpa.hibernate.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestImpiegato {

//	@PersistenceContext(unitName = "Persistenza")	--> in progetti web, per implementare l'entity manager
	private static EntityManager manager;	// interfaccia java che contiene i metodi di CRUD e di transazione insieme ad altri metodi
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {

		// si crea il gestore di persistenza (Entity Manager)
		emf = Persistence.createEntityManagerFactory("Persistenza");	// per creare un'oggetto di tipo EntityManagerFactory
		manager = emf.createEntityManager();	// per creare un'oggetto di tipo EntityManager
		System.out.println("salve!");
	}

}
