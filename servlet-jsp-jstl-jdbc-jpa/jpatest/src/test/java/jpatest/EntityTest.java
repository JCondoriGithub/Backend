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
//		em.persist(new User(null, "user2", "password"));	// è una classe-entity-astratta, quindi non può persistere
		em.persist(new Employee("userX", "passwordX", "IDxxxx"));
		em.persist(new Employee("userY", "passwordY", "IDyyyy"));
		Customer c;
		em.persist(c = new Customer("costumer1", "passwordC1", "costumer1.it"));
		em.persist(new Customer("costumer2", "passwordC2", "costumer2.it"));
		em.persist(new Order(1L, "ordine1", c));
		em.getTransaction().commit();
		em.close();							// anche se atratto, "User" è un'entità ma non ha una tabella propria e può essere usato in un eventuale query che darà come risultato misto i records di varie entità, anche se sono in tabelle diverse
	}

}
