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
/*		em.persist(new User(null, "user1", "password"));*/
		em.persist(new User(null, "user2", "password"));	// non è un'entità, quindi non può persistere
		em.persist(new Employee("userX", "passwordX", "IDxxxx"));
		em.persist(new Employee("userY", "passwordY", "IDyyyy"));
		em.persist(new Customer("costumer1", "passwordC1", "costumer1.it"));
		em.persist(new Customer("costumer2", "passwordC2", "costumer2.it"));
		em.getTransaction().commit();
		em.close();
	}

}
