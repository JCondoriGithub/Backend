package jpatest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class EntityTest {

	@Test
	void testUser() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
//		em.persist(new User(null, "user2", "password"));	 è una classe-entity-astratta, quindi non può persistere. Nel db non ci possono essere records che non appartengono ad una delle sottoclassi
		em.persist(new Employee("userX", "passwordX", "IDxxxx"));
		em.persist(new Employee("userY", "passwordY", "IDyyyy"));
		Customer c;
		em.persist(c = new Customer("costumer1", "passwordC1", "costumer1.it"));
		em.persist(new Customer("costumer2", "passwordC2", "costumer2.it"));
		em.persist(new Order(1L, "ordine1", c));
		em.getTransaction().commit();	// si genera: una tabella della superclasse che contiene solo i campi comuni, le tabelle delle sottoclassi ed hanno l'id che sono delle fk del record associato nella superclasse e i loro campi propri/aggiuntivi
		em.close();							// un eventuale query a "User" darà come risultato misto i records di varie entità, anche se sono in tabelle diverse
	}

}
