package jpa.hibernate.tests;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jpa.hibernate.modello.*;

public class TestImpiegato {

//	@PersistenceContext(unitName = "Persistenza")	--> in progetti web, per implementare l'entity manager
	private static EntityManager manager;	// interfaccia java che contiene i metodi di CRUD e di transazione insieme ad altri metodi
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {

		// si crea il gestore di persistenza (Entity Manager)
		emf = Persistence.createEntityManagerFactory("Persistenza");	// per creare un'oggetto di tipo EntityManagerFactory
		manager = emf.createEntityManager();	// per creare un'oggetto di tipo EntityManager
		System.out.println("salve!");
		
		Impiegato imp = new Impiegato(1, "Marco", "Meri", "21-07-1990");
		Impiegato imp2 = new Impiegato(2, "Giovanni",  "Regi", "30-09-1991");
		
		// inserire gli oggetti-entity nel database
		manager.getTransaction().begin();
		manager.persist(imp);	// inserisce l'oggetto nel database e lo converte in "oggetto-managed"
		imp.setCognome("Moggi");	// infatti poi lo posso modificare anche dopo il suo inserimento nel database
		manager.persist(imp2);
		manager.getTransaction().commit();
		
		stampaImpiegati();
		
		manager.getTransaction().begin();
		Impiegato i = manager.find(Impiegato.class, 2);	// restituisce l'oggetto-entity e anche managed
		i.setNome("Mario");
		manager.getTransaction().commit();
		
		stampaImpiegati();
		
		manager.close();	// quando non serve più, si chiude l'entity manager e tutti gli oggetti-managed mettono di essere menaged
	}

	private static void stampaImpiegati() {
		
		List<Impiegato> impiegati = (List<Impiegato>) manager.createQuery("FROM Impiegato").getResultList();	// contiene un'istruzione in mysqldialect
		System.out.println("totale impiegati: " + impiegati.size());
		for(Impiegato i: impiegati) {
			System.out.println(i.toString());
		}
	}
}
