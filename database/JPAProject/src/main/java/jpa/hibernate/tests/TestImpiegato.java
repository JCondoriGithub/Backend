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
		
		Indirizzo ind = new Indirizzo(1, "de angeli", "Gessate", "Lombardia", "Italia");	// si deve prima creare l'oggetto dell'entità d'arrivo, perche jpa non lo crea in automatico
		Impiegato imp = new Impiegato(1, "Maurizio", "Vizio", "21-07-1974");
		imp.setIndirizzo(ind);
		
		manager.getTransaction().begin();
		manager.persist(ind);
		manager.persist(imp);
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
