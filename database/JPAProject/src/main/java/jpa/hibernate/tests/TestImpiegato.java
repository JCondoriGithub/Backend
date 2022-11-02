package jpa.hibernate.tests;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jpa.hibernate.modello.*;

public class TestImpiegato {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistenza");
	private static EntityManager manager;
	
	public static void main(String[] args) {

		manager = emf.createEntityManager();
		
		Impiegato imp = new Impiegato(1, "Maurizio", "Vizio", "21-07-1974");
		
		manager.getTransaction().begin();
		manager.persist(imp);
		manager.getTransaction().commit();
		stampaImpiegati();

		manager.close();
		
		
		manager = emf.createEntityManager();

		manager.getTransaction().begin();
		imp = manager.merge(imp);	// si converte l'oggetto-entity a managed, quindi il record associato in database potrà essere modificato
		imp.setNome("Mario");		// visto che l'entity manager è stato precedentemente chiuso, l'oggetto-entity non è più managed e quindi il record associato in database non verrà modificato
		//manager.merge(imp);		
		manager.remove(imp);
		manager.getTransaction().commit();		
		stampaImpiegati();
		
		manager.close();
	}

	private static void stampaImpiegati() {
		
		List<Impiegato> impiegati = (List<Impiegato>) manager.createQuery("FROM Impiegato").getResultList();	// contiene un'istruzione in mysqldialect
		System.out.println("totale impiegati: " + impiegati.size());
		for(Impiegato i: impiegati) {
			System.out.println(i.toString());
		}
	}
}
