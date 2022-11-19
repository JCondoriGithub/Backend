package webhello.model;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateProductManager extends ProductManager {
	
	SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("webhello");
	
	private EntityManager openEM() throws SQLException {
		return sf.createEntityManager();
	}
	
	public HibernateProductManager() {
		
		EntityManager em = sf.createEntityManager();
		em.getTransaction().begin();
		if(em.createQuery("select c from Category c").getResultList().isEmpty()) {
			
			System.out.println("creando categorie di default");
			em.persist(new Category(null, "casa"));
			em.persist(new Category(null, "hobby"));
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Product> getByCategory(Category cat) {
		
		EntityManager em = null;
		try {
			em = openEM();
			return (List<Product>) em.createNamedQuery("Product.getByCategory").setParameter("c", cat).getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	public List<Product> getByCategoryName(String name) {
		
		EntityManager em = null;
		try {
			em = openEM();
			return (List<Product>) em.createNamedQuery("Product.getByCategoryName").setParameter("n", name).getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}

	public void remove(int code) {

		EntityManager em = null;
		try {
			em = openEM();
			em.getTransaction().begin();
			em.remove(em.find(Product.class, code));
			em.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if(em != null)
				em.close();	// è consigliabile chiudere sempre l'EM dopo l'utilizzo
		}
	}

	@Override
	public Product add(String name, int price) throws DuplicatedProductException {
		
		EntityManager em = null;
		try {
			em = openEM();
			em.getTransaction().begin();
			Product p = new Product(null, name, price);
			em.persist(p);
			em.getTransaction().commit();
			return p;
		} catch (EntityExistsException ex) {
			throw new DuplicatedProductException(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	@Override
	public Product add(String name, int price, Category cat) throws DuplicatedProductException {
		
		EntityManager em = null;
		try {
			em = openEM();
			em.getTransaction().begin();
			Product p = new Product(null, name, price);
			cat = cat == null ? cat : em.find(Category.class, cat.getCode());
			p.setCategory(cat);
			em.persist(p);
			em.getTransaction().commit();
			return p;
		} catch (EntityExistsException ex) {
			throw new DuplicatedProductException(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null)
				em.close();
		}
	}

	private static HibernateProductManager instance = new HibernateProductManager();

	public static HibernateProductManager getInstance() {
		return instance;
	}

	public Product getProduct(int id) {

			EntityManager em = null;
		try {	
			em = openEM();
			return em.find(Product.class, id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}

	}

	@Override
	public List<Product> getProducts() {
		
			EntityManager em = null;
		try {
			em = openEM();
			return (List<Product>) em.createNamedQuery("Product.getAll").getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	@Override
	public Category getCategory(int code) {
		
		EntityManager em = null;
		try {
			em = openEM();
			return em.find(Category.class, code);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}

	public List<Product> find(String txt) {
		
		EntityManager em = null;
		try {
			em = openEM();
			return em.createNamedQuery("Product.find").setParameter("txt", txt).getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	@Override
	public List<Category> getCategories() {
		
		EntityManager em = null;
		try {
			em = openEM();
			return (List<Category>) em.createNamedQuery("Category.getAll").getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}
}
