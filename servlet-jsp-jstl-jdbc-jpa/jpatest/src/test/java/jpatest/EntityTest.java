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
		em.persist(new User(null, "user2", "password"));	// la superclasse può essere persistita
		em.persist(new Employee("userX", "passwordX", "IDxxxx"));
		em.persist(new Employee("userY", "passwordY", "IDyyyy"));
		Customer c;
		em.persist(c = new Customer("costumer1", "passwordC1", "costumer1.it"));
		em.persist(new Customer("costumer2", "passwordC2", "costumer2.it"));
		em.persist(new Order(1L, "ordine1", c));	// nel database, la fk della tabella Orders si riferirà alla tabella User
		em.getTransaction().commit();		// viene generata un'unica tabella con tutti gli attributi di (User, Employee, Customer) e un'attributo ("DTYPE", varchar) che ha il nome dell'entità a cui appartiene il record
		em.close();							// un eventuale query darà come risultato misto i records di varie entità
	}

}
